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
 * Klasse PageStreamingServices: Auswahl eines Streamin-Dienstes.
 */
export default class PageStreamingServices extends Page {
    /**
     * Konstruktor
     * @param {App} app Zentrale Instanz der App-Klasse
     */
    constructor(app) {
        super("page_streaming_services");
        this._app = app;
    }

    /**
     * Streamingdienste vom Backend abrufen und anzeigen.
     */
    async renderContent(matches, pageDom) {
        // Streamingdienste vom Backend abrufen
        await this._app.api.get();
        let servicesApi = this._app.api.follow("streamingServices");
        let result = await servicesApi.get();

        if (!result.response.ok) {
            return;
        }

        // Empfangende Daten anzeigen
        let mainElement = pageDom.querySelector("main");
        let listElement = mainElement.querySelector(".list");
        let itemElement = pageDom.querySelector("#template-item");

        result.data._embedded.streamingServices.forEach(streamingService => {
            let url = streamingService._links.self.href.split("/");

            let html = itemElement.innerHTML;
            html = html.replace("$ID$", url[url.length - 1]);
            html = html.replace("$NAME$", streamingService.name);

            listElement.innerHTML += html;
        });

        this._app.setPageContent(mainElement);
    }
}