/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.dibeispiel.advanced;

/**
 * MOTD-Service, der einfach eine zufällige Nachricht zurückgibt.
 */
public interface MessageOfTheDayService {
    
    /**
     * @return Eine zufällige Nachricht
     */
    String getMessage();
    
}
