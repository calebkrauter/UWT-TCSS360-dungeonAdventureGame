package Actions;

import javax.swing.*;

public class UpdateSlider {

    static JSlider myVolumeSlider;
    VolumeChange volumeChange;
    public UpdateSlider(JSlider theVolumeSlider) {
        myVolumeSlider = theVolumeSlider;
        volumeChange = new VolumeChange();
    }
    public UpdateSlider() {

    }

    public void setMyVolumeSlider() {
        myVolumeSlider.setValue(volumeChange.getMusicVolume());
    }
    public int getMyVolumeSlider() {
       return myVolumeSlider.getValue();
    }
}
