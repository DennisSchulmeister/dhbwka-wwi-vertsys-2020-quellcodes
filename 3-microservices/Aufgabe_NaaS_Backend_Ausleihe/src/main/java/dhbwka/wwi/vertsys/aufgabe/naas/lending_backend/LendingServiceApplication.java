/* 
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.aufgabe.naas.lending_backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.filter.ForwardedHeaderFilter;

/**
 * Hauptklasse der Anwendung.
 */
@SpringBootApplication
@EnableFeignClients
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class LendingServiceApplication {

    private static Logger logger = LoggerFactory.getLogger(LendingServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LendingServiceApplication.class, args);

        logger.info("");
        logger.info("");
        logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.info(">>> Microservice: Ausleihe <<<");
        logger.info(">>> http://localhost:8082/ <<<");
        logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.info("");
        logger.info("");
    }

    /**
     * X-Forwarded HTTP-Header auswerten, die vom API-Gateway gesetzt werden.
     * Sonst verweisen die durch HATEOAS in die Webservice-Antworten eingebauten
     * URLs auf die interne Adresse des Backendservers und nicht auf das
     * API-Gateway.
     *
     * Vgl.
     * https://github.com/spring-projects/spring-hateoas/issues/862#issuecomment-474586873
     *
     * @return ForwardedHeaderFilter
     */
    @Bean
    ForwardedHeaderFilter forwardedHeaderFilter() {
        return new ForwardedHeaderFilter();
    }

}
