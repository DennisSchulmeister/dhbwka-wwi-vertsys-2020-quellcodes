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

import java.util.List;

/**
 * Einfacher Service zur Verwaltung einer Playlist mit Songs.
 */
public interface PlaylistService {
    
    /**
     * Hinzufügen eines weiteren Songs.
     * @param song Name des Songs
     */
    void addSong(String song);
    
    /**
     * Auslesen aller bisher gespeicherten Songs
     * @return Liste mit Songnamen
     */
    List<String> getSongs();
    
}
