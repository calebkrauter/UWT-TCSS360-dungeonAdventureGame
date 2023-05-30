package View;

import Controller.GamePanel;
import Model.Item.ParentItem;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ItemDisplay {
    GamePanel myGamePanel;

    public ItemDisplay(GamePanel theGP) {
        this.myGamePanel = theGP;
    }

    public void draw(Graphics2D g2, ParentItem theItem) {

        int screenX = theItem.getWorldX() - myGamePanel.myHero.getWorldX() + myGamePanel.myHero.getScreenX();
        int screenY = theItem.getWorldY() - myGamePanel.myHero.getWorldY() + myGamePanel.myHero.getScreenY();


        // create a boundary from the center in both directions based on player
        // screenX or screenY. This is essentially render distance
        if (theItem.getWorldX() + myGamePanel.ROOM_SIZE > myGamePanel.myHero.getWorldX() - myGamePanel.myHero.getScreenX() &&
                theItem.getWorldX() - myGamePanel.ROOM_SIZE < myGamePanel.myHero.getWorldX() + myGamePanel.myHero.getScreenX() &&
                theItem.getWorldY() + myGamePanel.ROOM_SIZE > myGamePanel.myHero.getWorldY() - myGamePanel.myHero.getScreenY() &&
                theItem.getWorldY() - myGamePanel.ROOM_SIZE < myGamePanel.myHero.getWorldY() + myGamePanel.myHero.getScreenY()) {
            g2.drawImage(theItem.getObjectImage(), screenX, screenY, myGamePanel.TILE_SIZE, myGamePanel.TILE_SIZE, null);

        }
    }
}