package View;

import Controller.GamePanel;
import Model.Item.ParentItem;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ItemDisplay {
    GamePanel myGamePanel;

    public ItemDisplay(GamePanel theGP, ParentItem theItems[]) {
        this.myGamePanel = theGP;
    }

    public void draw(Graphics2D g2, GamePanel theGP) {

        int mapCol = 0;
        int mapRow = 0;
//        while(){}
        BufferedImage image = null;
        int mapX = mapCol * theGP.ROOM_SIZE;
        int mapY = mapRow * theGP.ROOM_SIZE;

        int screenX = mapX - theGP.myHero.getWorldX() + theGP.myHero.getScreenX();
        int screenY = mapY - theGP.myHero.getWorldY() + theGP.myHero.getScreenY();


        // create a boundary from the center in both directions based on player
        // screenX or screenY. This is essentially render distance
        if (mapX + theGP.ROOM_SIZE > theGP.myHero.getWorldX() - theGP.myHero.getScreenX() &&
                mapX - theGP.ROOM_SIZE < theGP.myHero.getWorldX() + theGP.myHero.getScreenX() &&
                mapY + theGP.ROOM_SIZE > theGP.myHero.getWorldY() - theGP.myHero.getScreenY() &&
                mapY - theGP.ROOM_SIZE < theGP.myHero.getWorldY() + theGP.myHero.getScreenY()) {


        }
    }
}