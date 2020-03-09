/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.apigate.backend1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hauptklasse der Anwendung. Die Anwendung stellt nur einen ganz einfachen
 * Webservice dar, der über das API-Gateway verfügbar gemacht werden soll.
 * Unter welcher internen URL der Service erreichbar ist, wird dabei über den
 * Nameserver ermittelt.
 */
@SpringBootApplication
@EnableFeignClients
public class Backend1Application {

    public static void main(String[] args) {
        SpringApplication.run(Backend1Application.class, args);
    }

}
