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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity-Klasse für einen Streamingdienst. Dies ist die oberste Entität, von
 * der alle anderen abhängen. Die Idee ist: Ein Streamingdienst bietet Serien
 * an. Eine Serie besitzt mehrere Folgen, die einer Staffel angehören.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StreamingService implements Serializable {

    @Id
    @Column(length = 20)
    @Size(min = 1, max = 20, message = "Die ID muss zwischen einem und 20 Zeichen lang sein.")
    @NotNull(message = "Die ID darf nicht leer sein.")
    private String id = "";

    @Column(length = 64)
    @Size(min = 1, max = 64, message = "Der Name muss zwischen einem und 64 Zeichen lang sein.")
    @NotNull(message = "Der Name darf nicht leer sein.")
    private String name = "";

    @OneToMany(mappedBy = "streamingService", cascade = {CascadeType.REMOVE})
    List<Series> serieses = new ArrayList<>();

}
