/* 
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.loesung.gcplus.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Hauptklasse der Anwendung. Wie man sieht, gibt es hier keinerlei eigenen
 * Quellcode. Die gesamte Konfiguration des Servers befindet sich in der Datei
 * application.yaml. 
 */
@SpringBootApplication
public class Application {
    
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        ConfigurableEnvironment env = ctx.getEnvironment();
        String port = env.getProperty("server.port");

        logger.info("");
        logger.info("");
        logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.info(">>> Zentraler Gateway-Server, über den alle Kommunikation läuft!");
        logger.info(">>> Die restlichen Services können nun gestartet werden.");
        logger.info(">>> http://localhost:" + port + "/");
        logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.info("");
        logger.info("");
    }

}
