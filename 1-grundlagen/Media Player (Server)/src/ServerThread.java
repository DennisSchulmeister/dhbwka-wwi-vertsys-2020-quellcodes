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
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Serverthread, der eine Verbindung mit dem Client bedient und nach
 * Aufforderung durch den Client die Methoden der Klasse MediaPlayer aufruft.
 * Hätten wir eine Middleware, bräuchten wir diese Klasse nicht, die Middleware
 * würde diese Funktion schon bereitstellen.
 */
public class ServerThread extends Thread {

    private final BufferedReader fromClient;
    private final PrintWriter toClient;
    private final MediaPlayer mediaPlayer;

    public ServerThread(Socket socket, MediaPlayer mediaPlayer) throws IOException {
        this.fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.toClient = new PrintWriter(socket.getOutputStream());
        this.mediaPlayer = mediaPlayer;
    }

    @Override
    public void run() {
        System.out.println("Verbindung mit Client hergestellt");

        while (true) {
            try {
                String input = fromClient.readLine();
                if (input == null) break;
                String[] splitted = input.split(" ");

                switch (splitted[0]) {
                    case "getFiles":
                        System.out.println(">> getFiles");

                        for (File file : this.mediaPlayer.getFiles()) {
                            toClient.println(file.getName());
                        }

                        toClient.println();
                        break;
                    case "playFile":
                        System.out.println(">> playFile");

                        int index = new Integer(splitted[1]);
                        this.mediaPlayer.playFile(index);
                        break;
                    case "stopPlayback":
                        System.out.println(">> stopPlayback");

                        this.mediaPlayer.stopPlayback();
                        break;
                }

                toClient.flush();
            } catch (IOException ex) {
                // Fehler
            }
        }
    }
}
