/* 
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */

import RestClient from "./rest-client.js";

/**
 * Hilfsklasse zum Abruf von Poster-URLs mit dem OMDB-Webservice. Diese
 * Klasse wurde geschrieben, um die Logik zur Nutzung des OMDB-Webservices
 * an einer zentralen Stelle zu kappseln. Denn eigentlich nutzen wir den
 * Service nur als Workaround, um eine URL für ein Vorschaubild zu einer Serie
 * oder Folge zu bekommen. 
 */
export default class OmdbWrapper {
    /**
     * Konstruktor.
     * 
     * @param {type} url URL des OMDB-Webservices
     * @param {type} apikey API-Key des OMDB-Webservices
     */
    constructor(url, apikey) {
        this._restClient = new RestClient(url);
        this._apikey = apikey;
    }

    /**
     * Ermitteln der Poster URL für eine Serie oder Episode. Diese kann
     * verwendet werden, um ein passendes Vorschaubild anzuzeigen. Hierfür
     * wird der OMDB-Webservice aufgerufen, der auf den IMDB-Daten basiert.
     * 
     * @param {type} imdbId IMDB ID der gesuchten Serie oder Folge
     * @returns {String} Promise mit der Poster URL
     */
    async getPosterUrl(imdbId) {
        let result = await this._restClient.get({
            params: [
                {apikey: this._apikey},
                {i: imdbId}
            ]
        });
        
        return result.data.Poster || "";
    }
}