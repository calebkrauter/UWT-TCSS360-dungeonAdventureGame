package View.entity;

import Controller.GamePanel;
import Controller.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Hero extends Entity {

    KeyHandler keyH;
    GamePanel gp;

    private final int myScreenX;
    private final int myScreenY;
    private int myNumHealingPotions = 0;

    private int myNumSpeedPotions = 0;

    private int myNumKeys = 0;

    private int myBlockChance = 0;

    private int mySpecialChance = 0;

    private int mySpecialDamage = 0;

    public Hero (GamePanel theGP, KeyHandler theKeyH){
        this.gp = theGP;
        this.keyH = theKeyH;

        myScreenX = (gp.screenWidth / 2) - (gp.TILE_SIZE/2);
        myScreenY = (gp.screenHeight / 2) - (gp.TILE_SIZE/2);;


        setDefaultValues();
        getHeroImage();
    }

    // set the Hero's default values.
    public void setDefaultValues(){

        // Will eventually be set to center of start room. This would be the coordinate of the room
        // times the room size plus half the room size on both x and y.
        // EX: StartRoom = [1, 3], worldX = (1 * 400) + 200, worldY = (3 * 400) + 200
        setWorldX(gp.mapMaxCol / 2);
        setWorldY(gp.mapMaxRow / 2);
        setSpeed(4);
        //starting direction can vary.
        setDirection("down");

    }

    public int getScreenX(){
        return myScreenX;
    }
    public int getScreenY(){
        return myScreenY;
    }

    public void setNumHealingPotions(int theNumPotions){
        myNumHealingPotions = theNumPotions;
    }

    public int getNumHealingPotions() {
        return myNumHealingPotions;
    }

    public void setNumSpeedPotions(int theNumPotions){
        myNumSpeedPotions = theNumPotions;
    }
    public int getNumSpeedPotions(){
        return myNumSpeedPotions;
    }

    public void setNumKeys(int theKeys) {
        myNumKeys = theKeys;
    }
    public int getNumKeys() {
        return myNumKeys;
    }

    public void setBlockChance(int theChance){
        myBlockChance = theChance;
    }
    public int getBlockChance(){
        return myBlockChance;
    }

    public void setSpecialChance(int theChance){
        mySpecialChance = theChance;
    }
    public int getSpecialChance(){
        return mySpecialChance;
    }

    public void setSpecialDamage(int theDamage) {
        mySpecialDamage = theDamage;
    }

    public int getSpecialDamage() {
        return mySpecialDamage;
    }

    public void getHeroImage() {

        try {
            // only have two sprite pngs so its the same two in each direction. Need to make more
            // Need to make more later on.
            up1 = ImageIO.read(new File("res/hero/up1.png"));
            up2 = ImageIO.read(new File("res/hero/up2.png"));
            down1 = ImageIO.read(new File("res/hero/down1.png"));
            down2 = ImageIO.read(new File("res/hero/down2.png"));
            left1 = ImageIO.read(new File("res/hero/left1.png"));
            left2 = ImageIO.read(new File("res/hero/left1.png"));
            right1 = ImageIO.read(new File("res/hero/right1.png"));
            right2 = ImageIO.read(new File("res/hero/right1.png"));

        }catch(IOException e) {
            e.printStackTrace();
        }

    }


    // change player position. Important to remember that:
    // X value increases when going to the right
    // Y value increases when going downward
    // gets called 60 times a seconds in the game loop (GamePanel class)
    public void update(){
        int tempWorldX = getWorldX();
        int tempWorldY = getWorldY();
        // this if statement allows our character to be frozen when
        // we are stationary. Counter doesnt increase unless we press a key.
        if(keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true) {

            if(keyH.upPressed == true){
                setDirection("up");
                setWorldY(tempWorldY - getSpeed());
            }
            else if(keyH.downPressed == true){
                setDirection("down");
                setWorldY(tempWorldY + getSpeed());
            }
            else if(keyH.leftPressed == true){
                setDirection("left");
                setWorldX(tempWorldX - getSpeed());
            }
            else if(keyH.rightPressed == true){
                setDirection("right");
                setWorldX(tempWorldX + getSpeed());
            }
            spriteCounter++;
            // every 12 frames player image changes
            if(spriteCounter > 12) {
                if(spriteNum == 1){
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }



    }

    public void draw(Graphics2D g2){
//        test rectangle
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        if (getDirection().equals("up")){
            if(spriteNum == 1){
                image = up1;
            } else if (spriteNum == 2){
                image = up2;
            }
        }
        else if (getDirection().equals("down")){
            if(spriteNum == 1){
                image = down1;
            } else if (spriteNum == 2){
                image = down2;
            }
        }
        else if (getDirection().equals("left")){
            if(spriteNum == 1){
                image = left1;
            } else if (spriteNum == 2){
                image = left2;
            }
        }
        else if (getDirection().equals("right")){
            if(spriteNum == 1){
                image = right1;
            } else if (spriteNum == 2){
                image = right2;
            }
        }

        // g2.drawImage(image, positionX, positionY, dimensionX, dimensionY, ImageObserver);
        g2.drawImage(image, myScreenX, myScreenY, gp.TILE_SIZE, gp.TILE_SIZE, null);


    }

}
