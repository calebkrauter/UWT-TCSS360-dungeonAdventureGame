// Game Project with jylee33@uw.edu & krautercaleb@gmail.com

package Controller;

import MenuManagment.GUI;
import MenuManagment.MenuManager;
import MenuManagment.ScreenData;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {

    private static final JFrame myJFrame = new JFrame();
    private MenuManager newMenu;
    ScreenData screenData;
    boolean myPlayGame = false;
    public static String myGameStateFile = "";

    public static void main(String[] args)  throws IOException, UnsupportedAudioFileException, LineUnavailableException, ClassNotFoundException {

        if (!(new File("game-saves.ser").exists())) {
            new File("game-saves.ser").createNewFile();
        }
//        new SerializeGameSaves().serializeGameSaves();
//        new GUI();
        myJFrame.setDefaultCloseOperation(3);
        myJFrame.setMinimumSize(new Dimension(850, 850));
        myJFrame.setResizable(false);
        GamePanel myGamePanel = null;
        myGamePanel = new GamePanel(myGameStateFile);
        myJFrame.getContentPane().removeAll();
        myJFrame.add(myGamePanel);
        myGamePanel.requestFocus();

        // sets the objects
        myGamePanel.SetupGame();

        myGamePanel.startGameThread();

        myJFrame.pack();
        myJFrame.setVisible(true);
        myJFrame.setLocationRelativeTo(null);
    }
}
