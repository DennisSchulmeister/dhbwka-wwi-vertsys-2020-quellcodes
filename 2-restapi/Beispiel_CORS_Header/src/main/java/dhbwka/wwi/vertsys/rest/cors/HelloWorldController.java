/*
 * Copyright © 2021 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.rest.cors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simpler HTTP-Controller, der einfach eine "Hello, World" Nachricht zurückliefert.
 * Die Klasse hat zwei Methoden, die zeigen, wie die CORS HTTP-Header gesetzt werden
 * können:
 * 
 *   * getGreeting1: Besitzt keine besonderen Annotationen. Es werden daher die
 *                   in der Klasse CorsApplication zentral definierten CORS-Header
 *                   an den Aufrufer übertragen.
 * 
 *   * getGreeting2: Zeigt, wie die CORS-Header mit der Annotation @CrossOrigin
 *                   für einzelne Methoden übersteuert werden können.
 * 
 * BEACHTE: Die Annotation @CrossOrigin kann entweder vor einer einzelnen Methode oder
 * vor der gesamten Controller-Klasse stehen. Steht sie vor der Klasse, gilt sie für
 * alle Methoden.
 * 
 * Der Webservice reagiert auf folgende URLS:
 * 
 *   * http://localhost:8080/
 *   * http://localhost:8080/de
 */
@RestController
@RequestMapping("/")
public class HelloWorldController {
    
    public class HelloWorldMessage {
        public String message = "";
    }
    
    /**
     * Keine besonderen Annotationen. Es werden daher die in der Hauptklasse definierten
     * CORS-Header verwendet.
     * 
     * @return "Hello, World!"
     */
    @GetMapping
    public HelloWorldMessage getGreeting1() {
        HelloWorldMessage msg = new HelloWorldMessage();
        msg.message = "Hello, World!";
        return msg;
    }
    
    /**
     * Übersteuert der in der Hauptklasse definierten CORS-Header mit der Annotation
     * @CrossOrigin.
     * 
     * @return "Hallo, Welt!"
     */
    @GetMapping("/de")
    @CrossOrigin(origins = "http://www.beispiel.de")
    public HelloWorldMessage getGreeting2() {
        HelloWorldMessage msg = new HelloWorldMessage();
        msg.message = "Hallo, Welt!";
        return msg;
    }
}
