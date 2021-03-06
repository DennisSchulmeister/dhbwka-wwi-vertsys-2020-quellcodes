/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.aufgabe.gcplus.backend_scorecard.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Persistent Entity für eine einfache Scorekarte beim Golfsport. Die Scorekarte
 * speichert Ort, Zeit und Datum des gezählten Spiels. Zu jedem gespielten Loch
 * wird in einer abhängigen Entity die Anzahl der benötigten Schläge notiert.
 *
 * Vgl. https://de.wikipedia.org/wiki/Stableford
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Scorecard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;
    
    LocalDateTime gameDateTime = LocalDateTime.now();
    
    long golfCourseId = 0;
    String golfCourse = "";
    
    @OneToMany(mappedBy = "scorecard", cascade = CascadeType.ALL)
    List<Hole> holes = new ArrayList<>();
    
}
