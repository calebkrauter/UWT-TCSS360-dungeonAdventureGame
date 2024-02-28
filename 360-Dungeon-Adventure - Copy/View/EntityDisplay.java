package View;

import Controller.GameLoop;

import java.awt.*;

public class EntityDisplay {

    GameLoop myGameLoop;

    public EntityDisplay(GameLoop theGP) {
        this.myGameLoop = theGP;
    }

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
