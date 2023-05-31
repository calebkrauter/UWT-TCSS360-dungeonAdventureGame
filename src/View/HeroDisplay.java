package View;

import Controller.CollisionHandler;
import Controller.GamePanel;
import Controller.KeyHandler;
import Model.entity.Hero;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HeroDisplay {
    KeyHandler myKeyHandler;
    CollisionHandler myCollisionHandler;
    GamePanel myGamePanel;
    Hero myHero;

    public HeroDisplay(GamePanel theGP, KeyHandler theKeyH, Hero theHero, CollisionHandler theCollisionHandler) {
        this.myGamePanel = theGP;
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
                myHero.setWorldY(tempWorldY - myHero.getSpeed());
            }
            else if(myKeyHandler.downPressed == true){
                myHero.setDirection("down");
                myHero.setWorldY(tempWorldY + myHero.getSpeed());
            }
            else if(myKeyHandler.leftPressed == true){
                myHero.setDirection("left");
                myHero.setWorldX(tempWorldX - myHero.getSpeed());
            }
            else if(myKeyHandler.rightPressed == true){
                myHero.setDirection("right");
                myHero.setWorldX(tempWorldX + myHero.getSpeed());
            }

            myHero.setCollisionActive(false);
            myCollisionHandler.checkTile(myHero); //pass hero class as an entity

            myHero.spriteCounter++;
            // every 12 frames player image changes
            if(myHero.spriteCounter > 12) {
                if(myHero.spriteNum == 1){
                    myHero.spriteNum = 2;
                } else if (myHero.spriteNum == 2) {
                    myHero.spriteNum = 1;
                }
                myHero.spriteCounter = 0;
            }
        }



    }

    public void draw(Graphics2D g2){
//        test rectangle
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        if (myHero.getDirection().equals("up")){
            if(myHero.spriteNum == 1){
                image = myHero.getImageUp1();
            } else if (myHero.spriteNum == 2){
                image = myHero.getImageUp2();
            }
        }
        else if (myHero.getDirection().equals("down")){
            if(myHero.spriteNum == 1){
                image = myHero.getImageDown1();
            } else if (myHero.spriteNum == 2){
                image = myHero.getImageDown2();
            }
        }
        else if (myHero.getDirection().equals("left")){
            if(myHero.spriteNum == 1){
                image = myHero.getImageLeft1();
            } else if (myHero.spriteNum == 2){
                image = myHero.getImageLeft2();
            }
        }
        else if (myHero.getDirection().equals("right")){
            if(myHero.spriteNum == 1){
                image = myHero.getImageRight1();
            } else if (myHero.spriteNum == 2){
                image = myHero.getImageRight2();
            }
        }

        // g2.drawImage(image, positionX, positionY, dimensionX, dimensionY, ImageObserver);
        g2.drawImage(image, myHero.getScreenX(), myHero.getScreenY(), myGamePanel.TILE_SIZE, myGamePanel.TILE_SIZE, null);


    }
}
