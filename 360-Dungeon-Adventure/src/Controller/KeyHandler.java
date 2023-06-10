package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Makai Martinez
 * @author Caleb Krauter
 */

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    private GameLoop myGameloop;

    public KeyHandler(GameLoop theGameloop) {

        myGameloop = theGameloop;
    }



    @Override
        public void keyTyped (KeyEvent e){
            //unfinished
        }

        @Override
        public void keyPressed (KeyEvent e){
            // returns a number of the key that was pressed
            int code = e.getKeyCode();

            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_ESCAPE) {
                if (myGameloop.getGameState() == myGameloop.PLAY_STATE) {
                    myGameloop.setGameState(myGameloop.PAUSE_STATE);
                } else if (myGameloop.getGameState() == myGameloop.PAUSE_STATE) {
                    myGameloop.setGameState(myGameloop.PLAY_STATE);
                }
            }
        }

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

