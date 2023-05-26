package MenuManagment;

import Actions.*;
import ChangeMenuAttributes.*;
import Components.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import static javax.imageio.ImageIO.read;


// TODO - add all constraints to a method, use recursion or a for loop
//      to generate a new button with the constraints and an array to hold the new data
//      for each button.
// TODO - main menu, options menu, pause menu, loadSaves Menu, character select menu, difficulty and map size menu
public class MenuManager extends JPanel {
    //    BufferedImage image = ImageIO.read(new File("mainMenu.png"));
    Dimension myScreenSize;
    JFrame myJframe;
    BoxLayout bL;
    final int CUR_BUTTON = 0;
    final int CUR_BUTTON_CONSTRAINTS = 1;
    final private int BUTTON = 0;
    final private int TOGGLE_BUTTON = 1;
    final private int SLIDER = 2;
    final private int CHECK_BOX = 3;
    final private int[] BUTTON_CODES;
    //    final private int anchor, iPAD x, iPAD Y, INSET LEFT RIGHT TOP BOTTOM
    final private int GO_LEFT = 65;
    final private int GO_RIGHT = 99;
    final private int GO_UP = -88;
    final private int GO_DOWN = 90;
    String[] myTitles;
    ComponentGenerator mainMenuButton;
    JButton myMainPlayButton;
    JButton myMainLoadButton;
    JButton myMainOptionsButton;
    JButton myEasyModeButton;
    JButton myHardModeButton;
    JButton myStartButton;
    JButton myLeftSelect;
    JButton mySelect;
    JButton myRightSelect;
    JSlider myMapSizeSlider;
    JSlider myVolumeSlider;
    JToggleButton myAudioToggle;
    JToggleButton myMusicToggle;
    JButton myAboutButton;
    JButton myCreditsButton;
    JButton myBackButton;
    String[] mainMenuTitles;
    String[] myOptionMenuTitles;
    String[] characterSelectTitles;
    String[] gameplayMenuTitles;
    JComponent[] mainMenuComponents;
    JComponent[] optionsMenuComponents;
    ModifyInsets modifyInsets;
    ScreenData screenData;
    UpdateSlider updateSlider;
    private void setMyTitles(String[] theTitles) {
        myTitles = theTitles;
    }
    private String[] getMyTitles() {
        return myTitles;
    }
    final BufferedImage mainImage = ImageIO.read(new File("mainMenu.png"));

    final BufferedImage hero1 = ImageIO.read(new File("man1.png"));
    final BufferedImage hero2 = ImageIO.read(new File("man4.png"));
    final BufferedImage hero3 = ImageIO.read(new File("man5.png"));
    BufferedImage[] characters = new BufferedImage[] {hero1, hero2, hero3};
    HeroSelection heroSelection = new HeroSelection();
    ToggleMusicChange toggleMusicChange;
    MusicPlayer musicPlayer = new MusicPlayer();
    boolean showCharacter = false;

    private static int musicPlayedFirstTime = 0;

    // TODO - cleanup code such that each menu has its own class, and there are classes for any actions associated with a given class. Ex, OptionsMenu and OptionsActions.

    public MenuManager(JFrame theJFrame) throws IOException {
        this.setLayout(new GridBagLayout());
        modifyInsets = new ModifyInsets();
        myJframe = theJFrame;
        mainMenuTitles = new String[]{"Play Game", "Load Game", "Options"};
        myOptionMenuTitles = new String[]{"Volume", "Audio on/off", "Music on/off", "About", "Credits", "<--BACK"};
        characterSelectTitles = new String[]{"<--", "SELECT", "-->"};
        gameplayMenuTitles = new String[]{"EASY", "START!", "HARD"};
        BUTTON_CODES = new int[] {BUTTON, TOGGLE_BUTTON, SLIDER, CHECK_BOX};
        screenData = new ScreenData();
        addMainMenu();



    }


    private void addMainMenu() {
        setMyTitles(mainMenuTitles);
        modifyInsets.setInsets(this.getHeight()/2 + 200, 0, 0, 0);
        mainMenuButton = new ComponentGenerator(getMyTitles(), GridBagConstraints.PAGE_START, modifyInsets.getMyInsetTop(), modifyInsets.getMyInsetLeft(), modifyInsets.getMyInsetBottom(), modifyInsets.getMyInsetRight(), GO_DOWN);

        myMainPlayButton = (JButton) mainMenuButton.getComponents()[0][BUTTON];
        myMainLoadButton = (JButton) mainMenuButton.getComponents()[1][BUTTON];
        myMainOptionsButton = (JButton) mainMenuButton.getComponents()[2][BUTTON];

        mainMenuComponents = new JComponent[] {myMainPlayButton, myMainLoadButton, myMainOptionsButton};

        addMainMenuActions();
        this.add(myMainPlayButton, mainMenuButton.getMyButtonConstraints()[0]);
        this.add(myMainLoadButton, mainMenuButton.getMyButtonConstraints()[1]);
        this.add(myMainOptionsButton, mainMenuButton.getMyButtonConstraints()[2]);
    }
    private void addCharacterSelectMenu() throws IOException {
        setMyTitles(characterSelectTitles);
        modifyInsets.setInsets(this.getHeight() - 100, -800, 0, 0);
        ComponentGenerator characterSelection = new ComponentGenerator(getMyTitles(), GridBagConstraints.PAGE_START, modifyInsets.getMyInsetTop(), modifyInsets.getMyInsetLeft(), modifyInsets.getMyInsetBottom(), modifyInsets.getMyInsetRight(), GO_RIGHT);

        myLeftSelect = (JButton) characterSelection.getComponents()[0][BUTTON];
        mySelect = (JButton) characterSelection.getComponents()[1][BUTTON];
        myRightSelect = (JButton) characterSelection.getComponents()[2][BUTTON];
        JComponent[] characterSelectionComponents = new JComponent[] {myLeftSelect, mySelect, myRightSelect};

        for (int i = 0; i < characterSelectionComponents.length; i++) {
            this.add(characterSelectionComponents[i], characterSelection.getMyButtonConstraints()[i]);
        }
        new EnableMenu(characterSelectionComponents);
        addCharacterSelectionActions();
    }
    private void addOptionsMenu() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        setMyTitles(myOptionMenuTitles);
        modifyInsets.setInsets(this.getHeight()/2 - 200, 0, 0, 0);

        ComponentGenerator optionButton = new ComponentGenerator(getMyTitles(), GridBagConstraints.PAGE_START, modifyInsets.getMyInsetTop(), modifyInsets.getMyInsetLeft(), modifyInsets.getMyInsetBottom(), modifyInsets.getMyInsetRight(), GO_DOWN
        );
        myVolumeSlider = (JSlider) optionButton.getComponents()[0][SLIDER];
        myAudioToggle = (JToggleButton) optionButton.getComponents()[1][TOGGLE_BUTTON];
        myMusicToggle = (JToggleButton) optionButton.getComponents()[2][TOGGLE_BUTTON];
        myAboutButton = (JButton) optionButton.getComponents()[3][BUTTON];
        myCreditsButton = (JButton) optionButton.getComponents()[4][BUTTON];
        myBackButton = (JButton) optionButton.getComponents()[5][BUTTON];

        optionsMenuComponents = new JComponent[] {myVolumeSlider, myAudioToggle, myMusicToggle, myAboutButton, myCreditsButton, myBackButton};

        for (int i = 0; i < optionsMenuComponents.length; i++) {
            this.add(optionsMenuComponents[i], optionButton.getMyButtonConstraints()[i]);
        }

        updateAudioSettings();
        setSliderPos();

        myMusicToggle.setSelected(true);
        toggleMusicChange.setMyMusicToggleSelected(true);
        addOptionsActions();
    }
    private void updateAudioSettings() {
        updateSlider = new UpdateSlider(myVolumeSlider);

        toggleMusicChange = new ToggleMusicChange(myMusicToggle);
        if (musicPlayedFirstTime == 0) {

            updateSlider.setMyVolumeSlider();
        }
        musicPlayedFirstTime = 1;
    }

    private void addMainMenuActions() {
        myMainPlayButton.addActionListener(e -> {
            new DisableMenu(mainMenuComponents);
            try {
                addCharacterSelectMenu();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });
        myMainLoadButton.addActionListener(e -> new LoadAction());
        myMainOptionsButton.addActionListener(e -> {
            try {
                new OptionsButtonAction(mainMenuComponents);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try {
                addOptionsMenu();
            } catch (UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void addCharacterSelectionActions() throws IOException {
        int i = 0;
        AtomicBoolean goLeft = new AtomicBoolean(true);
        AtomicBoolean goRight = new AtomicBoolean(false);
        this.add(new AddCharacterSelectorPanel());
        showCharacter = true;
        HeroSelection heroSelection = new HeroSelection();
        myLeftSelect.addActionListener(e -> {
            goLeft.set(true);
            goRight.set(false);
            try {
                new HeroSelection(goLeft.get(), goRight.get());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            repaint();
        });
        mySelect.addActionListener(e -> new HeroSelection());
        myRightSelect.addActionListener(e -> {
            goLeft.set(false);
            goRight.set(true);
            try {
                new HeroSelection(goLeft.get(), goRight.get());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            repaint();
        });

        System.out.println("DO stuff");
        // TODO add actions
    }
    private void setSliderPos() {
        musicPlayedFirstTime = 0;
    }
    private void addOptionsActions() {
        myVolumeSlider.addChangeListener(e -> {
            new VolumeChange(myVolumeSlider.getValue());
        });
        myAudioToggle.addActionListener(e -> new ToggleAudioChange());
        myMusicToggle.addActionListener(e -> {
            try {
                if (myMusicToggle.isSelected()) {
                    new ToggleMusicChange(myMusicToggle, "MainMenu.wav");

                    updateAudioSettings();
                } else {
                    updateAudioSettings();
                    musicPlayer.stopMusic();
                }
            } catch (UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        myAboutButton.addActionListener(e -> new AboutAction());
        myCreditsButton.addActionListener(e -> new CreditsAction());
        myBackButton.addActionListener(e -> new BackToMainMenuAction(optionsMenuComponents, mainMenuComponents));
    }

    @Override
    protected void paintComponent(Graphics g) {
        BufferedImage image = hero1;
        super.paintComponent(g);
        g.drawImage(mainImage, 0, 0, myJframe.getWidth(), myJframe.getHeight(), null);

        if (showCharacter) {
            System.out.println("true");
            if (heroSelection.getMyHeroSelection() == 0) {
                image = characters[heroSelection.getMyHeroSelection()];
            } else if (heroSelection.getMyHeroSelection() == 1) {

                image = characters[heroSelection.getMyHeroSelection()];
            } else if (heroSelection.getMyHeroSelection() == 2) {

                image = characters[heroSelection.getMyHeroSelection()];
            }
            g.drawImage(image,(this.getWidth() - image.getWidth(null)) / 2,
                    (this.getHeight() - image.getHeight(null)) / 2, 50, 50, null);

        }
    }
}