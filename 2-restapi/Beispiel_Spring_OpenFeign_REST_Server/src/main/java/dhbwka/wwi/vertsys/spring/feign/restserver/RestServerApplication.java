/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.feign.restserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hauptklasse der Anwendung. Diese Anwendung stellt einen ganz simplen
 * REST-Webservice zur Verfügung, der in der Anwendung "Spring OpenFeign
 * (REST-Client) aufgerufen wird.
 */
@SpringBootApplication
public class RestServerApplication {

    private static Logger logger = LoggerFactory.getLogger(RestServerApplication.class);
    
    public static void main(String[] args) {
        SpringApplication.run(RestServerApplication.class, args);
        
        logger.info("");
        logger.info("");
        logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.info(">>> Beispiel REST-Webservice für den Aufruf mit OpenFeign in Java! <<<");
        logger.info(">>> Verwaltet eine einfache Liste mit Songs.                       <<<");
        logger.info(">>>                                                                <<<");
        logger.info(">>> GET http://localhost:8080/songs                                <<<");
        logger.info(">>> POST http://localhost:8080/songs                               <<<");
        logger.info(">>>                                                                <<<");
        logger.info(">>> Mehr kann der Service nicht. :-)                               <<<");
        logger.info(">>> Bei POST muss ein einzelner Song als JSON gesendet werden.     <<<");
        logger.info(">>> GET liefert eine Liste aller gespeicherten Songs.              <<<");
        logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.info("");
        logger.info("");
    }

}
