/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.admin.server;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hauptklasse der Anwendung. Die Anwendung selbst enthält keinen weiteren
 * Quellcode. Viel mehr dient sie als zentraler Admin-Server, der den Zustand
 * beliebig vieler Client-Anwendungen überwachen kann. Hierzu wurde in der
 * pom.xml das Modul spring-boot-admin-starter-server und hier die Annotation
 *
 * @EnableAdminServer hinzugefügt.
 */
@SpringBootApplication
@EnableAdminServer
public class AdminServerApplication {

    private static Logger logger = LoggerFactory.getLogger(AdminServerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AdminServerApplication.class, args);

        logger.info("");
        logger.info("");
        logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.info(">>> Zentraler Admin-Server!                                                 <<<");
        logger.info(">>> Starten Sie die Client-Anwendungen und rufen Sie dann folgende URL auf: <<<");
        logger.info(">>> http://localhost:8080/                                                  <<<");
        logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.info("");
        logger.info("");
    }

}
