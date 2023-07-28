package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Makai Martinez
 * @author Caleb Krauter
 * @version 1.0
 */

/**
 * Handles keys input.
 */
public class KeyHandler implements KeyListener {

    /**
     * Directional presses.
     */
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    /**
     * Reference to game loop class.
     */
    private GameLoop myGameloop;

    /**
     * Cosntructor.
     * @param theGameloop
     */

    public KeyHandler(GameLoop theGameloop) {

        myGameloop = theGameloop;


    }

    public void setUpPressed(boolean theUpPressed) {
        this.upPressed = theUpPressed;
    }
    public void setDownPressed(boolean theDownPressed) {
        this.upPressed = theDownPressed;
    }
    public void setLeftPressed(boolean theLeftPressed) {
        this.upPressed = theLeftPressed;
    }
    public void setRightPressed(boolean theRightPressed) {
        this.upPressed = theRightPressed;
    }

    public boolean getUpPressed() {
        return this.upPressed;
    }
    public boolean getDownPressed() {
        return this.downPressed;
    }
    public boolean getLeftPressed() {
        return this.leftPressed;
    }
    public boolean getRightPressed() {
        return this.rightPressed;
    }

    /**
     * Overridden keyTyped.
     * @param e the event to be processed
     */
    @Override
    public void keyTyped (KeyEvent e){
        //unfinished
    }

    /**
     * Overridden keyPressed.
     * @param e the event to be processed
     */
    @Override
    public void keyPressed (KeyEvent e){
        // returns a number of the key that was pressed
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = true;
            if (myGameloop.getPopUpActive()) {
                System.out.println("WHATUP w");
                myGameloop.setPopUpActive(false);
                setUpPressed(false);
                return;
            }
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
            if (myGameloop.getPopUpActive()) {
                System.out.println("WHATUP s");
                myGameloop.setPopUpActive(false);
                setDownPressed(false);
                return;
            }
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
            if (myGameloop.getPopUpActive()) {
                System.out.println("WHATUP a");
                myGameloop.setPopUpActive(false);
                setLeftPressed(false);
                return;
            }
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
            if (myGameloop.getPopUpActive()) {
                System.out.println("WHATUP d");
                myGameloop.setPopUpActive(false);
                setRightPressed(false);
                return;
            }
        }
        if (code == KeyEvent.VK_ESCAPE) {
            if (myGameloop.getGameState() == myGameloop.PLAY_STATE) {
                myGameloop.setGameState(myGameloop.PAUSE_STATE);
            } else if (myGameloop.getGameState() == myGameloop.PAUSE_STATE) {
                myGameloop.setGameState(myGameloop.PLAY_STATE);
            }
        }
    }
    /**
     * Overridden keyReleased.
     * @param e the event to be processed
     */
    @Override
    public void keyReleased (KeyEvent e){

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }

    }
}

