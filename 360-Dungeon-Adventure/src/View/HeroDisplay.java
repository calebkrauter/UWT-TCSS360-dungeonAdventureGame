// Makai Martinez 6/7/2023 TCSS 360 A

package View;

import Controller.CollisionHandler;
import Controller.GameLoop;
import Controller.KeyHandler;
import Model.Entity.Hero;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Caleb Krauter
 * @author Makai Martinez
 */
public class HeroDisplay {
    /**
     * The KeyHandler that reads key input needs to communicate with how the player's character is displayed.
     */
    KeyHandler myKeyHandler;
    /**
     * The CollisionHandler that checks for collision with items, walls, and monsters needs to communicate with how the
     * player's character is displayed.
     */
    CollisionHandler myCollisionHandler;
    /**
     * The GameLoop that runs the game needs to share world values with the character display.
     */
    GameLoop myGameLoop;
    /**
     * The Player's chosen character.
     */
    Hero myHero;

    /**
     * Displays the player's character.
     *
     * @param theGP The GameLoop that runs the game needs to share world values with the character display.
     * @param theKeyH The KeyHandler that reads key input needs to communicate with how the player's character is displayed.
     * @param theHero The Player's chosen character.
     * @param theCollisionHandler The CollisionHandler that checks for collision with items, walls, and monsters needs to communicate with how the
     * player's character is displayed.
     */
    public HeroDisplay(GameLoop theGP, KeyHandler theKeyH, Hero theHero, CollisionHandler theCollisionHandler) {
        this.myGameLoop = theGP;
        this.myKeyHandler = theKeyH;
        this.myHero = theHero;
        this.myCollisionHandler = theCollisionHandler;
    }

    // Important to remember that:
    // X value increases when going to the right
    // Y value increases when going downward
    /**
     * Gets called 60 times a seconds in the game loop to update player position. This is where collision checks are
     * also called.
     */
    public void update(){
        int tempWorldX = myHero.getWorldX();
        int tempWorldY = myHero.getWorldY();
        // This if statement allows our character to be frozen when
        // we are stationary. Counter doesnt increase unless we press a key.
        if(myKeyHandler.upPressed == true || myKeyHandler.downPressed == true ||
                myKeyHandler.leftPressed == true || myKeyHandler.rightPressed == true) {

            if(myKeyHandler.upPressed == true){
                myHero.setDirection("up");
            }
            else if(myKeyHandler.downPressed == true){
                myHero.setDirection("down");
            }
            else if(myKeyHandler.leftPressed == true){
                myHero.setDirection("left");
            }
            else if(myKeyHandler.rightPressed == true){
                myHero.setDirection("right");
            }

            // Check tile collision
            myHero.setCollision(false);
            myCollisionHandler.checkTile(myHero); // pass hero class as an Entity

            // Check item collision
            int myItemIndex = myCollisionHandler.checkItem(myHero, true); // pass hero class as an Entity
            myHero.pickUpItem(myItemIndex);
            myCollisionHandler.checkMonster(myHero);

            // if collision is false player can move
            if (myHero.getCollision() == false) {

                switch (myHero.getDirection()) {
                    case "up":
                        myHero.setWorldY(tempWorldY - myHero.getSpeed());
                        break;
                    case "down":
                        myHero.setWorldY(tempWorldY + myHero.getSpeed());
                        break;
                    case "left":
                        myHero.setWorldX(tempWorldX - myHero.getSpeed());
                        break;
                    case "right":
                        myHero.setWorldX(tempWorldX + myHero.getSpeed());
                        break;
                }
            }

            myHero.mySpriteCounter++;
            // every 12 frames player image changes
            if(myHero.mySpriteCounter > 12) {
                if(myHero.mySpriteNum == 1){
                    myHero.mySpriteNum = 2;
                } else if (myHero.mySpriteNum == 2) {
                    myHero.mySpriteNum = 1;
                }
                myHero.mySpriteCounter = 0;
            }
        }



    }

    /**
     * Draws the player's character at the current position on the map.
     * @param g2 Graphics2D for drawing image.
     */
    public void draw(Graphics2D g2){

        BufferedImage image = null;

        if (myHero.getDirection().equals("up")){
            if(myHero.mySpriteNum == 1){
                image = myHero.getImageUp1();
            } else if (myHero.mySpriteNum == 2){
                image = myHero.getImageUp2();
            }
        }
        else if (myHero.getDirection().equals("down")){
            if(myHero.mySpriteNum == 1){
                image = myHero.getImageDown1();
            } else if (myHero.mySpriteNum == 2){
                image = myHero.getImageDown2();
            }
        }
        else if (myHero.getDirection().equals("left")){
            if(myHero.mySpriteNum == 1){
                image = myHero.getImageLeft1();
            } else if (myHero.mySpriteNum == 2){
                image = myHero.getImageLeft2();
            }
        }
        else if (myHero.getDirection().equals("right")){
            if(myHero.mySpriteNum == 1){
                image = myHero.getImageRight1();
            } else if (myHero.mySpriteNum == 2){
                image = myHero.getImageRight2();
            }
        }
        g2.drawImage(image, myHero.getScreenX(), myHero.getScreenY(), myGameLoop.TILE_SIZE, myGameLoop.TILE_SIZE, null);


    }
}
