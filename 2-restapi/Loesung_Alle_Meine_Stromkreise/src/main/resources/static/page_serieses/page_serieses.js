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
        // Informationen zum Streamingdienst abrufen
        await this._app.api.get();
        
        let servicesApi = this._app.api.follow("streamingServices");
        let servicesResult = await servicesApi.get({url: `/${matches[1]}`});
        
        if (!servicesResult.response.ok) {
            return;
        }
       
        // Serien des Streamingdienstes abrufen
        let seriesesApi = servicesApi.follow("serieses");
        let seriesesResult = await seriesesApi.get();

        if (!seriesesResult.response.ok) {
            return;
        }
                
        // Empfangene Daten anzeigen
        let serviceUrl = servicesResult.data._links.self.href.split("/");
        servicesResult.data.id = serviceUrl[serviceUrl.length - 1];
                
        let mainElement = pageDom.querySelector("main");        
        mainElement.innerHTML = mainElement.innerHTML.replace("$STREAMING_SERVICE_NAME$", servicesResult.data.name);

        let listElement = mainElement.querySelector(".list");
        let itemElement = pageDom.querySelector("#template-item");
        
        seriesesResult.data._embedded.serieses.forEach(series => {
            let seriesUrl = series._links.self.href.split("/");
            series.id = seriesUrl[seriesUrl.length - 1];

            let html = itemElement.innerHTML;
            html = html.replace(/\$STREAMING_SERVICE_ID\$/g, servicesResult.data.id);
            html = html.replace(/\$SERIES_ID\$/g, series.id);
            html = html.replace(/\$NAME\$/g, series.name);

            listElement.innerHTML += html;
        });
        
        this._app.setPageContent(mainElement);

        // Bilder nachladen
        seriesesResult.data._embedded.serieses.forEach(async series => {
            let img = document.querySelector(`img[data-series-id="${series.id}"]`);
            
            if (img) {
                let url = await this._app.omdb.getPosterUrl(series.imdbId);
                
                if (url != "N/A") {
                    img.src = url;
                }
            }
        });
    }
}