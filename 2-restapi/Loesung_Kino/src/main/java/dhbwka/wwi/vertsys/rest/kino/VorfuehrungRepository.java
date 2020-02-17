/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.rest.kino;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Spring Data Repository für die Vorführung-Entität.
 */
@RepositoryRestResource(collectionResourceRel = "vorfuehrung", path = "vorfuehrung")
public interface VorfuehrungRepository extends PagingAndSortingRepository<Vorfuehrung, Long> {
    // Kann leer bleiben
}
