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

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Webservice-Proxy zum Aufruf von Service 2. Man beachte, dass hier die URL des
 * Services nicht hart-codiert ist, sondern lediglich sein Name, unter dem er im
 * Namensdienst gesucht wird.
 * 
 * Technisch gesehen wird die Annoation @LoadBalanced nicht benötigt, um den
 * Service aufrufen zu können. Dadurch wird jedoch ein client-seitiges
 * Load-Balancing aktiviert, wenn sich mehrere Instanzen am Namensdienst
 * registrieren.
 */
@FeignClient(name = "eureka-beispiel-service2")
public interface Service2Proxy {

    @GetMapping("/getRandomNumber")
    @LoadBalanced
    int getRandomNumber();
}
