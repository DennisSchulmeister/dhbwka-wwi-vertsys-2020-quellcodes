/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.beispiel.golfcard.controller;

import dhbwka.wwi.vertsys.beispiel.golfcard.service.ScoringResult;
import dhbwka.wwi.vertsys.beispiel.golfcard.service.ScoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HTTP REST-Controller für einen einfachen Webservice, der die Bewertung einer
 * Scorekarte ausrechnet. Gemäß den Empfehlungen zu Strukturierung einer
 * Spring-Anwendung befindet sich die eigentliche Logik zum Ausrechnen der
 * Bewertung in einer Service-Klasse, die hier aufgerufen wird.
 */
@RestController
@RequestMapping("${spring.data.rest.basePath}/scoring/{id}")
public class ScoringController {
    
    @Autowired
    private ScoringService scoringService;
    
    @GetMapping
    public ScoringResult getScoringResult(@PathVariable("id") long scorecardId) {
        return this.scoringService.calculateScoring(scorecardId);
    }
}
