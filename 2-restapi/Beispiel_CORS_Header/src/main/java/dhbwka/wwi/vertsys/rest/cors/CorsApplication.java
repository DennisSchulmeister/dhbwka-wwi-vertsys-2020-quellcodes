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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Haupklasse der Anwendung. Hier werden der Webserver gestartet (Methode main) sowie die CORS-Header für alle
 * HTTP-Controller definiert (Methode getCorsConfiguration).
 * 
 * Eine sehr gute Erklärung zu CORS findet sich hier: https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS
 * 
 * Zum Testen muss der Webserivce mit einem Werkzeug wie POSTMAN aufgerufen werden. Dabei fällt auf, dass der
 * Server in jeder Antwort folgende Header Fields mitsendet:
 * 
 *  * Vary: Origin
 *  * Vary: Access-Control-Request-Method
 *  * Vary: Access-Control-Request-Headers
 * 
 * Er teilt damit mit, welche Eigenschaften einer Anfrage sich auf CORS (und damit z.B. auch das Caching der
 * Antwort) auswirken. Schicken wir eine Anfrage, die den Origin Header enthält, z.B.
 * 
 *   GET / HTTP/1.1
 *   Origin: http://www.beispiel.de
 * 
 * sehen wir, dass die Antwort den entsprechenden CORS-Header antwortet:
 * 
 *   HTTP/1.1 200 Ok
 *   Access-Control-Allow-Origin: *
 * 
 * Ein Browser würde dann ahand dieses Header Fields entscheiden, ob er die Antwort akzeptiert oder nicht.
 * 
 * Da eine HTTP-Anfrage potentiell Veränderungen auf dem Server auslösen kann, schicken die Brower bei "gefährlichen"
 * Anfragen zu nächst einen sog. OPTIONS-PreFlight. Das heißt, vor der eigentlichen Anfrage schicken Sie eine OPTIONS-
 * Anfrage, um nur die CORS-Header abzurufen und entscheiden dann anhand der Antwort, ob sie die potentiell gefährliche
 * Anfrage durchführen oder nicht.
 */
@SpringBootApplication
@Component
public class CorsApplication implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(CorsApplication.class);
    
    /**
     * Webserver starten.
     * @param args 
     */
    public static void main(String[] args) {
        SpringApplication.run(CorsApplication.class, args);
    }

    /**
     * Zentrale CORS-Konfiguration. Kann als Alternative zur Annotation @CrossOrigin in den
     * HTTP-Controller-Klassen verwendet werden, weil hier durch den Aufruf von addMapping()
     * jeweils einer URL eine CORS-Konfiguration zugewiesen wird.
     * 
     * Normalerweise würdem an die Annotation @CrossOrigin in den HTTP-Controller-Klassen
     * vorziehen. Diese Variante hier kann aber auch verwendet werden, wenn man keinen
     * Zugriff auf die Klassen hat, z.B. weil der Webservice von Spring automatisch generiert
     * wird.
     * 
     * @return CORS-Konfiguration
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {        
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/").allowedOrigins("*");
            }
        };
    }
    
    /**
     * Eigentliche Hauptmethode des Programms.Wird von Spring automatisch
     * aufgerufen, da die Klasse das CommandLineRunner-Interface implementiert.
     *
     * @param args Kommandozeilenparameter
     */
    @Override
    public void run(String... args) {
        logger.info("");
        logger.info("");
        logger.info(">>> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.info(">>> Beispiel zur Verwendung der CORS HTTP-Header in Spring                  ");
        logger.info(">>>                                                                         ");
        logger.info(">>>   * http://localhost:8080/      « CORS-Konfiguration aus der Hauptklasse");
        logger.info(">>>   * http://localhost:8080/de    « CORS-Konfiguration via @CrossOrigin   ");
        logger.info(">>> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.info("");
        logger.info("");
    }

}
