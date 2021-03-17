/*
 * Copyright © 2021 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.rest.amsreview.service;

import dhbwka.wwi.vertsys.rest.amsreview.model.Review;
import dhbwka.wwi.vertsys.rest.amsreview.proxy.AmsApiProxy;
import dhbwka.wwi.vertsys.rest.amsreview.proxy.EpisodeDTO;
import dhbwka.wwi.vertsys.rest.amsreview.proxy.SeriesDTO;
import dhbwka.wwi.vertsys.rest.amsreview.repository.ReviewRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

/**
 * Service-Klasse zur vereinfachten Erstellung einer Bewertung. Ruft automatisch den Namen der Serie oder Episode vom
 * Backend ab, bevor die Bewertung gespeichert wird.
 */
@Service
public class ReviewService {

    @Autowired
    private AmsApiProxy proxy;

    @Autowired
    private ReviewRepository repository;

    /**
     * Speichern einer neuen Bewertung zu einer Serie.
     *
     * @param seriesId ID der Serie
     * @param reviewText Bewertungstext
     * @return Angelegte Bewertung
     * @throws dhbwka.wwi.vertsys.rest.amsreview.service.ReviewService.NotFoundException
     */
    public Review postReviewForSeries(long seriesId, String reviewText) throws NotFoundException {
        SeriesDTO series;
        
        try {
            series = this.proxy.getSeries(seriesId);
        } catch (FeignException ex) {
            throw new NotFoundException("Die gesuchte Serie wurde nicht gefunden.");
        }

        Review review = new Review();
        review.setSeriesId(seriesId);
        review.setSeriesName(series.getName());
        review.setReview(reviewText);

        return this.repository.save(review);
    }

    /**
     * Speichern einer neuen Bewertung zu einer Episode.
     *
     * @param episodeId ID der Episode
     * @param reviewText Bewertungstext
     * @return Angelegte Bewertung
     * @throws dhbwka.wwi.vertsys.rest.amsreview.service.ReviewService.NotFoundException
     */
    public Review postReviewForEpisode(long episodeId, String reviewText) throws NotFoundException {
        EpisodeDTO episode;
        SeriesDTO series;
        
        try {
            episode = this.proxy.getEpisode(episodeId);
        } catch (FeignException ex) {
            throw new NotFoundException("Die gesuchte Episode wurde nicht gefunden.");
        }
        
        try {
            series = this.proxy.getEpisodeSeries(episodeId);
        } catch (FeignException ex) {
            throw new NotFoundException("Die Serie zur gesuchten Episode wurde nicht gefunden.");
        }
        
        Review review = new Review();
        review.setSeason(episode.getSeason());
        review.setEpisode(episode.getNumber());
        review.setEpisodeName(episode.getName());
        review.setReview(reviewText);
        
        review.setSeriesId(series.getId());
        review.setSeriesName(series.getName());

        return this.repository.save(review);
    }

    /**
     * Ausnahme, wenn die gesuchte Serie oder Episode nicht gefunden wurde.
     */
    public static class NotFoundException extends Exception {

        public NotFoundException(String message) {
            super(message);
        }
    }
}
