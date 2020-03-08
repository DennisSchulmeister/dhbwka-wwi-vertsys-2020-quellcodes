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
import org.springframework.stereotype.Service;

/**
 * Einfache Implementierung des Taschenrechnerservices. Führt alle Berechnungen
 * lokal aus.
 */
@Service
public class CalculatorServiceLocal implements CalculatorService {
    
    private Logger logger = LoggerFactory.getLogger(CalculatorServiceLocal.class);

    public CalculatorServiceLocal() {
        logger.info(">>> LocalCalculatorService: Constructor <<<");
    }
    
    @Override
    public int add(int v1, int v2) {
        return v1 + v2;
    }

    @Override
    public int subtract(int v1, int v2) {
        return v1 - v2;
    }

    @Override
    public int multiply(int v1, int v2) {
        return v1 * v2;
    }

    @Override
    public int divide(int v1, int v2) {
        return v1 / v2;
    }
}
