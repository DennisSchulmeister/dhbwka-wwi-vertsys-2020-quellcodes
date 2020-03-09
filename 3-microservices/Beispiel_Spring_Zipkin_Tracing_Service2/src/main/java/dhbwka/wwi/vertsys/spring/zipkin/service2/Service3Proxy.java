/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.zipkin.service2;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Proxy-Interface zum Aufruf von Service 3.
 */
@FeignClient(name = "beispiel-zipkin-service3", url = "http://localhost:8083")
public interface Service3Proxy {

    @GetMapping("/")
    String callService();

}
