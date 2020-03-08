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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Einfacher REST-Webservice zum Abrufen einer zufälligen Nachricht.
 */
@RestController
public class MotdController {

    @Autowired
    private ApplicationConfig config;

    @GetMapping("/motd")
    public Message getMessageOfTheDay() {
        String[] allMessages = this.config.getMessagesArray();
        
        Message message = new Message();
        message.text = allMessages[(int) Math.floor(Math.random() * allMessages.length)];
        return message;
    }

    public class Message {
        public String text;
    }
}
