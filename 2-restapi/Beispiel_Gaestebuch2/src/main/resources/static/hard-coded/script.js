/* 
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */

const API_URL = "/api/guestbook";

/**
 * Daten nach dem Laden der Seite vom Server abrufen und anzeigen. Ebenso
 * Event Handler zum Anlegen neuer Einträge registrieren.
 */
window.addEventListener("load", async () => {
    // Vorhandene Einträge abrufen und anzeigen
    let entries = await fetch(API_URL);
    entries = await entries.json();
    entries._embedded.guestbookEntries.forEach(displayEntry);
    
    // Event Handler zum Hinzufügen neuer Einträge
    let formElement = document.getElementById("form");
    formElement.addEventListener("submit", event => {
        createNewEntry(event);
    
        // Old-School Abschicken des Formulars unterdrücken
        event.preventDefault();
        return false;
    });
});

/**
 * Hilfsfunktion zum Anzeigen eines Gästebucheintrags
 * @param {type} entry
 * @returns {undefined}
 */
function displayEntry(entry) {
    let listElement = document.getElementById("entries");
    listElement.innerHTML += `<li><b>${entry.datetime}:</b> ${entry.name}`;
}

/**
 * Hilfsfunktion zum Anlegen eines neuen Eintrags.
 */
async function createNewEntry() {
    // Eingegebenen Namen ermitteln
    let formElement = document.getElementById("form");    
    let name = formElement.name.value;
    if (name.trim() === "") return;    
    formElement.name.value = "";

    // Backend-API aufrufen
    let entry = await fetch(API_URL, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({name: name})
    });
    
    // Gespeicherten Eintrag anzeigen
    displayEntry(await entry.json());
}