/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.zipkin.service1;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Proxy-Interface zum Aufruf von Service 2.
 */
@FeignClient(name = "beispiel-zipkin-service2", url = "http://localhost:8082")
public interface Service2Proxy {
    
    @GetMapping("/")
    String callService();
    
}
