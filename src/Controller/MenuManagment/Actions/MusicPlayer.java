package Controller.MenuManagment.Actions;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
/**
 * @author Caleb Krauter
 * @version 1.0
 */

/**
 * Music player class.
 */
public class MusicPlayer {
    /**
     * Audio stream.
     */
    private AudioInputStream audioStream;
    /**
     * The clip of audio.
     */
    private static Clip clip;

    /**
     * The constructor.
     */
    public MusicPlayer() {

    }

    /**
     * Gets clip.
     * @return Clip
     */
    public Clip getClip() {
        return clip;
    }

    /**
     * Plays the music.
     * @param theMusic
     * @throws LineUnavailableException
     * @throws IOException
     * @throws UnsupportedAudioFileException
     */
    public void playMusic(String theMusic) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        audioStream = AudioSystem.getAudioInputStream(new File(theMusic));
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();

    }
/**
 * @author Caleb Krauter
 * @version 1.0
 */
}
