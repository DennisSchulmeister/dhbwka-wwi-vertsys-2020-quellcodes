/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.loesung.naas.lending_backend.proxy;

import dhbwka.wwi.vertsys.loesung.naas.lending_backend.dto.Device;
import org.springframework.stereotype.Component;

/**
 * Fallback-Implementierung für den Produktkatalog, falls dieser
 * nicht erreichbar ist.
 */
@Component
public class CatalogFallback implements CatalogProxy {

    @Override
    public Device getDeviceInformation(long id) {
        Device device = new Device();
        device.setModel("Laptop oder Smartphone");
        return device;
    }

    @Override
    public void checkDeviceExists(long id) {
    }

}
