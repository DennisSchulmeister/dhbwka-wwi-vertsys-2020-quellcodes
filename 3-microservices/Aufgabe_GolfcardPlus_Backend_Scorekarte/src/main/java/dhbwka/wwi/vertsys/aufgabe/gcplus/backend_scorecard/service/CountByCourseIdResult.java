/*
 * Copyright © 2021 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.aufgabe.gcplus.backend_scorecard.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Datentransferklasse mit der Anzahl gespielter Spiele eines Golfplatzes.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountByCourseIdResult {

    public long courseId = 0;
    public long count = 0;
    
}
