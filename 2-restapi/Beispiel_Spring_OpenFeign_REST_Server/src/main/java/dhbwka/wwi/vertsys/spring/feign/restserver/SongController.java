/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.feign.restserver;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller-Klasse des Beispiel-Webservices. Um das Beispiel nicht zu
 * kompliziert werden zu lassen, kann der Webservice einfach eine Liste von
 * Songs temporär speichern. Clients können mit POST einen neuen Song
 * hinzufügen und mit GET die Liste abfragen.
 */
@RestController
@RequestMapping("/songs")
public class SongController {
    
    private List<SongDTO> songs = new ArrayList<>();
    
    @GetMapping
    public List<SongDTO> getSongs() {
        return this.songs;
    }
    
    @PostMapping
    public MessageDTO addSong(@RequestBody SongDTO song) {
        this.songs.add(song);
        return new MessageDTO("Song hinzugefügt.");
    }
    
    @PostConstruct
    private void createDemoData() {
        this.addSong(new SongDTO("Joe Cocker", "My father's son"));
        this.addSong(new SongDTO("Beatles", "Let it be"));
        this.addSong(new SongDTO("Geroge Harrison", "Here comes the sun"));
        this.addSong(new SongDTO("Elton John", "Sorry seems to be the hardest word"));
        this.addSong(new SongDTO("Dire Straits", "So far away"));
    }
    
}
