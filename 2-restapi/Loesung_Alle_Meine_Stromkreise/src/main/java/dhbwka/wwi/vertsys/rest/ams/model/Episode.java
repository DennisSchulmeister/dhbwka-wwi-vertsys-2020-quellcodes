/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.rest.ams.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity-Klasse für eine Episode einer Serie.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Episode implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long id = 0L;

    @ManyToOne
    private Series series = null;
        
    @Min(value = 1, message = "Die Staffel darf nicht kleiner eins sein.")
    private int season = 0;

    @Min(value = 1, message = "Die Episodennummer darf nicht kleiner eins sein.")
    private int number = 0;

    @Column(length = 20, unique = true)
    @Size(min = 0, max = 20, message = "Die IMDB-ID darf maximal 20 Zeichen lang sein.")
    private String imdbId = "";

    @Column(length = 64)
    @Size(min = 1, max = 64, message = "Der Name muss zwischen einem und 64 Zeichen lang sein.")
    @NotNull(message = "Der Name darf nicht leer sein.")
    private String name = "";

}
