/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.hystrix.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hauptklasse der Anwendung. Diese Anwendung stellt einen ganz simplen
 * Webservice zur Verfügung, der eine Reihe von Zufallszahlen liefert. Dies
 * dient als Beispiel für einen Client, der den Service mit Hilfe eines Circuit
 * Breakers aufruft, um somit auch funktionsfähig zu sein, wenn der Service
 * nicht erreichbar ist.
 * 
 * Das Beispiel ist an das Beispiel in folgendem Buch angelehnt:
 * Ranga Rao Karanam, Mastering Spring 5, Second Edition, Packt Verlag, S. 471ff
 * 
 * URL des Webservices:
 * 
 *     » http://localhost:8081/random
 */
@SpringBootApplication
public class ServiceApplication {
    
    private static Logger logger = LoggerFactory.getLogger(ServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
        
        logger.info("");
        logger.info("");
        logger.info(">>> Aufruf des Services unter http://localhost:8081/random");
        logger.info("");
        logger.info("");
    }

}
