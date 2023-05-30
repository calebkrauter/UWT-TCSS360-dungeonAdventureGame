package Controller;

import Model.Item.Key;
import Model.Item.ParentItem;
import Model.MapGenerator;
import Model.entity.Archer;
import Model.entity.Hero;
import Model.entity.StartHero;
import Model.entity.Stevey;
import View.HeroDisplay;
import View.ItemDisplay;
import View.map.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS

    // many tiles are 16x16 pixels, some use more but this is just for practice.
    final int MIN_TILE_SIZE = 12;   // using 12 to get a 48 x 48 character

    // size of our game screen. How many tiles can be displayed on a single
    // screen both horizontally and vertically?
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;

    public final int SCALE = 4;

    // 48x48 View tile. Needs to be public so entities can access.
    public final int TILE_SIZE = MIN_TILE_SIZE * SCALE;

    // 760 pixels
    public final int screenWidth = TILE_SIZE * maxScreenCol;
    // 576 pixels
    public final int screenHeight = TILE_SIZE * maxScreenRow;


    // MAP SETTINGS
    final int MIN_ROOM_SIZE = 100;    // num pixels
    public final int ROOM_SIZE = MIN_ROOM_SIZE * SCALE;

    private final MapGenerator myMapGenerator = new MapGenerator();

    // should be changeable by the view
    public final int mapMaxCol = myMapGenerator.getMyMaxCols();
    public final int mapMaxRow = myMapGenerator.getMyMaxRows();


    // in pixels (400 * # of Columns)
    private final int worldWidth = ROOM_SIZE + mapMaxCol;
    private final int worldHeight = ROOM_SIZE + mapMaxRow;



    // We need a game clock
    // 60 fps = 60 updates a second
    // Keeps program running until it is told to stop/
    // implementing runnable is key to using thread.
    private Thread myGameThread;

    private TileManager myTileManager = new TileManager(this, myMapGenerator);
    private MapManager myMapManager = new MapManager();
    private KeyHandler myKeyHandler = new KeyHandler();
    private MouseHandler myMouseHandler = new MouseHandler();






    // THIS IS WHERE PICKING A CHARACTER FROM MENU NEEDS TO BE IMPLEMENTED:
    public Hero myHero;

    // Character types
    public Hero myStarterHero = new StartHero(this, myKeyHandler);
    public Hero myStevey = new Stevey(this, myKeyHandler);
    public Hero myArcher = new Archer(this, myKeyHandler);

    private HeroDisplay myHeroDisplay;

    // ASSETS
    public ParentItem myItems[] = new ParentItem[10];
    public ItemSetter myItemSetter = new ItemSetter(this, myMapManager);
    private ItemDisplay myItemDisplay;


    int FPS = 60;

    // constructor for game panel
    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);

        // some form of getHeroType() method from menu!!
        myHero = myArcher;

        myHeroDisplay = new HeroDisplay(this, myKeyHandler, myHero);
        myItemDisplay = new ItemDisplay(this);
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

    public void SetupGame(){
        myItemSetter.setObject();

        // Sets character's position to center of start room
        Point2D thePoint = new Point2D.Float(0, 0);
        thePoint = myMapManager.getStartPoint();
        myHero.setWorldX((int) thePoint.getX() * ROOM_SIZE + ROOM_SIZE/2 - TILE_SIZE/2);
        myHero.setWorldY((int) thePoint.getX() * ROOM_SIZE + ROOM_SIZE/2 - TILE_SIZE/2);
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
        myTileManager.draw(g2);

        // ITEMS
        for(int i = 0; i < myItems.length; i++) {
            if(myItems[i] != null){
                myItemDisplay.draw(g2, myItems[i]);
            }
        }

        //THE PLAYER
        myHeroDisplay.draw(g2);

        // good practice to save memory.
        g2.dispose();
    }
}
