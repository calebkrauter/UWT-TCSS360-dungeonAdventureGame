
package MenuManagment;

import Actions.interactionSound;
import Actions.MusicPlayer;
import Actions.VolumeChange;
import Controller.GameLoop;
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
    private final String buttonSound = "typeWriterSound.wav";

    MusicPlayer musicPlayer;
    JSlider volumeChangeSlider;
    private int myHeroSelection;
    public GUI() throws IOException, UnsupportedAudioFileException, LineUnavailableException, ClassNotFoundException {
        screenData = new ScreenData(this.myJFrame.getWidth(), this.myJFrame.getHeight());
        newMenu = new MenuManager(this.myJFrame);
        loadGui();
        musicPlayer = new MusicPlayer();
    }
    public GUI(boolean thePlayGame, String theGameStateFile, int theSelection) throws UnsupportedAudioFileException, LineUnavailableException, IOException, ClassNotFoundException {
        myPlayGame = thePlayGame;
        myGameStateFile = theGameStateFile;
        myHeroSelection = theSelection;
        setPlayGame(thePlayGame);
        loadGui();
    }
    static int firstPlay = 0;

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
    JMenuBar jMenuBar;
    static int val = 0;
    private void setSlider() {
        if (val == 1) {
            volumeChangeSlider.setValue(new VolumeChange().getMusicVolume());
        }
    }
    private void setPlayGame(boolean thePlayGame) {
        myPlayGame = thePlayGame;
    }

    public boolean getPlayGame() {
        return myPlayGame;
    }



}
