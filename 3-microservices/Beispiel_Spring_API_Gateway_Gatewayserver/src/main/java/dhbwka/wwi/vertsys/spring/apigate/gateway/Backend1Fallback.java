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
 * Fallback-Implementierungen für die Services von Backend 1.
 */
@RestController
@RequestMapping("/fallback/backend1")
public class Backend1Fallback {
    
    @GetMapping("hi")
    public String sayHi() {
        return "Das API-Gateway sagt: Hi!";
    }
    
    @GetMapping("bye")
    public String sayBye() {
        return "Das API-Gateway sagt: Bye bye!";
    }
}
