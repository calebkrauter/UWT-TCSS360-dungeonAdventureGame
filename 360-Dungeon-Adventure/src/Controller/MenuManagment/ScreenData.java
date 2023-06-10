package Controller.MenuManagment;

import java.awt.*;

public class ScreenData {
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    private int myJFrameWidth;
    private int myJFrameHeight;
    public ScreenData() {

    }

    public ScreenData(int theJFrameWidth, int theJFrameHeight) {
        myJFrameWidth = theJFrameWidth;
        myJFrameHeight = theJFrameHeight;
    }

    public Dimension getScreenSize() {
        return SCREEN_SIZE;
    }
    public int getJFrameWidth() {
        return myJFrameWidth;
    }
    public int getJFrameHeight() {
        return myJFrameHeight;
    }
}
