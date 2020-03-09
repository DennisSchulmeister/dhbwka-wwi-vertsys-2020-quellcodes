/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.hystrix.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Einfacher REST-Webservice zur Ermittlung von ein paar Zufallszahlen.
 */
@RestController
@RequestMapping("/random")
public class RandomController {
    
    @GetMapping
    List<Integer> getRandomNumbers() {
        List<Integer> numbers = new ArrayList<>();
        
        for (int i = 0; i < 5; i++) {
            numbers.add((int) Math.floor(Math.random() * 1000));
        }
        
        return numbers;
    }
    
}
