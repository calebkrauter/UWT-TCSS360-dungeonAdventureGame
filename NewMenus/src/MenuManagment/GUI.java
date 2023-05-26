
package MenuManagment;

import Actions.MusicPlayer;
import Actions.ToggleMusicChange;

import java.awt.Dimension;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.JFrame;

public class GUI {

    private JFrame myJFrame = new JFrame();
    private MenuManager newMenu;
    ScreenData screenData;

    public GUI() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        screenData = new ScreenData(this.myJFrame.getWidth(), this.myJFrame.getHeight());
        this.newMenu = new MenuManager(this.myJFrame);
        this.loadGui();
        new MusicPlayer("MainMenu.wav");
    }

    void loadGui() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        this.myJFrame.setDefaultCloseOperation(3);
        this.myJFrame.setSize(850, 850);
        this.myJFrame.setMinimumSize(new Dimension(750, 750));
        this.myJFrame.add(this.newMenu);
        this.myJFrame.setLocationRelativeTo(null);
        this.myJFrame.pack();
        this.myJFrame.setVisible(true);
    }



}
