/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.zipkin.service2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hauptklasse der Anwendung.
 * 
 * ACHTUNG! Dieses Beispiel benötigt einen installierten Zipkin-Server, der
 * auf dem Standardport 9411 erreichbar ist. Hierfür kann entweder von der
 * Zipkin-Webseite eine ausführbare JAR-Datei heruntergeladen oder Docker
 * genutzt werden. Mit Docker lässt sich der Zipkin-Server wie folgt starten:
 * 
 *   $ docker run -d -p 9411:9411 openzipkin/zipkin-slim
 * 
 * Bzw. unter Linux kann podman anstelle als moderne Docker-Alternative
 * verwendet werden:
 * 
 *   $ podman run -d -p 9411:9411 openzipkin/zipkin-slim
 * 
 * Mit `docker ps`, `docker stop` und `docker rm` kann die Zipkin-Instanz
 * verwaltet werden.
 * 
 * Nachdem der Zipkin-Server gestartet wurde, können die Beispiel-Services
 * gestartet werden. Anschließend sollten verschiedene Aufrufe an die Services
 * geschickt und diese in der Weboberfläche von Zipkin analysiert werden.
 * 
 * Der Einfachheit halber lassen sich die Beispielservices mit folgenden
 * URLs im Browser aufrufen, um Tracedaten zu erzeugen.
 * 
 *   » http://localhost:8081/
 *   » http://localhost:8082/
 *   » http://localhost:8083/
 * 
 * Service 1 ruft intern Service 2 und dieser intern Service 3 auf.
 * 
 * Die Weboberfläche von Zipkin findet sich unter folgender URL:
 * 
 *   » http://localhost:9411
 */
@SpringBootApplication
@EnableFeignClients
public class Service2Application {
    
    private static Logger logger = LoggerFactory.getLogger(Service2Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Service2Application.class, args);
        
        logger.info("");
        logger.info("");
        logger.info(">>>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~<<<");
        logger.info(">>> Beispiel zum Tracing mit Zipkin (Service 3)!                                      <<<");
        logger.info(">>>                                                                                   <<<");
        logger.info(">>> Dieses Beispiel benötigt einen laufenden Zipkin-Server.                           <<<");
        logger.info(">>> Dieser lässt sich mit Docker wie folgt starten:                                   <<<");
        logger.info(">>>                                                                                   <<<");
        logger.info(">>>     $ docker run -d -p 9411:9411 openzipkin/zipkin-slim                           <<<");
        logger.info(">>>                                                                                   <<<");
        logger.info(">>> Alternativ kann von der Zipkin-Seite ein ausführbares JAR heruntergeladen werden. <<<");
        logger.info(">>>                                                                                   <<<");
        logger.info(">>> Danach folgende URLs im Browser aufrufen, um Traces zu erzeugen:                  <<<");
        logger.info(">>>                                                                                   <<<");
        logger.info(">>>     » http://localhost:8081/                                                      <<<");
        logger.info(">>>     » http://localhost:8082/                                                      <<<");
        logger.info(">>>     » http://localhost:8083/                                                      <<<");
        logger.info(">>>                                                                                   <<<");
        logger.info(">>> Die Traces können in Zipkin unter folgender URL aufgerufen werden:                <<<");
        logger.info(">>>                                                                                   <<<");
        logger.info(">>>     » http://localhost:9411/                                                      <<<");
        logger.info(">>>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~<<<");
        logger.info("");
        logger.info("");
    }

}
