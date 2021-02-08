/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.pubsub.mini_chat.swing;

import dhbwka.wwi.vertsys.pubsub.mini_chat.ChatMessage;

/**
 * Interface zur Entkopplung der beiden Klassen ChatConnection und MainWindow.
 * Dadurch, dass die Klasse MainWindow dieses Interface implementiert, kann
 * sie darauf reagieren, wenn eine Verbindung hergestellt oder getrennt sowie
 * eine Nachricht empfangen wird, ohne dass die Klasse ChatConnection sie direkt
 * kennen muss.
 * 
 * → Programmiere gegen Schnittstellen und nicht gegen Implementierungen. ←
 */
public interface ChatConnectionListener {

    /**
     * Verbindung zum MQTT-Broker hergestellt.
     *
     * @param address Adresse des MQTT-Brokers
     * @param clientId Client-ID
     * @param username Benutzername
     * @param chatname Chatname
     */
    void onConnect(String address, String clientId, String username, String chatname);

    /**
     * Keine Verbindung zum MQTT-Broker.
     */
    void onDisconnect();

    /**
     * Eine neue Chatnachricht wurde empfangen.
     *
     * @param chatMessage Empfangene Nachricht
     */
    void onMessageReceived(ChatMessage chatMessage);
    
}
