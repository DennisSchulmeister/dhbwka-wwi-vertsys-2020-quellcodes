/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.eureka.client;

import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Hintergrundthread, in dem die Services aufgerufen wird. Die Aufrufe finden in
 * einem eigenen Thread statt, um Ausnahmen wegen zu schnellem Start und Stopp
 * der Anwendung zu vermeiden.
 */
@Component
public class CallServices {

    private static Logger logger = LoggerFactory.getLogger(CallServices.class);

    @Autowired
    private Service1Proxy service1;

    @Autowired
    private Service2Proxy service2;

    @PostConstruct
    public void run() {
        // Service 1 aufrufen
        logger.info("");
        logger.info(">>> Rufe Service 1 auf.");
        logger.info("");

        Service1Proxy.Message message = this.service1.sayHello();

        logger.info("");
        logger.info(">>> Antwort von Service 1: " + message.getText());
        logger.info("");

        // Service 2 aufrufen
        logger.info("");
        logger.info(">>> Rufe Service 2 auf.");
        logger.info("");

        int number = this.service2.getRandomNumber();

        logger.info("");
        logger.info(">>> Antwort von Service 2: " + number);
        logger.info("");
    }
}
