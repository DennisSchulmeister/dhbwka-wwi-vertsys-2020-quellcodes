/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hauptklasse der Anwendung. Man beachte die Einstellungen in der Datei
 * application.properties sowie die Annotation @EnableDiscoveryClient vor dieser
 * Klasse, damit sich der Service beim Namensdienst registriert.
 *
 * Zwar stellt diese Anwendung keine eigenen Services zur Verfügung, sondern
 * konsumiert nur die Services der anderen Anwendungen. Doch auch hierfür muss
 * man sich mit dem Namensdienst verbinden.
 * 
 * Die eigentlichen Aufrufe finden in der Klasse CallServices statt.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ClientApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

}
