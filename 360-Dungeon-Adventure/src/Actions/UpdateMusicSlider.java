package Actions;

import javax.swing.*;

public class UpdateMusicSlider {

    private static JSlider myVolumeSlider;
    private VolumeChange volumeChange;
    public UpdateMusicSlider(JSlider theVolumeSlider) {
        myVolumeSlider = theVolumeSlider;
        volumeChange = new VolumeChange();
    }
    public UpdateMusicSlider() {

    }

    public void setVolumeSlider() {
        myVolumeSlider.setValue(volumeChange.getMusicVolume());
    }
    public int getVolumeSlider() {
       return myVolumeSlider.getValue();
    }
}
