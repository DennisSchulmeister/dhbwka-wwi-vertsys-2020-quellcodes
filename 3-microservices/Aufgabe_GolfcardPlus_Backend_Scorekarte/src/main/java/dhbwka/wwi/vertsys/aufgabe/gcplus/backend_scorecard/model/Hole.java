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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Peristent Entity für ein gespieltes Loch während einem Golfspiel. Ein
 * Datensatz dieser Klasse gehört immer zu einer Scorekarte, mit der das
 * Spiel gezählt wird. Hier wird dann für jedes Loch gespeichert, wie viele
 * reguläre Schläge und wie viele Strafschläge gezählt wurden.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @ManyToOne
    private Scorecard scorecard;
    
    int holeNumber = 0;
    int par = 0;
    int regularStrokes = 0;
    int penaltyStrokes = 0;
}
