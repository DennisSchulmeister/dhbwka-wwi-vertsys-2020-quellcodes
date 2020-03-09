/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.hystrix.aufrufer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hauptklasse der Anwendung. Diese Anwendung ruft den Zufallszahlenservice
 * der Anwendung Beispiel_Spring_Hystrix_Circuit_Breaker_Service auf und
 * nutzt dabei einen Circuit Breaker. Dadurch funktioniert die Anwendung
 * auch dann, wenn der entfernte Service nicht erreichbar ist und verhindert,
 * dass dieser nach einem Ausfall zu schnell wieder aufgerufen wird.
 * 
 * Das Beispiel ist an das Beispiel in folgendem Buch angelehnt:
 * Ranga Rao Karanam, Mastering Spring 5, Second Edition, Packt Verlag, S. 471ff
 * 
 * URL dieser Anwendung:
 * 
 *     » http://localhost:8080/
 */
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class AufruferApplication {
    
    private static Logger logger = LoggerFactory.getLogger(AufruferApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AufruferApplication.class, args);
        
        logger.info("");
        logger.info("");
        logger.info(">>> Aufruf der Anwenudng unter http://localhost:8080/");
        logger.info("");
        logger.info("");
    }

}
