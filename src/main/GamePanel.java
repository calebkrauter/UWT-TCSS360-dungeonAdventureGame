package main;

import entity.Hero;

import java.awt.*;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS

    // many tiles are 16x16 pixels, some use more but this is just for practice.
    final int minTileSize = 16;

    // scaling is our friend.
    final int scale = 3;

    // 48x48 tile. Needs to be public so entities can access.
    public final int tileSize = minTileSize * scale;

    // size of our game screen. How many tiles can be displayed on a single
    // screen both horizontally and vertically?
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;

    // 760 pixels
    final int screenWidth = tileSize * maxScreenCol;
    // 576 pixels
    final int screenHeight = tileSize * maxScreenRow;

    // We need a game clock
    // 60 fps = 60 updates a second
    // Keeps program running until it is told to stop/
    // implementing runnable is key to using thread.
    Thread myGameThread;
    KeyHandler myKeyHandler = new KeyHandler();
    Hero myHero = new Hero(this, myKeyHandler);

    int FPS = 60;

    // Default player position
    int heroX = 100;
    int heroY = 100;
    // 4 pixels
    int heroSpeed = 4;



    // constructor for game panel
    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);

        // improves the game's rendering because all the drawing from this component
        // will be done in an offscreen painting buffer.
        this.setDoubleBuffered(true);

        this.addKeyListener(myKeyHandler);

        // this GamePanel can be "focused" to receive key input.
        this.setFocusable(true);

    }


    public void startGameThread(){

        // we instantiate game thread. we pass "this" (the gamepanel class) to
        // the thread constructor.
        myGameThread = new Thread(this);
        myGameThread.start();


    }

    // SLEEP GAME LOOP

//    // When we call myGameThread.start(), this method is automatically called.
//    @Override
//    public void run() {
//        // GAME LOOP / CORE OF THE GAME
//
//        // 1 billion nanoseconds / 60 frames per second = 0.01666 seconds
//        double drawInterval = 1000000000/FPS;
//
//        // current system time + drawinterval (allocated time for a single loop.
//        double nextDrawTime = System.nanoTime() + drawInterval;
//
//        // as long as the thread exists, it repeats the process in the loop
//        while (myGameThread != null) {
//
//            // we need to know how much time has passed from here to
//            // after the repaint. We cannot tell our program when to update
//            // or draw without interval
////            long currTime = System.nanoTime();
////            System.out.println("Current Time: " + currTime);
//
//            // UPDATE information such as positions.
//            update();
//
//
//            // DRAW the screen with the updated version.
//            repaint();
//
//            // Thread must sleep for remaining time.
//            try {
//                // Next we need to find the time left of our 0.016666 second's
//                // following the update and repaint.
//                double remainingTime = nextDrawTime - System.nanoTime();
//                // Sleep takes milliseconds so we must convert.
//                remainingTime = remainingTime/1000000;
//
//                // what if there is no time left after update and repaint?
//                if(remainingTime < 0) {
//                    remainingTime = 0;
//                }
//
//                Thread.sleep((long) remainingTime);
//
//                nextDrawTime += drawInterval;
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }

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
myHero.update();;
    }

    // paint the current state.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // allows us to use additional functions such as....
        Graphics2D g2 = (Graphics2D) g;

        myHero.draw(g2);

        // good practice to save memory.
        g2.dispose();
    }
}
