/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.eureka.service1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Mini-Webservice, dessen URL die Client-App vor dem Aufruf erst aus dem
 * Namensdienst ermitteln muss.
 */
@RestController
public class ServiceController {
    
    @GetMapping("/sayHello")
    public Message sayHello() {
        return new Message("Hallo von Service 1!");
    }
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Message {
        private String text;
    }
}
