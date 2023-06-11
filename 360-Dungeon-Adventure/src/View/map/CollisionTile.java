package View.map;

import java.awt.*;

/**
 * Makai
 * James
 */
public class CollisionTile {

    private boolean myCollision;

    private Rectangle myTileHitBox = new Rectangle();

    public void setCollision(boolean theCollision) {
        myCollision = theCollision;
    }

    public boolean getCollision(){
        return myCollision;
    }

    public void setTileHitBox(Rectangle theTileHitBox){
        myTileHitBox = theTileHitBox;
    }

    public Rectangle getTileHitBox(){
        return myTileHitBox;
    }

    @Override
    public String toString() {
        String myString;
        if (myCollision){
            myString = "1";
        } else{
            myString = "0";
        }
        return myString;
    }
}
