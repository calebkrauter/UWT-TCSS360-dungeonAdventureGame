package Controller.MenuManagment.Actions;

import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
/**
 * @author Caleb Krauter
 * @version 1.0
 */

/**
 * The volume change class.
 */
public class VolumeChange {

    /**
     * The gain control.
     */
    private static FloatControl gainControl;
    /**
     * The volume for the music.
     */
    private static int myMusicVolume;
    /**
     * The gain.
     */
    private float gain;
    /**
     * The reference to music player.
     */
    private static MusicPlayer musicPlayer;

    /**
     * The volume change constructor.
     * @param theMusicVolume
     * @throws LineUnavailableException
     */
    public VolumeChange(int theMusicVolume) throws LineUnavailableException {
        musicPlayer = new MusicPlayer();
        myMusicVolume = theMusicVolume;
        setVolumeChange(myMusicVolume);
    }

    /**
     * A constructor.
     */
    public VolumeChange() {

    }

    /**
     * Setter for volume change.
     * @param theMusicVolume
     */
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

    /**
     * Gets the volume.
     * @return
     */
    public int getMusicVolume() {
        return myMusicVolume;
    }
}
