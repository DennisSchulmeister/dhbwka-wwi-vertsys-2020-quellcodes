/* 
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.aufgabe.gcplus.frontend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Hauptklasse der Anwendung.
 */
@SpringBootApplication
public class FrontendApplication {

    private static Logger logger = LoggerFactory.getLogger(FrontendApplication.class);
    
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(FrontendApplication.class, args);
        ConfigurableEnvironment env = ctx.getEnvironment();
        String port = env.getProperty("server.port");
        
        logger.info("");
        logger.info("");
        logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.info(">>> Frontend-Server der Web-Anwendung");
        logger.info(">>> http://localhost:" + port + "/");
        logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.info("");
        logger.info("");
    }

}
