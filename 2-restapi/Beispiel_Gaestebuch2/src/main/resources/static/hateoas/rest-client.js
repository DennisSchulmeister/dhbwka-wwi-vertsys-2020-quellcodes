/* 
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */

/**
 * Beispiel für eine generische Klasse zum Aufruf von HATEOAS-konformen
 * REST-Webservices, wie sie von Spring Data REST erstellt werden.
 */
class RestClient {
    /**
     * Konstruktor.
     * @param {String} url Basis-URL des REST-Webservices (optional)
     */
    constructor(url) {
        this._url = url;
        this._lastRequest = undefined;
        this._lastResponse = undefined;
        this._lastJson = undefined;
        
        this.username = "";
        this.password = "";
    }

    /**
     * Benutzername und Passwort für die Authentifizierung setzen.
     * @param {String} username Benutzername
     * @param {String} password Passwort
     */
    setAuthData(username, password) {
        this.username = username;
        this.password = password;
    }
    
    /**
     * @returns {String} Basis-URL des Webservices.
     */
    get url() {
        return this._url;
    }

    /**
     * @returns {Object} Zuletzt gesendete HTTP-Anfrage
     */
    get lastRequest() {
        return this._lastRequest;
    }
    
    /**
     * @returns {Object} Zuletzt empfangene HTTP-Antwort
     */
    get lastResponse() {
        return this._lastResponse;
    }
    
    /**
     * @returns Zuletzt empfangene JSON-Daten (deserialisiert)
     */
    get lastJson() {
        return this._lastJson;
    }
    
    /**
     * Shortcut für eine GET-Anfrage.
     * 
     * @param {Object} options Zusatzoptionen für den Aufruf, vgl. `sendHttpRequest()`
     * @returns {Promise} Ergebnis von sendHttpRequest()
     */
    get(options) {
        return this.sendHttpRequest("GET", options);
    }

    /**
     * Shortcut für eine PUT-Anfrage.
     * 
     * @param {Object} options Zusatzoptionen für den Aufruf, vgl. `sendHttpRequest()`
     * @returns {Promise} Ergebnis von sendHttpRequest()
     */
    put(options) {
        return this.sendHttpRequest("PUT", options);
    }

    /**
     * Shortcut für eine POST-Anfrage.
     * 
     * @param {Object} options Zusatzoptionen für den Aufruf, vgl. `sendHttpRequest()`
     * @returns {Promise} Ergebnis von sendHttpRequest()
     */
    post(options) {
        return this.sendHttpRequest("POST", options);
    }

    /**
     * Shortcut für eine PATCH-Anfrage.
     * 
     * @param {Object} options Zusatzoptionen für den Aufruf, vgl. `sendHttpRequest()`
     * @returns {Promise} Ergebnis von sendHttpRequest()
     */
    patch(options) {
        return this.sendHttpRequest("PATCH", options);
    }

    /**
     * Shortcut für eine DELETE-Anfrage.
     * 
     * @param {Object} options Zusatzoptionen für den Aufruf, vgl. `sendHttpRequest()`
     * @returns {Promise} Ergebnis von sendHttpRequest()
     */
    delete(options) {
        return this.sendHttpRequest("DELETE", options);
    }

    /**
     * Allgemeine Methode zum Versenden von HTTP-Anfragen an den Webserivce.
     * Beim Aufruf muss mindestens der Parameter `method` mit dem zu sendenden
     * HTTP-Verb übergeben werden. Optional kann im zweiten Parameter ein
     * Konfigurationsobjekt mit folgenden Werten übergeben werden:
     * 
     *   {
     *     data:   Im HTTP Request Body zu sendende Daten. Werden vor dem
     *             Versand automatisch nach JSON konvertiert.
     *           
     *     url:    Untergeordnete der URL, die tatsächlich aufgerufen werden
     *             soll. Beispielsweise "/4711", um das Objekt mit der ID 4711
     *             aufzurufen. Wird an die Basis-URL des Webservices angehängt.
     *           
     *     params: Array mit an die URL anzuhängenden URL-Parametern, zum
     *             Beispiel [ {par1: "wert"}, {par2: "wert"} ].
     *   }
     * 
     * Als Antwort wird ein Promise-Objekt zurückgeliefert, das zu einem
     * Objekt der folgenden Art auflöst:
     * 
     *   {
     *     response:  Response-Objekte des fetch()-Aufrufs. Hier können u.a.
     *                die empfangenen HTTP-Header und der Statuscode ausgelesen
     *                werden.
     *     
     *     data:      Deserialisierte JSON-Daten.
     *   }
     *   
     * @param {String} method Zu sendendes HTTP-Verb
     * @param {Object} options Zu sendende Daten und Optionen (optional)
     * @returns {Promise} Ergebnis des fetch()-Aufrufs mit der Antwort
     */
    async sendHttpRequest(method, options) {
        // Aufzurufende URL ermitteln
        options = options || {};
        let url = this._url;
        
        if (options.url) {
            url += options.url;
        }
        
        // Parameter an die URL anhängen
        let firstParam = true;
        
        if (options.params) {
            for (let i in options.params) {                
                let parameter = options.params[i];
                
                for (let name in parameter) {
                    if (firstParam) {
                        url += "?";
                        firstParam = false;
                    } else {
                        url += "&";
                    }
                    
                    url += encodeURI(name) + "=" + encodeURI(parameter[name]);
                }
            }
        }
        
        // Zu sendende HTTP-Anfrage zusammenstellen
        let request = {
            method: method || "GET",
            headers: {
                "accept": "application/json"
            }
        };
        
        if (this.username || this.password) {
            request.headers["authorization"] = "Basic " + btoa(this.username + ":" + this.password);
        }
        
        if (options.data) {
            request.headers["content-type"] = "application/json";
            request.body = JSON.stringify(options.data);
        }
        
        // Anfrage senden und Antwort merken
        this._lastRequest = request;
        this._lastRequest._url = options.url;
        this._lastRequest._params = options.params;
        
        this._lastResponse = await fetch(url, request);
        this._lastJson = await this._lastResponse.json();
        
        return {
            response: this._lastResponse,
            data: this._lastJson
        };
    }
        
    /**
     * Diese Methode folgt einem Link in der zuletzt empfangenen JSON-Antwort
     * und erzeugt eine neue RestClient-Instanz dafür. Hierfür kann die Methode
     * auf zwei Arten aufgerufen werden:
     * 
     *   1) let newClient = client.follow("guestbook");
     *   2) let newClient = client.follow(obj, "parent");
     * 
     * Im ersten Fall wird ein Link in der zuletzt empfangenen JSON-Antwort
     * gesucht, der hierfür im Attribut `_links` in der Antwort enthalten
     * sein muss.
     * 
     * Im zweiten Fall wird ein Link im übergebenen Objekt gesucht, der
     * hierfür im Attribut `_links` des übergebenen Objekts enthalten
     * sein muss. Dies ist für Fälle gedacht, in denen zuerst eine Collection
     * abgerufen und dann einem Link innerhalb einem der zurückgelieferten
     * Datensätze gefolgt werden soll.
     * 
     * Wurde der Link gefunden, liefert die Methode eine neue Instanz der
     * Klasse RestClient. Andernfalls liefert sie den Wert null zurück.
     * 
     * @returns {RestClient} Neue RestClient-Instanz oder null
     */
    follow() {
        // Parameter auswerten
        let object = this.lastJson;
        let link = "";
        
        if (arguments.length === 1) {
            link = arguments[0];
        } else if (arguments.length === 2) {
            object = arguments[0];
            link = arguments[1];
        }
        
        // Neue URL ermitteln
        let links = object._links || {};
        let linkDescription = links[link] || {};
        let linkUrl = linkDescription.href || "";
        
        if (linkDescription.templated) {
            linkUrl = linkUrl.split("{")[0];
        }
        
        // Neue RestClient-Instanz für die ermittelte URL zurückgeben
        if (linkUrl.length > 0) {
            let restClient = new RestClient(linkUrl);
            restClient.setAuthData(this.username, this.password);
            return restClient;
        } else {
            return null;
        }
    }
}
