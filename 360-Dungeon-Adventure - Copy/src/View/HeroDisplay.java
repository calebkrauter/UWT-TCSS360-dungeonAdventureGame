// Makai Martinez 6/7/2023 TCSS 360 A

package View;

import Controller.CollisionHandler;
import Controller.GameLoop;
import Controller.KeyHandler;
import Model.Entity.Enemy.Monster;
import Model.Entity.Entity;
import Model.Entity.Hero;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Caleb Krauter
 * Makai
 * James?
 */
public class HeroDisplay {
    KeyHandler myKeyHandler;
    CollisionHandler myCollisionHandler;
    GameLoop myGameLoop;
    Hero myHero;
    boolean fightEnd = false;
    public HeroDisplay(GameLoop theGP, KeyHandler theKeyH, Hero theHero, CollisionHandler theCollisionHandler) {
        this.myGameLoop = theGP;
        this.myKeyHandler = theKeyH;
        this.myHero = theHero;
        this.myCollisionHandler = theCollisionHandler;
    }


    // change player position. Important to remember that:
    // X value increases when going to the right
    // Y value increases when going downward
    // gets called 60 times a seconds in the game loop (GamePanel class)
    public void update(){
        int tempWorldX = myHero.getWorldX();
        int tempWorldY = myHero.getWorldY();
        // this if statement allows our character to be frozen when
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
            fightEnd = myCollisionHandler.checkMonster(myHero);
//            if (fightEnd) {
//                myHero.setSpeed(0);
//
//                startTimer();
//
//            }
            myHero.setSpeed(myHero.getDefaultSpeed());


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
    private void startTimer() {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Code to be executed after 5 seconds

                timer.cancel(); // Cancel the timer after it expires
                fightEnd = false;
                myHero.setSpeed(0);
            }
        }, 3000);
    }
    public void draw(Graphics2D g2){
//        test rectangle
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

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

        // g2.drawImage(image, positionX, positionY, dimensionX, dimensionY, ImageObserver);
        g2.drawImage(image, myHero.getScreenX(), myHero.getScreenY(), myGameLoop.TILE_SIZE, myGameLoop.TILE_SIZE, null);


    }
}
