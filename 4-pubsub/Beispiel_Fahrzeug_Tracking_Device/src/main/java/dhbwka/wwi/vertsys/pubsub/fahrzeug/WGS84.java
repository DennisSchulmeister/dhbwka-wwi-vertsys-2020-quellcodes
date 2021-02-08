/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.pubsub.fahrzeug;

/**
 * Eine Koordinate nach WGS84-Standard. Vgl.
 * https://de.wikipedia.org/wiki/World_Geodetic_System_1984
 */
public class WGS84 {

    public double latitude = 0;
    public double longitude = 0;

    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public WGS84() {
    }
    
    public WGS84(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Hilfsmethoden">
    /**
     * @return String-Version zum Debuggen
     */
    @Override
    public String toString() {
        return "" + this.latitude + ", " + this.longitude;
    }
    
    /**
     * Berechnung der ungefähren Entfernung zwischen zwei Punkten in Kilometern
     * anhand der Haversine-Formel. Die Entfernung entspricht der angenäherten
     * Luftlinie zwischen den beiden Punkten.
     *
     * Diese Funktion wurde hier geklaut: https://stackoverflow.com/a/12600225
     * Vgl. https://en.wikipedia.org/wiki/Haversine_formula
     *
     * @param x1 Koordinate 1
     * @param x2 Koordinate 2
     * @return Ungefähre Entfernung in km (Luftlinie)
     */
    public static double distanceInKm(WGS84 x1, WGS84 x2) {
        double latDistance = Math.toRadians(x1.latitude - x2.latitude);
        double lngDistance = Math.toRadians(x1.longitude - x2.longitude);
        
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(x1.latitude)) * Math.cos(Math.toRadians(x2.latitude))
                * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);
        
        double c = 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        
        // Ungefährer Erdradius: 6371km
        return 6371.0 * c;
    }
    
    /**
     * Ausgehend von Koordinate x1 eine neue Koordinate x3 berechnen, die
     * km Kilometer in Richtung Koordinate x2 liegt.
     *
     * Der Einfachheit halber wird der neue Punkt linear interpoliert, auch wenn
     * das eigentlich nicht korrekt ist, da WGS84-Koordinaten von einer
     * spherischen Erdkugel ausgehen. Da wir aber nur mit kurzen Distanzen
     * rechnen, ist die Abweichung klein genug, um uns hier nicht zu stören.
     *
     * Vgl.
     * https://math.stackexchange.com/questions/601453/interpolating-gps-coordinates
     * für eine Erklärung, warum eine lineare Formel hier genügt.
     *
     * @param x1 Ausgangskoordinate
     * @param x2 Zielkoordinate
     * @param km Zurückzulegende Kilometer
     * @return Dazwischen liegende Koordinate
     */
    public static WGS84 moveTowards(WGS84 x1, WGS84 x2, double km) {
        double factor = km / distanceInKm(x1, x2);
        
        WGS84 x3 = new WGS84();
        x3.latitude = x1.latitude + factor * (x2.latitude - x1.latitude);
        x3.longitude = x1.longitude + factor * (x2.longitude - x1.longitude);
        return x3;
    }
    //</editor-fold>

}
