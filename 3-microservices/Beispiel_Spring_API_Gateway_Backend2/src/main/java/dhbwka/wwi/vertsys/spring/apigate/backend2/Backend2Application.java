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

/**
 * Hauptklasse der Anwendung. Die Anwendung stellt nur einen ganz einfachen
 * Webservice dar, der über das API-Gateway verfügbar gemacht werden soll.
 * Unter welcher internen URL der Service erreichbar ist, wird dabei über den
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

}
