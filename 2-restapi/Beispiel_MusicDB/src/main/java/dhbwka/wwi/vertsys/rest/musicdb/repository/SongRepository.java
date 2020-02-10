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

import dhbwka.wwi.vertsys.rest.musicdb.model.Song;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Einfaches Spring Data REST Repository, das den Zugriff auf die Songs über
 * den REST-Webservice erlaubt.
 */
@RepositoryRestResource(collectionResourceRel = "songs", path = "songs")
public interface SongRepository extends PagingAndSortingRepository<Song, Long> {
    
    // Kann leer bleiben oder weitere Zugriffsmethoden definieren
    List<Song> findByName(@Param("name") String name);
    List<Song> findByArtist(@Param("artist") String artist);
    List<Song> findBySongwriters(@Param("songwriters") String songwriters);
    List<Song> findByReleaseYear(@Param("releaseYear") int releaseYear);
}
