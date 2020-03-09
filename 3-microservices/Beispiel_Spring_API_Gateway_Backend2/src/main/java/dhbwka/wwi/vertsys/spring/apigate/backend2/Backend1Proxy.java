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

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Proxy-Interface zur Kommunikation mit Backend 1 über das API-Gateway.
 */
@FeignClient(name="beispiel-apigate-gateway")
public interface Backend1Proxy {
    
    @GetMapping("/api/hi/{name}")
    String sayHi(@PathVariable("name") String name);
    
    @GetMapping("/api/bye/{name}")
    String sayBye(@PathVariable("name") String name);
    
}
