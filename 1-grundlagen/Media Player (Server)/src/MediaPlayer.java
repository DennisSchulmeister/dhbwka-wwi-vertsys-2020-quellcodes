/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

/**
 * Klasse, deren Methoden der Server ausführen soll, wenn er von den Clients
 * dazu aufgefordert wird. Als Beispiel haben wir hier eine Media Player-Klasse,
 * die eine List mit Songs beinhaltet, die abgespielt werden können.
 */
public class MediaPlayer {

    private final File[] files;
    private AdvancedPlayer player = null;

    /**
     * Konstruktor
     */
    public MediaPlayer() {
        File songDir = new File("songs");
        this.files = songDir.listFiles((File f) -> f.getName().matches(".*\\.mp3"));
    }

    /**
     * @return Liste mit den abspielbaren Dateien
     */
    public File[] getFiles() {
        return this.files;
    }

    /**
     * Beginnt die Wiedergabe einer Datei.
     *
     * @param index
     */
    public void playFile(int index) {
        this.stopPlayback();

        new Thread(() -> {
            try {
                player = new AdvancedPlayer(new FileInputStream(files[index]));
                player.play();
            } catch (FileNotFoundException | JavaLayerException ex) {
                System.err.println("Fehler bei Start der Wiedergabe!");
            }
        }).start();
    }

    /**
     * Stoppt die Wiedergabe.
     */
    public void stopPlayback() {
        if (this.player != null) {
            this.player.close();
        }
    }
}
