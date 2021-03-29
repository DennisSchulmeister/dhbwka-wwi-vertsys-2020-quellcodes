/*
 * Copyright © 2021 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.aufgabe.gcplus.backend_golfcourse.repository;

import dhbwka.wwi.vertsys.aufgabe.gcplus.backend_golfcourse.model.Course;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Einfaches Spring Data Repository für den Zugriff auf die in der Datenbank
 * gespeicherten Golfplätze. Spring erzeugt automatisch eine abgeleitete Klasse
 * mit den üblichen Methoden zum Lesen, Schreiben und Löschen von Einträgen.
 */
@RepositoryRestResource(collectionResourceRel = "courses", path = "course")
public interface CourseInterface extends PagingAndSortingRepository<Course, Long> {
    
    // Kann leer bleiben
    
}
