/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.dibeispiel.advanced;

import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Beispiele für die fortgeschrittene Verwendung von Dependency Injection.
 * Für den MOTD-Service gibt es keine Implementierung, so dass dieser als
 * optional gekennzeichnet werden musste. Der Playlist-Service arbeite nicht
 * mit Singletons, damit wir mehrere Playlists verwalten können.
 */
@Component
public class UseAdvancedServices {

    private Logger logger = LoggerFactory.getLogger(UseAdvancedServices.class);

    // Optionale Abhängigkeit
    @Autowired(required = false)
    private MessageOfTheDayService motd;

    // Erzeugung mehrere Objektinstanzen, da die implementierende Klasse mit
    // der Annotation @Scoped("prototype") ausgestattet wurde
    @Autowired
    private PlaylistService playlist1;

    @Autowired
    private PlaylistService playlist2;

    public UseAdvancedServices() {
        logger.info(">>> UseAdvancedServices: Constructor");
    }

    /**
     * PostConstruct-Methode, die automatisch aufgerufen wird, sobald die
     * Dependency Injection zu Ende gelaufen ist.
     */
    @PostConstruct
    private void doSomething() {
        // MOTD-Service aufrufen
        if (this.motd != null) {
            String message = motd.getMessage();
            logger.info(">>> UseAdvancedServices: Nachricht des Tages: " + message);
        }

        // Playlist-Service aufrufen
        this.playlist1.addSong("Mark Knopfler - Sailing to Philadelphia");
        this.playlist1.addSong("Mark Knopfler - Get Lucky");
        this.playlist1.addSong("Mark Knopfler - Don't Suck Me In");

        for (String song : this.playlist1.getSongs()) {
            logger.info(">>> UseAdvancedServices: Playlist 1: " + song);
        }

        this.playlist2.addSong("Joe Cocker - Summer in the City");
        this.playlist2.addSong("Joe Cocker - My Father's Son");
        this.playlist2.addSong("Joe Cocker - Hard Knocks");

        for (String song : this.playlist2.getSongs()) {
            logger.info(">>> UseAdvancedServices: Playlist 2: " + song);
        }
    }

}
