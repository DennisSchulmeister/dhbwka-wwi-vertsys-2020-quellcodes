/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.apigate.backend2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.ForwardedHeaderFilter;

/**
 * Hauptklasse der Anwendung. Die Anwendung stellt nur einen ganz einfachen
 * Webservice dar, der über das API-Gateway verfügbar gemacht werden soll. Unter
 * welcher internen URL der Service erreichbar ist, wird dabei über den
 * Nameserver ermittelt.
 *
 * Als kleines Schmankerl ruft Backend 2 intern das Backend 1 auf, was ebenfalls
 * über das API-Gateway geschieht.
 */
@SpringBootApplication
@EnableFeignClients
public class Backend2Application {

    public static void main(String[] args) {
        SpringApplication.run(Backend2Application.class, args);
    }

    /**
     * X-Forwarded HTTP-Header auswerten, die vom API-Gateway gesetzt werden.
     * Sonst verweisen durch HATEOAS in die Webservice-Antworten eingebaute URLs
     * auf die interne Adresse des Backendservers und nicht auf das API-Gateway.
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
