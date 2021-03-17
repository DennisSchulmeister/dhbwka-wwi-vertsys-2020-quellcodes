/* 
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */

import Page from "../page.js";

/**
 * Klasse PageSerieses: Auswahl einer Serie
 */
export default class PageSerieses extends Page {
    /**
     * Konstruktor
     * @param {App} app Zentrale Instanz der App-Klasse
     */
    constructor(app) {
        super("page_serieses");
        this._app = app;
    }

    /**
     * Serien vom Backend abrufen und anzeigen.
     */
    async renderContent(matches, pageDom) {
        // TODO: Namen des Streamingdiensts abrufen und anzeigen
       
        // TODO: Serien des Streamingdienstes abrufen und anzeigen

    }
}