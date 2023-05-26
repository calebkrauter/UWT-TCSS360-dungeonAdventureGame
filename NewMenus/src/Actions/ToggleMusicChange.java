package Actions;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

public class ToggleMusicChange {
    JCheckBox myMusicToggle;
    MusicPlayer musicPlayer;
    String myMusic;
    public ToggleMusicChange(JCheckBox theMusicToggle, String theMusic) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        myMusicToggle = theMusicToggle;
        musicPlayer = new MusicPlayer();
        myMusic = theMusic;
        toggleMusicChange();
    }

    public ToggleMusicChange(JCheckBox theMusicToggle) {
        myMusicToggle = theMusicToggle;

    }

    private void toggleMusicChange() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        if (myMusicToggle.isSelected()) {
                musicPlayer.playMusic(myMusic);
        } else {
            musicPlayer.stopMusic();
        }
    }

    public void setMyMusicToggleSelected(boolean theSelectValue) {
        myMusicToggle.setSelected(theSelectValue);
    }
}
