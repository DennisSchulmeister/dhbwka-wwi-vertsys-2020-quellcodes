/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.loesung.naas.lending_backend.controller;

import dhbwka.wwi.vertsys.loesung.naas.lending_backend.entity.LendingRequest;
import dhbwka.wwi.vertsys.loesung.naas.lending_backend.service.LendingException;
import dhbwka.wwi.vertsys.loesung.naas.lending_backend.service.LendingService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Zusätzliche Controller-Klasse mit erweiterten HTTP-Endpunkten zum Ausleihen
 * eines Geräts. Im Gegensatz zum automatisch generierten Webservice für
 * Ausleihanträge besteht hier die Möglichkeit, einen Antrag erst auf fachliche
 * Richtigkeit zu prüfen, bevor er gespeichert wird. Im Normalfall sollten
 * Clients daher den hier definierten Endpunkt /device/lend verwenden, um
 * einen neuen Ausleihantrag zu speichern, da dieser einen Fehler zurück gibt,
 * falls das gewünschte Gerät nicht existiert oder nicht verfügbar ist.
 */
@RestController
@RequestMapping("/device")
public class LendDeviceController {

    @Autowired
    private LendingService lendingService;

    /**
     * Endpunkt /device/exists/{deviceId}
     * 
     * Prüft, ob es das Gerät mit der übergebenen ID überhaupt gibt.
     * 
     * @param deviceId Geräte-ID
     * @return Webservice-Antwort
     */
    @GetMapping("/exists/{deviceId}")
    public DeviceExistsResponse deviceExists(@PathVariable long deviceId) {
        DeviceExistsResponse response = new DeviceExistsResponse();
        response.setExists(this.lendingService.checkDeviceExists(deviceId));
        return response;
    }

    /**
     * Endpunkt /device/available/{deviceId}?starTime=&endTime=
     * 
     * Prüft, ob das Gerät im gesuchten Zeitraum ausleihbar ist.
     * 
     * @param deviceId Geräte-ID
     * @param startTime Startzeit
     * @param endTime Endzeit
     * @return Webservice-Antwort
     */
    @GetMapping("/available/{deviceId}")
    public DeviceAvailableResponse deviceIsAvailable(
            @PathVariable long deviceId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {

        DeviceAvailableResponse response = new DeviceAvailableResponse();
        response.setAvailable(this.lendingService.checkDeviceIsAvailable(deviceId, startTime, endTime));
        return response;
    }

    /**
     * Endpunkt /device/lend
     * 
     * Prüft den übergebenen Ausleihantrag und speichert ihn, falls das Gerät
     * existiert und im gewünschten Zeitraum verfügbar ist.
     * 
     * @param lendingRequest Ausleihantrag
     * @return Der gespeicherte Antrag
     * @throws LendingException Gerät nicht vorhanden oder nicht verfügbar
     */
    @RequestMapping(path = "/lend", method = { RequestMethod.PUT, RequestMethod. POST })
    public LendingRequest lend(@RequestBody LendingRequest lendingRequest) throws LendingException {
        return this.lendingService.validateAndSaveLendingRequest(lendingRequest);
    }
    
}
