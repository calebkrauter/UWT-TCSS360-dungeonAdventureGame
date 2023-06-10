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

/**
 * @author Caleb Krauter
 * @version 1.0
 */

/**
 * Creates and adds menus.
 */
public class MenuManager extends JPanel {
    /**
     * JFrame.
     */
    private JFrame myJframe;
    /**
     * Button code for BUTTON.
     */
    final private int BUTTON = 0;
    /**
     * Button code for SLIDER.
     */
    final private int SLIDER = 2;
    /**
     * Button code for TEXT_FIELD.
     */
    final private int TEXT_FIELD = 4;
    /**
     * Button code for GO_RIGHT.
     */
    final private int GO_RIGHT = 99;
    /**
     * Button code for GO_DOWN.
     */
    final private int GO_DOWN = 90;
    /**
     * Button code for NOT_FIRST_TIME_PLAYED.
     */
    final private int NOT_FIRST_TIME_PLAYED = 1;
    /**
     * String for the file of the gameplay music.
     */
    final private String TREASURE_HUNT_SONG = "TreasureHunt.wav";
    /**
     * Game save name.
     */
    private String gameSaveName;
    /**
     * Titles.
     */
    private String[] myTitles;
    /**
     * Main menu button.
     */
    private ComponentGenerator mainMenuButton;
    /**
     * A play button.
     */
    private JButton myMainPlayButton;
    /**
     * A Load button.
     */
    private JButton myMainLoadButton;
    /**
     * An Options button.
     */
    private JButton myMainOptionsButton;
    /**
     * A Start button.
     */
    private JButton myStartButton;
    /**
     * A leftSelect button.
     */
    private JButton myLeftSelect;
    /**
     * A select button.
     */
    private JButton mySelect;
    /**
     * A rightSelect button.
     */
    private JButton myRightSelect;
    /**
     * Reference to the delete button.
     */
    private JButton myDelete;
    /**
     * A volume slider.
     */
    private JSlider myVolumeSlider;
    /**
     * A play button.
     */
    private JButton myAboutButton;
    /**
     * A play button.
     */
    private JButton myCreditsButton;
    /**
     * A play button.
     */
    private JButton myBackButton;
    /**
     * Menu titles.
     */
    private String[] mainMenuTitles;
    /**
     * Menu titles.
     */
    private String[] myOptionMenuTitles;
    /**
     * Menu titles.
     */
    private String[] characterSelectTitles;
    /**
     * Menu titles.
     */
    private String[] loadGameTitles;
    /**
     * Menu titles.
     */
    private String[] gameplayMenuTitles;
    /**
     * Menu components.
     */
    private JComponent[] mainMenuComponents;
    /**
     * Menu components.
     */
    private JComponent[] optionsMenuComponents;
    /**
     * Menu components.
     */
    private JComponent[] characterSelectionComponents;
    /**
     * Reference to Mondify Insets.
     */
    private ModifyInsets modifyInsets;

    /**
     * Menu components.
     */
    private JComponent[] gamePlayMenuComponents;
    /**
     * Text field for game save.
     */
    private JTextField myGameStateField;
    /**
     * Menu components.
     */
    private JComponent[] loadSaveSelectionComponents;
    /**
     * Main menu image.
     */
    private final BufferedImage mainImage = ImageIO.read(new File("mainMenu.png"));
    /**
     * Hero Stevey image.
     */
    private final BufferedImage hero1 = ImageIO.read(new File("res/Hero/Stevey/SteveyDown1 copy.png"));
    /**
     * Hero Archer image.
     */
    private final BufferedImage hero2 = ImageIO.read(new File("res/Hero/Archer/ArcherDown1 copy.png"));
    /**
     * Hero Fred image.
     */
    private final BufferedImage hero3 = ImageIO.read(new File("res/Hero/down1.png"));
    /**
     * Array of heros.
     */
    private BufferedImage[] characters = new BufferedImage[] {hero1, hero2, hero3};
    /**
     * Array of hero names.
     */
    private String[] characterNames = new String[] {"Stevy", "Linky", "FRED"};
    /**
     * Hero selection instance.
     */
    private HeroSelection heroSelection = new HeroSelection();
    /**
     * Music player instance.
     */
    private MusicPlayer musicPlayer = new MusicPlayer();
    /**
     * Boolean to determine if the character is shown.
     */
    private static boolean myShownCharacter = false;
    /**
     * Reference to deserialize game saves.
     */
    private DeserializeGameSaves deserializeGameSaves;
    /**
     * Reference to the button sound.
     */
    private final String buttonSound = "typeWriterSound.wav";

    /**
     * The hero selection.
     */
    private static int myHeroSelection = 0;

    /**
     * Constructor.
     * @throws IOException
     */
    public MenuManager() throws IOException {

    }

    /**
     * Constructor.
     * @param theJFrame
     * @throws IOException
     */
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
        addMainMenu();
    }

    /**
     * Sets titles.
     * @param theTitles
     */
    private void setMyTitles(String[] theTitles) {
        myTitles = theTitles;
    }

    /**
     * Gets titles.
     * @return
     */
    private String[] getMyTitles() {
        return myTitles;
    }

    /**
     * Adds the main menu.
     */
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

    /**
     * Adds the character selector menu.
     * @throws IOException
     */
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

    /**
     * Adds the load save selector menu.
     * @throws IOException
     */
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

    /**
     * Adds the options' menu.
     * @throws UnsupportedAudioFileException
     * @throws LineUnavailableException
     * @throws IOException
     */
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

        addOptionsActions();
    }

    /**
     * Adds game play menu.
     */
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

    /**
     * Adds the load save actions.
     * @throws IOException
     */
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

    /**
     * Checks if index is out of bounds.
     * @param theSelection
     * @return theSelection
     */
    private int indexInBoundsOfList(int theSelection) {
        if (theSelection >= deserializeGameSaves.getDeserializedGameSaves().size()) {
            theSelection = deserializeGameSaves.getDeserializedGameSaves().size()-1;
        } else if (theSelection < 0){
            theSelection = 0;
        }
        return theSelection;
    }

    /**
     * Sets the load save buttons enabled.
     * @param theEnabledValue
     */
    private void setLoadSaveButtonsEnabled(boolean theEnabledValue) {
        myDelete.setEnabled(theEnabledValue);
        myLeftSelect.setEnabled(theEnabledValue);
        myRightSelect.setEnabled(theEnabledValue);
        mySelect.setEnabled(theEnabledValue);
    }


    /**
     * Adds main menu actions.
     */
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

    /**
     * Adds character selection actions.
     */
    private void addCharacterSelectionActions() {
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

    /**
     * Adds game play menu actions.
     */
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

    /**
     * Starts in game music
     * @param theMusic
     */
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

    /**
     * Adds options actions.
     */
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

    /**
     * Sets teh shown character to true or false.
     * @param theCharacterIsShown
     */
    public void setShownCharacter(boolean theCharacterIsShown) {
        myShownCharacter = theCharacterIsShown;
    }

    /**
     * Gets if character is shown.
     * @return myShownCharacter
     */
    private boolean getShownCharacter() {
        return myShownCharacter;
    }

    /**
     * Paints data to the GUI like the menu image and characters.
     * @param g the <code>Graphics</code> object to protect
     */
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

    /**
     * Sets the hero selection.
     * @param theSelection
     */
    private void setHeroSelection(int theSelection) {
        myHeroSelection = theSelection;
    }

    /**
     * Gets the hero selection.
     * @return the hero selection value.
     */
    private int getHeroSelection() {
        return myHeroSelection;
    }
}