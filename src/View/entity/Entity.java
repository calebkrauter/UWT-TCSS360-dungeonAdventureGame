package View.entity;
import java.awt.image.BufferedImage;

// Parent
public class Entity {

    // Player position on world map
    public int myWorldX, myWorldY;
    public int mySpeed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    private String myEntityName;
    public String myDirection;

    // after how many updates should the sprite change?
    public int spriteCounter = 0;
    // number of sprite images is the max
    public int spriteNum = 1;

    private int myHealthPoints = 75;

    private int myMinDamage = 0;

    private int myMaxDamage = 0;

    private int myHitChance = 0;

    public int getWorldX(){
        return myWorldX;
    }
    public void setWorldX (int theX) {
        myWorldX = theX;
    }
    public int getWorldY(){
        return myWorldY;
    }
    public void setWorldY (int theY) {
        myWorldY = theY;
    }

    public void setSpeed(int theSpeed) {
        mySpeed = theSpeed;
    }
    public int getSpeed() {
        return mySpeed;
    }

    public void setEntityName(String theName){
        myEntityName = theName;
    }

    public String getEntityName(){
        return myEntityName;
    }
    public void setDirection(String theDirection){
        myDirection = theDirection;
    }

    public String getDirection(){
        return myDirection;
    }

    public void setHealth(int thePoints) {
        myHealthPoints = thePoints;
    }

    public int getHealth() {
        return myHealthPoints;
    }

    public void setMinDamage(int theDamage) {
        myMinDamage = theDamage;
    }

    public int getMinDamage() {
        return myMinDamage;
    }
    public void setMaxDamage(int theDamage) {
        myMaxDamage = theDamage;
    }

    public int getMaxDamage() {
        return myMaxDamage;
    }

    public void setHitChance(int theChance) {
        myHitChance = theChance;
    }

    public int getHitChance() {
        return myHitChance;
    }



//    // Overriding toString() method of String class
//    @Override
//    public String toString() {
//       return "Name_Health_numHealingPotions_numSpeedPotions_numKeys";
//    }


}
