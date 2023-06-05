package Actions;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class ButtonSound {

    public ButtonSound() {
        try {
            playButtonSound();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void playButtonSound() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("typeWriterSound.wav"));
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }
}
