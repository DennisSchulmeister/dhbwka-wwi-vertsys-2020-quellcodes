/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.aufgabe.naas.lending_backend.service;

/**
 * Fachlicher Fehler beim Ausleihen eines Geräts.
 */
public class LendingException extends Exception {
    
    public LendingException() {
        super();
    }
    
    public LendingException(String message) {
        super(message);
    }
    
    public LendingException(Throwable cause) {
        super(cause);
    }
    
    public LendingException(String message, Throwable cause) {
        super(message, cause);
    }
}
