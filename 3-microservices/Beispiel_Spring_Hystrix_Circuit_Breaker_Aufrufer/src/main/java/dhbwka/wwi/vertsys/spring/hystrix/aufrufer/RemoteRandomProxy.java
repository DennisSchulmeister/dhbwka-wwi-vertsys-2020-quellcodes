/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.hystrix.aufrufer;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Proxy-Klasse zum Aufruf des entfernten Services. Mit
 * Fallback-Implementierung, falls der Service nicht erreichbar ist.
 */
@FeignClient(
        name = "beispiel-hystrix-service",
        url = "http://localhost:8081",
        fallback = RemoteRandomFallback.class
)
public interface RemoteRandomProxy {

    @GetMapping("/random")
    List<Integer> getRandomNumbers();

}
