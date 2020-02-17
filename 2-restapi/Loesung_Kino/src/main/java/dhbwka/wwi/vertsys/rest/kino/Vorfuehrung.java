/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.rest.kino;

import java.io.Serializable;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * Persistence Entity für eine Vorführung.
 */
@Entity
@Data
public class Vorfuehrung implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long id;
    
    @ManyToOne
    private Film film;
    
    @Column(length = 20)
    @Size(min = 1, max = 20, message = "Der Wochentag muss zwischen einem und 20 Zeichen lang sein.")
    @NotNull(message = "Der Wochentag darf nicht leer sein.")
    private String wochentag = "";
    
    @NotNull(message = "Die Sallnummer darf nicht leer sein.")
    private int saalnr = 0;
    
    @NotNull(message = "Die Uhrzeit darf nicht leer sein.")
    private LocalTime uhrzeit = LocalTime.now();
}
