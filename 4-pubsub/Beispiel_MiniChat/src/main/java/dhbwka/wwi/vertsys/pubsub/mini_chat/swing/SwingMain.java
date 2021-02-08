/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.pubsub.mini_chat.swing;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Beispielprogramm, dass MQTT nutzt, um Nachrichten an eine Warteschlange zu
 * senden. Damit es interessant wird, handelt es sich um eine kleine
 * Swing-Anwendung, die einen kleinen Chat simuliert.
 */
public class SwingMain {
    
    public static void main(String[] args) {
        // Swing Look&Feel setzen
        try {
            for (UIManager.LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
                if (laf.getName().equalsIgnoreCase("Nimbus")) {
                    UIManager.setLookAndFeel(laf.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            // Nicht tragisch, gesuchtes Look&Feel ist nicht verfügbar
        }

        // Hauptfenster öffnen
        MainWindow mainWindow = new MainWindow();
        mainWindow.getWindow().setVisible(true);
    }
}
