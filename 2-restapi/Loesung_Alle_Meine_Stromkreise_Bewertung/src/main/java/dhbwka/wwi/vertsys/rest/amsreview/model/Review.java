/*
 * Copyright © 2021 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.rest.amsreview.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity-Klasse für eine Bewertung zu einer Serie oder Episode.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long id = 0L;
    
    private LocalDateTime timestamp = LocalDateTime.now();

    private long seriesId = 0L;
    private int season = 0;
    private int episode = 0;

    @Column(length = 64)
    @Size(min = 1, max = 64, message = "Der Name der Serie muss zwischen einem und 64 Zeichen lang sein.")
    private String seriesName = "";

    @Column(length = 64)
    @Size(max = 64, message = "Der Name der Episode darf nicht länger als 64 Zeichen lang sein.")
    private String episodeName = "";

    @Lob
    private String review;
}
