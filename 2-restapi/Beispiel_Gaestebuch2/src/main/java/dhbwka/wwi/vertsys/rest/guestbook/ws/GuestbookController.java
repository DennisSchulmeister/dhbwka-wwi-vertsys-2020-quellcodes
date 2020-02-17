/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 *
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 *
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.rest.guestbook.ws;

import dhbwka.wwi.vertsys.rest.guestbook.GuestbookEntry;
import dhbwka.wwi.vertsys.rest.guestbook.GuestbookEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Diese Klasse zeigt, wie ein einfacher REST-Webservice zum Lesen und Schreiben
 * von Daten aus einer Datenbank von Hand programmiert werden kann.
 *
 * Die Klasse ist bewusst unvollständig gehalten, da sie eigentlich komplett
 * überflüssig ist. Spring Data REST generiert nämlich bereits einen fertigen
 * Webservice, der nicht nur alle HTTP-Verben inkl. PATCH versteht, sondern auch
 * HATEOAS unterstützt. Es wäre daher eigentlich besser, diese Klasse einfach
 * zu löschen und die unter `/api/guestbook` aufrufbare Implementierung zu
 * verwenden. :-)
 */
@RestController
@RequestMapping("/api/guestbook-manual")
public class GuestbookController {

    // Dependency-Injection des Datenbank-Repositories
    @Autowired
    private GuestbookEntryRepository repository;

    /**
     * Handler-Methode für GET-Anfragen.
     *
     * @return Liste aller vorhandenen Einträge.
     */
    @GetMapping
    public Iterable<GuestbookEntry> getAllEntries() {
        return this.repository.findAll();
    }

    /**
     * Handler-Methode für POST-Anfragen.
     *
     * @param entry Anzulegender Eintrag
     * @return Angelegter Eintrag
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GuestbookEntry createNewEntry(@RequestBody GuestbookEntry entry) {
        entry = this.repository.save(entry);
        return entry;
    }
}
