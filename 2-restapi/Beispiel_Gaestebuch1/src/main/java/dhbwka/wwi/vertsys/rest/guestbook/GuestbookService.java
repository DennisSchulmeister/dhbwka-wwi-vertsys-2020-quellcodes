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

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Einfache Service-Klasse zur Verwaltung der Gästebucheinträge. Man
 * beachte, dass diese Klasse die Gästebucheinträge nur temporär speichert,
 * da wir uns Datenbankzugriffe noch nicht angeschaut haben.
 * 
 * Die Annotation @Service kennzeichnet die Klasse als eine Serviceklasse,
 * also eine Klasse mit Geschäftslogik.
 */
@Service
public class GuestbookService {
    
    private static final List<GuestbookEntry> ENTRIES = new ArrayList<>();
    
    /**
     * Hinzufügen eines neuen Eintrags
     * @param entry Zu speichernder Eintrag
     */
    public void addEntry(GuestbookEntry entry) {
        ENTRIES.add(entry);
    }
    
    /**
     * Auslesen einer Liste aller EInträge
     * @return Liste aller gespeicherten Einträge
     */
    public List<GuestbookEntry> getEntries() {
        return ENTRIES;
    }
    
}
