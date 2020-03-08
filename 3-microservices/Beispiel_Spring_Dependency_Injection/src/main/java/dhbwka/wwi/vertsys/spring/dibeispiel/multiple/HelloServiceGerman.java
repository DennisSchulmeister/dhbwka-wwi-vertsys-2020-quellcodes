/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.dibeispiel.multiple;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * Deutsche Version des Hallo-Service.
 *
 * Durch die Annotation @Primary wird sichergestellt, dass immer diese
 * Implementierung gezogen wird, wenn ein Client keine Angabe darüber macht,
 * welche Implementierung er haben will, es aber mehrere zur Auswahl gibt.
 * 
 * Alternativ kann ein Client mit @Qualifier("German") auch explizit diese
 * Version anfordern, selbst wenn die Annotation @Primary hier irgendwann
 * später mal entfernt wird.
 */
@Component
@Primary
@Qualifier("German")
public class HelloServiceGerman implements HelloService {

    @Override
    public String sayHello(String name) {
        return "Hallo, " + name + ".";
    }
    
}
