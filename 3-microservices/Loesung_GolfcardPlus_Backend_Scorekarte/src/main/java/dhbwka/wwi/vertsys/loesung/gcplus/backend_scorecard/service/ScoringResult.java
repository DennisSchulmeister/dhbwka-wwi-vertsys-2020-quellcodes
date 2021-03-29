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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Datentransferklasse für die Bewertung einer Scorekarte. Objekte dieser
 * Klasse werden nicht in der Datenbank abgespeichert sondern zur Laufzeit
 * erzeugt, um die Bewertung einer Scorekarte festzuhalten.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoringResult {
    
    public int countHoles = 0;
    public int totalRegularStrokes = 0;
    public int totalPenaltyStrokes = 0;
    public int totalStrokes = 0;
    public int stableford = 0;
    
}
