/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.eureka.nameserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Hauptklasse der Anwendung. Die Anwendung besitzt sonst keinen weiteren Code,
 * da ihr einziger Zweck darin besteht, den Eureka Namensdienst zu starten, der
 * für das Service Discovery von Microservices genutzt werden kann.
 *
 * Man beachte die Einstellungen in der application.properties und die
 * Annotation
 *
 * @EnableEurekaServer vor dieser Klasse.
 */
@SpringBootApplication
@EnableEurekaServer
public class NameserverApplication {

    private static Logger logger = LoggerFactory.getLogger(NameserverApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(NameserverApplication.class, args);

        logger.info("");
        logger.info("");
        logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.info(">>> Zentraler Namensdienst zum Auffinden der anderen Microservices! <<<");
        logger.info(">>> Die Services und dann der Client können nun gestartet werden.   <<<");
        logger.info(">>> http://localhost:8761/                                          <<<");
        logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.info("");
        logger.info("");
    }

}
