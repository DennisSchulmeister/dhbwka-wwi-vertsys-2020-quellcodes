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
 * Klasse PageEpisodes: Anzeige aller Folgen einer Serie
 */
export default class PageEpisodes extends Page {
    /**
     * Konstruktor
     * @param {App} app Zentrale Instanz der App-Klasse
     */
    constructor(app) {
        super("page_episodes");
        this._app = app;
    }

    /**
     * Episoden vom Backend abrufen und anzeigen.
     */
    async renderContent(matches, pageDom) {
        // Informationen zur Serie abrufen
        await this._app.api.get();

        let seriesesApi = this._app.api.follow("serieses");
        let seriesesResult = await seriesesApi.get({url: `/${matches[2]}`});

        if (!seriesesResult.response.ok) {
            return;
        }

        let episodesApi = seriesesApi.follow("episodes");
        let episodesResult = await episodesApi.get();

        if (!episodesResult.response.ok) {
            return;
        }

        // Allgemeine Seriendatenanzeigen
        let mainElement = pageDom.querySelector("main");

        mainElement.innerHTML = mainElement.innerHTML.replace("$STREAMING_SERVICE_ID$", matches[1]);
        mainElement.innerHTML = mainElement.innerHTML.replace("$SERIES_NAME$", seriesesResult.data.name);
        mainElement.innerHTML = mainElement.innerHTML.replace("$SERIES_DESCRIPTION$", seriesesResult.data.description);
        mainElement.innerHTML = mainElement.innerHTML.replace("$SERIES_COUNTRY$", seriesesResult.data.country);
        mainElement.innerHTML = mainElement.innerHTML.replace("$SERIES_MAX_SEASONS$", seriesesResult.data.maxSeasons);
        mainElement.innerHTML = mainElement.innerHTML.replace("$SERIES_YEAR_FROM$", seriesesResult.data.yearFrom);
        mainElement.innerHTML = mainElement.innerHTML.replace("$SERIES_YEAR_TO$", seriesesResult.data.yearTo);
        mainElement.innerHTML = mainElement.innerHTML.replace("$SERIES_STATUS$", seriesesResult.data.inProduction ? "Aktiv" : "Eingestellt");

        let seasonSelector = mainElement.querySelector("#select-season");
        let optionTemplate = pageDom.querySelector("#template-season-option");
        let episodeTemplate = pageDom.querySelector("#template-episode");
        let episodeList = mainElement.querySelector("#episodes .list");

        // Episodenliste anzeigen
        episodesResult.data._embedded.episodes.forEach(episode => {
            let html = episodeTemplate.innerHTML;
            html = html.replace(/\$EPISODE_SEASON\$/g, episode.season);
            html = html.replace(/\$EPISODE_NUMBER\$/g, episode.number);
            html = html.replace(/\$EPISODE_NAME\$/g, episode.name);
            episodeList.innerHTML += html;
        });

        // Auswahlelement zum Wechseln der Staffel anzeigen        
        for (let i = 1; i <= seriesesResult.data.maxSeasons; i++) {
            let html = optionTemplate.innerHTML;
            html = html.replace(/\$SEASON_NUMBER\$/g, i);
            seasonSelector.innerHTML += html;
        }

        seasonSelector.addEventListener("change", event => this._onNewSeasonSelected(event.target.value));

        this._app.setPageContent(mainElement);
        this._onNewSeasonSelected(1);

        // Bilder nachladen
        let img = document.querySelector(".series-poster");

        if (img) {
            let url = await this._app.omdb.getPosterUrl(seriesesResult.data.imdbId);

            if (url != "N/A") {
                img.src = url;
            }
        }

        episodesResult.data._embedded.episodes.forEach(async episode => {
            let img = document.querySelector(`.episode[data-season="${episode.season}"][data-number="${episode.number}"] img`);

            if (img) {
                let url = await this._app.omdb.getPosterUrl(episode.imdbId);

                if (url != "N/A") {
                    img.src = url;
                }
            }
        });
    }

    /**
     * Umschalten der angezeigten Staffel.
     */
    _onNewSeasonSelected(season) {
        document.querySelectorAll(".episode").forEach(e => e.classList.add("hidden"));
        document.querySelectorAll(`.episode[data-season="${season}"`).forEach(e => e.classList.remove("hidden"));
    }
}