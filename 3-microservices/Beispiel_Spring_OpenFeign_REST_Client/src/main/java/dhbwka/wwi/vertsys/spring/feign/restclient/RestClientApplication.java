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

import dhbwka.wwi.vertsys.spring.feign.restclient.SongListProxy.Message;
import dhbwka.wwi.vertsys.spring.feign.restclient.SongListProxy.Song;
import java.util.List;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Component;

/**
 * Hauptklasse der Anwendung. Diese Anwendung konsumiert den in der Anwendung
 * "Beispiel Spring OpenFeign (REST-Server)" bereitgestellten Webservice.
 */
@SpringBootApplication
@EnableFeignClients
@Component
public class RestClientApplication {

    private static Logger logger = LoggerFactory.getLogger(RestClientApplication.class);

    @Autowired
    SongListProxy webservice;

    public static void main(String[] args) {
        SpringApplication.run(RestClientApplication.class, args);
    }

    @PostConstruct
    private void callWebservice() {
        logger.info("");
        logger.info("");
        logger.info(">>> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.info(">>>");

        // Abruf der vorhandenen Songs
        logger.info(">>> Aktuell auf dem Server gespeicherte Songs:");
        logger.info(">>>");

        List<Song> songs = this.webservice.getSongs();

        for (Song song : songs) {
            logger.info(">>>  » " + song.getArtist() + " – " + song.getTitle());
        }

        logger.info(">>>");

        // Weiteren Song speichern
        logger.info(">>> Schicke einen neuen Song an den Server.");

        Song newSong = new Song();
        newSong.setArtist("Joe Zavinul");
        newSong.setTitle("Mercy, Mercy!");

        Message message = this.webservice.addSong(newSong);

        logger.info(">>> Antwort: " + message.getText());
        logger.info(">>>");

        // Erneuter Abruf der vorhandenen Songs
        logger.info(">>> Jetzt auf dem Server gespeicherte Songs:");

        songs = this.webservice.getSongs();

        for (Song song : songs) {
            logger.info(">>>  » " + song.getArtist() + " – " + song.getTitle());
        }

        logger.info(">>>");
        logger.info(">>> ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.info("");
        logger.info("");
    }

}
