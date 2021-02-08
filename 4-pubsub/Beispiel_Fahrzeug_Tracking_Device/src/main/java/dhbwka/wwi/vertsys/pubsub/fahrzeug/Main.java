/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.pubsub.fahrzeug;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * Hauptklasse unseres kleinen Progrämmchens.
 *
 * Mit etwas Google-Maps-Erfahrung lassen sich relativ einfach eigene
 * Wegstrecken definieren. Man muss nur Rechtsklick auf einen Punkt machen und
 * "Was ist hier?" anklicken, um die Koordinaten zu sehen. Allerdings speichert
 * Goolge Maps eine Nachkommastelle mehr, als das ITN-Format erlaubt. :-)
 */
public class Main {

    public static void main(String[] args) throws Exception {
        // Fahrzeug-ID abfragen
        String vehicleId = Utils.askInput("Beliebige Fahrzeug-ID", "postauto");

        // Zu fahrende Strecke abfragen
        File workdir = new File("./waypoints");
        String[] waypointFiles = workdir.list((File dir, String name) -> {
            return name.toLowerCase().endsWith(".itn");
        });

        System.out.println();
        System.out.println("Aktuelles Verzeichnis: " + workdir.getCanonicalPath());
        System.out.println();
        System.out.println("Verfügbare Wegstrecken");
        System.out.println();

        for (int i = 0; i < waypointFiles.length; i++) {
            System.out.println("  [" + i + "] " + waypointFiles[i]);
        }

        System.out.println();
        int index = Integer.parseInt(Utils.askInput("Zu fahrende Strecke", "0"));
        List<WGS84> waypoints = parseItnFile(new File(workdir, waypointFiles[index]));

        // Adresse des MQTT-Brokers abfragen
        String mqttAddress = Utils.askInput("MQTT-Broker", Utils.MQTT_BROKER_ADDRESS);

        // Verbindungsoptionen und Letzter-Wille-Nachricht, wenn die Verbindung verloren geht
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        //options.setUserName("USERNAME");
        //options.setPassword("PASSWORD");

        StatusMessage lastWillMessage = new StatusMessage();
        lastWillMessage.vehicleId = vehicleId;
        lastWillMessage.type = StatusType.CONNECTION_LOST;
        lastWillMessage.message = "Verbindung zum Fahrzeug wurde getrennt.";

        options.setWill(Utils.MQTT_TOPIC_NAME, lastWillMessage.toJson(), 0, false);

        // Verbindung zum MQTT-Broker nun herstellen            
        String clientId = vehicleId + "-" + System.currentTimeMillis();

        System.out.println("Client ID: " + clientId);
        System.out.println("Starte Simulation. Drücke ENTER zum Beenden.");
        System.out.println();

        MqttClient client = new MqttClient(mqttAddress, clientId);
        client.connect(options);

        // Nachricht schicken, dass das Fahrzeug nun überwacht werden kann
        if (client.isConnected()) {
            StatusMessage statusMessage = new StatusMessage();
            statusMessage.vehicleId = vehicleId;
            statusMessage.type = StatusType.VEHICLE_READY;
            statusMessage.message = "Verbindung zum Fahrzeug hergestellt.";
            byte[] json = statusMessage.toJson();

            System.out.println(Utils.MQTT_TOPIC_NAME + " → " + new String(json, StandardCharsets.UTF_8));
            client.publish(Utils.MQTT_TOPIC_NAME, json, 0, false);
        }

        // Thread starten, der jede Sekunde den aktuellen Sensorwert verschickt
        Vehicle vehicle = new Vehicle(vehicleId, waypoints);
        vehicle.startVehicle();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    if (client.isConnected()) {
                        // JSON mit aktuellen Sensordaten ermitteln
                        SensorMessage sensorData = vehicle.getSensorData();
                        byte[] sensorJson = sensorData.toJson();

                        // Topic, an das wir senden ermitteln
                        String topic = Utils.MQTT_TOPIC_NAME + "/" + vehicleId;

                        // Protokollzeile ausgeben
                        System.out.println(topic + " → " + new String(sensorJson, StandardCharsets.UTF_8));

                        // Und wegschicken
                        client.publish(topic, sensorJson, 0, false);
                    }
                } catch (MqttException ex) {
                    Utils.logException(ex);
                }
            }
        };

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(task, 0, 1000);

        // Warten, bis das Programm beendet werden soll
        Utils.fromKeyboard.readLine();

        vehicle.stopVehicle();
        timer.cancel();

        if (client.isConnected()) {
            byte[] lastWillJson = lastWillMessage.toJson();
            System.out.println(Utils.MQTT_TOPIC_NAME + " → " + new String(lastWillJson, StandardCharsets.UTF_8));
            client.publish(Utils.MQTT_TOPIC_NAME, lastWillJson, 0, false);

            client.disconnect();
        }

        System.exit(0);
    }

    /**
     * Öffnet die in "filename" übergebene ITN-Datei und extrahiert daraus die
     * Koordinaten für die Wegstrecke des Fahrzeugs. Das Dateiformat ist ganz
     * simpel:
     *
     * <pre>
     * 0845453|4902352|Point 1 |0|
     * 0848501|4900249|Point 2 |0|
     * 0849295|4899460|Point 3 |0|
     * 0849796|4897723|Point 4 |0|
     * </pre>
     *
     * Jede Zeile enthält einen Wegpunkt. Die Datenfelder einer Zeile werden
     * durch | getrennt. Das erste Feld ist die "Longitude", das zweite Feld die
     * "Latitude". Die Zahlen müssen durch 100_000.0 geteilt werden.
     *
     * @param file ITN-Datei
     * @return Liste mit Koordinaten
     * @throws java.io.IOException
     */
    public static List<WGS84> parseItnFile(File file) throws IOException {
        List<WGS84> waypoints = new ArrayList<>();

        BufferedReader fromFile = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line;

        while ((line = fromFile.readLine()) != null) {
            double latitude, longitude;
            String[] fields = line.split("\\|");

            if (fields.length < 2) {
                continue;
            }

            try {
                longitude = Integer.parseInt(fields[0]) / 100_000.0;
                latitude = Integer.parseInt(fields[1]) / 100_000.0;
            } catch (NumberFormatException ex) {
                Utils.logException(ex);
                continue;
            }

            WGS84 waypoint = new WGS84(latitude, longitude);
            waypoints.add(waypoint);
        }
        
        fromFile.close();
        return waypoints;
    }

}
