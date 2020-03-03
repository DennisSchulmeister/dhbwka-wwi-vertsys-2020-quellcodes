/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.rest.ams.repository;

import dhbwka.wwi.vertsys.rest.ams.model.Episode;
import dhbwka.wwi.vertsys.rest.ams.model.Series;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Spring Data REST Repository für die StreamingService-Entität.
 */
@RepositoryRestResource(collectionResourceRel = "episodes", path = "episodes")
public interface EpisodeRepository extends PagingAndSortingRepository<Episode, Long> {

    List<Episode> findByName(@Param("name") String name);
    Episode findByImdbId(@Param("imdbId") String imdbId);
    
}
