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
import org.springframework.stereotype.Component;

/**
 * Amerikanische Version des Hallo-Services. Ein Client muss diese Version
 * explizit mit @Qulifier("American") anfordern.
 */
@Component
@Qualifier("American")
public class HelloServiceAmerican implements HelloService {

    @Override
    public String sayHello(String name) {
        return "Hi, " + name + "!";
    }

}
