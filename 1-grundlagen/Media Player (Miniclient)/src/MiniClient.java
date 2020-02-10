/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Minimaler Client für den Mediaplayer-Server. Zeigt die Grundlagen einer
 * einfachen, textbasierten Socketverbindung als Client.
 */
public class MiniClient {

    public static void main(String[] args) throws Exception {
        // Verbindung mit Server herstellen und am Ende bzw. bei Fehlern
        // automatisch wieder schließen (durch den try-Befehl)
        try (Socket socket = new Socket("localhost", 4444)) {
            // Datenströme für die Kommunikation mit dem Server erzeugeb
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter toServer = new PrintWriter(socket.getOutputStream());
            
            // Verfügbare Songs vom Server abrufen und anzeigen
            toServer.println("getFiles");
            toServer.flush();
            
            while (true) {
                String line = fromServer.readLine();
                if (line == null || line.isEmpty()) {
                    break;
                }
                System.out.println(line);
            }

            // Ersten Song abspielen
            toServer.println("playFile 1");
            toServer.flush();
        }
    }
}
