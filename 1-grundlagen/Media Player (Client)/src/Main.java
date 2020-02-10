/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Hauptklasse für die Clientanwendung. Zeigt, wie eine einfache Clientanwendung
 * mit Streams und Sockets realisiert werden kann. Die eigentliche Kommunikation
 * mit dem Server ist dabei in der Klasse RemoteMediaPlayer gekapselt.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        // Verbindung mit Server aufbauen
        RemoteMediaPlayer mediaPlayer = new RemoteMediaPlayer();

        // Liste aller Songs ausgeben
        int index = 0;

        System.out.println("Aktuelle Playlist");
        System.out.println("=================");
        System.out.println();

        for (String file : mediaPlayer.getFiles()) {
            System.out.println("[" + index++ + "] " + file);
        }

        System.out.println();
        System.out.println("[S] Wiedergabe stoppen");
        System.out.println("[X] Client beenden");
        System.out.println();

        // Vom Anwender ausgewählten Song abspielen
        boolean quit = false;
        BufferedReader fromKeyboard = new BufferedReader(new InputStreamReader(System.in));

        while (!quit) {
            System.out.print("Ihre Wahl: ");
            String input = fromKeyboard.readLine();

            switch (input.toUpperCase()) {
                case "X":
                    mediaPlayer.stopPlayback();
                    quit = true;
                    break;
                case "S":
                    mediaPlayer.stopPlayback();
                    break;
                default:
                    try {
                        index = new Integer(input);
                        mediaPlayer.playFile(index);
                    } catch (NumberFormatException ex) {
                        // Fehleingabe
                    }
                    break;
            }
        }
    }
}
