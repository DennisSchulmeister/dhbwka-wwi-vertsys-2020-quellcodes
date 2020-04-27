/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.loesung.naas.frontend.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HTTP-Controller für den Endpunkt /api/gateway. Dieser liefert die URL des
 * Gateway-Servers zurück, damit der JavaScript-Code im Frontend seine Anfragen
 * ohne Umweg über den Frontend-Server direkt dorthin schicken kann.
 */
@RestController
public class GetGatewayUrlController {

    @Autowired
    private EurekaClient discoveryClient;
    
    @Value("${naas.gateway}")
    private String gatewayName;

    /**
     * Endpunkt /api/gateway
     * @return String mit der URL des Gateway-Servers
     */
    @GetMapping(value = "/api/gateway", produces = "text/plain")
    public String getGatewayUrl() {
        InstanceInfo instance = discoveryClient.getNextServerFromEureka(this.gatewayName, false);
        return instance.getHomePageUrl();
    }
}
