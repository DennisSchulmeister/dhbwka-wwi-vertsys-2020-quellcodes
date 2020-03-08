/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.config.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Hauptklasse der Anwendung. Diese Anwendung beinhaltet keinen weiteren
 * Quellcode. Sie zeigt aber, wie ein eigener Sprint Cloud Config Server
 * aufgesetzt werden kann, der den anderen Microservices ihre Konfiguration
 * zur Verfügung stellt. Die hierfür notwendigen Stellen sind in der pom.xml
 * und der application.properties gekennzeichnet. Hier in der Klasse wird
 * dann zusätzlich noch die Annotation @EnableConfigServer benötigt.
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

    private static Logger logger = LoggerFactory.getLogger(ConfigServerApplication.class);
    
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
        
        logger.info("");
        logger.info("");
        logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.info(">>> Zentraler Konfigurations-Server                                <<<");
        logger.info(">>> Die abhängigen Client-Anwendungen können nun gestartet werden. <<<");
        logger.info(">>> http://localhost:8888/                                         <<<");
        logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.info("");
        logger.info("");
    }

}
