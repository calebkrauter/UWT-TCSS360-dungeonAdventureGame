// Makai Martinez 6/7/2023 TCSS 360 A

package Controller;

import Controller.LoadSave.DeserializeMapGenerator;
import Model.Entity.*;
import ControllerAndView.Item.ParentItem;
import Model.MapGenerator;
import ControllerAndView.EntityDisplay;
import ControllerAndView.HeroDisplay;
import ControllerAndView.ItemDisplay;
import ControllerAndView.map.RoomManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * @author Makai Marteniz
 * @author Caleb Krauter
 */

public class GameLoop extends JPanel implements Runnable {
    /**
     * The max screen columns.
     */
    public final int maxScreenCol = 16;
    /**
     * Max screen rows.
     */
    public final int maxScreenRow = 12;

    /**
     * Scale.
     */
    public final int SCALE = 4;
    /**
     * FPS.
     */
    private int FPS = 60;

    // many tiles are 16x16 pixels, some use more but this is just for practice.
    /**
     * Min size of tile.
     */
    final int MIN_TILE_SIZE = 12;   // using 12 to get a 48 x 48 character

    // 48x48 View tile. Needs to be public so entities can access.
    /**
     * Tile size.
     */
    public final int TILE_SIZE = MIN_TILE_SIZE * SCALE;

    // A collision tile is 50 x 50 pixel rectangle (because room visuals are 400x400, and collision txt files are 8x8). 8 * 50 = 400
    /**
     * Collision tile size.
     */
    public final int COLLISION_TILE_SIZE = 50;

    // 760 pixels
    /**
     * Screen width.
     */
    public final int screenWidth = TILE_SIZE * maxScreenCol;
    // 576 pixels
    /**
     * Screen height.
     */
    public final int screenHeight = TILE_SIZE * maxScreenRow;

    // MAP SETTINGS
    // ROOM
    /**
     * Min size for room.
     */
    final int MIN_ROOM_SIZE = 100;    // num pixels
    /**
     * Room size.
     */
    public final int ROOM_SIZE = MIN_ROOM_SIZE * SCALE;
    /**
     * A reference to the map generator.
     */
    private final MapGenerator myMapGenerator;
    /**
     * The world map.
     */
    private final String[][] myWorldMap;

    // below should be changeable by the view but should change the map generation which would
    // then reflect in these two values below
    /**
     * The max amount of max cols for the world.
     */
    public int myWorldMapMaxCol;
    /**
     * The max amount of max rows for the world.
     */
    public int myWorldMapMaxRow;

    // size in pixels (400 * # of Columns)
    /**
     * The world map width.
     */
    private int myWorldMapWidth;
    /**
     * The world map height.
     */
    private int myWorldHeight;

    // We need a game clock
    // 60 fps = 60 updates a second
    // Keeps program running until it is told to stop/
    // implementing runnable is key to using thread.
    /**
     * The thread.
     */
    private Thread myGameThread;
    /**
     * Refeence to room manager.
     */
    private RoomManager myRoomManager;
    /**
     * Reference to the collision handler.
     */
    private CollisionHandler myCollisionHandler;
    /**
     * An instance of the key handler.
     */
    private KeyHandler myKeyHandler = new KeyHandler(this);

    // THIS CLASS IS WHERE THE START MENU COMMUNICATING WHAT CHARACTER IS INSTANTIATED NEEDS TO BE IMPLEMENTED:
    /**
     * A hero.
     */
    public Hero myHero;
    // Character types
    /**
     * The starter hero called Fred.
     */
    public Hero myStarterHero;
    /**
     * Stevey hero.
     */
    public Hero myStevey;
    /**
     * Linky hero.
     */
    public Hero myArcher;
    /**
     * A reference to the hero display class.
     */
    private HeroDisplay myHeroDisplay;

    // ASSETS
    /**
     * Items' parents.
     */
    public ParentItem myItems[];
    // for every 10 columns we add to the map add 300 item indexes.
    /**
     * Item Setter reference.
     */
    private ItemSetter myItemSetter;
    /**
     * Item Display reference.
     */
    private ItemDisplay myItemDisplay;

    // ENTITIES
    /**
     * The entities.
     */
    public Entity myEntities[];
    /**
     * The Entity Setter reference.
     */
    private EntitySetter myEntitySetter;
    /**
     * The Entity Display reference.
     */
    private EntityDisplay myEntityDisplay;
    /**
     * The game state value.
     */
    private int myGameState = 2;
    /**
     * The code for play state.
     */
    public final int PLAY_STATE = 1;
    /**
     * The code for pause state.
     */
    public final int PAUSE_STATE = 0;


    /**
     * The constructor.
      * @param theGameFile
     * @param myHeroSelection
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public GameLoop(String theGameFile, int myHeroSelection) throws IOException, ClassNotFoundException {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);

        myMapGenerator = new DeserializeMapGenerator(theGameFile).getMyMapGenerator();
        myWorldMap = myMapGenerator.getMap();
        myWorldMapMaxCol = myMapGenerator.getMyMaxCols();
        myWorldMapMaxRow = myMapGenerator.getMyMaxRows();
        myWorldMapWidth = ROOM_SIZE * myWorldMapMaxCol;
        myWorldHeight = ROOM_SIZE * myWorldMapMaxRow;
        myRoomManager = new RoomManager(this, myWorldMap);

        myItems = new ParentItem[(myWorldMapMaxCol/10) * 300];
        myEntities = new Entity[(myWorldMapMaxCol/10) * 150];
        myStarterHero = new StartHero(this, myKeyHandler);
        myStevey = new Stevey(this, myKeyHandler);
        myArcher = new Archer(this, myKeyHandler);
        // some form of getHeroType() method from menu!!
        myHero = myArcher;

        myItemSetter = new ItemSetter(this, myRoomManager);
        myEntitySetter = new EntitySetter(this, myRoomManager);
        if (myHeroSelection == 0) {
            myHero = myStevey;
        } else if (myHeroSelection == 1) {
            myHero = myArcher;
        } else if (myHeroSelection == 2) {
            myHero = myStarterHero;
        }

        myCollisionHandler = new CollisionHandler(this, myRoomManager);



        myHeroDisplay = new HeroDisplay(this, myKeyHandler, myHero, myCollisionHandler);
        myItemDisplay = new ItemDisplay(this);
        myEntityDisplay = new EntityDisplay(this);
        // sets the objects
        SetupGame();

        // improves the game's rendering because all the drawing from this component
        // will be done in an offscreen painting buffer.
        this.setDoubleBuffered(true);

        // listens to keyboard.
        this.addKeyListener(myKeyHandler);



        // this GamePanel can be "focused" to receive key input.
        this.setFocusable(true);

    }

    /**
     * @return the 2D array representing theWorldMap of rooms.
     */
    public String[][] getWorldMap(){
        return myWorldMap;
    }

    /**
     * Creates items and enemies then sets them according to randomly generated map.
     */
    public void SetupGame(){

        // set up items
        myItemSetter.setStartItems();
        myItemSetter.setEndDoors();
        myItemSetter.setKeys();
        myItemSetter.setDoors();
        myItemSetter.setPillars();
        myItemSetter.setHealthPotions();
        myItemSetter.setSpeedPotions();


        // set up entities
        myEntitySetter.setOgres();
        myEntitySetter.setGremlins();
        myEntitySetter.setSkeletons();
        myEntitySetter.setHero();

        setGameState(PLAY_STATE);
        myGameState = PLAY_STATE;
    }

    /**
     * Sets the game state.
     * @param theGameState
     */
    public void setGameState(int theGameState) {
        myGameState = theGameState;
    }

    /**
     * Gets the game state.
     * @return
     */
    public int getGameState() {
        return myGameState;
    }

    /**
     * Starts the thread.
     */
    public void startGameThread(){

        // we instantiate game thread. we pass "this" (the gamepanel class) to
        // the thread constructor.
        myGameThread = new Thread(this);
        myGameThread.start();
    }

    /**
     * DELTA / ACCUMULATOR GAME LOOP.
     */
    @Override
    public void run() {
        int run = 0;
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currTime;

//        long timer = 0;
//        int drawCount = 0;

        while (myGameThread != null) {

            //check current time
            currTime = System.nanoTime();

            // how much time has passed, divide it by interval, add it to delta
            delta += (currTime - lastTime) / drawInterval;

//            timer += (currTime - lastTime);

            lastTime = currTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
//                drawCount++;
            }

            if(myHero.getNumEndDoorsRemoved() == 4 && run == 0){
                run += 1;
                new JOptionPane().showMessageDialog(null, "YOU WON. To play again go to the main menu and load a new save.\n"
                        + "If you want to look around at the map go for it.\n " +
                        "MAP SYMOLS\n" +
                        "S = START\n" +
                        "E = END \n" +
                        "O = INTERSECTION\n" +
                        "| = VERTICAL PATH\n" +
                        "- = HORIZONTAL PATH\n" +
                        "# = OUT OF BOUNDS \n" +
                        "" + printMap(), "YOU WON.", JOptionPane.PLAIN_MESSAGE);
            }

        }

    }

    /**
     * Prints the map at end of game.
     * @return
     */
    private String printMap() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getWorldMap().length; i++) {
            sb.append("\n");
            for (int j = 0; j < getWorldMap().length; j++) {
                sb.append(getWorldMap()[i][j]);
            }
        }
        return sb.toString();
    }
    // Important to remember that:
    // X value increases when going to the right
    // Y value increases when going downward

    /**
     * Update display with changes in player position.
     */
    public void update (){
        if (myGameState == PLAY_STATE) {
            myHeroDisplay.update();
        }

        // only update player stats/display while myGameState is play
        if(myGameState == PLAY_STATE) {
            myHeroDisplay.update();
        }
        if(myGameState == PAUSE_STATE) {
            //do nun
        }

    }



    /**
     * Paint the current state.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;        // allows us to use additional functions



        // ROOMS
        myRoomManager.draw(g2);

        // ITEMS
        for(int i = 0; i < myItems.length; i++) {
            if(myItems[i] != null){
                myItemDisplay.draw(g2, myItems[i]);
            }
        }

        // ENTITIES
        for (int i = 0; i < myEntities.length; i++) {
            if(myEntities[i] != null){
                myEntityDisplay.draw(g2, myEntities[i]);
            }
        }

        //THE PLAYER
        myHeroDisplay.draw(g2);
        if (getGameState() == PAUSE_STATE) {
            String pauseString = "PAUSE";
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 99f));
            g2.drawString(pauseString, screenWidth/2, screenHeight/2);
        }
        g2.dispose();


    }

}
