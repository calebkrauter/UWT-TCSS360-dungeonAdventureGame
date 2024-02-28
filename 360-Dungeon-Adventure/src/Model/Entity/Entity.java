

package Model.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;


/**
 * The entity class that heroes and monsters inherit basic methods from.
 *
 * @author Makai Martinez
 */
public abstract class Entity {

    /**
     * The entity sprites for display.
     */
    private BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    /**
     * The entity's hit box for collision.
     */
    private Rectangle myHitBox;
    /**
     * The entity's collision state.
     */
    private boolean myCollision = false;
    /**
     * The entity's name.
     */
    private String myEntityName;
    /**
     * The entity's direction (left, right, up, down).
     */
    private String myDirection;
    /**
     * The entity's position on world map.
     */
    private int myWorldX, myWorldY;
    /**
     * The entity's speed.
     */
    private int mySpeed;
    /**
     * After how many updates should the sprite change.
     */
    public int mySpriteCounter = 0;
    /**
     * The number of sprite images is the max.
     */
    public int mySpriteNum = 1;
    /**
     * The entity's health.
     */
    private int myHealthPoints = 75;
    /**
     * The entity's minimum damage dealt.
     */
    private int myMinDamage = 0;
    /**
     * The entity's maximum damage dealt.
     */
    private int myMaxDamage = 0;
    /**
     * The entity's chance of hitting and therefor dealing damage.
     */
    private int myHitChance = 0;
    /**
     * The entity's hit box default x position.
     */
    private int myHitboxDefaultX;
    /**
     * The entity's hit box default y position.
     */
    private int myHitboxDefaultY;

    /**
     * Get the entity's world x coordinate.
     * @return
     */
    public int getWorldX(){
        return myWorldX;
    }
    public void setWorldX (int theX) {
        myWorldX = theX;
    }
    /**
     * Get the entity's
     * @return
     */
    public int getWorldY(){
        return myWorldY;
    }
    public void setWorldY (int theY) {
        myWorldY = theY;
    }

    public void setSpeed(int theSpeed) {
        mySpeed = theSpeed;
    }
    /**
     * Get the entity's
     * @return
     */
    public int getSpeed() {
        return mySpeed;
    }
    /**
     * Sets entity name.
     * @param theName
     */
    public void setEntityName(String theName){
        myEntityName = theName;
    }
    /**
     * Get the entity's
     * @return
     */
    public String getEntityName(){
        return myEntityName;
    }

    /**
     * Sets direction.
     * @param theDirection
     */
    public void setDirection(String theDirection){
        myDirection = theDirection;
    }
    /**
     * Get the entity's
     * @return
     */
    public String getDirection(){
        return myDirection;
    }

    /**
     * Sets HP.
     * @param thePoints
     */
    public void setHealth(int thePoints) {
        myHealthPoints = thePoints;
    }
    /**
     * Get the entity's
     * @return
     */
    public int getHealth() {
        return myHealthPoints;
    }

    /**
     * Sets minimum damage.
     * @param theDamage
     */
    public void setMinDamage(int theDamage) {
        myMinDamage = theDamage;
    }
    /**
     * Get the entity's
     * @return
     */
    public int getMinDamage() {
        return myMinDamage;
    }

    /**
     * Sets max damage.
     * @param theDamage
     */
    public void setMaxDamage(int theDamage) {
        myMaxDamage = theDamage;
    }
    /**
     * Get the entity's maximum damage.
     * @return
     */
    public int getMaxDamage() {
        return myMaxDamage;
    }

    /**
     * Sets hit chance.
     * @param theChance
     */
    public void setHitChance(int theChance) {
        myHitChance = theChance;
    }
    /**
     * Get the entity's
     * @return
     */
    public int getHitChance() {
        return myHitChance;
    }

    /**
     * Sets a directional image.
     * @param theBufferedImage
     */
    public void setImageUp1(BufferedImage theBufferedImage) {
        up1 = theBufferedImage;
    }
    /**
     * Sets a directional image.
     * @param theBufferedImage
     */
    public void setImageUp2(BufferedImage theBufferedImage) {
        up2 = theBufferedImage;
    }
    public void setImageDown1(BufferedImage theBufferedImage) {
        down1= theBufferedImage;
    }
    /**
     * Sets a directional image.
     * @param theBufferedImage
     */
    public void setImageDown2(BufferedImage theBufferedImage) {
        down2 = theBufferedImage;
    }
    /**
     * Sets a directional image.
     * @param theBufferedImage
     */
    public void setImagLeft1(BufferedImage theBufferedImage) {
        left1 = theBufferedImage;
    }
    /**
     * Sets a directional image.
     * @param theBufferedImage
     */
    public void setImageLeft2(BufferedImage theBufferedImage) {
        left2 = theBufferedImage;
    }
    /**
     * Sets a directional image.
     * @param theBufferedImage
     */
    public void setImageRight1(BufferedImage theBufferedImage) {
        right1 = theBufferedImage;
    }
    /**
     * Sets a directional image.
     * @param theBufferedImage
     */
    public void setImageRight2(BufferedImage theBufferedImage) {
        right2 = theBufferedImage;
    }
    /**
     * Get the entity's
     * @return
     */
    public BufferedImage getImageUp1() {
        return up1;
    }
    /**
     * Get the entity's
     * @return
     */
    public BufferedImage getImageUp2() {
        return up2;
    }
    /**
     * Get the entity's
     * @return
     */
    public BufferedImage getImageDown1() {
        return down1;
    }
    /**
     * Get the entity's
     * @return
     */
    public BufferedImage getImageDown2() {
        return down2;
    }
    /**
     * Get the entity's
     * @return
     */
    public BufferedImage getImageLeft1() {
        return left1;
    }
    /**
     * Get the entity's
     * @return
     */
    public BufferedImage getImageLeft2() {
        return left2;
    }
    /**
     * Get the entity's
     * @return
     */
    public BufferedImage getImageRight1() {
        return right1;
    }
    /**
     * Get the entity's
     * @return
     */
    public BufferedImage getImageRight2() {
        return right2;
    }
    /**
     * Get the entity's
     * @return
     */
    public Rectangle getHitBox() {
        return myHitBox;
    }

    /**
     * Sets hitbox.
     * @param theHitBox
     */
    public void setHitBox(Rectangle theHitBox) {
        myHitBox = theHitBox;
    }
    /**
     * Get the entity's
     * @return
     */
    public int getHitboxDefaultX(){ return myHitboxDefaultX;}

    /**
     * Sets hitbox default x.
     * @param theX
     */

    public void setHitboxDefaultX(int theX) {
        myHitboxDefaultX = theX;
    }
    /**
     * Get the entity's
     * @return
     */
    public int getHitboxDefaultY(){ return myHitboxDefaultX;}

    /**
     * Sets hitbox default y.
     * @param theY
     */
    public void setHitboxDefaultY(int theY) {
        myHitboxDefaultY = theY;
    }
    /**
     * Get the entity's collision state.
     * @return
     */
    public boolean getCollision(){
        return myCollision;
    }

    /**
     * Sets collision.
     * @param hasCollision
     */
    public void setCollision(boolean hasCollision){
        myCollision = hasCollision;
    }

}
