/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.admin.client2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hauptklasse der Anwendung. Die Anwendung selbst enthält keinen weiteren
 * Quellcode. Viel mehr dient sie als Beispiel für eine Anwendung, die mit einen
 * zentralen Admin-Server überwacht werden kann. Hierzu wurden lediglich in der
 * pom.xml spring-boot-admin-starter-client als weitere Abhängigkeit und in in
 * der applications.properties die Adresse des Admin-Servers eingetragen.
 */
@SpringBootApplication
public class Client2Application {

    private static Logger logger = LoggerFactory.getLogger(Client2Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Client2Application.class, args);

        logger.info("");
        logger.info("");
        logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.info(">>> Client-Anwendung 2!                                               <<<");
        logger.info(">>> Starten Sie erst den Admin-Server dann die Client-Anwendungen.    <<<");
        logger.info(">>> Anschließend öffnen Sie die Admin-Oberfläche unter folgender URL: <<<");
        logger.info(">>> http://localhost:8080/                                            <<<");
        logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.info("");
        logger.info("");
    }

}
