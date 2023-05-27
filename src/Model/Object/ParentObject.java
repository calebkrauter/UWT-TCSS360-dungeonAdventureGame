package Model.Object;

import java.awt.image.BufferedImage;

public abstract class ParentObject {

    private BufferedImage myObjectImage;
    private String myObjectName;
    private boolean myCollision = false;
    private int myWorldX, myWorldY;


    private void setObjectImage(BufferedImage theObjectImage){
        myObjectImage = theObjectImage;
    }
    private BufferedImage getObjectImage(){
        return myObjectImage;
    }

    private void setObjectName(String theObjectName){
        myObjectName = theObjectName;
    }
    private String getObjectName(){
        return myObjectName;
    }

    private void setCollision(boolean theCollision) {
        myCollision = theCollision;
    }

    private boolean getCollision() {
        return myCollision;
    }

    private void setWorldX(int theWorldX) {
        myWorldX = theWorldX;
    }

    private int getWorldX() {
        return myWorldX;
    }

    private void setWorldY(int theWorldY) {
        myWorldY = theWorldY;
    }

    private int getWorldY() {
        return myWorldY;
    }
}
