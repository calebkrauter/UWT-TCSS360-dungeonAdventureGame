package Actions;

import javax.swing.*;

public class UpdateSlider {

    private static JSlider myVolumeSlider;
    private VolumeChange volumeChange;
    public UpdateSlider(JSlider theVolumeSlider) {
        myVolumeSlider = theVolumeSlider;
        volumeChange = new VolumeChange();
    }
    public UpdateSlider() {

    }

    public void setVolumeSlider() {
        myVolumeSlider.setValue(volumeChange.getMusicVolume());
    }
    public int getVolumeSlider() {
       return myVolumeSlider.getValue();
    }
}
