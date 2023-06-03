package Actions;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

public class ToggleMusicChange {
    JToggleButton myMusicToggle;
    MusicPlayer musicPlayer;
    String myMusic;
    VolumeChange volumeChange;
    UpdateSlider updateSlider;
    public ToggleMusicChange(JToggleButton theMusicToggle, String theMusic) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        myMusicToggle = theMusicToggle;
        musicPlayer = new MusicPlayer();
        volumeChange = new VolumeChange();
        updateSlider = new UpdateSlider();
        myMusic = theMusic;
        toggleMusicChange();
    }

    public ToggleMusicChange(JToggleButton theMusicToggle) {
        myMusicToggle = theMusicToggle;

    }

    private void toggleMusicChange() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        if (myMusicToggle.isSelected()) {
            musicPlayer.playMusic(myMusic);
            int curVolume = updateSlider.getMyVolumeSlider();
            volumeChange.setVolumeChange((curVolume));
        } else {
            musicPlayer.stopMusic();
        }

        if (!musicPlayer.getClip().isOpen()) {
            myMusicToggle.setSelected(false);
        } else {
            myMusicToggle.setSelected(true);
        }
    }

    public void setMyMusicToggleSelected(boolean theSelectValue) {
        myMusicToggle.setSelected(theSelectValue);
    }
}
