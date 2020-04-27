package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Copyright © 2020 Martin Kutscher
 * 
 * E-Mail: martin.kutscher@exxeta.com
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 * 
 * Beispiel REST-Anwendung zum Auslesen vordefinierter Nutzer und Gruppen mit Verwendung von HAL, 
 * orientiert am Beispiel von https://howtodoinjava.com/spring5/hateoas/spring-hateoas-tutorial/ .
 * Die Dateien schema.sql und data.sql im Ordner /resources erzeugen ein Schema und Testdaten fuer eine In-Memory H2-Datenbank.
*/
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
