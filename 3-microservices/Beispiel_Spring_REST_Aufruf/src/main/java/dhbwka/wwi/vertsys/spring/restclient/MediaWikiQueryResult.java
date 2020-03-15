/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.restclient;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Datentransferklasse für die Webservice-Antwort bei einer query-Anfrage an den
 * Wikipedia-Webservice. Die Klasse beinhaltet die wichtigsten Attribute zum
 * Auslesen der Properties "description" und "coordinates". Sollen noch weitere
 * Properties unterstützt werden, muss ihre Antwortstruktur hier entsprechend
 * ergänzt werden.
 *
 * Siehe zum Beispiel: https://de.wikipedia.org/wiki/Spezial:ApiSandbox
 * #action=query&format=json &uselang=de&prop=description%7Ccoordinates
 * &titles=Duale_Hochschule_Baden-W%C3%BCrttemberg_Karlsruhe
 * &utf8=1&formatversion=2
 * 
 * Der Einfachheit halber wird die Antwortstruktur hier mit geschachtelten,
 * inneren Klassen nachgebildet, für die mit @Data automatisch Setter und
 * Getter generiert werden.
 */
@Data
@NoArgsConstructor
public class MediaWikiQueryResult {

    private boolean batchcomplete = false;
    private Query query = new Query();

    @Data
    @NoArgsConstructor
    public static class Query {

        private List<Normalized> normalized = new ArrayList<>();
        private List<Page> pages = new ArrayList<>();

        @Data
        @NoArgsConstructor
        public static class Normalized {

            private boolean formencoded = false;
            private String from = "";
            private String to = "";
        }

        @Data
        @NoArgsConstructor
        public static class Page {

            private int pageid = -1;
            private int n = -1;
            private String title = "";
            private String description = "";
            private String descriptionsource = "";
            private List<Coordinate> coordinates = new ArrayList<>();

            @Data
            @NoArgsConstructor
            public static class Coordinate {

                private double lat = 0.0;
                private double lon = 0.0;
                private boolean primary = false;
                private String globe = "";
            }
        }
    }

}
