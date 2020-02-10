/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.rest.musicdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hauptklasse der Anwendung. Hier wird von Spring automatisch ein eingebetteter
 * Webserver gestartet, unter dem die Webservices erreichbar sind.
 */
@SpringBootApplication
public class MusicdbApplication {

    /**
     * Gutes altes public static void main.
     * @param args 
     */
    public static void main(String[] args) {
        SpringApplication.run(MusicdbApplication.class, args);
    }
}
