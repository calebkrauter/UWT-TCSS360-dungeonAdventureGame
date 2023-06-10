package Model.Item;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class ParentItem {

    /**
     * The Item's image.
     */
    private BufferedImage myItemImage;
    private String myItemName;
    private boolean myCollision = false;
    private boolean myIsCollectible = true;
    private int myWorldX, myWorldY;

    private int myHitboxDefaultX = 0;

    private int myHitboxDefaultY = 0;

    // Solid area of hitbox is entire 48 x 48 tile
    private Rectangle myItemHitbox = new Rectangle(myHitboxDefaultX,myHitboxDefaultY, 48, 48);


    public void setObjectImage(BufferedImage theObjectImage){
        myItemImage = theObjectImage;
    }
    public BufferedImage getObjectImage(){
        return myItemImage;
    }

    public void setObjectName(String theObjectName){
        myItemName = theObjectName;
    }
    public String getObjectName(){
        return myItemName;
    }

    public void setCollision(boolean theCollision) {
        myCollision = theCollision;
    }

    public boolean getCollision() {
        return myCollision;
    }

    public void setWorldX(int theWorldX) {
        myWorldX = theWorldX;
    }

    public int getWorldX() {
        return myWorldX;
    }

    public void setWorldY(int theWorldY) {
        myWorldY = theWorldY;
    }

    public int getWorldY() {
        return myWorldY;
    }

    public void setHitboxDefaultX(int theX){
        myHitboxDefaultX = theX;
    }

    public int getHitboxDefaultX(){
        return myHitboxDefaultX;
    }

    public void setHitboxDefaultY(int theY){
        myHitboxDefaultY = theY;
    }

    public int getHitboxDefaultY(){
        return myHitboxDefaultY;
    }

    public void setItemHitbox(Rectangle theItemHitbox){
        myItemHitbox = theItemHitbox;
    }

    // Solid area of hitbox is entire 48 x 48 tile
    public Rectangle getItemHitbox(){
        return myItemHitbox;
    }

    public boolean getIsCollectible() { return myIsCollectible; }

    public void setIsCollectible(boolean theCollectible) {
        myIsCollectible = theCollectible;
    }


}
