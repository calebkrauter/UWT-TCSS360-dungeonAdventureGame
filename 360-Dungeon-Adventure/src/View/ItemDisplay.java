package View;

import Controller.GameLoop;
import Model.Item.ParentItem;

import java.awt.*;

/**
 * @author Makai Marteniz
 * @version 1.0
 */

/**
 * Class that displays items.
 */
public class ItemDisplay {
    /**
     * A reference to the Game Loop class.
     */
    private GameLoop myGameLoop;

    /**
     * A constructor.
     * @param theGP
     */
    public ItemDisplay(GameLoop theGP) {
        this.myGameLoop = theGP;
    }

    /**
     * Draws items.
     * @param g2
     * @param theItem
     */
    public void draw(Graphics2D g2, ParentItem theItem) {

        int screenX = theItem.getWorldX() - myGameLoop.myHero.getWorldX() + myGameLoop.myHero.getScreenX();
        int screenY = theItem.getWorldY() - myGameLoop.myHero.getWorldY() + myGameLoop.myHero.getScreenY();


        // create a boundary from the center in both directions based on player
        // screenX or screenY. This is essentially render distance
        if (theItem.getWorldX() + myGameLoop.ROOM_SIZE > myGameLoop.myHero.getWorldX() - myGameLoop.myHero.getScreenX() &&
                theItem.getWorldX() - myGameLoop.ROOM_SIZE < myGameLoop.myHero.getWorldX() + myGameLoop.myHero.getScreenX() &&
                theItem.getWorldY() + myGameLoop.ROOM_SIZE > myGameLoop.myHero.getWorldY() - myGameLoop.myHero.getScreenY() &&
                theItem.getWorldY() - myGameLoop.ROOM_SIZE < myGameLoop.myHero.getWorldY() + myGameLoop.myHero.getScreenY()) {
            g2.drawImage(theItem.getObjectImage(), screenX, screenY, myGameLoop.TILE_SIZE, myGameLoop.TILE_SIZE, null);

        }
    }
}