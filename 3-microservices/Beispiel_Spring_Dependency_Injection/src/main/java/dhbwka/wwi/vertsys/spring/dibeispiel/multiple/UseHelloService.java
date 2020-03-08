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

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Beispiel für die Verwendung der in diesem Projekt definierten Services.
 */
@Component
public class UseHelloService {
    
    private Logger logger = LoggerFactory.getLogger(UseHelloService.class);
    
    // Dependency Injection der mit @Primary gekennzeichneten Default-Implementierung
    // eines Services mit mehreren Implementierungen
    @Autowired
    private HelloService helloDefault;
    
    // Explizite Anfrage einer bestimmten Implementierung
    @Autowired
    @Qualifier("American")
    private HelloService helloAmerican;
    
    // Dependency Injection via expliziter Setter-Methode
    private HelloService helloBavarian;
    
    @Autowired
    @Qualifier("Bavarian")
    public void setHelloBavarian(HelloService helloBavarian) {
        this.helloBavarian = helloBavarian;
    }
    
    // Dependency Injection via Konstruktor-Parameter
    private HelloService helloFrench;
    
    @Autowired
    public UseHelloService(@Qualifier("French") HelloService helloFrench) {
        logger.info(">>> UseHelloService: Constructor");
        this.helloFrench = helloFrench;
    }
    
    /**
     * PostConstruct-Methode, die automatisch aufgerufen wird, sobald die
     * Dependency Injection zu Ende gelaufen ist.
     */
    @PostConstruct
    private void doSomething() {
        logger.info(">>> UseHelloService: @PostConstruct doSomething");
        
        // Hello-Service aufrufen
        String greeting = helloDefault.sayHello("Spring");
        logger.info(">>> UseHelloService: Standard-Begrüßung: " + greeting);
        
        greeting = helloBavarian.sayHello("Frühling");
        logger.info(">>> UseHelloService: Bayrische Begrüßung: " + greeting);
        
        greeting = helloAmerican.sayHello("Spring");
        logger.info(">>> UseHelloService: Amerikanische Begrüßung: " + greeting);
        
        greeting = helloFrench.sayHello("le Printemps");
        logger.info(">>> UseHelloService: Französische Begrüßung: " + greeting);
    }
    
    /**
     * PreDestroy-Methode, die automatisch augerufen wird, kurz bevor das
     * eigene Objekt wieder verschwindet.
     */
    @PreDestroy
    private void timeToSayGoodbye() {
        logger.info(">>> UseHelloService: @PreDestroy timeToSayGoodbye");
    }
    
}
