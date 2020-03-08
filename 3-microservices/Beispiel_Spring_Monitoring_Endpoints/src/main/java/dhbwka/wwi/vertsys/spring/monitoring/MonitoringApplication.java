/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.monitoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hauptklasse der Anwendung. Diese Anwendung besitzt keinen weiteren Quellcode.
 * Sie zeigt lediglich, wie in der pom.xml eine Abhängigkeit eingetragen werden
 * kann, um ein ausführliches App-Monitoring zu ermöglichen.
 * 
 * Zum Testen der Services kann folgende URL verwendet werden:
 * http://localhost:8080/explorer/index.html#uri=/actuator
 */
@SpringBootApplication
public class MonitoringApplication {

    private static Logger logger = LoggerFactory.getLogger(MonitoringApplication.class);
    
    public static void main(String[] args) {
        SpringApplication.run(MonitoringApplication.class, args);
        
        logger.info("");
        logger.info("");
        logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.info(">>> Monitoring-Serivces verfügbar unter http://localhost:8080/actuator          <<<");
        logger.info(">>> Test im Browser mit http://localhost:8080/explorer/index.html#uri=/actuator <<<");
        logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

}
