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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Webservice-Proxy zum Aufruf von Service 1. Man beachte, dass hier die URL des
 * Services nicht hart-codiert ist, sondern lediglich sein Name, unter dem er im
 * Namensdienst gesucht wird.
 * 
 * Technisch gesehen wird die Annoation @LoadBalanced nicht benötigt, um den
 * Service aufrufen zu können. Dadurch wird jedoch ein client-seitiges
 * Load-Balancing aktiviert, wenn sich mehrere Instanzen am Namensdienst
 * registrieren.
 */
@FeignClient(name = "eureka-beispiel-service1")
public interface Service1Proxy {

    @GetMapping("/sayHello")
    @LoadBalanced
    Message sayHello();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Message {

        private String text;
    }
}
