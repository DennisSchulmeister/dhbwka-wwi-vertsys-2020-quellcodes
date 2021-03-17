/*
 * Copyright © 2021 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.rest.ams.controller;

import dhbwka.wwi.vertsys.rest.ams.model.Series;
import dhbwka.wwi.vertsys.rest.ams.model.StreamingService;
import dhbwka.wwi.vertsys.rest.ams.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simpler REST-Webservice, der eine zufällige Serie als Empfehlung liefert.
 */
@RestController
@RequestMapping("/api/recommend")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping
    public SeriesDTO getRandomSeries() {
        Series series = this.recommendationService.getRandomSeries();
        return SeriesDTO.createFromSeries(series);
    }

    /**
     * Datentransferklasse für das Ergebnis des Webservices. Entspricht im Grunde genommen der Series-Entität ohne
     * die Fremdschlüsselfelder, um eine Endlosrekursion bei der Serialisierung der Antwortdaten zu vermeiden.
     * Außerdem wollen wir eine etwas andere Antwortstruktur zurückliefern, die nicht 1:1 der Entität entspricht.
     */
    public static class SeriesDTO {

        /**
         * Factorymethode zur Erzeugung eines neuen Objekts anhand einer Series-Entität.
         * @param series Datenbankentität
         * @return Datentransferobjekt
         */
        public static SeriesDTO createFromSeries(Series series) {
            SeriesDTO seriesDTO = new SeriesDTO();

            seriesDTO.id = series.getId();
            seriesDTO.imdbId = series.getImdbId();
            seriesDTO.name = series.getName();
            seriesDTO.country = series.getCountry();
            seriesDTO.description = series.getDescription();
            
            StreamingService streamingService = series.getStreamingService();
            
            if (streamingService != null) {
                seriesDTO.streamingServiceId = streamingService.getId();
                seriesDTO.streamingServiceName = streamingService.getName();
            }

            return seriesDTO;
        }

        public long id = 0L;
        public String streamingServiceId = "";
        public String streamingServiceName = "";
        public String imdbId = "";
        public String name = "";
        public String country = "";
        public String description = "";
    }

}
