/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.config.hallo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Einfacher REST-Webservice zum Begrüßen des Anwenders.
 */
@RestController
public class HalloController {
    
    @Autowired
    private ApplicationConfig config;

    @GetMapping("/hallo")
    public Greeting sayHello(@RequestParam String name) {
        Greeting greeting = new Greeting();
        greeting.message = this.config.getGreeting();
        greeting.message = greeting.message.replace("$NAME$", name);
        return greeting;
    }
    
    public class Greeting {
        public String message;
    }
}
