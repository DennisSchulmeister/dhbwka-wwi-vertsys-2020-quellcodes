/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.restclient;

import dhbwka.wwi.vertsys.spring.restclient.MediaWikiQueryResult.Query.Page;
import dhbwka.wwi.vertsys.spring.restclient.MediaWikiQueryResult.Query.Page.Coordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Component;

/**
 * Hauptklasse der Anwendung.
 *
 * Zur Annotation @ConditionalOnProperty vgl.
 * https://stackoverflow.com/a/45036625. Sie dient dazu, dass die run()-Methode
 * nicht in den Unit Tests der Anwendung ausgeführt wird.
 */
@SpringBootApplication
@EnableFeignClients
@Component
@ConditionalOnProperty(prefix = "job.autorun", name = "enabled", havingValue = "true", matchIfMissing = true)
public class RestAufrufApplication implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(RestAufrufApplication.class);

    @Autowired
    MediaWikiProxy webservice;

    /**
     * Startmethode des Programms
     *
     * @param args Kommandozeilenparameter
     */
    public static void main(String[] args) {
        SpringApplication.run(RestAufrufApplication.class, args);
    }

    /**
     * Eigentliche Hauptmethode des Programms.Wird von Spring automatisch
     * aufgerufen, da die Klasse das CommandLineRunner-Interface implementiert.
     *
     * @param args Kommandozeilenparameter
     */
    @Override
    public void run(String... args) {
        logger.info("");
        logger.info("");
        logger.info(">>> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.info(">>>");

        MediaWikiQueryResult queryResult = this.webservice.query("Duale_Hochschule_Baden-Württemberg_Karlsruhe|Schloss_Karlsruhe", "description|coordinates");

        for (Page page : queryResult.getQuery().getPages()) {
            logger.info(">>> Seite: " + page.getTitle());

            for (Coordinate coordinate : page.getCoordinates()) {
                logger.info(">>> Koordinaten: " + coordinate.getLat() + ", " + coordinate.getLon());
            }

            logger.info(">>>");
        }

        logger.info(">>> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.info("");
        logger.info("");
    }

}
