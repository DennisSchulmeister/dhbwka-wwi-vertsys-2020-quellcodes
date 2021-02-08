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

import dhbwka.wwi.vertsys.pubsub.mini_chat.ChatMessage;
import dhbwka.wwi.vertsys.pubsub.mini_chat.Utils;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * Controllerklasse für das Hauptfenster.
 */
public class MainWindow implements ChatConnectionListener {

    JFrame window;
    JButton connectToolbarButton;
    JButton disconnectToolbarButton;
    JButton sendToolbarButton;
    JButton exitToolbarButton;
    JTextArea messageArea;
    JTextField textField;
    JButton sendButton;
    JLabel status;

    ChatConnection chatConnection;
    String chatname = "";

    /**
     * Konstruktor.
     */
    public MainWindow() {
        this.chatConnection = new ChatConnection(this);

        try {
            SwingUtilities.invokeAndWait(() -> {
                this.buildUserInterface();
            });
        } catch (InvocationTargetException | InterruptedException ex) {
            Utils.displayException(ex);
        }
    }

    /**
     * UI-Elemente erstellen und Fenster zusammenbauen.
     */
    private void buildUserInterface() {
        this.window = new JFrame(Utils.translations.getString("window-title"));
        this.window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        //<editor-fold defaultstate="collapsed" desc="Icons einlesen">
        URL appUrl = getClass().getResource("/icons/appicon.png");
        ImageIcon appIcon = new ImageIcon(appUrl);

        URL connectUrl = getClass().getResource("/icons/connect.png");
        ImageIcon connectIcon = new ImageIcon(connectUrl);

        URL disconnectUrl = getClass().getResource("/icons/disconnect.png");
        ImageIcon disconnectIcon = new ImageIcon(disconnectUrl);

        URL sendUrl = getClass().getResource("/icons/send.png");
        ImageIcon sendIcon = new ImageIcon(sendUrl);

        URL exitUrl = getClass().getResource("/icons/exit.png");
        ImageIcon exitIcon = new ImageIcon(exitUrl);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Fensterelemente erzeugen">
        // Zeile 1: Toolbar
        JToolBar toolbar = new JToolBar();

        this.connectToolbarButton = new JButton(Utils.translations.getString("action-connect"));
        this.connectToolbarButton.setIcon(connectIcon);
        this.connectToolbarButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.connectToolbarButton.setHorizontalTextPosition(SwingConstants.CENTER);
        toolbar.add(this.connectToolbarButton);

        this.disconnectToolbarButton = new JButton(Utils.translations.getString("action-disconnect"));
        this.disconnectToolbarButton.setIcon(disconnectIcon);
        this.disconnectToolbarButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.disconnectToolbarButton.setHorizontalTextPosition(SwingConstants.CENTER);
        toolbar.add(this.disconnectToolbarButton);

        this.sendToolbarButton = new JButton(Utils.translations.getString("action-send-long"));
        this.sendToolbarButton.setIcon(sendIcon);
        this.sendToolbarButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.sendToolbarButton.setHorizontalTextPosition(SwingConstants.CENTER);
        toolbar.add(this.sendToolbarButton);

        toolbar.add(new JToolBar.Separator());

        this.exitToolbarButton = new JButton(Utils.translations.getString("action-exit"));
        this.exitToolbarButton.setIcon(exitIcon);
        this.exitToolbarButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.exitToolbarButton.setHorizontalTextPosition(SwingConstants.CENTER);
        toolbar.add(this.exitToolbarButton);

        // Zeile 2: Nachrichtenbereich
        this.messageArea = new JTextArea();
        this.messageArea.setEditable(false);

        // Zeile 3: Textfeld und Senden-Button
        this.textField = new JTextField();
        this.sendButton = new JButton(Utils.translations.getString("action-send-short"));
        this.sendButton.setIcon(sendIcon);

        JPanel textPanel = new JPanel();
        BorderLayout messagePanelLayout = new BorderLayout();
        textPanel.setLayout(messagePanelLayout);
        textPanel.add(BorderLayout.CENTER, this.textField);
        textPanel.add(BorderLayout.EAST, this.sendButton);

        // Zeile 4: Statuszeile
        this.status = new JLabel();

        // Layout zusammenbauen
        BorderLayout windowLayout = new BorderLayout();
        this.window.setLayout(windowLayout);

        this.window.add(BorderLayout.NORTH, toolbar);
        this.window.add(BorderLayout.CENTER, new JScrollPane(this.messageArea));

        JPanel southPanel = new JPanel();
        BorderLayout southPanelLayout = new BorderLayout();
        southPanel.setLayout(southPanelLayout);
        southPanel.add(BorderLayout.CENTER, textPanel);
        southPanel.add(BorderLayout.SOUTH, this.status);
        this.window.add(BorderLayout.SOUTH, southPanel);

        this.onDisconnect();

        this.window.setIconImage(appIcon.getImage());
        this.window.setPreferredSize(new Dimension(1024, 768));
        this.window.setMinimumSize(new Dimension(640, 480));
        this.window.pack();
        //</editor-fold>

        // Verbindung herstellen
        this.connectToolbarButton.addActionListener(event -> {
            CreateConnectionDialog ccDialog = new CreateConnectionDialog(this.window, chatConnection);
            ccDialog.getDialog().setVisible(true);
        });

        // Verbindung trennen
        this.disconnectToolbarButton.addActionListener(event -> {
            this.doDisconnect();
        });

        // Nachricht senden
        this.sendToolbarButton.addActionListener(event -> {
            this.doSend();
        });

        this.sendButton.addActionListener(event -> {
            this.doSend();
        });

        this.textField.addActionListener(event -> {
            this.doSend();
        });

        // Programm beenden
        this.exitToolbarButton.addActionListener(event -> {
            this.window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
        });

        this.window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                doDisconnect();
                System.exit(0);
            }

        });
    }

    /**
     * @return Zugrunde liegendes JWindow
     */
    public JFrame getWindow() {
        return this.window;
    }

    /**
     * Keine Verbindung zum MQTT-Broker. Daher alle Elemente ausgrauen.
     */
    @Override
    public final void onDisconnect() {
        this.connectToolbarButton.setVisible(true);
        this.disconnectToolbarButton.setVisible(false);
        this.sendToolbarButton.setEnabled(false);

        this.textField.setText("");
        this.textField.setEditable(false);
        this.sendButton.setEnabled(false);

        this.status.setText(Utils.translations.getString("status-disconnected"));

        this.chatname = "";
    }

    /**
     * Verbindung zum MQTT-Broker hergestellt. Daher alle Elemente aktiv
     * schalten.
     *
     * @param address Adresse des MQTT-Brokers
     * @param clientId Client-ID
     * @param username Benutzername
     * @param chatname Chatname
     */
    @Override
    public final void onConnect(String address, String clientId, String username, String chatname) {
        this.connectToolbarButton.setVisible(false);
        this.disconnectToolbarButton.setVisible(true);
        this.sendToolbarButton.setEnabled(true);

        this.messageArea.setText("");
        this.textField.setText("");
        this.textField.setEditable(true);
        this.sendButton.setEnabled(true);

        this.status.setText(Utils.translations.getString("status-connected")
                .replace("$B$", address)
                .replace("$I$", clientId)
                .replace("$U$", username)
                .replace("$C$", chatname));

        this.chatname = chatname;

        // Nachricht senden, dass ein neuer Benutzer hinzugekommen ist
        try {
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.text = Utils.translations.getString("message-new-user").replace("$C$", this.chatname);
            this.chatConnection.sendChatMessage(chatMessage);
        } catch (MqttException ex) {
            Utils.displayException(ex);
        }
    }

    /**
     * Anzeige einer neuen, empfangenen Chatnachricht.
     *
     * @param chatMessage Empfangene Nachricht
     */
    @Override
    public final void onMessageReceived(ChatMessage chatMessage) {
        String text = "";

        if (chatMessage.name != null && !chatMessage.name.trim().isEmpty()) {
            text = "[" + chatMessage.name.trim() + "] ";
        }

        if (chatMessage.text != null && !chatMessage.text.trim().isEmpty()) {
            text += chatMessage.text;
        }

        this.messageArea.append(text + "\n");
    }

    /**
     * Abschicken der aktuell eingegebenen Nachricht.
     */
    public void doSend() {
        try {
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.name = this.chatname;
            chatMessage.text = this.textField.getText().trim();

            this.chatConnection.sendChatMessage(chatMessage);

            this.textField.setText("");
        } catch (MqttException ex) {
            Utils.displayException(ex);
        }
    }

    /**
     * Verbindung trennen und Abschiedsnachricht an den Chat senden.
     */
    public void doDisconnect() {
        try {
            // Nachricht senden, dass ein der Benutzer gegangen ist
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.text = Utils.translations.getString("message-user-left").replace("$C$", this.chatname);
            this.chatConnection.sendChatMessage(chatMessage);

            // Verbindung trennen
            this.chatConnection.disconnect();
        } catch (MqttException ex) {
            Utils.displayException(ex);
        }
    }
}
