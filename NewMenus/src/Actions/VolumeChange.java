package Actions;

import javax.sound.sampled.FloatControl;

public class VolumeChange {

    private static FloatControl gainControl;
    private static int myMusicVolume;
    float gain;
    static MusicPlayer musicPlayer;
    public VolumeChange(int theMusicVolume) {
        musicPlayer = new MusicPlayer();
        myMusicVolume = theMusicVolume;
        setVolumeChange(myMusicVolume);
    }

    public VolumeChange() {

    }

    public void setVolumeChange(int theMusicVolume) {
        gainControl = (FloatControl) musicPlayer.getClip().getControl(FloatControl.Type.MASTER_GAIN);
        gain = (float) (theMusicVolume - 80);
        if (gain > 6) {
            gain = gainControl.getMaximum();
        }

        if (gain < -80) {
            musicPlayer.getClip().close();
        }
        gainControl.setValue(gain);
    }

    public int getMusicVolume() {
        int volume = (int) (gain + 80);
        if (gain > 6) {
            volume = (int) gainControl.getMaximum();
        }
        return volume;
    }
}
