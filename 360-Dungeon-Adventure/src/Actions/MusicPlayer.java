package Actions;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {
    private AudioInputStream audioStream;
    // Make getter for clip
    private static Clip clip;
    public MusicPlayer() {

    }

    public Clip getClip() {
        return clip;
    }

    public void playMusic(String theMusic) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        audioStream = AudioSystem.getAudioInputStream(new File(theMusic));
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();

    }

    public void stopMusic() throws IOException, UnsupportedAudioFileException {
        clip.stop();
    }
}
