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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.nio.charset.StandardCharsets;

/**
 * Datentransferklasse mit den aktuell "gemessenen" Sensordaten. Die Klasse
 * beinhaltet gleich passende Methoden, um ein Objekt in JSON umzuwandeln bzw.
 * anhand der JSON-Daten wiederherzustellen. Somit eignet sich diese Klasse für
 * den Versand via MQTT.
 */
public class SensorMessage {

    // Zeitstempel
    public long time = System.currentTimeMillis();
    
    // Nachrichtentyp
    public String type = "SENSOR_DATA";

    // Fahrzeug ID
    public String vehicleId = "";

    // Motor ist an
    public boolean running = false;

    // Koordinaten im WGS84-Format
    public double latitude = 0;
    public double longitude = 0;

    // Drehzahl des Motors
    public double rpm = 0;

    // Geschwindigkeit in km/h
    public double kmh = 0;

    // Eingelegter Gang (0 = Leerlauf, Max = 6)
    // Der Rückwaärtsgang wird der Einfachheit halber hier ignoriert :-)
    public int gear = 0;

    //<editor-fold defaultstate="collapsed" desc="Objekt klonen">
    /**
     * Kopie der Nachrichten erzeugen und zurückgeben. Wichtig, damit es nicht
     * zu unvollständig ausgelesenen Daten kommt. Denn die Klasse Vehicle hat
     * eine fest Instanz von SensorData im Bauch, die sie zur Berechnung der
     * Sensordaten in einem eigenen Thread nutzt. Wenn nun ein anderer Thread
     * die Werte ausliest könnten die Daten unvollständig sein. Daher nutzt
     * Vehicle eine Sperre auf das SensorMessage-Objekt und gibt immer eine
     * frische Kopie des Objekts an den Aufrufer zurück, wenn die Sensordaten
     * ausgelesen werden sollen.
     *
     * @return
     */
    public SensorMessage copy() {
        SensorMessage copy = new SensorMessage();
        
        copy.time = this.time;
        copy.vehicleId = this.vehicleId;
        copy.running = this.running;
        copy.latitude = this.latitude;
        copy.longitude = this.longitude;
        copy.rpm = this.rpm;
        copy.kmh = this.kmh;
        copy.gear = this.gear;
        
        return copy;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="JSON-Serialisierung">
    /**
     * Erzeugt ein Byte-Array mit einem JSON-String für diese Nachricht.
     *
     * @return JSON-String als UTF-8 kodiertes Byte-Array
     */
    public byte[] toJson() {
        GsonBuilder builder = new GsonBuilder();
        builder.serializeSpecialFloatingPointValues();
        Gson gson = builder.create();
        return gson.toJson(this).getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Wandelt einen empfangenen JSON-String wieder zurück ein Objekt. Der
     * String muss hierzu als UTF-8 kodiertes Bytearray vorliegen.
     *
     * @param json Empfangene JSON-Daten, UTF-8 kodiert
     * @return ChatMessage-Objekts
     */
    public static SensorMessage fromJson(byte[] json) {
        GsonBuilder builder = new GsonBuilder();
        builder.serializeSpecialFloatingPointValues();
        Gson gson = builder.create();
        return gson.fromJson(new String(json, StandardCharsets.UTF_8), SensorMessage.class);
    }
    //</editor-fold>
}
