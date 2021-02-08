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

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Simulation eines Fahrzeugs, das munter durch die Gegend fährt. 🚗
 *
 * Auch wenn die Berechnungen auf den ersten Blick undurchschaubar erscheinen,
 * handelt es sich hierbei um ein stark vereinfachtes Modell eins Fahrzeugs:
 *
 * # Das Auto fährt immer vor vorwärts, niemals rückwärts :-)
 *
 * # Das Auto fährt die vorgegebene Wegstrecke ständig im Kreis. Es besitzt
 * dabei unendlich viel Kraftstoff und ist absolut umweltfreundlich. 🌲
 *
 * # Das Auto besitzt "maxGears" Vorwärtsgänge.
 *
 * # Gang 0 entspricht dem Leerlauf bzw. der Parkposition.
 *
 * # Das Auto besitzt eine quadratische Beschleunigung, die um den Faktor
 * "acceleration" verstärkt oder gedämpft wird.
 *
 * # Ebenso bremst das Auto quadratisch, was durch den Faktor "deceleration"
 * verstärkt oder gedämpft wird.
 *
 * # Das Auto versucht ständig, eine bestimmte Zielgeschwindigkeit zu erreichen,
 * wofür es beschleunigen oder bremsen muss.
 *
 * # Wurde die angepeilte Zielgeschwindigkeit erreicht, wird diese für maximal
 * "keepKmhSeconds" gehalten, danach wird die Geschwindigkeit angepasst (eine
 * neue Zielgeschwindigkeit ermittelt).
 *
 * # Ist die aktuelle Geschwindigkeit kleiner die Zielgeschwindigkeit, werden
 * stufenweise alle Gänge hochgeschaltet, bis das Auto mit mittlerer Drehzahl
 * fahren kann oder der letzte Gang erreicht wurde.
 *
 * # Ist das Auto zu schnell, bremst es ab ohne den Gang zu wechseln. Der Gang
 * wird erst gewechselt, wenn es wieder beschleunigt oder wenn die Drehzahl
 * unter das Minimum fällt. Dabei können beliebig viele Gänge übersprungen
 * werden.
 * 
 * # Es werden keine Verkehrsregeln beachtet. :D
 */
public class Vehicle {

    // Simulierte Motorwerte
    private final SensorMessage sensors = new SensorMessage();  // Aktuelle Sensordaten des Fahrzeugs

    private double startKmh = 100;                              // Startwert, um aus den Löchern zu kommen
    private double targetKmh = 0;                               // Angepeilte Geschwindigkeit
    private double prevKmh = 0;                                 // Vorherige Geschwindigkeit bei der letzten Anpassung
    private final int kmhMaxDifference = 40;                    // Neue Geschwindigkeit maximal um 40 kmh abweichen lassen
    private long kmhReachedTime = 0;                            // Zeit, wann die Zielgeschwindigkeit erreicht wurde
    private final int keepKmhSeconds = 5;                       // Zeit, die die Zielgeschwindigkeit gehalten wird

    // Gefahrene Strecke
    private List<WGS84> waypoints = new ArrayList<>();          // Wegpunkte der Strecke
    private boolean warnNoWaypoints = true;                     // Warnung ausgeben, wenn die Streckendaten fehlen
    private int lastWaypointIndex = 0;                          // Index des zuletzt angefahrenen Wegpunkts
    private long lastPositionTime = 0L;                         // Zeit, wann die letzte Position ermittelt wurde
    private double traveledKmFromLastWaypoint = 0;              // Gefahrene Strecke seit dem letzten Wegpunkt

    // Hintergrundthread für die Simulation
    private final Timer timer = new Timer(true);                // Timer, der alle 500ms neue Werte berechnen lässt
    private final int timerMillis = 500;                        // Millisekunden zwischen zwei Berechnungsläufen

    // Fahrzeugparameter zum Feintuning der Simulation
    private final int minRpm = 800;                             // Minimal zulässige Drehzahl während der Fahrt
    private final int maxRpm = 4000;                            // Maximal zulässige Drehzahl während der Fahrt
    private final int maxGears = 6;                             // Anzahl der Vorwärtsgänge
    private final double rpmMidPoint = 0.5;                     // Halbe Max-Drehzahl ist okay
    private final double rpmRange = 0.2;                        // Korridor um die Wohlfühldrehzahl vor Gangwechsel
    private final int baseKmh = 30;                             // Maximale Geschwindigkeit im ersten Gang
    private final double acceleration = 1.04;                   // Beschleunigungsfaktor
    private final double deceleration = 0.04;                   // Bremsfaktor

    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public Vehicle(String vehicleId, List<WGS84> waypoints) {
        this.sensors.vehicleId = vehicleId;
        this.waypoints = waypoints;

        if (waypoints.size() > 0) {
            WGS84 startPosition = waypoints.get(0);
            this.sensors.latitude = startPosition.latitude;
            this.sensors.longitude = startPosition.longitude;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Sensordaten auslesen">
    /**
     * Sensordaten auslesen
     *
     * @return Aktuelle Sensordaten
     */
    public SensorMessage getSensorData() {
        synchronized (this.sensors) {
            return this.sensors.copy();
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Simulation starten und stoppen">
    /**
     * Simulation starten, so dass regelmäßig neue Werte berechnet werden.
     */
    public void startVehicle() {
        synchronized (this.sensors) {
            if (this.sensors.running) {
                return;
            }

            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    update();
                }
            };

            timer.scheduleAtFixedRate(task, 0, this.timerMillis);

            this.sensors.running = true;
            this.lastPositionTime = System.currentTimeMillis();
        }
    }

    /**
     * Simulation stoppen und keine neuen Werte mehr berechnen.
     */
    public void stopVehicle() {
        synchronized (this.sensors) {
            this.timer.cancel();
            this.sensors.running = false;
            this.sensors.gear = 0;
        }
    }
    //</editor-fold>

    /**
     * Berechnen der simulierten Fahrzeugdaten. Diese Methode wird in einem
     * eigenen Thread periodisch aufgerufen, um die aktuellen Motordaten und die
     * Position des Fahrzeugs zu berechnen. Allerdings nur, wenn zuvor die
     * Ausführung mit der Methode startVehicle() gestartet wurde.
     */
    private void update() {
        synchronized (this.sensors) {
            // Läuft der Motor überhaupt?
            if (!this.sensors.running) {
                return;
            }

            // Zeit fortlaufen lassen
            long time = this.sensors.time = System.currentTimeMillis();

            // Neue Position berechnen
            int nextWaypointIndex = this.lastWaypointIndex < this.waypoints.size() - 1 ? this.lastWaypointIndex + 1 : 0;

            try {
                WGS84 lastWaypoint = this.waypoints.get(this.lastWaypointIndex);
                WGS84 currentPosition = new WGS84(this.sensors.latitude, this.sensors.longitude);
                WGS84 nextWaypoint = this.waypoints.get(nextWaypointIndex);

                // Gefahrene Strecke seit dem letzten Aufruf
                long timeDelta = time - this.lastPositionTime;
                double traveledKm = this.sensors.kmh * (timeDelta / 1000.0 / 60.0 / 60.0);
                this.lastPositionTime = time;

                // Aktuelle Position entsprechend verschieben
                WGS84 newPosition = WGS84.moveTowards(currentPosition, nextWaypoint, traveledKm);
                this.sensors.latitude = newPosition.latitude;
                this.sensors.longitude = newPosition.longitude;

                // Erkennen, wann der nächste Wegpunkt erreicht wurde
                this.traveledKmFromLastWaypoint += traveledKm;

                if (this.traveledKmFromLastWaypoint >= WGS84.distanceInKm(lastWaypoint, nextWaypoint)) {
                    this.lastWaypointIndex = nextWaypointIndex;
                    this.traveledKmFromLastWaypoint = 0;

                    // DEBUG
                    System.out.println("Nächster Wegpunkt erreicht: " + nextWaypoint);
                }
            } catch (IndexOutOfBoundsException ex) {
                // Keine Wegstrecke vorhanden
                if (this.warnNoWaypoints) {
                    this.warnNoWaypoints = false;
                    System.out.println();
                    System.out.println("+---------------------------------------------------------------------------+");
                    System.out.println("| ACHTUNG - ACHTUNG - ACHTUNG - ACHTUNG - ACHTUNG - ACHTUNG - ACHTUNG       |");
                    System.out.println("| Keine Wegpunkte vorhanden, das Fahrzeug weiß nicht, wo es hinfahren soll! |");
                    System.out.println("+---------------------------------------------------------------------------+");
                    System.out.println();
                }
            }

            // Neue Zielgeschwindigkeit ermitteln
            if (this.kmhReachedTime >= 0 && time - this.kmhReachedTime > this.keepKmhSeconds * 1000) {
                if (this.startKmh > 0) {
                    this.prevKmh = this.startKmh;
                    this.startKmh = 0;
                } else {
                    this.prevKmh = this.sensors.kmh;
                }

                this.targetKmh = this.prevKmh + (this.kmhMaxDifference * Math.random());
                this.kmhReachedTime = -1;

                if (this.targetKmh > this.kmhMaxDifference) {
                    this.targetKmh -= this.kmhMaxDifference / 2;
                }

                // DEBUG
                System.out.println("Neue Ziel km/h: " + this.targetKmh);
            }

            // Beschleunigen, falls das Auto zu langsam fährt
            if (this.targetKmh > 0 && this.sensors.kmh == 0) {
                this.sensors.kmh = 2;
            }

            if (this.sensors.kmh < this.targetKmh) {
                if (this.sensors.kmh < this.baseKmh) {
                    // Damit es am Anfang nicht so langsam geht
                    this.sensors.kmh *= this.acceleration * this.acceleration * this.acceleration;
                    this.sensors.kmh *= this.acceleration * this.acceleration * this.acceleration;
                } else {
                    this.sensors.kmh *= this.acceleration;
                }

                this.sensors.kmh = Math.min(this.sensors.kmh, this.targetKmh);
            }

            // Bremsen, falls das Auto zu schnell fährt
            if (this.sensors.kmh > this.targetKmh) {
                this.sensors.kmh *= this.deceleration;
                this.sensors.kmh = Math.max(this.sensors.kmh, this.targetKmh);
            }

            // Merken, wenn die Zielgeschwindigkeit erreicht wurde
            if (this.sensors.kmh == this.targetKmh && this.kmhReachedTime < 0) {
                this.kmhReachedTime = time;
            }

            // Drehzahl ausrechnen und Gang wechseln, wenn erforderlich
            double rpmMiddle = this.maxRpm * this.rpmMidPoint;
            double rpmUpper = rpmMiddle + (this.maxRpm * this.rpmRange);
            double rpmLower = rpmMiddle - (this.maxRpm * this.rpmRange);

            if (this.sensors.kmh > 0 && this.sensors.gear == 0) {
                this.sensors.gear = 1;
            }

            for (int i = 0; i < this.maxGears; i++) {
                this.sensors.rpm = (this.maxRpm * this.sensors.kmh) / (this.baseKmh * this.sensors.gear);

                if (this.sensors.rpm > rpmUpper) {
                    this.sensors.gear++;
                    this.sensors.gear = Math.min(this.sensors.gear, this.maxGears);

                    if (this.sensors.gear == this.maxGears) {
                        break;
                    }
                } else if (this.sensors.rpm < rpmLower) {
                    this.sensors.gear--;
                    this.sensors.gear = Math.max(this.sensors.gear, 1);

                    if (this.sensors.gear == 1) {
                        break;
                    }
                }
            }
        }
    }

}
