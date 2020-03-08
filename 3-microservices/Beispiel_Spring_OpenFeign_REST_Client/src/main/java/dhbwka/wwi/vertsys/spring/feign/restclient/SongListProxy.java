/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.feign.restclient;

import java.util.List;
import lombok.Data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Proxy-Interface (lokaler Stellvertreter) zum Aufruf des Beispiel-Webservices
 * zur Verwaltung einer Songliste.
 */
@FeignClient(name = "restserver", url = "localhost:8080")
@RequestMapping("/songs")
public interface SongListProxy {

    /**
     * GET-Anfrage an /songs
     * @return Liste der gespeicherten Songs
     */
    @GetMapping
    List<Song> getSongs();

    /**
     * POST-Anfrage an /songs
     * @param song Zu speichernder Song
     * @return Statusmeldung
     */
    @PostMapping
    Message addSong(Song song);

    /**
     * Datentransferklasse für die (De)Serialisierung eines Songs.
     * Könnte natürlich auch in einer eigenen Quellcodedatei definiert sein,
     * wenn sie von mehreren Webservice-Proxies verwendet wird.
     */
    @Data
    class Song {

        private String artist;
        private String title;
    }

    /**
     * Datentransferklasse für die (De)Serialisierung einer Statusmeldung.
     * Könnte natürlich auch in einer eigenen Quellcodedatei definiert sein,
     * wenn sie von mehreren Webservice-Proxies verwendet wird.
     */
    @Data
    class Message {

        private String text;
    }
    
}
