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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Einfache Datentransferklasse für eine Statusmeldung, wie sie der Webservice
 * nach dem Hinzufügen eines Eintrags zurückgibt.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    
    private String text;

}
