/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hauptklasse des Media Servers. Hier nochmal klassisch mit Sockets
 * programmiert. Mit einer Middleware wäre nicht mehr so viel zu tun.
 * Es würde reichen, ein MediaPlayer-Objekt zu erzeugen und der Middleware
 * bekannt zu machen.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(4444);
        MediaPlayer mediaPlayer = new MediaPlayer();

        System.out.println("Server wartet auf Verbindungen ...");

        while (true) {
            Socket socket = serverSocket.accept();
            new ServerThread(socket, mediaPlayer).start();
        }
    }
}
