/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.aufgabe.naas.lending_backend.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity-Klasse für einen Ausleihantrag. Ein Gerät gilt als ausgeliehen, wenn
 * es innerhalb des gesuchten Zeitraums mindestens einen Antrag mit dem Status
 * LendingStatus.LENDED gibt.
 */
@Entity
@Data
@NoArgsConstructor
public class LendingRequest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Die ID darf nicht null sein.")
    private long id;
    
    @NotNull(message = "Die Geräte-ID darf nicht null sein.")
    private long deviceId;
    
    @NotNull(message = "Die Startzeit darf nicht leer sein.")
    private LocalDateTime startTime = LocalDateTime.now();
    
    @NotNull(message = "Die Endzeit darf nicht leer sein.")
    private LocalDateTime endTime = LocalDateTime.now();
    
    private LendingStatus status;
    
    @Column(length = 100)
    @Size(min = 1, max = 100, message = "Die Kontaktdaten des Ausleihenden müssen zwischen einem und 100 Zeichen lang sein.")
    @NotNull(message = "Die Kontaktdaten des Ausleihenden dürfen nicht leer sein.")
    private String contactData;
    
    @Column(length = 64)
    @Size(min = 1, max = 64, message = "Der Bereitstellungsort muss zwischen einem und 64 Zeichen lang sein.")
    @NotNull(message = "Der Bereitstellungsort darf nicht leer sein.")
    private String location;
}
