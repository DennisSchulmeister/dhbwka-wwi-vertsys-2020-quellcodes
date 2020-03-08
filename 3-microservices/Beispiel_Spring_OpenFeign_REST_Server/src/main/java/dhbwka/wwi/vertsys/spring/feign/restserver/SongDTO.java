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

import lombok.Data;

/**
 * Einfaches Datentransferobjekt für einen Song. Der Beispiel-Webservice
 * liefert als Antwort einfach eine Liste mit Objekten dieses Typs.
 */
@Data
public class SongDTO {
    
    private String artist;
    private String title;
    
    public SongDTO() {
    }
    
    public SongDTO(String artist, String title) {
        this.artist = artist;
        this.title = title;
    }
    
}
