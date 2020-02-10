/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Ersatz für die MediaPlayer-Klasse. Hat genau dieselben Methoden.
 * Schickt aber Befehle an einen Server, anstatt selbst die Musik
 * abzuspielen.
 */
public class RemoteMediaPlayer {
    private final Socket socket;
    private final BufferedReader fromServer;
    private final PrintWriter toServer;

    /**
     * Konstruktor. Verbindet sich mit dem Server.
     * @throws java.io.IOException
     */
    public RemoteMediaPlayer() throws IOException {
        this.socket = new Socket("localhost", 4444);

        this.fromServer = new BufferedReader(
            new InputStreamReader(
                socket.getInputStream()
            )
        );

        this.toServer = new PrintWriter(
            socket.getOutputStream()
        );
    }

    /**
     * Abruf einer Liste mit allen abspielbaren Dateien.
     * @return
     * @throws IOException 
     */
    public List<String> getFiles() throws IOException {
        toServer.println("getFiles");
        toServer.flush();

        List<String> files = new ArrayList<>();

        while (true) {
            String filename = fromServer.readLine();
            if (filename.isEmpty()) { break; }

            files.add(filename);
        }

        return files;
    }

    /**
     * Wiedergabe starten.
     * @param index
     * @throws IOException 
     */
    public void playFile(int index) throws IOException {
        toServer.println("playFile " + index);
        toServer.flush();
    }

    /**
     * Wiedergabe beenden.
     * @throws IOException 
     */
    public void stopPlayback() throws IOException {
        toServer.println("stopPlayback");
        toServer.flush();
    }
}
