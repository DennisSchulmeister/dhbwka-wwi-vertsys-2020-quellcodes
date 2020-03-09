/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.aufgabe.naas.device_service;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity-Klasse für ein Gerät.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long id;

    @Column(length = 64)
    @Size(min = 1, max = 64, message = "Der Hersteller muss zwischen einem und 64 Zeichen lang sein.")
    @NotNull(message = "Der Hersteller darf nicht leer sein.")
    private String manufacturer = "";
    
    @Column(length = 64)
    @Size(min = 1, max = 64, message = "Das Modell muss zwischen einem und 64 Zeichen lang sein.")
    @NotNull(message = "Das Modell darf nicht leer sein.")
    private String model = "";
    
    @NotNull(message = "Das Kaufdatum darf nicht leer sein.")
    LocalDate buyingDate = LocalDate.now();
    
    @ManyToOne
    private DeviceClass deviceClass;
}
