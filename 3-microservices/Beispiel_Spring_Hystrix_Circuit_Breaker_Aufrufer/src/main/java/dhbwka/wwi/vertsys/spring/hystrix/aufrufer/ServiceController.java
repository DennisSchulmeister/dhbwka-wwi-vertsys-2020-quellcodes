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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Eigener kleiner Webservice zum Aufruf der App. Nutzt den Remote Proxy zum
 * Aufruf des entfernten Random-Services. Ist der Servie erreichbar, erhalten
 * wir wirklich Zufallszahlen. Andernfalls die Zahlen unserer lokalen
 * Fallback-Implementierung danke des Hystrix Circuit Breakers.
 */
@RestController
@RequestMapping("/")
public class ServiceController {
    
    @Autowired
    private RemoteRandomProxy randomService;

    @GetMapping
    public String callService() {
        // Entfernten Service aufrufen
        List<Integer> numbers = randomService.getRandomNumbers();
        
        // Eigene Antwort erzeugen
        int sum = 0;
        sum = numbers.stream().map((i) -> i).reduce(sum, Integer::sum);
        
        String response = "<b>Empfangene Zufallszahlen:</b> " + numbers.toString() + "<br/>";
        response += "<b>Summe:</b> " + sum + "<br/>";
        
        return response;
    }
}
