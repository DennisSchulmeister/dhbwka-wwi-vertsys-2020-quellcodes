/*
 * Copyright © 2021 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.rest.amsreview.controller;

import dhbwka.wwi.vertsys.rest.amsreview.model.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Einfacher, von Hand gestrickter Webservice zur vereinfachten Anlage von Bewertungen.
 */
@RestController
@RequestMapping("/api/simple-review")
public class ReviewController {

    /**
     * Vereinfache Bewertung einer Serie.
     *
     * @param simpleReview Daten der neuen Bewertung
     * @return Tatsächlich gespeicherte Bewertung
     */
    @PostMapping("/series")
    public Review postReviewForSeries(@RequestBody SimpleReview simpleReview) {
        // TODO
        return null;
    }

    /**
     * Vereinfache Bewertung einer Episode.
     *
     * @param simpleReview Daten der neuen Bewertung
     * @return Tatsächlich gespeicherte Bewertung
     */
    @PostMapping("/episode")
    public Review postReviewForEpisode(@RequestBody SimpleReview simpleReview) {
        // TODO
        return null;
    }

    /**
     * Datentransferklasse zur Definition der JSON-Struktur für neue Bewertungen.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SimpleReview {

        private long id = 0L;
        private String review = "";
    }

}
