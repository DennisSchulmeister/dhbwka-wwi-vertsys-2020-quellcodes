/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.rest.musicdb.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * Einfache Entity-Klasse für einen Song.
 */
@Entity
@Data
public class Song implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long id = 0L;

    @Column(length = 64)
    @Size(min = 1, max = 64, message = "Der Name muss zwischen einem und 64 Zeichen lang sein.")
    @NotNull(message = "Der Name darf nicht leer sein.")
    private String name = "";

    @Column(length = 64)
    @Size(min = 1, max = 64, message = "Der Künstler muss zwischen einem und 64 Zeichen lang sein.")
    @NotNull(message = "Der Künstler darf nicht leer sein.")
    private String artist = "";

    @Column(length = 64)
    @Size(min = 1, max = 64, message = "Der Songwriter muss zwischen einem und 64 Zeichen lang sein.")
    @NotNull(message = "Der Songwriter darf nicht leer sein.")
    private String songwriters = "";

    @NotNull
    private int releaseYear = 0;

    /**
     * Default-Konstruktor
     */
    public Song() {
    }
}
