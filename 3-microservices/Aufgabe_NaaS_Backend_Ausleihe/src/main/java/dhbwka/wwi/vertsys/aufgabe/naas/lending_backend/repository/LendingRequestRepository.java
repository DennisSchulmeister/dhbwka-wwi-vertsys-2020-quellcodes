/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.aufgabe.naas.lending_backend.repository;

import dhbwka.wwi.vertsys.aufgabe.naas.lending_backend.entity.LendingRequest;
import dhbwka.wwi.vertsys.aufgabe.naas.lending_backend.entity.LendingStatus;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Einfaches Spring Data Repository für den Zugriff auf die in der Datenbank
 * gespeicherten Ausleihanträge. Spring erzeugt automatisch eine abgeleitete
 * Klasse mit den üblichen Methoden zum Lesen, Schreiben und Löschen von
 * Einträgen.
 */
@RepositoryRestResource(collectionResourceRel = "lending-requests", path = "lending-requests")
public interface LendingRequestRepository extends PagingAndSortingRepository<LendingRequest, Long> {

    List<LendingRequest> findByDeviceId(long deviceId);

    List<LendingRequest> findByDeviceIdAndStatus(long deviceId, LendingStatus status);

    List<LendingRequest> findByStatus(LendingStatus status);

    @Query("SELECT count(lr) FROM LendingRequest lr "
            + "WHERE lr.deviceId = :deviceId AND "
            + " lr.status = dhbwka.wwi.vertsys.loesung.naas.lending_backend.entity.LendingStatus.LENDED AND ("
            + "  (lr.startTime <= :startTime AND lr.endTime >= :startTime) OR "
            + "  (lr.startTime <= :endTime AND lr.endTime >= :endTime) OR "
            + "  (lr.startTime >= :startTime AND lr.endTime <= :endTime) "
            + ")")
    int countConflicts(long deviceId, LocalDateTime startTime, LocalDateTime endTime);
}
