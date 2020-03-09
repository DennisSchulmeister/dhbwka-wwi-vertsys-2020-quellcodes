/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.apigate.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Fallback-Implementierungen für die Services von Backend 2.
 */
@RestController
@RequestMapping("/fallback/backend2")
public class Backend2Fallback {
    
    @GetMapping("/greetRandom")
    public String greetRandom() {
        return "Das API-Gateway sagt: Hallo, zufällige Person!";
    }
    
}
