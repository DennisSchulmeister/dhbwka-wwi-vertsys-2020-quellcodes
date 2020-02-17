/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.rest.guestbook;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST-Controller unseres einfachen Webservices.
 */
@RestController
@RequestMapping("/api/guestbook")
public class GuestbookController {

    // Dependency-Injection der Service-Klasse
    @Autowired
    private GuestbookService guestbookService;

    /**
     * Handler-Methode für GET-Anfragen.
     * @return Liste aller vorhandenen Einträge.
     */
    @GetMapping
    public List<GuestbookEntry> getAllEntries() {
        return this.guestbookService.getEntries();
    }

    /**
     * Handler-Methode für POST-Anfragen.
     *
     * @param request Anzulegender Eintrag
     * @return Angelegter Eintrag
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GuestbookEntry createNewEntry(@RequestBody CreateRequest request) {
        // Neuen Eintrag speichern
        GuestbookEntry entry = new GuestbookEntry();
        entry.setName(request.name);
        this.guestbookService.addEntry(entry);

        // Gespeicherte Daten zurück geben
        return entry;
    }

    /**
     * Hilfsklasse zur Deserialisierung der JSON-Anfrage zur Anlage eines
     * Eintrags.
     */
    public static class CreateRequest {

        public String name = "";
    }
}
