package Actions;

import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;

public class VolumeChange {

    private static FloatControl gainControl;
    private static int myMusicVolume;
    private float gain;
    private static MusicPlayer musicPlayer;
    public VolumeChange(int theMusicVolume) throws LineUnavailableException {
        musicPlayer = new MusicPlayer();
        myMusicVolume = theMusicVolume;
        setVolumeChange(myMusicVolume);
    }

    public VolumeChange() {

    }

    public void setVolumeChange(int theMusicVolume) {
        gainControl = (FloatControl) musicPlayer.getClip().getControl(FloatControl.Type.MASTER_GAIN);
        gain = theMusicVolume;
        if (gain > 6) {
            gain = gainControl.getMaximum();
        }

        if (gain <= -80) {
            gain = gainControl.getMinimum();
        }
        gainControl.setValue(gain);
    }

    public int getMusicVolume() {
//        int volume = (int) (gain + 80);
//        if (gain > 6) {
//            volume = (int) gainControl.getMaximum();
//        }
        return myMusicVolume;
    }
}
