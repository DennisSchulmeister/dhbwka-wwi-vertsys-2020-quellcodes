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

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Beispiel für eine Klasse mit Prototype-Scope. Standardmäßig sind alle mit dem
 * Spring IoC-Container verwaltete Klassen Singletons, so dass ein und dieselbe
 * Instanz von allen Clients geteilt wird. Dies entspricht der Annotation
 * @Scope("singleton"), die aber nur selten verwendet wird, da es sich ja um das
 * Standardverhalten von Spring handelt.
 *
 * Soll jeder Client seine eigene Objektinszanz bekommen, muss dies stattdessen
 * mit @Scope("prototype") sichergestellt werden.
 */
@Component
@Scope("prototype")
public class PlaylistServiceSimple implements PlaylistService {

    private List<String> songs = new ArrayList<>();

    @Override
    public void addSong(String song) {
        this.songs.add(song);
    }

    @Override
    public List<String> getSongs() {
        return this.songs;
    }

}
