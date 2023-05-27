package Actions;

import javax.swing.*;

public class MapSizeChange {
    static JSlider myMapSizeSlider;
    public MapSizeChange(JSlider theMapSizeSlider) {
        myMapSizeSlider = theMapSizeSlider;
        System.out.println(myMapSizeSlider.getValue());
    }

    public int getMapSizeFromSlider() {
        return myMapSizeSlider.getValue();
    }

}
