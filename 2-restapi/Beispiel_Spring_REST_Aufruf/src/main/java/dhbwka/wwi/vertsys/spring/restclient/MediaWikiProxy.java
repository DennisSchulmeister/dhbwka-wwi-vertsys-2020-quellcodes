/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.restclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Proxy-Interface zum Aufruf der MediaWiki-API von Wikipedia. Vgl.
 * https://de.wikipedia.org/wiki/Spezial:ApiSandbox
 *
 * Das Interface unterstützt nicht alle Parameter der MediaWiki-API, da diese
 * sehr umfangreich sein können. In diesem einfachen Beispiel wird nur die
 * Aktion "query" mit den abgefragten Properties "description" und "coordinates"
 * unterstützt.
 */
@FeignClient(name = "wikipedia", url = "${wikipedia_api_url}", configuration = FeignConfiguration.class)
public interface MediaWikiProxy {

    /**
     * Webservice-Aktion "query"
     *
     * @param titles Abzufragende Seitentitel (mehrere durch | getrennt)
     * @param prop Abzufragende Properties (mehrere durch | getrennt)
     * @return
     */
    @GetMapping
    MediaWikiQueryResult query(@RequestParam String titles, @RequestParam String prop);
}
