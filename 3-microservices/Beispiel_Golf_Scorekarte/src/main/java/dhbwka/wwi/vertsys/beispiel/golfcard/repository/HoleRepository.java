/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.beispiel.golfcard.repository;

import dhbwka.wwi.vertsys.beispiel.golfcard.model.Hole;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Einfaches Spring Data Repository für den Zugriff auf die in der Datenbank
 * gespeicherten gespielten Löcher einer Scorekarte. Spring erzeugt automatisch
 * eine abgeleitete Klasse mit den üblichen Methoden zum Lesen, Schreiben und
 * Löschen von Einträgen.
 */
@RepositoryRestResource(collectionResourceRel = "holes", path = "holes")
public interface HoleRepository extends PagingAndSortingRepository<Hole, Long> {

    List<Hole> findByScorecard_Id(long scorecardId);
    
}
