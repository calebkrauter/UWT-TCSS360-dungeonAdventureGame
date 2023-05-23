package Model.entity;
import java.awt.image.BufferedImage;

// Parent
public abstract class Entity {

    // Player position on world map
    private int myWorldX, myWorldY;
    private int mySpeed;
    private BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    private String myEntityName;
    private String myDirection;

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

    public void setImageUp1(BufferedImage theBufferedImage) {
        up1 = theBufferedImage;
    }
    public void setImageUp2(BufferedImage theBufferedImage) {
        up2 = theBufferedImage;
    }
    public void setImageDown1(BufferedImage theBufferedImage) {
        down1= theBufferedImage;
    }
    public void setImageDown2(BufferedImage theBufferedImage) {
        down2 = theBufferedImage;
    }
    public void setImagLeft1(BufferedImage theBufferedImage) {
        left1 = theBufferedImage;
    }
    public void setImageLeft2(BufferedImage theBufferedImage) {
        left2 = theBufferedImage;
    }
    public void setImageRight1(BufferedImage theBufferedImage) {
        right1 = theBufferedImage;
    }
    public void setImageRight2(BufferedImage theBufferedImage) {
        right2 = theBufferedImage;
    }

    public BufferedImage getImageUp1() {
        return up1;
    }
    public BufferedImage getImageUp2() {
        return up2;
    }
    public BufferedImage getImageDown1() {
        return down1;
    }
    public BufferedImage getImageDown2() {
        return down2;
    }
    public BufferedImage getImageLeft1() {
        return left1;
    }
    public BufferedImage getImageLeft2() {
        return left2;
    }
    public BufferedImage getImageRight1() {
        return right1;
    }
    public BufferedImage getImageRight2() {
        return right2;
    }


//    // Overriding toString() method of String class
//    @Override
//    public String toString() {
//       return "Name_Health_numHealingPotions_numSpeedPotions_numKeys";
//    }


}
