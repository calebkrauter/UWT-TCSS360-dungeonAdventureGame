
package MenuManagment;

import Actions.MusicPlayer;
import Actions.PlayButtonSound;
import Controller.GamePanel;
import LoadSave.SerializeMapGenerator;

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
    String myGameStateFile = "";
    private String MAIN_MENU_MUSIC = "MainMenu.wav";
    MusicPlayer musicPlayer;
    public GUI() throws IOException, UnsupportedAudioFileException, LineUnavailableException, ClassNotFoundException {
        screenData = new ScreenData(this.myJFrame.getWidth(), this.myJFrame.getHeight());
        this.newMenu = new MenuManager(this.myJFrame);
        this.loadGui();
        musicPlayer = new MusicPlayer();
    }
    public GUI(boolean thePlayGame, String theGameStateFile) throws UnsupportedAudioFileException, LineUnavailableException, IOException, ClassNotFoundException {
        myPlayGame = thePlayGame;
        myGameStateFile = theGameStateFile;
        setPlayGame(thePlayGame);
        loadGui();
    }
    static int firstPlay = 0;

    void loadGui() throws IOException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException {
        this.myJFrame.setDefaultCloseOperation(3);
        this.myJFrame.setMinimumSize(new Dimension(850, 850));
        GamePanel myGamePanel = null;
        if (getPlayGame()) {
            myGamePanel = new GamePanel(myGameStateFile);
            myJFrame.getContentPane().removeAll();
            myJFrame.add(myGamePanel);
            addMenuBar();
            myJFrame.setJMenuBar(jMenuBar);
            myGamePanel.requestFocus();
            myGamePanel.startGameThread();

        } else {
            firstPlay++;
            if (firstPlay == 1) {
                newMenu.startInGameMusic(MAIN_MENU_MUSIC);
            }
            if (jMenuBar != null) {

                myJFrame.setJMenuBar(null);
            }
            myJFrame.getContentPane().removeAll();

            myJFrame.add(new MenuManager(this.myJFrame));
        }

        this.myJFrame.pack();
        this.myJFrame.setVisible(true);
        myJFrame.setLocationRelativeTo(null);
    }
    private void addMenuBar() {
        jMenuBar = new JMenuBar();
        JMenu menu = new JMenu("THE LOST PILLARS OF OOP");
        JButton mainMenuButton = new JButton("Main Menu");
        mainMenuButton.addActionListener(e -> {
            new PlayButtonSound();

            setPlayGame(false);
            try {
                loadGui();
                new MenuManager().startInGameMusic(MAIN_MENU_MUSIC);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }
        });
        JButton saveButton = new JButton("SAVE");
        saveButton.addActionListener(e -> {
            new SerializeMapGenerator(myGameStateFile);
            new PlayButtonSound();

            System.out.println("Serialized " + myGameStateFile + " ... SAVING");
        });
        menu.add(mainMenuButton);
        menu.add(saveButton);

        jMenuBar.add(menu);
    }
    JMenuBar jMenuBar;

    private void setPlayGame(boolean thePlayGame) {
        myPlayGame = thePlayGame;
    }

    public boolean getPlayGame() {
        return myPlayGame;
    }



}
