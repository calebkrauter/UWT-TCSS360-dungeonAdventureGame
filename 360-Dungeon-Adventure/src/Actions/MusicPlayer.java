package Actions;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {
    String myMusic;
    AudioInputStream audioStream;
    // Make getter for clip
    public static Clip clip;

    public MusicPlayer(String theMusic) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        myMusic = theMusic;
        playMusic(myMusic);
    }

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
