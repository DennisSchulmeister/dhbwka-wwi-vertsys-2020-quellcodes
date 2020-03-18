/* 
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
"use strict";

import {Wizard, WizardPage} from "./wizard.js";
import {WizardPageSearch} from "./pages/search.js";
import {WizardPageDevice} from "./pages/device.js";
import {WizardPageLend} from "./pages/lend.js";
import {WizardPageFinish} from "./pages/finish.js";

window.addEventListener("load", async () => {
    // Basis-URL des API-Gateways abrufen
    let response = await fetch("/api/gateway");
    let gatewayURL = await response.text();

    let urls = {
        catalog: "api/catalog",
        devices: "api/catalog/devices",
        deviceClasses: "api/catalog/device_classes",
        lending: "api/lending",
        lendingRequests: "api/lending/lending_requests",
        deviceExists: "api/lending/device/exists",
        deviceAvailable: "api/lending/device/available",
        deviceLend: "api/lending/device/lend"
    };

    // Neuladen der Seite bei Kilck auf einen der Submit-Buttons unterbinden
    document.querySelectorAll("form").forEach(form => {
        form.addEventListener("submit", event => event.preventDefault());
    });

    // Wizard-Ablauf starten
    let carouselElement = $("#main-carousel");

    let wizard = new Wizard(carouselElement, {
        search: WizardPageSearch,
        device: WizardPageDevice,
        lend: WizardPageLend,
        finish: WizardPageFinish
    }, {
        // Hilfsmethode zum Ermitteln einer Webserivce-URL.
        // Parameter ist der Schlüssel im urls-Objekt oben.
        api: a => gatewayURL + urls[a]
    });

    wizard.gotoNextPage("search");
});