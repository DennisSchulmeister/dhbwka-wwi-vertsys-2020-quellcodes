/*
 * Copyright © 2021 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.rest.amsreview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Hauptklasse der Anwendung.
 */
@SpringBootApplication
@EnableFeignClients
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Zentrale CORS-Konfiguration. Kann als Alternative zur Annotation @CrossOrigin in den HTTP-Controller-Klassen
     * verwendet werden, weil hier durch den Aufruf von addMapping() jeweils einer URL eine CORS-Konfiguration
     * zugewiesen wird.
     *
     * Normalerweise würdem an die Annotation @CrossOrigin in den HTTP-Controller-Klassen vorziehen. Diese Variante hier
     * kann aber auch verwendet werden, wenn man keinen Zugriff auf die Klassen hat, z.B. weil der Webservice von Spring
     * automatisch generiert wird.
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

}
