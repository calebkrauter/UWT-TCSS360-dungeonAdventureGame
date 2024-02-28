
package Controller;

import Controller.MenuManagment.Actions.interactionSound;
import Controller.MenuManagment.Actions.MusicPlayer;
import Controller.MenuManagment.Actions.VolumeChange;
import Controller.MenuManagment.LoadSave.SerializeMapGenerator;
import Controller.MenuManagment.MenuManager;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
/**
 * @author Caleb Krauter
 * @author Makai Marteniz
 * @version 1.0
 */

/**
 * Displays the window and GUI.
 */
public class GUI {

    /**
     * A JFrame.
     */
    public static final JFrame myJFrame = new JFrame();
    /**
     * A reference to the MenuManager.
     */
    private MenuManager newMenu;
    /**
     * Used to determine if the main menu should be shown or the map.
     */
    private boolean myPlayGame = false;
    /**
     * Game file.
     */
    private String myGameStateFile = "";
    /**
     * Main menu music.
     */
    private String MAIN_MENU_MUSIC = "MainMenu.wav";
    /**
     * Button sound.
     */
    private final String buttonSound = "typeWriterSound.wav";

    /**
     * Reference to music player.
     */
    private MusicPlayer musicPlayer;
    /**
     * Volume change slider.
     */
    private JSlider volumeChangeSlider;
    /**
     * Selection of hero.
     */
    private int myHeroSelection;
    /**
     * Variable for which time we have played.
     */
    private static int firstPlay = 0;
    /**
     * Menu bar.
     */
    private JMenuBar jMenuBar;
    /**
     * Generic value for updating data in the menu bar.
     */
    private static int val = 0;

    /**
     * Constructor.
     * @throws IOException
     * @throws UnsupportedAudioFileException
     * @throws LineUnavailableException
     * @throws ClassNotFoundException
     */
    public GUI() throws IOException, UnsupportedAudioFileException, LineUnavailableException, ClassNotFoundException {
        newMenu = new MenuManager(this.myJFrame);
        loadGui();
        musicPlayer = new MusicPlayer();
    }

    /**
     * Constructor.
     * @param thePlayGame
     * @param theGameStateFile
     * @param theSelection
     * @throws UnsupportedAudioFileException
     * @throws LineUnavailableException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public GUI(boolean thePlayGame, String theGameStateFile, int theSelection) throws UnsupportedAudioFileException, LineUnavailableException, IOException, ClassNotFoundException {
        myPlayGame = thePlayGame;
        myGameStateFile = theGameStateFile;
        myHeroSelection = theSelection;
        setPlayGame(thePlayGame);
        loadGui();
    }

    /**
     * Loads GUI.
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws UnsupportedAudioFileException
     * @throws LineUnavailableException
     */
    void loadGui() throws IOException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException {
        this.myJFrame.setDefaultCloseOperation(3);
        this.myJFrame.setMinimumSize(new Dimension(850, 850));
        myJFrame.setResizable(false);
        GameLoop myGameLoop = null;
        if (getPlayGame()) {
            myGameLoop = new GameLoop(myGameStateFile, myHeroSelection);
            myJFrame.getContentPane().removeAll();
            myJFrame.add(myGameLoop);
            addMenuBar();
            myJFrame.setJMenuBar(jMenuBar);
            myGameLoop.requestFocus();
            myGameLoop.startGameThread();

        } else {
            val = 0;
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

    /**
     * Menu bar and buttons.
     */
    private void addMenuBar() {
        jMenuBar = new JMenuBar();
        JMenu menu = new JMenu("THE LOST PILLARS OF OOP");
        JButton mainMenuButton = new JButton("Main Menu");
        mainMenuButton.addActionListener(e -> {
            new interactionSound(buttonSound);

            setPlayGame(false);
            try {
                loadGui();
                val = 1;
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
            new interactionSound(buttonSound);

            System.out.println("Serialized " + myGameStateFile + " ... SAVING");
        });
        volumeChangeSlider = new JSlider();
        volumeChangeSlider.setMaximum(6);
        volumeChangeSlider.setMinimum(-80);
        if (getPlayGame()) {
            val = 0;
            setSlider();
        }
        volumeChangeSlider.addChangeListener(e -> {
            try {
                new VolumeChange(volumeChangeSlider.getValue());
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }
        });

        menu.add(mainMenuButton);
        menu.add(saveButton);

        JMenu audioMenu = new JMenu("MUSIC");
        audioMenu.add(volumeChangeSlider);

        jMenuBar.add(menu);
        jMenuBar.add(audioMenu);
    }

    /**
     * Setter for the volume slider.
     */
    private void setSlider() {
        if (val == 1) {
            volumeChangeSlider.setValue(new VolumeChange().getMusicVolume());
        }
    }

    /**
     * Setter for play game.
     * @param thePlayGame
     */
    private void setPlayGame(boolean thePlayGame) {
        myPlayGame = thePlayGame;
    }

    /**
     * Getter for play game.
     * @return
     */
    public boolean getPlayGame() {
        return myPlayGame;
    }



}
