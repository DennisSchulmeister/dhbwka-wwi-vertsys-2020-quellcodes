/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.dibeispiel.simple;

/**
 * Interface für einen einfachen Taschenrechnerservice. Dieses Interface legt
 * die Methoden der Geschäftslogik fest, ohne sie auszuprogrammieren. Auf diese
 * Weise können wir die konkrete Implementierung später einfach austauschen,
 * ohne die Verwender anpassen zu müssen.
 *
 * Beispielsweise könnte eine einfache Implementierung die Berechnungen lokal
 * ausführen und eine andere Implementierung hierfür einen entfernten Webservice
 * aufrufen.
 */
public interface CalculatorService {

    /**
     * Addiert zwei Zahlen.
     * 
     * @param v1 Zahl 1
     * @param v2 Zahl 2
     * @return Summe
     */
    int add(int v1, int v2);

    /**
     * Subtrahiert zwei Zahlen.
     * 
     * @param v1 Zahl 1
     * @param v2 Zahl 2
     * @return Differenz zwischen Zahl 1 und 2
     */
    int subtract(int v1, int v2);

    /**
     * Multipliziert zwei Zahlen.
     * 
     * @param v1 Zahl 1
     * @param v2 Zahl 2
     * @return Produkt
     */
    int multiply(int v1, int v2);

    /**
     * Teilt zwei Zahlen
     * 
     * @param v1 Zahl 1
     * @param v2 Zahl 2
     * @return Quotient von Zahl 1 und Zahl 2
     */
    int divide(int v1, int v2);
    
}
