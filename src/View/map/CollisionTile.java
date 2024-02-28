package View.map;

import java.awt.*;

/**
 * @author Makai Marteniz
 * @author James Lee
 */
public class CollisionTile {

    /**
     * Represents the state of collision.
     */
    private boolean myCollision;
    /**
     * Represents a tile hitbox.
     */
    private Rectangle myTileHitBox = new Rectangle();

    /**
     * Sets collision.
     * @param theCollision
     */
    public void setCollision(boolean theCollision) {
        myCollision = theCollision;
    }

    /**
     * Gets the collision state.
     * @return
     */
    public boolean getCollision(){
        return myCollision;
    }

    /**
     * Sets the tile hitbox.
     * @param theTileHitBox
     */
    public void setTileHitBox(Rectangle theTileHitBox){
        myTileHitBox = theTileHitBox;
    }

    /**
     * Overridden toString.
     * @return myString
     */
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
