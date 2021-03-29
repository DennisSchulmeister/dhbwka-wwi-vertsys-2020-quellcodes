/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.aufgabe.gcplus.frontend.controller;

// AUFGABE: Die auskommentierten Quellcodes einkommentieren, nachdem die Abhängigkeiten für den Nameserver Client
// In der pom.xml einkommentiert und das Projekt einmal gebaut wurden. Dadurch wird die Möglichkeit geschaffen,
// in der Property gcplus.gateway der Spring-Konfiguration anstelle einer HTTP-Adresse einen logischen Namen für
// das API-Gateway zu hinterlegen, der im Namensdienst nachgeschlagen wird.

/*
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
 */
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HTTP-Controller für den Endpunkt /api/gateway. Dieser liefert die URL des Gateway-Servers zurück, damit der
 * JavaScript-Code im Frontend seine Anfragen ohne Umweg über den Frontend-Server direkt dorthin schicken kann.
 */
@RestController
public class GetGatewayUrlController {

/*
    @Autowired
    private EurekaClient discoveryClient;
*/
    @Value("${gcplus.gateway}")
    private String gatewayName;

    /**
     * Endpunkt /api/gateway
     *
     * @return String mit der URL des Gateway-Servers
     */
    @GetMapping(value = "/api/gateway", produces = "text/plain")
    public String getGatewayUrl() {
        if (this.gatewayName.toLowerCase().startsWith("http")) {
            return this.gatewayName;
        }

/*
        InstanceInfo instance = discoveryClient.getNextServerFromEureka(this.gatewayName, false);
        String url = instance.getHomePageUrl();

        if (instance.isPortEnabled(InstanceInfo.PortType.SECURE)) {
            // HTTPS-URL zurückgeben, wenn SSL aktiv ist. Einerseits ist das sicherer,
            // andererseits laden die Browser heute keine unverschlüsselten Inhalte mehr,
            // wenn die Seite selbst verschlüsselt geladen wurde. Beim Testen auf dem
            // localhost fällt dies meistens nicht auf, wohl aber, wenn man die Anwendung
            // produktiv setzt und das erste mal via HTTPS aufruft.
            url = url.replace("http://", "https://");
            url = url.replace(":" + instance.getPort(), ":" + instance.getSecurePort());
        }

        return url;
*/
        return "";      // AUFGABE: Diese Zeile entfernen
    }
}
