package Model.Entity;

import Controller.DB.MonsterStatsDB;
import Controller.EntitySetter;
import Controller.GameLoop;
import Controller.KeyHandler;
import Model.Entity.Enemy.Monster;
import Model.Item.Pillar;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Makai
 * Caleb
 */
public abstract class Hero extends Entity {

    KeyHandler keyH;
    GameLoop myGameLoop;

    private final int myScreenX;
    private final int myScreenY;
    private int myNumHealthPotions = 0;

    private int myNumSpeedPotions = 0;

    private int myNumKeys = 0;

    private int myNumPillars = 0;

    private int myBlockChance = 0;

    private int mySpecialChance = 0;

    private int mySpecialDamage = 0;

    private int myNumEndDoorsRemoved = 0;

    public Hero (GameLoop theGP, KeyHandler theKeyH){
        this.myGameLoop = theGP;
        this.keyH = theKeyH;

        myScreenX = (myGameLoop.screenWidth / 2) - (myGameLoop.TILE_SIZE/2);
        myScreenY = (myGameLoop.screenHeight / 2) - (myGameLoop.TILE_SIZE/2);;

        // can be changed, we don't want hitbox as big as character So hit box top left corner is at (8, 16) width is 32 height is 32
        // !!Do not move this or it will cause problems (values being null)!!
        Rectangle solidHitBox = new Rectangle(myGameLoop.TILE_SIZE/3, myGameLoop.TILE_SIZE/4, myGameLoop.TILE_SIZE * 3/4, myGameLoop.TILE_SIZE* 3/4);
        setHitboxDefaultX(solidHitBox.x);
        setHitboxDefaultY(solidHitBox.y);
        setHitBox(solidHitBox);

        setDefaultValues();
    }

    // set the Hero's default values.
    public void setDefaultValues(){
        // Will eventually be set to center of start room. This would be the coordinate of the room
        // times the room size plus half the room size on both x and y.
        // EX: StartRoom = [1, 3], worldX = (1 * 400) + 200, worldY = (3 * 400) + 200
        setWorldX(myGameLoop.myWorldMapMaxCol / 2);
        setWorldY(myGameLoop.myWorldMapMaxRow / 2);
        setSpeed(4);
        //starting direction can vary.
        setDirection("down");
        setHeroImages();
    }

    public int getDefaultSpeed() {
        return 4;
    }
    public int getScreenX(){
        return myScreenX;
    }
    public int getScreenY(){
        return myScreenY;
    }

    public void setNumHealthPotions(int theNumPotions){
        myNumHealthPotions = theNumPotions;
    }

    public int getNumHealthPotions() {
        return myNumHealthPotions;
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
    public void setNumPillars(int theNumPillars) { myNumPillars = theNumPillars; }

    public int getNumPillars() { return myNumPillars; }

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

    public void setNumEndDoorsRemoved(int theNumDoors){
        myNumEndDoorsRemoved = theNumDoors;
    }
    public int getNumEndDoorsRemoved(){
        return myNumEndDoorsRemoved;
    }

    /**
     * The collision cctions for different items.
     *
     * @param theIndex the index of the item in the array of all world items
     */
    static int val = 0;
    public void pickUpItem(int theIndex){

        // if index is not 999 we touched an object. 999 can be any index not used initem array
        if(theIndex != 999) {
            String itemName = myGameLoop.myItems[theIndex].getObjectName();
            switch (itemName) {
                case "Key":
                    setNumKeys(getNumKeys() + 4);   // add items to inventory
                    myGameLoop.myItems[theIndex] = null;   // delete item from the map
                    break;
                case "LeftDoor", "RightDoor", "TopDoor", "BottomDoor":
                    if(getNumKeys() >= 1) {
                        setNumKeys(getNumKeys() - 1);   // delete a key from inventory
                        myGameLoop.myItems[theIndex] = null;   // delete door from the map
                    }
                    break;
                case "Speed Potion":
                    setNumSpeedPotions(getNumSpeedPotions() + 1);
                    myGameLoop.myItems[theIndex] = null;   // delete from the map
                    break;
                case "Health Potion":
                    setNumHealthPotions(getNumHealthPotions() + 1);
                    myGameLoop.myItems[theIndex] = null;   // delete from the map
                    break;
                case "Pillar":
                    setNumPillars(getNumPillars() + 1);
                    myGameLoop.myItems[theIndex] = null;   // delete from the map

                    // for testing
//                    System.out.println("number of pillars in hero inventory: " + getNumPillars());


                    break;
                case "EndDoor":
                    if(getNumPillars() >= 1) {
                        setNumPillars(getNumPillars() - 1); // remove pillar to open end door
//                        int worldX = myGameLoop.myItems[theIndex].getWorldX();
//                        int worldY = myGameLoop.myItems[theIndex].getWorldY();

                        myGameLoop.myItems[theIndex] = null;    // delete from the map
                        setNumEndDoorsRemoved(getNumEndDoorsRemoved() + 1);         // add to count of number of doors removed


//                        myGameLoop.myItems[theIndex] = new Pillar();    // create new pillar in place of end room
//                        myGameLoop.myItems[theIndex].setCollision(false);
//                        myGameLoop.myItems[theIndex].setWorldX(worldX);
//                        myGameLoop.myItems[theIndex].setWorldY(worldY);

                    }
                    break;
            }
        }

    }


//    private void addInteractWithDoorPane(int theIndex) {
//        int openDoorOption = new JOptionPane().showOptionDialog(new JFrame(), "You have "
//                + getNumKeys() + " keys left would you like to use one to open the door?", "ARE YOU SURE?????!!!",
//                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
//
//        if (openDoorOption == JOptionPane.YES_OPTION) {
//            setNumKeys(getNumKeys() - 1);   // delete a key from inventory
//            myGameLoop.myItems[theIndex] = null;   // delete door from the map
//        } else if (openDoorOption == JOptionPane.NO_OPTION) {
//        }
//        val = 0;
//    }

//    private void moveHeroBack() {
//        switch(getDirection()) {
//            case "up":
//                setWorldY(getWorldY() + myGameLoop.ROOM_SIZE/2);
//                break;
//            case "down":
//                setWorldY(getWorldY() - myGameLoop.ROOM_SIZE/2);
//                break;
//            case "left":
//                setWorldX(getWorldX() + myGameLoop.ROOM_SIZE/2);
//                break;
//            case "right":
//                setWorldX(getWorldX() - myGameLoop.ROOM_SIZE/2);
//                break;
//        }
//    }
    public void setHeroImages() {

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
