package Actions;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class interactionSound {

    public interactionSound(String theSound) {
        try {
            playSound(theSound);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void playSound(String theSound) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(theSound));
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }
}
