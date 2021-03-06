/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.loesung.gcplus.nameserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

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
public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);
    
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        ConfigurableEnvironment env = ctx.getEnvironment();
        String port = env.getProperty("server.port");
        
        logger.info("");
        logger.info("");
        logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.info(">>> Zentraler Namensdienst zum Auffinden der anderen Microservices!");
        logger.info(">>> Die restlichen Services können nun gestartet werden.");
        logger.info(">>> http://localhost:" + port + "/");
        logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.info("");
        logger.info("");
    }

}
