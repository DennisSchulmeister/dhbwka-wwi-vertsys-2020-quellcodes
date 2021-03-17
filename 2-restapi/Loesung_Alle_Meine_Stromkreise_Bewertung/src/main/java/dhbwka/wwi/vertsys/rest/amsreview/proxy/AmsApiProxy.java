/*
 * Copyright © 2021 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.rest.amsreview.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Proxy-Interface zum Abruf von Informationen vom AMS-Backend.
 */
@FeignClient(name="amsapi", url="localhost:9090")
@RequestMapping("/api")
public interface AmsApiProxy {
    
    @GetMapping("/serieses/{id}")
    public SeriesDTO getSeries(@PathVariable("id") long id);
    
    @GetMapping("/episodes/{id}")
    public EpisodeDTO getEpisode(@PathVariable("id") long id);
    
    @GetMapping("/episodes/{id}/series")
    public SeriesDTO getEpisodeSeries(@PathVariable("id") long id);
    
}
