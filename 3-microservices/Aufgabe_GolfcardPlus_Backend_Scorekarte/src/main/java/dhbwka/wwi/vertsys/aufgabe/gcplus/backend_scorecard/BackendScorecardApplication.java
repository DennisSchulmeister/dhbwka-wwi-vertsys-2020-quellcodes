/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.aufgabe.gcplus.backend_scorecard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.filter.ForwardedHeaderFilter;

/**
 * Hauptklasse der Anwendung.
 */
@SpringBootApplication
@EnableFeignClients
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class BackendScorecardApplication {

    private static Logger logger = LoggerFactory.getLogger(BackendScorecardApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(BackendScorecardApplication.class, args);
        ConfigurableEnvironment env = ctx.getEnvironment();
        String port = env.getProperty("server.port");

        logger.info("");
        logger.info("");
        logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.info(">>> Backend: Scorekarte");
        logger.info(">>> http://localhost:" + port + "/");
        logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.info("");
        logger.info("");
    }

    /**
     * X-Forwarded HTTP-Header auswerten, die vom API-Gateway gesetzt werden. Sonst verweisen die durch HATEOAS in die
     * Webservice-Antworten eingebauten URLs auf die interne Adresse des Backendservers und nicht auf das API-Gateway.
     *
     * Vgl. https://github.com/spring-projects/spring-hateoas/issues/862#issuecomment-474586873
     *
     * @return ForwardedHeaderFilter
     */
    @Bean
    ForwardedHeaderFilter forwardedHeaderFilter() {
        return new ForwardedHeaderFilter();
    }
    
}
