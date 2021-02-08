/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.pubsub.mini_chat.logger;

import dhbwka.wwi.vertsys.pubsub.mini_chat.ChatMessage;
import dhbwka.wwi.vertsys.pubsub.mini_chat.Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Kleines Konsolenprogramm, das alle Nachrichten mitschneidet und auf der
 * Konsole ausgibt.
 */
public class LoggerMain {

    public static void main(String[] args) {
        try {
            // Adresse des MQTT-Brokers abfragen
            BufferedReader fromKeyboard = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.print("MQTT-Broker [" + Utils.MQTT_BROKER_ADDRESS + "] ");
            String address = fromKeyboard.readLine();
            
            if (address.trim().isEmpty()) {
                address = Utils.MQTT_BROKER_ADDRESS;
            }

            // Verbindung zum MQTT-Broker herstellen            
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            //options.setUserName("USERNAME");
            //options.setPassword("PASSWORD");
            
            String clientId = "MiniChat-Logger-" + System.currentTimeMillis();

            System.out.println("Client ID: " + clientId);
            System.out.println("Starte Empfang. Drücke ENTER zum Beenden.");
            System.out.println();

            MqttClient client = new MqttClient(address, clientId);
            client.connect(options);

            // Callback-Registrieren, der die Nachrichten empfängt
            client.subscribe(Utils.MQTT_TOPIC_MINI_CHAT);

            client.setCallback(new MqttCallback() {
                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    // Empfangenes ChatMessage-Objekt wieder herstellen
                    byte[] payload = message.getPayload();
                    ChatMessage chatMessage = ChatMessage.fromJson(payload);

                    // Und ab auf den Schirm damit
                    String text = "";

                    if (chatMessage.name != null && !chatMessage.name.trim().isEmpty()) {
                        text = "[" + chatMessage.name.trim() + "] ";
                    }

                    if (chatMessage.text != null && !chatMessage.text.trim().isEmpty()) {
                        text += chatMessage.text;
                    }
                    
                    System.out.println(text);
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken imdt) {
                }

                @Override
                public void connectionLost(Throwable thrwbl) {
                }
            });

            // Warten, bis das Programm beendet werden soll
            fromKeyboard.readLine();

            // Verbinden trennen, alle Threads stoppen
            client.disconnect();
            System.exit(0);
        } catch (IOException | MqttException ex) {
            Utils.logException(ex);
        }
    }

}
