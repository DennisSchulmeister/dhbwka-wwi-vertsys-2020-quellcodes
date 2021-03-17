/*
 * Copyright © 2021 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.rest.amsreview.repository;

import dhbwka.wwi.vertsys.rest.amsreview.model.Review;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Spring Data REST Repository für die Review-Entität.
 */
@RepositoryRestResource(collectionResourceRel = "reviews", path = "review")
public interface ReviewRepository extends PagingAndSortingRepository<Review, Long> {
    
    List<Review> findBySeriesId(long seriesId);
    List<Review> findBySeriesIdAndSeasonAndEpisode(long seriesId, int season, int episode);
    List<Review> findBySeriesNameContaining(String seriesName);
    List<Review> findByEpisodeNameContaining(String episodeName);
    
}
