/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.pubsub.mini_chat;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * Konstanten und Hilfsmethoden.
 */
public class Utils {

    // Hilfsklasse zum Protokollieren von Fehlern
    public static final Logger LOGGER = Logger.getLogger(Utils.class.getName());

    // Übersetzbare UI-Texte
    public static ResourceBundle translations;

    static {
        Locale locale = Locale.getDefault();
        translations = ResourceBundle.getBundle("Translations", locale);
    }
    
    // Adresse des MQTT-Brokers und Namen der Topics
    public static final String MQTT_BROKER_ADDRESS = "tcp://iot.eclipse.org:1883";
    public static final String MQTT_TOPIC_MINI_CHAT = "MiniChat";

    /**
     * Exception ausgeben
     *
     * @param t Exception
     */
    public static void logException(Throwable t) {
        LOGGER.log(Level.SEVERE, null, t);
    }

    /**
     * Hilfsmethode zur Anzeige einer Exception mit Swing. Druckt die Exception
     * auf StdErr und öffnet ein JOptionPane-Dialog mit dem Stack Trace.
     *
     * @param ex Anzuzeigende Exception
     */
    public static void displayException(Exception ex) {
        String traceLines = "";
        Throwable throwable = ex;

        while (throwable != null) {
            // Name der Exception und Nachricht
            String exceptionNameAndMessage = throwable.getClass().getCanonicalName();

            if (throwable.getMessage() != null && !throwable.getMessage().isEmpty()) {
                exceptionNameAndMessage += ": " + throwable.getMessage();
            }

            if (traceLines.isEmpty()) {
                traceLines = exceptionNameAndMessage;
            } else {
                traceLines += "\n" + String.format(translations.getString("caused-by"), exceptionNameAndMessage);
            }

            // Stack Trace
            StackTraceElement[] trace = throwable.getStackTrace();

            for (int i = 0; i < trace.length; i++) {
                traceLines += "\n  " + trace[i].getFileName() + ":" + trace[i].getLineNumber();
            }

            throwable = throwable.getCause();
        }

        System.err.println(traceLines);

        // Sieht so aus, als ob Java keine echten Closoures kennt. Innerhalb von Lambdas und anonymen Klassen
        // darf man nur auf finale Variablen des übergeordneten Scope zugreifen …
        final String traceLines1 = traceLines;

        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, traceLines1, translations.getString("error-title"), JOptionPane.ERROR_MESSAGE);
        });
    }

}
