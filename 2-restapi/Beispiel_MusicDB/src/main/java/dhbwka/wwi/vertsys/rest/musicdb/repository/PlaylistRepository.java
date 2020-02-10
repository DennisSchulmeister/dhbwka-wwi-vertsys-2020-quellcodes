/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.rest.musicdb.repository;

import dhbwka.wwi.vertsys.rest.musicdb.model.Playlist;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Einfaches Spring Data REST Repository, das den Zugriff auf die Playlists über
 * den REST-Webservice erlaubt.
 */
@RepositoryRestResource(collectionResourceRel = "playlists", path = "playlists")
public interface PlaylistRepository extends PagingAndSortingRepository<Playlist, Long> {

    // Kann leer bleiben oder weitere Zugriffsmethoden definieren
    List<Playlist> findByName(@Param("name") String name);
}
