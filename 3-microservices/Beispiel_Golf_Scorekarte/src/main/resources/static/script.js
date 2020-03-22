/* 
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */

/*******************************************************************************
 * ALLGEMEINES
 *******************************************************************************/

let API = "http://localhost:8080/api";

window.addEventListener("load", showOverviewPage);

/**
 * Sichtbare Bildschirmseite umschalten.
 * @param {String} page Name der Seite
 */
function switchPage(page) {
    document.querySelectorAll(".page").forEach(p => p.classList.add("hidden"));

    let pageElement = document.querySelector(`#page-${page}`);

    if (pageElement) {
        pageElement.classList.remove("hidden");
    }
}

/**
 * Popup mit einer Sicherheitsabfrage öffnen. Der Funktion muss ein Objekt
 * mit folgendem Aufbau übergeben werden:
 * 
 *   {
 *       title: "Titel",
 *       message: "Fragetext",
 *       confifm: "Ja-Button",
 *       dismiss: "Nein-Button"
 *   }
 * 
 * @param {Object} options Anzuzeigende Texte
 * @returns {Promise} Promise-Objekt das true oder false liefert
 */
function popupToConfirm(options) {
    let popupElement = document.querySelector("#popup-to-confirm");
    let newPopupElement = popupElement.cloneNode(true);
    popupElement.parentNode.replaceChild(newPopupElement, popupElement);

    newPopupElement.querySelector(".modal-title").innerHTML = options.title || "Sicherheitsfrage";
    newPopupElement.querySelector(".modal-body").innerHTML = options.message || "";
    newPopupElement.querySelector(".btn-primary").innerHTML = options.confirm || "Ja";
    newPopupElement.querySelector(".btn-secondary").innerHTML = options.dismiss || "Nein";

    if (!options.confirm) {
        newPopupElement.querySelector(".btn-primary").classList.add("hidden");
    } else {
        newPopupElement.querySelector(".btn-primary").classList.remove("hidden");
    }

    return new Promise((resolve, reject) => {
        let popup = $('#popup-to-confirm');
        popup.modal("show");

        newPopupElement.querySelector(".btn-primary").addEventListener("click", () => {
            popup.modal("hide");
            resolve(true);
        });

        newPopupElement.querySelector(".btn-secondary").addEventListener("click", () => {
            popup.modal("hide");
            resolve(false);
        });

        newPopupElement.querySelector("button.close").addEventListener("click", () => {
            popup.modal("hide");
            resolve(false);
        });
    });
}

/*******************************************************************************
 * ÜBERSICHTSSEITE
 *******************************************************************************/

/**
 * Startseite aufrufen.
 */
async function showOverviewPage() {
    // Scorekarten abrufen
    let result = await fetch(`${API}/scorecards`);
    let json = await result.json();

    // Scorekarten anzeigen
    let pageElement = document.querySelector("#page-overview");
    let scorecardsElement = pageElement.querySelector(".scorecards");
    scorecardsElement.innerHTML = "";

    json._embedded.scorecards.forEach(scorecard => {
        let gameDateTime = new Date(scorecard.gameDateTime).toLocaleString("de-DE", {
            year: "numeric",
            month: "long",
            day: "numeric",
            hour: "2-digit",
            minute: "2-digit",
        });

        let cardElement = document.createElement("div");
        cardElement.classList.add("card");
        cardElement.dataset.scorecard = scorecard.id;
        cardElement.dataset.golfCourse = scorecard.golfCourse;
        cardElement.dataset.gameDateTime = gameDateTime;
        scorecardsElement.appendChild(cardElement);

        cardElement.innerHTML = `
            <!-- https://pixabay.com/de/photos/kugel-fairway-golf-golfball-gras-1850984/ -->
            <img src="cardheader.jpg" class="card-img-top">
            <div class="card-body">
                <h5 class="card-title">${scorecard.golfCourse}</h5>
                <p class="card-text">
                    ${gameDateTime}, <span class="inactive count-holes">-- Loch</span>
                </p>

                <div class="numbers d-flex justify-content-between align-items-stretch flex-wrap align-content-start">
                    <div class="big-number flex-grow-1 mb-4 regular-strokes inactive">
                        <div class="bn-value">
                            ---
                        </div>
                        <div class="bn-label">
                            Schläge
                        </div>
                    </div>

                    <div class="big-number flex-grow-1 mb-4 penalty-strokes inactive">
                        <div class="bn-value">
                            ---
                        </div>
                        <div class="bn-label">
                            Strafschläge
                        </div>
                    </div>

                    <div class="big-number flex-grow-1 mb-4 total-strokes inactive">
                        <div class="bn-value">
                            ---
                        </div>
                        <div class="bn-label">
                            Gesamt
                        </div>
                    </div>

                    <div class="big-number flex-grow-1 mb-4 stableford inactive">
                        <div class="bn-value">
                            ---
                        </div>
                        <div class="bn-label">
                            Stabelford
                        </div>
                    </div>
                </div>

                <a href="#" class="card-link details">Details</a>
                <a href="#" class="card-link delete">Löschen</a>
            </div>
        `;

        let detailsLink = cardElement.querySelector("a.details");
        let deleteLink = cardElement.querySelector("a.delete");

        detailsLink.addEventListener("click", () => showScorecard(scorecard.id));
        deleteLink.addEventListener("click", () => deleteScorecard(scorecard.id));

        let countHolesElement = cardElement.querySelector(".count-holes");
        let numbersElement = cardElement.querySelector(".numbers");

        fetch(`${API}/scoring/${scorecard.id}`).then(result => {
            return result.json();
        }).then(scoring => {
            countHolesElement.classList.remove("inactive");
            countHolesElement.textContent = `${scoring.countHoles} Loch`;

            numbersElement.querySelectorAll(".inactive").forEach(e => e.classList.remove("inactive"));
            numbersElement.querySelector(".regular-strokes .bn-value").innerHTML = scoring.totalRegularStrokes;
            numbersElement.querySelector(".penalty-strokes .bn-value").innerHTML = scoring.totalPenaltyStrokes;
            numbersElement.querySelector(".total-strokes .bn-value").innerHTML = scoring.totalStrokes;
            numbersElement.querySelector(".stableford .bn-value").innerHTML = scoring.stableford;
        });
    });

    switchPage("overview");

    // Event Handler zum Anlegen einer neuen Scorekarte registrieren
    pageElement.querySelector("button.new-scorecard").addEventListener("click", createScorecard);
}

/**
 * Löschen einer Scorekarte mit Sicherheitsabfrage.
 * @param {number} id ID der zu löschenden Scorekarte
 */
async function deleteScorecard(id) {
    let cardElement = document.querySelector(`.card[data-scorecard="${id}"`);
    let golfCourse = cardElement.dataset.golfCourse;
    let gameDateTime = cardElement.dataset.gameDateTime;

    let result = await popupToConfirm({
        title: `${golfCourse}<br><small>${gameDateTime}</small>`,
        message: "Wollen Sie die ausgewählte Scorekarte wirklich löschen?",
        confirm: "Löschen",
        dismiss: "Nicht löschen"
    });

    if (!result) {
        return;
    }

    fetch(`${API}/scorecards/${id}`, {method: "DELETE"});
    cardElement.parentNode.removeChild(cardElement);
}

/*******************************************************************************
 * ANLEGEN UND BEARBEITEN EINER SCOREKARTE
 *******************************************************************************/

/**
 * Anlegen einer neuen Scorekarte.
 */
function createScorecard() {
    showScorecard(-1);
}

/**
 * Anlegen einer neuen Scorekarte oder vorhandene Scorekarte anzeigen.
 * @param {number} id ID der nazuzeigenden Scorekarte oder -1 für eine neue
 */
async function showScorecard(id) {
    // Details zur ausgewählten Scorekarte abrufen
    let date = new Date();
    let currentDate = date.toISOString().split("T")[0];
    let currentTime = date.toISOString().split("T")[1].slice(0, 5);

    let scorecard = {
        gameDateTime: `${currentDate}T${currentTime}`,
        golfCourse: ""
    };

    let holes = {
        _embedded: {
            holes: []
        }
    };

    if (id > 0) {
        scorecard = fetch(`${API}/scorecards/${id}`).then(result => scorecard = result);
        holes = fetch(`${API}/scorecards/${id}/holes`).then(result => holes = result);
        await Promise.all([scorecard, holes]);

        scorecard = scorecard.json().then(json => scorecard = json);
        holes = holes.json().then(json => holes = json);
        await Promise.all([scorecard, holes]);
    }

    // Kopfdaten anzeigen
    let pageElement = document.querySelector("#page-scorecard");
    let scorecardForm = pageElement.querySelector("#scorecard-form");
    scorecardForm.dataset.scorecard = id;

    scorecardForm.dataset.status = id > 0 ? "saved" : "new";
    scorecardForm.elements.course.value = scorecard.golfCourse;
    scorecardForm.elements.date.value = scorecard.gameDateTime.split("T")[0]
    scorecardForm.elements.time.value = scorecard.gameDateTime.split("T")[1].slice(0, 5);

    if (id > 0) {
        scorecardForm.elements.course.setAttribute("readonly", "readonly");
        scorecardForm.elements.date.setAttribute("readonly", "readonly");
        scorecardForm.elements.time.setAttribute("readonly", "readonly");
    } else {
        scorecardForm.elements.course.removeAttribute("readonly");
        scorecardForm.elements.date.removeAttribute("readonly");
        scorecardForm.elements.time.removeAttribute("readonly");
    }

    // Tabelle der gespielten Löcher anzeigen
    let holesTable = pageElement.querySelector("table.holes");
    pageElement.querySelectorAll("table.holes > tr").forEach(tr => tr.parentNode.removeChild(tr));
    
    holes._embedded.holes.forEach(hole => {
        let trElement = createHoleTrElement(hole, "saved");
        holesTable.appendChild(trElement);
    });

    // Link zum Einfügen weiterer Löcher zeigen
    updateInsertHoleLinks();
    
    pageElement.querySelector("a.first").addEventListener("click", appendInsertHoleLine);
    pageElement.querySelector("a.next").addEventListener("click", appendInsertHoleLine);

    // Sonstige Event Handler registrieren und Seite anzeigen
    pageElement.querySelector("button.back").addEventListener("click", leaveScorecard);
    switchPage("scorecard");
}

/**
 * Neue Zeile für die Tabelle der gespielten Löcher erzeugen. Werden der Methode
 * die Daten eines bereits gespeicherten Lochs übergeben, werden diese als
 * einfache Tabellenfelder angezeigt. Werden keine Daten übergeben, erzeugt
 * die Methode ein Eingabeformular zum Anlegen eines neuen Eintrags.
 * 
 * @param {Object} hole Vom Webservice empfangene Daten zu einem Loch
 * @param {Straing} status "saved", "changed" oder "new", je nach Status der Daten
 * @returns {DOMElement} Neues tr-Element
 */
function createHoleTrElement(hole, status) {
    hole = hole || {};
    status = status || "new";
    
    let trElement = document.createElement("tr");
    trElement.dataset.hole = hole.id || 0;
    trElement.dataset.status = status;
    trElement.dataset.number = hole.holeNumber || 0;
    
    if (status === "saved") {
        trElement.innerHTML = `
            <td>
                <a href="#" class="hole">${hole.holeNumber}</a>
            </td>
            <td>
                ${hole.par}
            </td>
            <td>
                ${hole.regularStrokes}
            </td>
            <td>
                ${hole.penaltyStrokes}
            </td>
            <td>
                <button type="button" class="close delete">
                    <span aria-hidden="true">&times;</span>
                </button>
            </td>
        `;
    } else {
        let holeNumber = hole.holeNumber || "";
        let par = hole.par || "";
        let regularStrokes = hole.regularStrokes || "";
        let penaltyStrokes = hole.penaltyStrokes || "";
                
        trElement.innerHTML = `
            <td>
                <input type="number" name="number" value=${holeNumber}>
            </td>
            <td>
                <input type="number" name="par" value=${par}>
            </td>
            <td>
                <input type="number" name="strokes" value=${regularStrokes}>
            </td>
            <td>
                <input type="number" name="penalty" value=${penaltyStrokes}>
            </td>
            <td>
                <button type="button" class="close delete">
                    <span aria-hidden="true">&times;</span>
                </button>
            </td>
        `;
    }

    let holeLink = trElement.querySelector("a.hole");
    let removeButton = trElement.querySelector("button.delete");
    
    if (holeLink) {
        holeLink.addEventListener("click", async () => {
            if (await saveScorecard()) {
                let newTrElement = createHoleTrElement(hole, "changed");
                trElement.parentNode.replaceChild(newTrElement, trElement);
                updateInsertHoleLinks();
            }
        });
    }
    
    if (removeButton) {
        removeButton.addEventListener("click", async () => {
            let title = "Neues Loch";
            
            if (status !== "new") {
                title = `Loch ${hole.holeNumber}`;
            }
            
            let confirm = await popupToConfirm({
                title: title,
                message: "Wollen Sie das Loch wirklich aus der Scorekarte entfernen?",
                confirm: "Löschen",
                dismiss: "Nicht löschen"
            });
            
            if (!confirm) {
                return;
            }
            
            if (trElement.dataset.status !== "new") {
                fetch(`${API}/holes/${trElement.dataset.hole}`, {method: "DELETE"});
            }
            
            trElement.parentNode.removeChild(trElement);
        });
    }
    
    return trElement;
}

/**
 * Aktualisieren der Links zum Anlegen eines weiteren Lochs je nach Anzahl
 * der bereits vorhandenen Datensätze.
 */
function updateInsertHoleLinks() {
    let pageElement = document.querySelector("#page-scorecard");
    let countHoles = pageElement.querySelectorAll("table.holes > tr").length;
    
    if (countHoles < 1) {
        pageElement.querySelector("a.first").classList.remove("hidden");
        pageElement.querySelector("a.next").classList.add("hidden");
    } else {
        pageElement.querySelector("a.first").classList.add("hidden");
        pageElement.querySelector("a.next").classList.remove("hidden");
    }
}

/**
 * Änderungen sichern und eine neue Zeile für das nächste Loch anzeigen.
 */
async function appendInsertHoleLine() {
    if (await saveScorecard()) {
        // Größtes bereits gespieltes Loch ermitteln
        let nextHoleNumber = 0;
        let pageElement = document.querySelector("#page-scorecard");
        
        pageElement.querySelectorAll("table.holes tr[data-number]").forEach(tr => {
            if (parseInt(tr.dataset.number) > nextHoleNumber) {
                nextHoleNumber = parseInt(tr.dataset.number);
            }
        });
        
        nextHoleNumber++;
        
        // Zeile für das nächste Loch einfügen
        let holesTable = pageElement.querySelector("table.holes");
        
        let trElement = createHoleTrElement({holeNumber: nextHoleNumber}, "new");
        holesTable.appendChild(trElement);
        updateInsertHoleLinks();
    }
}

/**
 * Ungesicherte Änderungen an der Scorekarte übernehmen.
 * @returns {boolean} False, bei fehlerhaften Daten
 */
async function saveScorecard() {
    // Kopfdaten sichern
    let pageElement = document.querySelector("#page-scorecard");
    let scorecardForm = pageElement.querySelector("#scorecard-form");
    let scorecardId = scorecardForm.dataset.scorecard;

    if (scorecardForm.dataset.status === "new") {
        if (!scorecardForm.elements.course.value
                || !scorecardForm.elements.date.value
                || !scorecardForm.elements.time.value) {
            popupToConfirm({
                title: "Fehlerhafte Eingaben",
                message: "Füllen Sie erst alle Eingabefelder aus.",
                dismiss: "Okay"
            });

            return false;
        }

        let scorecard = {
            id: scorecardForm.dataset.scorecard,
            golfCourse: scorecardForm.elements.course.value,
            gameDateTime: `${scorecardForm.elements.date.value}T${scorecardForm.elements.time.value}`
        };

        let result = await fetch(`${API}/scorecards`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(scorecard)
        });
        
        scorecard = await result.json();
        
        scorecardForm.dataset.status = "saved";
        scorecardForm.dataset.scorecard = scorecard.id;
        scorecardForm.elements.course.setAttribute("readonly", "readonly");
        scorecardForm.elements.date.setAttribute("readonly", "readonly");
        scorecardForm.elements.time.setAttribute("readonly", "readonly");
    }

    // Neue und bearbeitete Löcher sichern
    holesTable = pageElement.querySelector("table.holes");
    holesTrCollection = holesTable.querySelectorAll("tr[data-status='new'], tr[data-status='changed']")

    for (let i = 0; i < holesTrCollection.length; i++) {
        let trElement = holesTrCollection.item(i);
        
        let holeNumber = trElement.querySelector("input[name='number'").value;
        let par = trElement.querySelector("input[name='par'").value;
        let regularStrokes = trElement.querySelector("input[name='strokes'").value;
        let penaltyStrokes = trElement.querySelector("input[name='penalty'").value;

        if (holeNumber < 1 || par < 1 || regularStrokes < 1) {
            popupToConfirm({
                title: "Fehlerhafte Eingaben",
                message: "Füllen Sie erst alle Eingabefelder aus.",
                dismiss: "Okay"
            });

            return false;
        }

        hole = {
            id: trElement.dataset.hole,
            scorecard: `${API}/scorecards/${scorecardId}`,
            holeNumber: holeNumber,
            par: par,
            regularStrokes: regularStrokes,
            penaltyStrokes: penaltyStrokes
        };

        if (trElement.dataset.hole < 1) {
            let result = await fetch(`${API}/holes`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(hole)
            });
            
            hole = await result.json();
        } else {
            let result = await fetch(`${API}/holes/${trElement.dataset.hole}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(hole)
            });
            
            hole = await result.json();
        }
        
        let newTrElement = createHoleTrElement(hole, "saved");
        trElement.parentNode.replaceChild(newTrElement, trElement);
        updateInsertHoleLinks();
    }

    return true;
}

/**
 * Zuletzt eingegebenes Loch speichern und Scorekarte dann verlassen.
 */
async function leaveScorecard() {
    if (await saveScorecard()) {
        showOverviewPage();
    }
}