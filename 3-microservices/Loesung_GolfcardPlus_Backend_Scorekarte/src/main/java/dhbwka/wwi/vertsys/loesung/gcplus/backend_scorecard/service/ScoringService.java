/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.loesung.gcplus.backend_scorecard.service;

import dhbwka.wwi.vertsys.loesung.gcplus.backend_scorecard.model.Hole;
import dhbwka.wwi.vertsys.loesung.gcplus.backend_scorecard.repository.HoleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Serviceklasse zur Bewertung einer Scorekarte. Mit Hilfe dieser Klasse können
 * die Gesamtzahl der Schläge sowie die Stableford-Bewertung eines Spiels
 * ausgerechnet werden.
 */
@Service
public class ScoringService {

    @Autowired
    private HoleRepository holeRepository;

    /**
     * Scorekarte bewerten. Rechnet zu einer gegebenen Scorekarten-ID die
     * Anzahl der Schläge sowie die Stableford-Bewertung aus.
     * 
     * @param scorecardId ID der zu bewertenden Scorekarte
     * @return Bewertung der Scorekarte
     */
    public ScoringResult calculateScoring(long scorecardId) {
        List<Hole> holes = this.holeRepository.findByScorecard_Id(scorecardId);
        ScoringResult result = new ScoringResult();

        for (Hole hole : holes) {
            // Anzahl der Schläge aufsummieren
            result.totalRegularStrokes += hole.getRegularStrokes();
            result.totalPenaltyStrokes += hole.getPenaltyStrokes();

            // Stableford-Bewertung ausrechnen
            int totalStrokes = hole.getRegularStrokes() + hole.getPenaltyStrokes();
            int par = hole.getPar();

            if (totalStrokes > 0) {
                if (totalStrokes <= par - 3) {
                    result.stableford += 5;
                } else if (totalStrokes == par - 2) {
                    result.stableford += 4;
                } else if (totalStrokes == par - 1) {
                    result.stableford += 3;
                } else if (totalStrokes == par) {
                    result.stableford += 2;
                } else if (totalStrokes == par + 1) {
                    result.stableford += 1;
                }
            }
        }

        result.countHoles = holes.size();
        result.totalStrokes = result.totalRegularStrokes + result.totalPenaltyStrokes;
        return result;
    }
}
