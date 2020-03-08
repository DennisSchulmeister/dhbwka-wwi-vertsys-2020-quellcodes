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
 * Einfache Datentransferklasse für eine Statusmeldung, wie sie der Webservice
 * nach dem Hinzufügen eines Eintrags zurückgibt.
 */
@Data
public class MessageDTO {
    
    private String text;
    
    public MessageDTO() {
    }
    
    public MessageDTO(String text) {
        this.text = text;
    }
    
}
