/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.loesung.naas.lending_backend.proxy;

import dhbwka.wwi.vertsys.loesung.naas.lending_backend.dto.Device;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Proxy-Interface zum Abruf benötigter Informationen vom Katalog-Backend.
 * Wird benötigt, damit das Ausleih-Backend prüfen kann, ob es ein Gerät,
 * das ausgeliehen werden soll, überhaupt gibt.
 */
@FeignClient(name = "AUFGABE-NAAS-CATALOG", url = "http://localhost:8081")
public interface CatalogProxy {
    
    /**
     * Abruf der Daten eines Geräts.
     * @param id ID des gesuchten Geräts.
     * @return Gerätedaten
     */
    @GetMapping(path = "/devices/{id}")
    Device getDeviceInformation(@PathVariable("id") long id);
    
    @RequestMapping(path = "/devices/{id}", method = RequestMethod.HEAD)
    void checkDeviceExists(@PathVariable("id") long id);
    
}
