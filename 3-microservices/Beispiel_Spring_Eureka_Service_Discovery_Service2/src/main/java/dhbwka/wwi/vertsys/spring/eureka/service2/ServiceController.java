/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.eureka.service2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Mini-Webservice, dessen URL die Client-App vor dem Aufruf erst aus dem
 * Namensdienst ermitteln muss.
 */
@RestController
public class ServiceController {
    
    @GetMapping("/getRandomNumber")
    public int getRandomNumber() {
        // Garantiert zufällig, da eben zufällig ausgedacht.
        return 42;
    }
}

