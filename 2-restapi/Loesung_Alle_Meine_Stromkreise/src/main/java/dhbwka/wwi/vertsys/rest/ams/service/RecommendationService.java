/*
 * Copyright © 2021 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.rest.ams.service;

import dhbwka.wwi.vertsys.rest.ams.model.Series;
import dhbwka.wwi.vertsys.rest.ams.repository.SeriesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Serviceklasse zur Ermittlung empfohlener Serien.
 */
@Service
public class RecommendationService {
    
    @Autowired
    private SeriesRepository seriesRepository;
    
    /**
     * Ermittelt eine zufällige Serie als Empfehlung.
     * @return Ermittelte Serie oder null, wenn es keine Serien gibt
     */
    public Series getRandomSeries() {
        long count = this.seriesRepository.count();
        int random = (int) Math.floor(Math.random() * count);
        
        Page<Series> results = this.seriesRepository.findAll(PageRequest.of(random, 1));
        List<Series> list = results.getContent();
        
        return list.size() > 0 ? list.get(0) : null;
    }
    
}
