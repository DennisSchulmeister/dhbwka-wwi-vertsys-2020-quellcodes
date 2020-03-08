/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.dibeispiel.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Beispiel für eine einfache mit dem Spring IoC-Container verwaltete Klasse.
 * Wie man sieht, muss die Klasse hierfür nur mit @Component gekennzeichnet
 * werden, oder eine der anderen Annotationen zur Kennzeichnung von Komponenten,
 * wie @Service oder @Repository verwenden. Dadurch wird die Klasse automatisch
 * beim Start der Anwendung instantiiert und kann mit @Autowired sich benötigte
 * geben lassen.
 */
@Component
public class SimpleComponent {

    private Logger logger = LoggerFactory.getLogger(SimpleComponent.class);
    
    // Dependency Injecten des Taschrechnerservices
    @Autowired
    private CalculatorService calculatorService;

    /**
     * Konstruktor. Zeigt, dass die Klasse automatisch beim Start der Anwendung
     * instantiiert wird, indem einfach eine Log-Meldung ausgegeben wird.
     */
    public SimpleComponent() {
        logger.info(">>> SimpleComponent: Constructor <<<");
    }
    
}
