package Model.Entity;

import Controller.GameLoop;
import Controller.KeyHandler;
import Model.Item.Pillar;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Makai Marteniz
 * @author Caleb Krauter
 */

/**
 * The Hero class is a Hero class that sets up initial data and is inherited by different heros.
 */
public abstract class Hero extends Entity {
    /**
     * A reference to KeyHandler.
     */
    private KeyHandler keyH;
    /**
     * A reference to the Game Loop class.
     */
    public GameLoop myGameLoop;
    /**
     * Screen x.
     */
    private final int myScreenX;
    /**
     * Screen y.
     */
    private final int myScreenY;
    /**
     * Reference to the number of health potions.
     */
    private int myNumHealthPotions = 0;
    /**
     * Reference to the number of speed potions.
     */
    private int myNumSpeedPotions = 0;
    /**
     * Reference to the number of keys.
     */
    private int myNumKeys = 0;
    /**
     * Reference to the number of pillars.
     */
    private int myNumPillars = 0;
    /**
     * Reference to the block chance.
     */
    private int myBlockChance = 0;
    /**
     * Reference to the special chance.
     */
    private int mySpecialChance = 0;
    /**
     * Reference to the special damage.
     */
    private int mySpecialDamage = 0;
    /**
     * Reference to the number of doors removed from the end room.
     */
    private int myNumEndDoorsRemoved = 0;

    /**
     * Constructor.
     * @param theGP
     * @param theKeyH
     */
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

    /**
     * set the Hero's default values.
     */
    public void setDefaultValues(){
        setWorldX(myGameLoop.myWorldMapMaxCol / 2);
        setWorldY(myGameLoop.myWorldMapMaxRow / 2);
        setSpeed(getDefaultSpeed());
        //starting direction can vary.
        setDirection("down");
        setHeroImages();
    }

    /**
     * Gets default speed.
     * @return speed integer.
     */
    public int getDefaultSpeed() {
        return 2;
    }

    /**
     * Gets the screen x.
     * @return screen x integer.
     */
    public int getScreenX(){
        return myScreenX;
    }

    /**
     * Gets the screen y.
     * @return screen y integer.
     */
    public int getScreenY(){
        return myScreenY;
    }

    /**
     * Sets the number of keys.
     * @param theKeys
     */
    public void setNumKeys(int theKeys) {
        myNumKeys = theKeys;
    }

    /**
     * Gets the number of keys.
     * @return int number of keys
     */
    public int getNumKeys() {
        return myNumKeys;
    }

    /**
     * Sets the number of pillars.
     * @param theNumPillars
     */
    public void setNumPillars(int theNumPillars) { myNumPillars = theNumPillars; }

    /**
     * Gets the number of pillars.
     * @return the int number of pillars
     */
    public int getNumPillars() { return myNumPillars; }

    /**
     * Sets the block chance.
     * @param theChance
     */
    public void setBlockChance(int theChance){
        myBlockChance = theChance;
    }

    /**
     * Gets the block chance.
     * @return
     */
    public int getBlockChance(){
        return myBlockChance;
    }

    /**
     * Sets the special chance.
     * @param theChance
     */
    public void setSpecialChance(int theChance){
        mySpecialChance = theChance;
    }

    /**
     * Gets the special chance.
     * @return
     */
    public int getSpecialChance(){
        return mySpecialChance;
    }

    /**
     * Sets the special damage.
     * @param theDamage
     */
    public void setSpecialDamage(int theDamage) {
        mySpecialDamage = theDamage;
    }

    /**
     * Gets the special damage.
     * @return
     */
    public int getSpecialDamage() {
        return mySpecialDamage;
    }

    /**
     * Sets the number of end doors removed.
     * @param theNumDoors
     */
    public void setNumEndDoorsRemoved(int theNumDoors){
        myNumEndDoorsRemoved = theNumDoors;
    }

    /**
     * Gets the number of end doors removed.
     * @return
     */
    public int getNumEndDoorsRemoved(){
        return myNumEndDoorsRemoved;
    }

    /**
     * The collision actions for different items.
     *
     * @param theIndex the index of the item in the array of all world items
     */
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
                    setSpeed(getSpeed() + 1);
                    myGameLoop.myItems[theIndex] = null;   // delete from the map
                    System.out.println("You got a health potion. Current speed is: " + getSpeed());
                    break;
                case "Health Potion":
                    setHealth(getHealth() + 20);
                    myGameLoop.myItems[theIndex] = null;   // delete from the map
                    break;
                case "Pillar":
                    if (myGameLoop.myItems[theIndex].getIsCollectible()) {
                        setNumPillars(getNumPillars() + 1);
                        myGameLoop.myItems[theIndex] = null;   // delete from the map
                    }
                    break;
                case "EndDoor":
                    if(getNumPillars() >= 1) {
                        setNumPillars(getNumPillars() - 1); // remove pillar to open end door

                        //saving the end door coordinates
                        int worldX = myGameLoop.myItems[theIndex].getWorldX();
                        int worldY = myGameLoop.myItems[theIndex].getWorldY();

                        myGameLoop.myItems[theIndex] = null;    // delete from the map
                        setNumEndDoorsRemoved(getNumEndDoorsRemoved() + 1);         // add to count of number of doors removed

                        myGameLoop.myItems[theIndex] = new Pillar();    // create new pillar in place of end room
                        myGameLoop.myItems[theIndex].setIsCollectible(false);
                        myGameLoop.myItems[theIndex].setCollision(false);


                        myGameLoop.myItems[theIndex].setWorldX(worldX);
                        myGameLoop.myItems[theIndex].setWorldY(worldY);

                    }
                    break;
            }
        }

    }

    /**
     * Sets Hero images.
     */
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
