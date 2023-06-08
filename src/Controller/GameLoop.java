// Makai Martinez 6/7/2023 TCSS 360 A

package Controller;

import Model.Entity.*;
import Model.Item.ParentItem;
import Model.MapGenerator;
import View.EntityDisplay;
import View.HeroDisplay;
import View.ItemDisplay;
import View.map.RoomManager;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.IOException;

public class GameLoop extends JPanel implements Runnable {

    // SCREEN SETTINGS

    // size of our game screen. How many tiles can be displayed on a single
    // screen both horizontally and vertically?
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;

    public final int SCALE = 4;

    private int FPS = 60;

    // many tiles are 16x16 pixels, some use more but this is just for practice.
    final int MIN_TILE_SIZE = 12;   // using 12 to get a 48 x 48 character

    // 48x48 View tile. Needs to be public so entities can access.
    public final int TILE_SIZE = MIN_TILE_SIZE * SCALE;

    // A collision tile is 50 x 50 pixel rectangle (because room visuals are 400x400, and collision txt files are 8x8). 8 * 50 = 400
    public final int COLLISION_TILE_SIZE = (int) 12.25 * SCALE;

    // 760 pixels
    public final int screenWidth = TILE_SIZE * maxScreenCol;
    // 576 pixels
    public final int screenHeight = TILE_SIZE * maxScreenRow;


    // MAP SETTINGS

    // ROOM
    final int MIN_ROOM_SIZE = 100;    // num pixels
    public final int ROOM_SIZE = MIN_ROOM_SIZE * SCALE;

    private final MapGenerator myMapGenerator = new MapGenerator();
    private String[][] myWorldMap = myMapGenerator.getMap();

    // below should be changeable by the view but should change the map generation which would
    // then reflect in these two values below
    public int myWorldMapMaxCol = myMapGenerator.getMyMaxCols();
    public int myWorldMapMaxRow = myMapGenerator.getMyMaxRows();

    // size in pixels (400 * # of Columns)
    private int myWorldMapWidth = ROOM_SIZE * myWorldMapMaxCol;
    private int myWorldHeight = ROOM_SIZE * myWorldMapMaxRow;



    // We need a game clock
    // 60 fps = 60 updates a second
    // Keeps program running until it is told to stop/
    // implementing runnable is key to using thread.
    private Thread myGameThread;

    private RoomManager myRoomManager;


    private CollisionHandler myCollisionHandler;
    private KeyHandler myKeyHandler = new KeyHandler();
    private MouseHandler myMouseHandler = new MouseHandler();





    // THIS CLASS IS WHERE THE START MENU COMMUNICATING WHAT CHARACTER IS INSTANTIATED NEEDS TO BE IMPLEMENTED:
    public Hero myHero;

    // Character types
    public Hero myStarterHero = new StartHero(this, myKeyHandler);
    public Hero myStevey = new Stevey(this, myKeyHandler);
    public Hero myArcher = new Archer(this, myKeyHandler);
    private HeroDisplay myHeroDisplay;




    // ASSETS
    public ParentItem myItems[] = new ParentItem[(myWorldMapMaxCol/10) * 300];   // for every 10 columns we add to the map add 300 item indexes.]; // change num items based on map???
    private ItemSetter myItemSetter;
    private ItemDisplay myItemDisplay;

    public Entity myEntities[] = new Entity[(myWorldMapMaxCol/10) * 150];
    private EntitySetter myEntitySetter;
    private EntityDisplay myEntityDisplay;


    // constructor for game panel
    public GameLoop(String theGameFile) throws IOException, ClassNotFoundException {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);

//        myMapGenerator = new DeserializeMapGenerator(theGameFile).getMyMapGenerator();
        myRoomManager = new RoomManager(this, myWorldMap);

        // some form of getHeroType() method from menu!!
        myHero = myArcher;

        myItemSetter = new ItemSetter(this, myRoomManager);
        myEntitySetter = new EntitySetter(this, myRoomManager);

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

        // listens to when mouse is clicked, pressed, released, has entered or exited
        this.addMouseListener(myMouseHandler);

        // this GamePanel can be "focused" to receive key input.
        this.setFocusable(true);

    }

    public String[][] getWorldMap(){
        return myWorldMap;
    }

    public void SetupGame(){

        // set up items
        myItemSetter.setStartItems();
        myItemSetter.setKeys();
        myItemSetter.setDoors();
        myItemSetter.setPillars();


        // set up entities
        myEntitySetter.setOgres();
        myEntitySetter.setGremlins();
        myEntitySetter.setSkeletons();
        myEntitySetter.setHero();
    }

    public void startGameThread(){

        // we instantiate game thread. we pass "this" (the gamepanel class) to
        // the thread constructor.
        myGameThread = new Thread(this);
        myGameThread.start();
    }

    // DELTA / ACCUMULATOR GAME LOOP
    @Override
    public void run() {

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

//            // Check fps
//            if(timer >= 1000000000) {
//                System.out.println("FPS: " + drawCount);
//                drawCount = 0;
//                timer = 0;
//            }

        }

    }

    // change player position. Important to remember that:
    // X value increases when going to the right
    // Y value increases when going downward
    public void update (){
        myHeroDisplay.update();
    }

    // paint the current state.
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

        // good practice to save memory.
        g2.dispose();
    }
}
