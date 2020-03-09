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

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Fallback-Implementierung für den Random-Service, falls dieser nicht
 * erreichbar ist.
 */
@Component
public class RemoteRandomFallback implements RemoteRandomProxy {

    @Override
    public List<Integer> getRandomNumbers() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);
        numbers.add(50);
        return numbers;
    }

}
