/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.loesung.naas.lending_backend.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Datentransferklasse für eine Gerätekategorie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceClass {

    private long id = 0L;
    private String name = "";
    private List<Device> devices = new ArrayList<>();
}
