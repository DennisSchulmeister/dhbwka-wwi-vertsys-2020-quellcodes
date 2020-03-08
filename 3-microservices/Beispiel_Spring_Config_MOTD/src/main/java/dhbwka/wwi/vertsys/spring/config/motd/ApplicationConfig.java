/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.config.motd;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Klasse zum Auslesen der Anwendungskonfiguration.
 */
@Component
@ConfigurationProperties("motdservice")
@Data
public class ApplicationConfig {
    
    private String messages;
    
    /**
     * @return Array mit den verfügbaren Nachrichten
     */
    public String[] getMessagesArray() {
        String[] messagesArray = this.messages.split("/");
        
        for (int i = 0; i < messagesArray.length; i++) {
            messagesArray[i] = messagesArray[i].trim();
        }
        
        return messagesArray;
    }
    
}
