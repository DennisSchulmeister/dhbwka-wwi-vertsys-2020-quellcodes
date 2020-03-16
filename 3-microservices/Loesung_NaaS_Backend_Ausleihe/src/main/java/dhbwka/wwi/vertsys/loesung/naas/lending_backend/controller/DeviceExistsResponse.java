/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.loesung.naas.lending_backend.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Datentransferklasse mit den Antwortdaten für die Prüfung, ob ein Gerät
 * überhaupt existiert.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceExistsResponse {
    private boolean exists = false;
}
