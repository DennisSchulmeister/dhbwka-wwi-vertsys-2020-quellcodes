/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.loesung.naas.lending_backend.service;

import dhbwka.wwi.vertsys.loesung.naas.lending_backend.dto.Device;
import dhbwka.wwi.vertsys.loesung.naas.lending_backend.entity.LendingRequest;
import dhbwka.wwi.vertsys.loesung.naas.lending_backend.proxy.CatalogProxy;
import dhbwka.wwi.vertsys.loesung.naas.lending_backend.repository.LendingRequestRepository;
import feign.FeignException;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Interne Serviceklasse zur Prüfung von Ausleihanträgen. Diese Klasse kann
 * immer dann verwendet werden, wenn geprüft werden soll, ob es ein Gerät
 * überhaupt gibt (per Anfrage an das Katalog-Backend) oder ob es in einem
 * gesuchten Zeitraum verfügbar ist. Außerdem gibt es hier auch eine Methode zum
 * direkten Prüfen und Sichern eines Ausleihantrags.
 */
@Service
public class LendingService {

    @Autowired
    private CatalogProxy catalogProxy;

    @Autowired
    private LendingRequestRepository repository;

    /**
     * Prüft, ob es das Gerät mit der übergebenen ID überhaupt gibt. Hierzu
     * werden die Gerätedaten im Katalog-Backend angefragt.
     *
     * @param deviceId Geräte ID
     * @return true, wenn das Gerät existiert
     */
    public boolean checkDeviceExists(long deviceId) {
        try {
            this.catalogProxy.checkDeviceExists(deviceId);
            return true;
        } catch (FeignException.NotFound ex) {
            return false;
        }
    }

    /**
     * Prüft, ob das Gerät im gewünschten Zeitraum verfügbar ist. Hierzu wird
     * nach Anträgen mit Status LendingStatus.LENDED gesucht, deren Zeitraum
     * sich mit dem zu prüfenden Zeitraum überschneidet. Wird mindestens ein
     * solcher Antrag gefunden, gilt das Gerät als nicht verfügbar.
     *
     * @param deviceId Geräte ID
     * @param startTime Startzeit
     * @param endTime Endzeit
     * @return true, wenn das Gerät verfügbar ist
     */
    public boolean checkDeviceIsAvailable(long deviceId, LocalDateTime startTime, LocalDateTime endTime) {
        return this.repository.countConflicts(deviceId, startTime, endTime) < 1;
    }

    /**
     * Validiert den übergebenen Ausleihantrag. Hierzu wird geprft, ob es das
     * gewünschte Gerät überhaupt gibt und ob es im gesuchten Zeitraum verfügbar
     * ist. Schläft die Validierung fehl, wird eine Exception geworfen. Sonst
     * macht die Methode nichts.
     *
     * @param request Ausleihantrag
     * @throws LendingException Validierung fehlgeschlagen
     */
    public void validateLendingRequest(LendingRequest request) throws LendingException {
        if (!checkDeviceExists(request.getDeviceId())) {
            throw new LendingException("Das Gerät wurde nicht gefunden.");
        }

        if (!checkDeviceIsAvailable(request.getDeviceId(), request.getStartTime(), request.getEndTime())) {
            throw new LendingException("Das Gerät ist im gewünschten Zeitraum nicht verfügbar.");
        }
        
        if (request.getStartTime().isAfter(request.getEndTime())) {
            throw new LendingException("Das Rückgabedatum darf nicht vor dem Ausleihdatum liegen.");
        }
    }

    /**
     * Validiert den übergebenen Ausleihantrag und speichert ihn ab, wenn dabei
     * keine Fehler gefunden werden. Dabei werden auf die beiden Felder
     * "manufacturer" und "model" vom Katalogbackend abgerufen und in den Antrag
     * übernommen.
     *
     * Kann der Antrag nicht gespeichert werden, wird eine Exception geworfen.
     *
     * @param request Ausleihantrag
     * @return Der gespeicherte Antrag
     * @throws LendingException Validierung fehlgeschlagen
     */
    public LendingRequest validateAndSaveLendingRequest(LendingRequest request) throws LendingException {
        this.validateLendingRequest(request);
        
        Device device = this.catalogProxy.getDeviceInformation(request.getDeviceId());
        request.setManufacturer(device.getManufacturer());
        request.setModel(device.getModel());
        
        return this.repository.save(request);
    }
}
