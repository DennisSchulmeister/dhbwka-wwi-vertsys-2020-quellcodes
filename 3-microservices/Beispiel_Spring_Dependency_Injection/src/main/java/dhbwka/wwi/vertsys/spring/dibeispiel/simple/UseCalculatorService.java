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

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Beispiel für die Verwendung der in diesem Projekt definierten Services.
 */
@Component
public class UseCalculatorService {
    
    private Logger logger = LoggerFactory.getLogger(UseCalculatorService.class);
    
    @Autowired
    private CalculatorService calculatorService;
    
    public UseCalculatorService() {
        logger.info(">>> UseCalculatorService: Constructor");
    }
    
    /**
     * PostConstruct-Methode, die automatisch aufgerufen wird, sobald die
     * Dependency Injection zu Ende gelaufen ist.
     */
    @PostConstruct
    private void doSomething() {
        logger.info(">>> UseCalculatorService: @PostConstruct doSomething");
        
        int result = this.calculatorService.add(4711, 2412);
        logger.info(">>> UseCalculatorService: 4711 + 2412 = " + result);
        
        result = this.calculatorService.subtract(4711, 2412);
        logger.info(">>> UseCalculatorService: 4711 - 2412 = " + result);
        
        result = this.calculatorService.multiply(4711, 2412);
        logger.info(">>> UseCalculatorService: 4711 * 2412 = " + result);
        
        result = this.calculatorService.divide(4711, 2412);
        logger.info(">>> UseCalculatorService: 4711 / 2412 = " + result);
    }
    
    /**
     * PreDestroy-Methode, die automatisch augerufen wird, kurz bevor das
     * eigene Objekt wieder verschwindet.
     */
    @PreDestroy
    private void timeToSayGoodbye() {
        logger.info(">>> UseCalculatorService: @PreDestroy timeToSayGoodbye");
    }
    
}
