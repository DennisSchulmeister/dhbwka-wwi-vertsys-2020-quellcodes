/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.aufgabe.gcplus.backend_scorecard.repository;

import dhbwka.wwi.vertsys.aufgabe.gcplus.backend_scorecard.model.Scorecard;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Einfaches Spring Data Repository für den Zugriff auf die in der Datenbank gespeicherten Scorekarten. Spring erzeugt
 * automatisch eine abgeleitete Klasse mit den üblichen Methoden zum Lesen, Schreiben und Löschen von Einträgen.
 */
@RepositoryRestResource(collectionResourceRel = "scorecards", path = "scorecard")
public interface ScorecardRepository extends PagingAndSortingRepository<Scorecard, Long> {

    List<Scorecard> findByGolfCourseId(long golfCourseId);

}
