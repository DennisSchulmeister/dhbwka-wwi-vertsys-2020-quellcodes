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
 * Datentransferklasse mit einer Statusmeldung. Die Klasse beinhaltet gleich
 * passende Methoden, um ein Objekt in JSON umzuwandeln bzw. anhand der
 * JSON-Daten wiederherzustellen. Somit eignet sich diese Klasse für den Versand
 * via MQTT.
 */
public class StatusMessage {

    // Zeitstempel
    public long time = System.currentTimeMillis();
    
    // Fahrzeug ID
    public String vehicleId = "";
    
    // Statusart
    public StatusType type;
    
    // Meldungstext
    public String message = "";
    
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
    public static StatusMessage fromJson(byte[] json) {
        GsonBuilder builder = new GsonBuilder();
        builder.serializeSpecialFloatingPointValues();
        Gson gson = builder.create();
        return gson.fromJson(new String(json, StandardCharsets.UTF_8), StatusMessage.class);
    }
    //</editor-fold>
}
