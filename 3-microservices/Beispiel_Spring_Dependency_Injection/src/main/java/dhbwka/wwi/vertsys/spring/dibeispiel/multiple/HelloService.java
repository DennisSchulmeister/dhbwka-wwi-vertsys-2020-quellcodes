/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.dibeispiel.multiple;

/**
 * Interface für einen einfachen Hallo-Service. Dieser zeigt, wie mit den
 * Annotationen @Primary und @Qualifier zwischen mehreren Implementierungen
 * ausgewählt werden kann.
 */
public interface HelloService {

    /**
     * Grüßt einen Benutzer.
     * 
     * @param name Der zu grüßenden Benutzer
     * @return String mit der Begrüßung
     */
    String sayHello(String name);
    
}
