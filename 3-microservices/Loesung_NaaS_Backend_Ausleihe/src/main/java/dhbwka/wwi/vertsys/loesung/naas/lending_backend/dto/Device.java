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

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Datentransferklasse für ein ausleihbares Gerät.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Device {

    private long id = 0L;
    private String manufacturer = "";
    private String model = "";
    LocalDate buyingDate = LocalDate.now();
    private String imgUrl = "";
    private DeviceClass deviceClass = new DeviceClass();
}
