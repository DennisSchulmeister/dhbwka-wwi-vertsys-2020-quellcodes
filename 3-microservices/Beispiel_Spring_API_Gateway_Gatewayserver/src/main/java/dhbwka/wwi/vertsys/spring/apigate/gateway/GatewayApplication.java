/* 
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.apigate.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hauptklasse der Anwendung. Wie man sieht, gibt es hier keinerlei eigenen
 * Quellcode. Die gesamte Konfiguration des Servers befindet sich in der Datei
 * application.yaml. Jedoch besitzt die Klasse ein paar Controller, die als
 * Fallback für nicht erreichbare Backend-Services genutzt werden.
 */
@SpringBootApplication
public class GatewayApplication {

    private static Logger logger = LoggerFactory.getLogger(GatewayApplication.class);
    
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
        
        logger.info("");
        logger.info("");
        logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.info(">>> Zentraler Gateway-Server, über den alle Kommunikation läuft!    <<<");
        logger.info(">>> Die restlichen Services können nun gestartet werden.            <<<");
        logger.info(">>> http://localhost:8088/                                          <<<");
        logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.info("");
        logger.info("");
    }

}
