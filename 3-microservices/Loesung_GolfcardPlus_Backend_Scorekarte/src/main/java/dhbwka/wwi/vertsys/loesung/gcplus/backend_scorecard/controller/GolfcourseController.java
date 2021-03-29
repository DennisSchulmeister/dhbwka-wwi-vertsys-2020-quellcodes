/*
 * Copyright © 2021 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.loesung.gcplus.backend_scorecard.controller;

import dhbwka.wwi.vertsys.loesung.gcplus.backend_scorecard.service.CountByCourseIdResult;
import dhbwka.wwi.vertsys.loesung.gcplus.backend_scorecard.service.GolfcourseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HTTP REST-Controller für einen einfachen Webservice, der die Anzahl der gespielten Spiele je Golfplatz ermittelt.
 */
@RestController
@RequestMapping("${spring.data.rest.basePath}/golfcourse")
public class GolfcourseController {
    
    @Autowired
    private GolfcourseService golfcourseService;
    
    /**
     * @return Liste aller Golfplätze mit der jeweiligen Anzahl gespielter Spiele
     */
    @GetMapping("/countByCourseId")
    public List<CountByCourseIdResult> getCountByCourseId() {
        return this.golfcourseService.countByCourseId();
    }
    
}
