/*
 * Copyright © 2021 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.rest.amsreview.proxy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Datentransferobjekt mit den wichtigsten Daten einer vom AMS-Backend abgerufenen Episode.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EpisodeDTO {
    
    private long id = 0L;
    private int season = 0;
    private int number = 0;
    private String name = "";
    
}
