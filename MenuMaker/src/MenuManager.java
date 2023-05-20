import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
//    final private int anchor, iPAD x, iPAD Y, INSET LEFT RIGHT TOP BOTTOM
    final private int GO_LEFT = 65;
    final private int GO_RIGHT = 99;
    final private int GO_UP = -88;
    final private int GO_DOWN = 90;
    private int myIPadX = 50;
    private int myIPadY = 10;
    private int myInsetTop = 0;
    private int myInsetLeft = 0;
    private int myInsetBottom = 0;
    private int myInsetRight= 0;
    String[] myTitles;
    ComponentGenerator mainMenuButton;
    JButton myMainPlayButton;
    JButton myMainLoadButton;
    JButton myMainOptionsButton;
    JButton myEasyModeButton;
    JButton myHardModeButton;
    JButton myStartButton;
    JSlider myMapSizeSlider;
    JSlider myVolumeSlider;
    JCheckBox myAudioToggle;
    JCheckBox myMusicToggle;
    JButton myAboutButton;
    JButton myCreditsButton;
    JButton myBackButton;
    String[] mainMenuTitles;
    String[] myOptionMenuTitles;
    String[] characterSelectTitles;
    String[] gameplayMenuTitles;
    JComponent[] mainMenuComponents;
    JComponent[] optionsMenuComponents;

    // TODO - cleanup code such that each menu has its own class, and there are classes for any actions associated with a given class. Ex, OptionsMenu and OptionsActions.
    public MenuManager(Dimension theScreenSize, JFrame theJFrame) throws IOException {
        this.setLayout(new GridBagLayout());
        myScreenSize = theScreenSize;
        myJframe = theJFrame;
        mainMenuTitles = new String[]{"Play Game", "Load Game", "Options"};
        myOptionMenuTitles = new String[]{"Volume", "Audio on/off", "Music on/off", "About", "Credits", "<--BACK"};
        characterSelectTitles = new String[]{"<--", "SELECT", "-->"};
        gameplayMenuTitles = new String[]{"EASY", "START!", "HARD"};

        addMainMenu();


//        ComponentGenerator characterSelection = new ComponentGenerator(characterSelectTitles, GridBagConstraints.PAGE_START, getMyInsetTop(), getMyInsetLeft(), getMyInsetBottom(), getMyInsetRight(), GO_DOWN);
//        this.add(characterSelection.getComponents()[0][BUTTON], characterSelection.getMyButtonConstraints()[0]);
//        this.add(characterSelection.getComponents()[1][BUTTON], characterSelection.getMyButtonConstraints()[1]);
//        this.add(characterSelection.getComponents()[2][BUTTON], characterSelection.getMyButtonConstraints()[2]);
    }
    private void addMainMenu() {
        setMyTitles(mainMenuTitles);
        setInsets(200, 0, 0, 0);
        mainMenuButton = new ComponentGenerator(getMyTitles(), GridBagConstraints.PAGE_START, getMyInsetTop(), getMyInsetLeft(), getMyInsetBottom(), getMyInsetRight(), GO_DOWN);

        myMainPlayButton = (JButton) mainMenuButton.getComponents()[0][BUTTON];
        myMainLoadButton = (JButton) mainMenuButton.getComponents()[1][BUTTON];
        myMainOptionsButton = (JButton) mainMenuButton.getComponents()[2][BUTTON];

        mainMenuComponents = new JComponent[] {myMainPlayButton, myMainLoadButton, myMainOptionsButton};

        addMainMenuActions();
        this.add(myMainPlayButton, mainMenuButton.getMyButtonConstraints()[0]);
        this.add(myMainLoadButton, mainMenuButton.getMyButtonConstraints()[1]);
        this.add(myMainOptionsButton, mainMenuButton.getMyButtonConstraints()[2]);

    }
    private void addMainMenuActions() {
        myMainPlayButton.addActionListener(e -> playAction());
        myMainLoadButton.addActionListener(e -> loadAction());
        myMainOptionsButton.addActionListener(e -> optionsAction());
    }
    private void addOptionsActions() {
        myVolumeSlider.addChangeListener(e -> setVolumeChange());
        myAudioToggle.addActionListener(e -> toggleAudioChange());
        myMusicToggle.addActionListener(e -> toggleMusicChange());
        myAboutButton.addActionListener(e -> aboutAction());
        myCreditsButton.addActionListener(e -> creditsAction());
        myBackButton.addActionListener(e -> backToMainMenuAction());
    }

    private void playAction() {
        System.out.println("PLAY");
    }
    private void loadAction() {
        System.out.println("LOAD");
    }
    private void optionsAction() {
        disableMenu(mainMenuComponents);
        addOptionsMenu();
        System.out.println("OPTIONS");
    }
    private void addOptionsMenu() {
        setMyTitles(myOptionMenuTitles);
        setInsets(200, 0, 0, 0);

        ComponentGenerator optionButton = new ComponentGenerator(getMyTitles(), GridBagConstraints.PAGE_START, getMyInsetTop(), getMyInsetLeft(), getMyInsetBottom(), getMyInsetRight(), GO_DOWN);

        myVolumeSlider = (JSlider) optionButton.getComponents()[0][SLIDER];
        myAudioToggle = (JCheckBox) optionButton.getComponents()[1][CHECK_BOX];
        myMusicToggle = (JCheckBox) optionButton.getComponents()[2][CHECK_BOX];
        myAboutButton = (JButton) optionButton.getComponents()[3][BUTTON];
        myCreditsButton = (JButton) optionButton.getComponents()[4][BUTTON];
        myBackButton = (JButton) optionButton.getComponents()[5][BUTTON];

        optionsMenuComponents = new JComponent[] {myVolumeSlider, myAudioToggle, myMusicToggle, myAboutButton, myCreditsButton, myBackButton};

        for (int i = 0; i < optionsMenuComponents.length; i++) {
            this.add(optionsMenuComponents[i], optionButton.getMyButtonConstraints()[i]);
        }
        this.add(myVolumeSlider, optionButton.getMyButtonConstraints()[0]);
        this.add(myAudioToggle, optionButton.getMyButtonConstraints()[1]);
        this.add(myMusicToggle, optionButton.getMyButtonConstraints()[2]);
        this.add(myAboutButton, optionButton.getMyButtonConstraints()[3]);
        this.add(myCreditsButton, optionButton.getMyButtonConstraints()[4]);
        addOptionsActions();
    }

    private void setEasyModeAction() {
        System.out.println("EASY");
    }
    private void setHardModeAction() {
        System.out.println("HARD");
    }
    private void startAction() {
        System.out.println("START");
    }

    private void setMapSizeChange() {
        System.out.println("MAPSIZE");
    }
    private void setVolumeChange() {
        System.out.println("VOLUME CHANGE");
    }
    private void toggleAudioChange() {
        System.out.println("audioTOGGLE");
    }
    private void toggleMusicChange() {
        System.out.println("musicTOGGLE");
    }
    private void aboutAction() {
        System.out.println("ABOUT");
    }
    private void creditsAction() {
        System.out.println("Let the credits role");
    }
    private void backToMainMenuAction() {
        disableMenu(optionsMenuComponents);
        enableMenu(mainMenuComponents);
        System.out.println("LETS GO BACK");
    }

    private void disableMenu(JComponent[] theMenu) {
        for (int i = 0; i < theMenu.length; i++) {
            theMenu[i].setEnabled(false);
            theMenu[i].setVisible(false);
        }
    }
    private void enableMenu(JComponent[] theMenu) {
        for (int i = 0; i < theMenu.length; i++) {
            theMenu[i].setEnabled(true);
            theMenu[i].setVisible(true);
        }
    }

    private void setInsets(int theInsetTop, int theInsetLeft, int theInsetBottom, int theInsetRight) {
        setMyInsetTop(theInsetTop);
        setMyInsetLeft(theInsetLeft);
        setMyInsetBottom(theInsetBottom);
        setMyInsetRight(theInsetRight);
    }

    private void setMyInsetTop(int theInsetTop) {
        myInsetTop = theInsetTop;
    }
    private void setMyInsetLeft(int theInsetLeft) {
        myInsetLeft = theInsetLeft;
    }
    private void setMyInsetBottom(int theInsetBottom) {
        myInsetBottom = theInsetBottom;
    }
    private void setMyInsetRight(int theInsetRight) {
        myInsetRight = theInsetRight;
    }

    private int getMyInsetTop() {
        return myInsetTop;
    }
    private int getMyInsetLeft() {
        return myInsetLeft;
    }
    private int getMyInsetBottom() {
        return myInsetBottom;
    }
    private int getMyInsetRight() {
        return myInsetRight;
    }
    private void setMyTitles(String[] theTitles) {
        myTitles = theTitles;
    }
    private String[] getMyTitles() {
        return myTitles;
    }
    final BufferedImage image = ImageIO.read(new File("mainMenu.png"));

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, myJframe.getWidth(), myJframe.getHeight(), null);
        }

}
