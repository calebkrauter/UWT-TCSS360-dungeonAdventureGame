
package MenuManagment;

import Actions.MusicPlayer;
import Controller.GamePanel;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GUI {

    private static final JFrame myJFrame = new JFrame();
    private MenuManager newMenu;
    ScreenData screenData;
    boolean myPlayGame = false;

    public GUI() throws IOException, UnsupportedAudioFileException, LineUnavailableException, ClassNotFoundException {
        screenData = new ScreenData(this.myJFrame.getWidth(), this.myJFrame.getHeight());
        this.newMenu = new MenuManager(this.myJFrame);
        this.loadGui();
        new MusicPlayer("MainMenu.wav");
    }
    public GUI(boolean thePlayGame) throws UnsupportedAudioFileException, LineUnavailableException, IOException, ClassNotFoundException {
        myPlayGame = thePlayGame;
        setPlayGame(thePlayGame);
        loadGui();
    }

    void loadGui() throws IOException, UnsupportedAudioFileException, LineUnavailableException, ClassNotFoundException {
        this.myJFrame.setDefaultCloseOperation(3);
        this.myJFrame.setSize(850, 850);
        this.myJFrame.setMinimumSize(new Dimension(750, 750));

        if (getPlayGame()) {
            GamePanel myGamePanel = new GamePanel();
            myJFrame.add(myGamePanel);
            myGamePanel.requestFocus();
            myGamePanel.startGameThread();

        } else {
            myJFrame.add(newMenu);
        }

        this.myJFrame.pack();
        this.myJFrame.setVisible(true);
        myJFrame.setLocationRelativeTo(null);
    }

    private void setPlayGame(boolean thePlayGame) {
        myPlayGame = thePlayGame;
    }

    public boolean getPlayGame() {
        return myPlayGame;
    }



}
