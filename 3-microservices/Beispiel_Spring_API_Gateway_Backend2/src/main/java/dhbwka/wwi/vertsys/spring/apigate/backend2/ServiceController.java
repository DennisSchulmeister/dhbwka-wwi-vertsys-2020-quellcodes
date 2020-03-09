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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementierung des Beispiel-Webservices. Ruft spaßeshalber Backend 1 über
 * das API-Gateway auf.
 */
@RestController
public class ServiceController {

    @Autowired
    private Backend1Proxy backend1;

    private static final String[] NAMES = {"Joe", "Mark", "Freddy", "Eric", "Tina"};

    @GetMapping("/greetRandom")
    public String greetRadom() {
        String name = NAMES[(int) Math.floor(Math.random() * NAMES.length)];

        return "Backend 2 sagt: <br/>"
                + "&nbsp;&nbsp;&nbsp;&nbsp;" + this.backend1.sayHi(name) + "<br/>"
                + "&nbsp;&nbsp;&nbsp;&nbsp;" + this.backend1.sayBye(name);
    }

}
