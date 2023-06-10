package Controller.MenuManagment;

import Actions.*;
import ChangeMenuAttributes.DisableMenu;
import ChangeMenuAttributes.EnableMenu;
import Components.ComponentGenerator;
import Components.ModifyInsets;
import Controller.LoadSave.CheckFileValidity;
import Controller.LoadSave.DeserializeGameSaves;
import Controller.LoadSave.SerializeGameSaves;
import Controller.LoadSave.SerializeMapGenerator;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;


// TODO - add all constraints to a method, use recursion or a for loop
//      to generate a new button with the constraints and an array to hold the new data
//      for each button.
// TODO - main menu, options menu, pause menu, loadSaves Menu, character select menu, difficulty and map size menu
// TODO - update setinsets in game loop so the buttons move as game is resized.
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
    final private int TEXT_FIELD = 4;
    final private int LABEL = 5;
    //    final private int anchor, iPAD x, iPAD Y, INSET LEFT RIGHT TOP BOTTOM
    final private int GO_LEFT = 65;
    final private int GO_RIGHT = 99;
    final private int GO_UP = -88;
    final private int GO_DOWN = 90;
    final private int FIRST_TIME_PLAYED = 0;
    final private int NOT_FIRST_TIME_PLAYED = 1;
    final private String TREASURE_HUNT_SONG = "TreasureHunt.wav";
    String gameSaveName;
    String[] myTitles;
    ComponentGenerator mainMenuButton;
    JButton myMainPlayButton;
    JButton myMainLoadButton;
    JButton myMainOptionsButton;
    JButton myStartButton;
    JButton myLeftSelect;
    JButton mySelect;
    JButton myRightSelect;
    JSlider myVolumeSlider;
    JToggleButton myMusicToggle;
    JButton myAboutButton;
    JButton myCreditsButton;
    JButton myBackButton;
    String[] mainMenuTitles;
    String[] myOptionMenuTitles;
    String[] characterSelectTitles;
    String[] loadGameTitles;
    String[] gameplayMenuTitles;
    JComponent[] mainMenuComponents;
    JComponent[] optionsMenuComponents;
    JComponent[] characterSelectionComponents;
    ModifyInsets modifyInsets;
    ScreenData screenData;
    JComponent[] gamePlayMenuComponents;
    JTextField myGameStateField;
    JComponent[] loadSaveSelectionComponents;

    public MenuManager() throws IOException {

    }

    private void setMyTitles(String[] theTitles) {
        myTitles = theTitles;
    }
    private String[] getMyTitles() {
        return myTitles;
    }
    final BufferedImage mainImage = ImageIO.read(new File("mainMenu.png"));

    final BufferedImage hero1 = ImageIO.read(new File("res/Hero/Stevey/SteveyDown1 copy.png"));
    final BufferedImage hero2 = ImageIO.read(new File("res/Hero/Archer/ArcherDown1 copy.png"));
    final BufferedImage hero3 = ImageIO.read(new File("res/Hero/down1.png"));
    BufferedImage[] characters = new BufferedImage[] {hero1, hero2, hero3};
    String[] characterNames = new String[] {"Stevy", "Linky", "FRED"};
    HeroSelection heroSelection = new HeroSelection();
    MusicPlayer musicPlayer = new MusicPlayer();
    static boolean myShownCharacter = false;
    DeserializeGameSaves deserializeGameSaves;
    JButton myDelete;
    private final String buttonSound = "typeWriterSound.wav";

    private static int musicPlayedFirstTime = 0;

    // TODO - cleanup code such that each menu has its own class, and there are classes for any actions associated with a given class. Ex, OptionsMenu and OptionsActions.

    public MenuManager(JFrame theJFrame) throws IOException {
        deserializeGameSaves = new DeserializeGameSaves();
        this.setLayout(new GridBagLayout());
        modifyInsets = new ModifyInsets();
        myJframe = theJFrame;
        mainMenuTitles = new String[]{"Play New Game", "Load Game Save", "Options"};
        myOptionMenuTitles = new String[]{"Volume", "About", "Credits", "<--BACK"};
        characterSelectTitles = new String[]{"<--BACK", "<--", "SELECT", "-->"};
        loadGameTitles = new String[]{"<--BACK", "<--", "SELECT", "-->", "DELETE"};
        gameplayMenuTitles = new String[]{"Game File Name", "START!", "<--BACK"};
        screenData = new ScreenData();
        addMainMenu();
    }

    private void addMainMenu() {
        setMyTitles(mainMenuTitles);
        modifyInsets.setInsets(this.getHeight()/2 + 350, 0, 0, 0);
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

        myBackButton = (JButton) characterSelection.getComponents()[0][BUTTON];
        myLeftSelect = (JButton) characterSelection.getComponents()[1][BUTTON];
        mySelect = (JButton) characterSelection.getComponents()[2][BUTTON];
        myRightSelect = (JButton) characterSelection.getComponents()[3][BUTTON];
        characterSelectionComponents = new JComponent[] {myBackButton, myLeftSelect, mySelect, myRightSelect};

        mySelect.setText(characterNames[getHeroSelection()]);
        for (int i = 0; i < characterSelectionComponents.length; i++) {
            this.add(characterSelectionComponents[i], characterSelection.getMyButtonConstraints()[i]);
        }
        new EnableMenu(characterSelectionComponents);
        addCharacterSelectionActions();
    }

    private void addLoadSaveSelectMenu() throws IOException {
        setMyTitles(loadGameTitles);
        modifyInsets.setInsets(this.getHeight() - 100, -800, 0, 0);
        ComponentGenerator loadSaveSelectionComponent = new ComponentGenerator(getMyTitles(), GridBagConstraints.PAGE_START, modifyInsets.getMyInsetTop(), modifyInsets.getMyInsetLeft(), modifyInsets.getMyInsetBottom(), modifyInsets.getMyInsetRight(), GO_RIGHT);
        myBackButton = (JButton) loadSaveSelectionComponent.getComponents()[0][BUTTON];
        myLeftSelect = (JButton) loadSaveSelectionComponent.getComponents()[1][BUTTON];
        mySelect = (JButton) loadSaveSelectionComponent.getComponents()[2][BUTTON];
        myRightSelect = (JButton) loadSaveSelectionComponent.getComponents()[3][BUTTON];
        myDelete = (JButton) loadSaveSelectionComponent.getComponents()[4][BUTTON];
        GridBagConstraints deleteButtonConstraints = new GridBagConstraints();

        deserializeGameSaves.deserializeGameSaves();
        if (deserializeGameSaves.getDeserializedGameSaves().size() > 0) {
            mySelect.setText(deserializeGameSaves.getDeserializedGameSaves().get(0).toString());
        } else {
            mySelect.setText("NO SAVES");
        }
        SerializeMapGenerator serializeMapGenerator = new SerializeMapGenerator();

        loadSaveSelectionComponents = new JComponent[] {myBackButton, myLeftSelect, mySelect, myRightSelect, myDelete};
        for (int i = 0; i < loadSaveSelectionComponents.length; i++) {
            this.add(loadSaveSelectionComponents[i], loadSaveSelectionComponent.getMyButtonConstraints()[i]);
        }
        addLoadSaveActions();
    }

    private void addLoadSaveActions() throws IOException {
        new EnableMenu(loadSaveSelectionComponents);
        myBackButton.addActionListener(e -> {
            new interactionSound(buttonSound);
            new GoBackAction(loadSaveSelectionComponents, mainMenuComponents);

        });
        LoadSaveSelection loadSaveSelection = new LoadSaveSelection();
        SerializeGameSaves serializeGameSaves = new SerializeGameSaves();

        myLeftSelect.addActionListener(e -> {
            new interactionSound(buttonSound);
            try {
                if (deserializeGameSaves == null || deserializeGameSaves.getDeserializedGameSaves() == null || deserializeGameSaves.getDeserializedGameSaves().isEmpty()) {
                    setLoadSaveButtonsEnabled(false);
                    mySelect.setText("NO SAVES");
                } else {
                    setLoadSaveButtonsEnabled(true);
                }
                deserializeGameSaves.deserializeGameSaves();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            try {
                loadSaveSelection.loadSaveSelection(true, false);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            mySelect.setText(deserializeGameSaves.getDeserializedGameSaves().get(loadSaveSelection.getLoadSaveSelection()).toString());
            System.out.println(loadSaveSelection.getLoadSaveSelection() + " GO LEFT INDEX");

        });
        mySelect.addActionListener(e -> {
            new interactionSound(buttonSound);
            try {
                if (deserializeGameSaves == null || deserializeGameSaves.getDeserializedGameSaves() == null || deserializeGameSaves.getDeserializedGameSaves().isEmpty()) {
                    setLoadSaveButtonsEnabled(false);
                    mySelect.setText("NO SAVES");
                } else {
                    setLoadSaveButtonsEnabled(true);
                }
                deserializeGameSaves.deserializeGameSaves();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            startInGameMusic(TREASURE_HUNT_SONG);

            System.out.println(deserializeGameSaves.getDeserializedGameSaves().get(loadSaveSelection.getLoadSaveSelection()).toString());
            try {
                new GUI(true, deserializeGameSaves.getDeserializedGameSaves().get(loadSaveSelection.getLoadSaveSelection()).toString(), getHeroSelection());
            } catch (UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }


        });

        myRightSelect.addActionListener(e -> {
            new interactionSound(buttonSound);
            try {
                if (deserializeGameSaves == null || deserializeGameSaves.getDeserializedGameSaves() == null || deserializeGameSaves.getDeserializedGameSaves().isEmpty()) {
                    setLoadSaveButtonsEnabled(false);
                    mySelect.setText("NO SAVES");
                } else {
                    setLoadSaveButtonsEnabled(true);
                }
                deserializeGameSaves.deserializeGameSaves();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            try {
                loadSaveSelection.loadSaveSelection(true, false);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            mySelect.setText(deserializeGameSaves.getDeserializedGameSaves().get(loadSaveSelection.getLoadSaveSelection()).toString());

            System.out.println(loadSaveSelection.getLoadSaveSelection() + " GO RIGHT INDEX");

        });
        myDelete.addActionListener(e -> {
            new interactionSound(buttonSound);

            try {
                if (deserializeGameSaves == null || deserializeGameSaves.getDeserializedGameSaves() == null || deserializeGameSaves.getDeserializedGameSaves().isEmpty()) {
                    setLoadSaveButtonsEnabled(false);
                    mySelect.setText("NO SAVES");
                } else {

                    setLoadSaveButtonsEnabled(true);
                    File file = new File(deserializeGameSaves.getDeserializedGameSaves().get(indexInBoundsOfList(loadSaveSelection.getLoadSaveSelection())));
                    System.out.println(deserializeGameSaves.getDeserializedGameSaves().get(indexInBoundsOfList(loadSaveSelection.getLoadSaveSelection())));
                    if (file.exists()) {
                        file.delete();
                    }
                    serializeGameSaves.deleteSaveAndSerializeRemainingSaves(indexInBoundsOfList(loadSaveSelection.getLoadSaveSelection()));
                    deserializeGameSaves.deserializeGameSaves();

                    try {
                        if (deserializeGameSaves == null || deserializeGameSaves.getDeserializedGameSaves() == null || deserializeGameSaves.getDeserializedGameSaves().isEmpty()) {
                            setLoadSaveButtonsEnabled(false);
                            mySelect.setText("NO SAVES");
                        } else {
                            setLoadSaveButtonsEnabled(true);
                        }
                        deserializeGameSaves.deserializeGameSaves();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    try {
                        loadSaveSelection.loadSaveSelection(true, false);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    if (deserializeGameSaves == null || deserializeGameSaves.getDeserializedGameSaves() == null || deserializeGameSaves.getDeserializedGameSaves().isEmpty()) {
                        setLoadSaveButtonsEnabled(false);
                        mySelect.setText("NO SAVES");
                    } else {

                        System.out.println(indexInBoundsOfList(loadSaveSelection.getLoadSaveSelection()) + " DELETE NUM INDEX");
                        System.out.println("NAME   " + deserializeGameSaves.getDeserializedGameSaves().get(indexInBoundsOfList(loadSaveSelection.getLoadSaveSelection())).toString());
                        mySelect.setText(deserializeGameSaves.getDeserializedGameSaves().get(indexInBoundsOfList(loadSaveSelection.getLoadSaveSelection())).toString());


                    }
                }

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

    }

    private int indexInBoundsOfList(int theSelection) {
        if (theSelection >= deserializeGameSaves.getDeserializedGameSaves().size()) {
            theSelection = deserializeGameSaves.getDeserializedGameSaves().size()-1;
        } else if (theSelection < 0){
            theSelection = 0;
        }
        return theSelection;
    }

    private void setLoadSaveButtonsEnabled(boolean theEnabledValue) {
        myDelete.setEnabled(theEnabledValue);
        myLeftSelect.setEnabled(theEnabledValue);
        myRightSelect.setEnabled(theEnabledValue);
        mySelect.setEnabled(theEnabledValue);
    }
    private void addOptionsMenu() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        setMyTitles(myOptionMenuTitles);
        modifyInsets.setInsets(this.getHeight()/2 - 150, 0, 0, 0);

        ComponentGenerator optionButton = new ComponentGenerator(getMyTitles(), GridBagConstraints.PAGE_START, modifyInsets.getMyInsetTop(), modifyInsets.getMyInsetLeft(), modifyInsets.getMyInsetBottom(), modifyInsets.getMyInsetRight(), GO_DOWN
        );
        myVolumeSlider = (JSlider) optionButton.getComponents()[0][SLIDER];
        myVolumeSlider.setMinimum(-80);
        myVolumeSlider.setMaximum(6);
        myAboutButton = (JButton) optionButton.getComponents()[1][BUTTON];
        myCreditsButton = (JButton) optionButton.getComponents()[2][BUTTON];
        myBackButton = (JButton) optionButton.getComponents()[3][BUTTON];

        optionsMenuComponents = new JComponent[] {myVolumeSlider, myAboutButton, myCreditsButton, myBackButton};

        for (int i = 0; i < optionsMenuComponents.length; i++) {
            this.add(optionsMenuComponents[i], optionButton.getMyButtonConstraints()[i]);
        }

        setMusicPlayedFirstTime(NOT_FIRST_TIME_PLAYED);

        addOptionsActions();
    }


    private void addMainMenuActions() {
        myMainPlayButton.addActionListener(e -> {
            new interactionSound(buttonSound);
            new DisableMenu(mainMenuComponents);
            try {
                addCharacterSelectMenu();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });
        myMainLoadButton.addActionListener(e -> {
            new interactionSound(buttonSound);
            new DisableMenu(mainMenuComponents);
            try {
                addLoadSaveSelectMenu();
                if (deserializeGameSaves == null || deserializeGameSaves.getDeserializedGameSaves() == null || deserializeGameSaves.getDeserializedGameSaves().isEmpty()) {
                    setLoadSaveButtonsEnabled(false);
                    mySelect.setText("NO SAVES");
                } else {
                    setLoadSaveButtonsEnabled(true);
                }
                deserializeGameSaves.deserializeGameSaves();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        myMainOptionsButton.addActionListener(e -> {
            new interactionSound(buttonSound);
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
        setShownCharacter(true);
        repaint();
        HeroSelection heroSelection = new HeroSelection();
        myLeftSelect.addActionListener(e -> {
            new interactionSound(buttonSound);
            try {
                new HeroSelection(true, false);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            repaint();
        });
        mySelect.addActionListener(e -> {
            new interactionSound(buttonSound);
            heroSelection.setHeroSelected(true);
            addGamePlayMenu();
        });
        myRightSelect.addActionListener(e -> {
            new interactionSound(buttonSound);
            try {
                new HeroSelection(false, true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            repaint();
        });
        myBackButton.addActionListener(e -> {
            new interactionSound(buttonSound);
            new GoBackAction(characterSelectionComponents, mainMenuComponents);
            setShownCharacter(false);
            repaint();
        });
    }
    JLabel myMapSizeInfoLabel;
    JLabel myMapSizeLabel;
    private void addGamePlayMenu() {
        setMyTitles(gameplayMenuTitles);
        modifyInsets.setInsets(this.getHeight()/2 - 200, 0, 0, 0);
        ComponentGenerator gamePlayComponentMaker = new ComponentGenerator(getMyTitles(), GridBagConstraints.PAGE_START, modifyInsets.getMyInsetTop(), modifyInsets.getMyInsetLeft(), modifyInsets.getMyInsetBottom(), modifyInsets.getMyInsetRight(), GO_DOWN);

        // Easy mode provides a chance that there will be less paths overall and generates a simple path from start to end.
        myGameStateField = (JTextField) gamePlayComponentMaker.getComponents()[0][TEXT_FIELD];
        myStartButton = (JButton) gamePlayComponentMaker.getComponents()[1][BUTTON];
        myBackButton = (JButton) gamePlayComponentMaker.getComponents()[2][BUTTON];


        gamePlayMenuComponents = new JComponent[] {myGameStateField, myStartButton, myBackButton};
        for (int i = 0; i < gamePlayMenuComponents.length; i++) {
            this.add(gamePlayMenuComponents[i], gamePlayComponentMaker.getMyButtonConstraints()[i]);
        }

        addGamePlayMenuActions();

    }
    private void addGamePlayMenuActions() {
        new DisableMenu(characterSelectionComponents);
        new EnableMenu(gamePlayMenuComponents);
        setShownCharacter(false);
        myGameStateField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                new interactionSound(buttonSound);

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                new interactionSound(buttonSound);

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                new interactionSound(buttonSound);

            }
        });

        myGameStateField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                myGameStateField.selectAll();
            }
        });
        myStartButton.addActionListener(e -> {
            new interactionSound(buttonSound);
            gameSaveName = myGameStateField.getText();
            CheckFileValidity checkFileValidity = new CheckFileValidity();
            System.out.println(gameSaveName);
            checkFileValidity.checkAlreadyExists(gameSaveName);
            gameSaveName = checkFileValidity.checkValidLength(gameSaveName);
            String gameStateFile = new AppendExtension().appendExtension(gameSaveName);
            SerializeMapGenerator serializeMapGenerator = new SerializeMapGenerator(gameStateFile);
            SerializeGameSaves serializeGameSaves = new SerializeGameSaves();
            try {
                System.out.println(gameStateFile);
                serializeGameSaves.serializeGameSaves(gameStateFile);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            new DisableMenu(gamePlayMenuComponents);
            try {
                new GUI(true, gameStateFile, getHeroSelection());
            } catch (UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            startInGameMusic(TREASURE_HUNT_SONG);
        });

        myBackButton.addActionListener(e -> {
            new interactionSound(buttonSound);
            new GoBackAction(gamePlayMenuComponents, characterSelectionComponents);
            setShownCharacter(true);
            repaint();
        });

    }
    public void stopInGameMusic() {
        musicPlayer.getClip().close();
    }
    public void startInGameMusic(String theMusic) {
        if (musicPlayer != null && musicPlayer.getClip() != null) {

            musicPlayer.getClip().close();
        }
        try {
            musicPlayer.playMusic(theMusic);
        } catch (LineUnavailableException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (UnsupportedAudioFileException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void setMusicPlayedFirstTime(int thePlayValue) {
        musicPlayedFirstTime = thePlayValue;
    }
    public int getMusicPlayedFirstTimeState() {
        return musicPlayedFirstTime;
    }
    private void addOptionsActions() {
        myVolumeSlider.addChangeListener(e -> {
            try {
                new VolumeChange(myVolumeSlider.getValue());
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }
        });
        myAboutButton.addActionListener(e -> {
            new interactionSound(buttonSound);
            try {
                new AboutAction();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        myCreditsButton.addActionListener(e -> {
            new interactionSound(buttonSound);
            new CreditsAction();
        });
        myBackButton.addActionListener(e -> {
            new interactionSound(buttonSound);
            new GoBackAction(optionsMenuComponents, mainMenuComponents);
        });
    }

    public void setShownCharacter(boolean theCharacterIsShown) {
        myShownCharacter = theCharacterIsShown;
    }
    private boolean getShownCharacter() {
        return myShownCharacter;
    }
    @Override
    protected void paintComponent(Graphics g) {
        BufferedImage image = hero1;
        super.paintComponent(g);
        g.drawImage(mainImage, 0, 0, myJframe.getWidth(), myJframe.getHeight(), null);

        if (getShownCharacter()) {
            if (heroSelection.getHeroSelection() == 0) {
                image = characters[heroSelection.getHeroSelection()];
                setHeroSelection(heroSelection.getHeroSelection());
                mySelect.setText(characterNames[getHeroSelection()]);

            } else if (heroSelection.getHeroSelection() == 1) {
                image = characters[heroSelection.getHeroSelection()];
                setHeroSelection(heroSelection.getHeroSelection());
                mySelect.setText(characterNames[getHeroSelection()]);
            } else if (heroSelection.getHeroSelection() == 2) {
                image = characters[heroSelection.getHeroSelection()];
                setHeroSelection(heroSelection.getHeroSelection());
                mySelect.setText(characterNames[getHeroSelection()]);
            }
            g.drawImage(image,(this.getWidth() - image.getWidth(null)) / 2 - 20,
                    (this.getHeight() - image.getHeight(null)) / 2 - 20, 100, 100, null);

        }
    }
    static int mySelection = 0;
    private void setHeroSelection(int theSelection) {
        mySelection = theSelection;
    }
    private int getHeroSelection() {
        return mySelection;
    }
}