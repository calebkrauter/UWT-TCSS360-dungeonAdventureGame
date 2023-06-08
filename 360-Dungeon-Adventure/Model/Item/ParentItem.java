package Model.Item;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class ParentItem {

    private BufferedImage myObjectImage;
    private String myObjectName;
    private boolean myCollision = false;
    private int myWorldX, myWorldY;

    private int myHitboxDefaultX = 0;

    private int myHitboxDefaultY = 0;

    // Solid area of hitbox is entire 48 x 48 tile
    private Rectangle myItemHitbox = new Rectangle(myHitboxDefaultX,myHitboxDefaultY, 48, 48);


    public void setObjectImage(BufferedImage theObjectImage){
        myObjectImage = theObjectImage;
    }
    public BufferedImage getObjectImage(){
        return myObjectImage;
    }

    public void setObjectName(String theObjectName){
        myObjectName = theObjectName;
    }
    public String getObjectName(){
        return myObjectName;
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



}
