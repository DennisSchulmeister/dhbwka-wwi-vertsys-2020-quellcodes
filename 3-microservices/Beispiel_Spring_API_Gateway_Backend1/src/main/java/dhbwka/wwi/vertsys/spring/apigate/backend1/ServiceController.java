/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.apigate.backend1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementierung des Beispiel-Webservices. Liefert ein ganz simples Hallo
 * Welt, jedoch mit verschiedenen URLs, um die Konfiguration des Gateway-Servers
 * interessanter zu machen.
 */
@RestController
public class ServiceController {
    
    @GetMapping("/hi/{name}")
    public String sayHi(@PathVariable("name") String name) {
        return "Backend 1 sagt: Hi, " + name + "!";
    }
    
    @GetMapping("/bye/{name}")
    public String sayBye(@PathVariable("name") String name) {
        return "Backend 1 sagt: Goodbye, " + name + "!";
    }
    
}
