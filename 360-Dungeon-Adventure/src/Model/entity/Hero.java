package Model.entity;

import Controller.GamePanel;
import Controller.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public abstract class Hero extends Entity {

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

        // can be changed, we don't want hitbox as big as character
        Rectangle solidHitBox = new Rectangle(theGP.TILE_SIZE/8, theGP.TILE_SIZE/4, theGP.TILE_SIZE * 3/4, theGP.TILE_SIZE* 3/4);
        setHitBox(solidHitBox);

        setDefaultValues();
        getHeroImage();
    }

    // set the Hero's default values.
    public void setDefaultValues(){

        // Will eventually be set to center of start room. This would be the coordinate of the room
        // times the room size plus half the room size on both x and y.
        // EX: StartRoom = [1, 3], worldX = (1 * 400) + 200, worldY = (3 * 400) + 200
        setWorldX(gp.myWorldMapMaxCol / 2);
        setWorldY(gp.myWorldMapMaxRow / 2);
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
            setImageUp1(ImageIO.read(new File("res/hero/up1.png")));
            setImageUp2(ImageIO.read(new File("res/hero/up2.png")));
            setImageDown1(ImageIO.read(new File("res/hero/down1.png")));
            setImageDown2(ImageIO.read(new File("res/hero/down2.png")));
            setImagLeft1(ImageIO.read(new File("res/hero/left1.png")));
            setImageLeft2(ImageIO.read(new File("res/hero/left1.png")));
            setImageRight1(ImageIO.read(new File("res/hero/right1.png")));
            setImageRight2(ImageIO.read(new File("res/hero/right1.png")));

        } catch(IOException e) {
            e.printStackTrace();
        }

    }




}
