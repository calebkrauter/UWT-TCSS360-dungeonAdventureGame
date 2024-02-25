package View;

import Controller.GameLoop;
import Model.Entity.Entity;

import java.awt.*;

/**
 * @author Makai Marteniz
 * @version 1.0
 */

/**
 * A class for displaying entities.
 */
public class EntityDisplay {
    /**
     * A reference to the Game Loop class.
     */
    private GameLoop myGameLoop;

    /**
     * A constructor.
     * @param theGP
     */
    public EntityDisplay(GameLoop theGP) {
        this.myGameLoop = theGP;
    }

    /**
     * Draws entities.
     * @param g2
     * @param theEntity
     */
    public void draw(Graphics2D g2, Entity theEntity) {

        int screenX = theEntity.getWorldX() - myGameLoop.myHero.getWorldX() + myGameLoop.myHero.getScreenX();
        int screenY = theEntity.getWorldY() - myGameLoop.myHero.getWorldY() + myGameLoop.myHero.getScreenY();


        // create a boundary from the center in both directions based on player
        // screenX or screenY. This is essentially render distance
        if (theEntity.getWorldX() + myGameLoop.ROOM_SIZE > myGameLoop.myHero.getWorldX() - myGameLoop.myHero.getScreenX() &&
                theEntity.getWorldX() - myGameLoop.ROOM_SIZE < myGameLoop.myHero.getWorldX() + myGameLoop.myHero.getScreenX() &&
                theEntity.getWorldY() + myGameLoop.ROOM_SIZE > myGameLoop.myHero.getWorldY() - myGameLoop.myHero.getScreenY() &&
                theEntity.getWorldY() - myGameLoop.ROOM_SIZE < myGameLoop.myHero.getWorldY() + myGameLoop.myHero.getScreenY()) {
            // Only using down 1 bc Ive only recieved 1 image for each monster type
            g2.drawImage(theEntity.getImageDown1(), screenX, screenY, myGameLoop.TILE_SIZE, myGameLoop.TILE_SIZE, null);

        }
    }
}
