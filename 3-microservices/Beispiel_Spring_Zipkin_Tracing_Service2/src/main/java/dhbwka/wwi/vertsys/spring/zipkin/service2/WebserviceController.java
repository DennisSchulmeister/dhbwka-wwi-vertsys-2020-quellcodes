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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Beispiel-Implementierung eines ganz, ganz einfachen Webservices.
 */
@RestController
@RequestMapping("/")
public class WebserviceController {

    @Autowired
    private Service3Proxy service3;

    @GetMapping
    public String callService() {
        String response3 = this.service3.callService();
        return "Antwort von Service 2 + " + response3;
    }

}
