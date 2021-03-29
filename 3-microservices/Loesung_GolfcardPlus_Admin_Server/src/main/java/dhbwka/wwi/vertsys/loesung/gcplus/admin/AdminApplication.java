/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.loesung.gcplus.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Hauptklasse der Anwendung. Die Anwendung selbst enthält keinen weiteren Quellcode. Viel mehr dient sie als zentraler
 * Admin-Server, der den Zustand beliebig vieler Client-Anwendungen überwachen kann. Hierzu wurde in der pom.xml das
 * Modul spring-boot-admin-starter-server und hier die Annotation
 *
 * @EnableAdminServer hinzugefügt.
 */
@SpringBootApplication
@EnableAdminServer
public class AdminApplication {

    private static Logger logger = LoggerFactory.getLogger(AdminApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(AdminApplication.class, args);
        ConfigurableEnvironment env = ctx.getEnvironment();
        String port = env.getProperty("server.port");

        logger.info("");
        logger.info("");
        logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.info(">>> Zentraler Admin-Server!");
        logger.info(">>> Starten Sie die Client-Anwendungen und rufen Sie dann folgende URL auf:");
        logger.info(">>> http://localhost:" + port + "/");
        logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.info("");
        logger.info("");
    }

}
