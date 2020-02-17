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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * Persistence Entity für einen Film.
 */
@Entity
@Data
public class Film implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long id;

    @Column(length = 64)
    @Size(min = 1, max = 64, message = "Der Name muss zwischen einem und 64 Zeichen lang sein.")
    @NotNull(message = "Der Name darf nicht leer sein.")
    private String name = "";

    @NotNull(message = "Das Jahr darf nicht leer sein.")
    private int jahr = 0;

    @Column(length = 64)
    @Size(min = 1, max = 64, message = "Das Genre muss zwischen einem und 64 Zeichen lang sein.")
    @NotNull(message = "Das Genre darf nicht leer sein.")
    private String genre = "";

    // Rückwärtsbeziehung vom Film zu seinen Vorführunen
    // Steht so nicht in den Folien, ist aber sehr praktisch
    @OneToMany(mappedBy="film")
    private List<Vorfuehrung> vorfuehrungen = new ArrayList<>();

    /**
     * Standard-Konstruktor
     */
    public Film() {
    }
}
