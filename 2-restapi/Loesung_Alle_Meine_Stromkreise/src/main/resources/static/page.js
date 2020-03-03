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
 * Basisklasse für eine Bildschirmseite. Diese Klasse definiert die von der
 * App-Klasse aufgerufene show()-Methode und implementiert diese derart, dass
 * der anzuzeigende Inhalt aus einer statischen HTML- und CSS-Datei mit dem
 * Namen der Seite in einem gleichnamigen Unterordnet nachgeladen wird.
 */
export default class Page {
    /**
     * Konstruktor.
     * 
     * @param {String} name Technischer Name der Seite. Wird benutzt, um die URL zum
     * Abruf der HTML/CSS-Dateien zu berechnen.
     */
    constructor(name) {
        this._name = name;
        this._html = `${name}/${name}.html`;
        this._css = `${name}/${name}.css`;
    }

    /**
     * Seite anzeigen. Wird von der App-Klasse aufgerufen.
     * 
     * @param {Matches} Object Gematchte URL-Bestandteile
     */
    async show(matches) {
        // Anzuzeigenden Seiteninhalt nachladen
        let html = await fetch(this._html);
        let css = await fetch(this._css);

        if (html.ok && css.ok) {
            html = await html.text();
            css = await css.text();
        } else {
            console.error(`Seite ${this._name}: Fehler beim Laden des HTML/CSS-Inhalts`);
            return;
        }

        // Seite zur Anzeige bringen
        let pageDom = document.createElement("div");
        pageDom.innerHTML = html;

        this._app.setPageCss(css);
        this.renderContent(matches, pageDom);
    }
    
    /**
     * Template-Methode zum Rendern des Seiteninhalts. Diese Methode wird
     * von der show()-Methode aufgerufen, um den Inhalt der Seite anzuzeigen.
     * Die Methode muss daher von den erbenden Klassen überschrieben werden.
     * Hierfür bekommt sie ein DOM-Objekt mit dem nachgeladenen HTML-Inhalt
     * der Seite übergeben. Standardmäßig wird das HTML unverändert einfach
     * angezeigt.
     * 
     * @param {Matches} Object Gematchte URL-Bestandteile
     * 
     * @param {Element} pageDom HTML-Element mit dem dynamisch nachgelandenen
     * HTML-Inhalt der Seite.
     */
    async renderContent(matches, pageDom) {
        // Muss von den Unterklassen überschrieben werden
        this._app.setPageContent(pageDom);
    }
}