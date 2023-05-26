package Actions;

import javax.swing.*;

public class UpdateSlider {

    JSlider myVolumeSlider;
    static VolumeChange volumeChange;
    public UpdateSlider(JSlider theVolumeSlider) {
        myVolumeSlider = theVolumeSlider;
        volumeChange = new VolumeChange();
    }

    public void setMyVolumeSlider() {
        myVolumeSlider.setValue(volumeChange.getMusicVolume());
    }
}
