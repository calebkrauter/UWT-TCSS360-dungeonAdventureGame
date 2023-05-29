package Model.Item;

import java.awt.image.BufferedImage;

public class ParentItem {

    private BufferedImage myObjectImage;
    private String myObjectName;
    private boolean myCollision = false;
    private int myWorldX, myWorldY;


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
}
