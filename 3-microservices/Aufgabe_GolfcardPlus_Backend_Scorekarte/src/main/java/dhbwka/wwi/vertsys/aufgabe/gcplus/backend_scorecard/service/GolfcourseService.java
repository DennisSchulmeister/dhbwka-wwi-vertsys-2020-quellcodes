/*
 * Copyright © 2021 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.aufgabe.gcplus.backend_scorecard.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Serviceklasse zur Ermittlung der Anzahl gespielerter Spiele je Golfplatz.
 */
@Service
public class GolfcourseService {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * @return Liste aller Golfplätze mit der jeweiligen Anzahl gespielter Spiele
     */
    public List<CountByCourseIdResult> countByCourseId() {
        return this.entityManager.createQuery("SELECT new dhbwka.wwi.vertsys.aufgabe.gcplus.backend_scorecard.service.CountByCourseIdResult( "
                + "    s.golfCourseId, COUNT(s) "
                + ") "
                + "FROM Scorecard AS s "
                + "GROUP BY s.golfCourseId "
                + "ORDER BY s.golfCourseId ", CountByCourseIdResult.class).getResultList();
    }

}
