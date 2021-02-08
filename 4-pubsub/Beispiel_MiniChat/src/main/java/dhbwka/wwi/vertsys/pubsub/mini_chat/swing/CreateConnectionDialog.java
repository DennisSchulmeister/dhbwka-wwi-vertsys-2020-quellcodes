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

import dhbwka.wwi.vertsys.pubsub.mini_chat.Utils;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.event.WindowEvent;
import java.net.URL;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * Controllerklasse für das Dialogfenster zum Herstellen einer neuen Verbindung.
 */
public class CreateConnectionDialog {

    JFrame parent;
    JDialog dialog;
    JTextField addressText;
    JTextField usernameText;
    JTextField passwordText;
    JTextField chatnameText;

    ChatConnection chatConnection;

    /**
     * Konstruktor
     *
     * @param parent
     * @param chatConnection
     */
    public CreateConnectionDialog(JFrame parent, ChatConnection chatConnection) {
        this.chatConnection = chatConnection;
        this.parent = parent;
        this.buildUserInterface();
    }

    /**
     * UI-Elemente erstellen und Fenster zusammenbauen.
     */
    private void buildUserInterface() {
        this.dialog = new JDialog(parent, Utils.translations.getString("ccdialog-title"), Dialog.ModalityType.DOCUMENT_MODAL);

        //<editor-fold defaultstate="collapsed" desc="Fensterelemente erzeugen">
        // Zeile 1: MQTT-Broker
        JLabel addressLabel = new JLabel(Utils.translations.getString("ccdialog-address"));
        addressText = new JTextField(Utils.MQTT_BROKER_ADDRESS);

        // Zeile 2: Benutzername
        JLabel usernameLabel = new JLabel(Utils.translations.getString("ccdialog-username"));
        usernameText = new JTextField();

        // Zeile 3: Passwort
        JLabel passwordLabel = new JLabel(Utils.translations.getString("ccdialog-password"));
        passwordText = new JTextField();

        // Zeile 4: Chatname
        JLabel chatnameLabel = new JLabel(Utils.translations.getString("ccdialog-chatname"));
        chatnameText = new JTextField(System.getProperty("user.name"));

        // Zeile 5: Buttons
        JButton connectButton = new JButton(Utils.translations.getString("ccdialog-action-connect"));
        JButton cancelButton = new JButton(Utils.translations.getString("ccdialog-action-cancel"));

        // Layout zusammenbauen
        BorderLayout windowLayout = new BorderLayout();
        this.dialog.setLayout(windowLayout);

        JPanel mainPanel = new JPanel();
        this.dialog.add(BorderLayout.CENTER, mainPanel);
        GroupLayout groupLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(groupLayout);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hGroup = groupLayout.createSequentialGroup();
        hGroup.addGroup(groupLayout.createParallelGroup()
                .addComponent(addressLabel)
                .addComponent(usernameLabel)
                .addComponent(passwordLabel)
                .addComponent(chatnameLabel)
        );
        hGroup.addGroup(groupLayout.createParallelGroup()
                .addComponent(this.addressText)
                .addComponent(this.usernameText)
                .addComponent(this.passwordText)
                .addComponent(this.chatnameText)
        );
        groupLayout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = groupLayout.createSequentialGroup();
        vGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(addressLabel)
                .addComponent(this.addressText)
        );
        vGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(usernameLabel)
                .addComponent(this.usernameText)
        );
        vGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(passwordLabel)
                .addComponent(this.passwordText)
        );
        vGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(chatnameLabel)
                .addComponent(this.chatnameText)
        );
        groupLayout.setVerticalGroup(vGroup);

        JPanel buttonPanel = new JPanel();
        this.dialog.add(BorderLayout.SOUTH, buttonPanel);
        BoxLayout boxLayout = new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS);
        buttonPanel.setLayout(boxLayout);
        buttonPanel.add(connectButton);
        buttonPanel.add(cancelButton);

        URL appUrl = getClass().getResource("/icons/appicon.png");
        ImageIcon appIcon = new ImageIcon(appUrl);
        this.dialog.setIconImage(appIcon.getImage());
        
        this.dialog.pack();
        //</editor-fold>

        // Verbindung herstellen und Fenster schließen
        connectButton.addActionListener(event -> {
            // Eingaben prüfen
            String address = this.addressText.getText().trim();
            String username = this.usernameText.getText().trim();
            String password = this.passwordText.getText().trim();
            String chatname = this.chatnameText.getText().trim();

            if (address.isEmpty()) {
                String message = Utils.translations.getString("ccdialog-msg-address");
                String title = Utils.translations.getString("error");
                JOptionPane.showMessageDialog(this.dialog, message, title, JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (chatname.isEmpty()) {
                String message = Utils.translations.getString("ccdialog-msg-chatname");
                String title = Utils.translations.getString("error");
                JOptionPane.showMessageDialog(this.dialog, message, title, JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                // Verbindung herstellen
                this.chatConnection.connect(address, username, password, chatname);
                this.dialog.dispatchEvent(new WindowEvent(this.dialog, WindowEvent.WINDOW_CLOSING));
            } catch (MqttException ex) {
                Utils.displayException(ex);
            }
        });

        // Fenster schließen, ohne eine Verbindung herzustellen
        cancelButton.addActionListener(event -> {
            this.dialog.dispatchEvent(new WindowEvent(this.dialog, WindowEvent.WINDOW_CLOSING));
        });
    }

    public JDialog getDialog() {
        return this.dialog;
    }
}
