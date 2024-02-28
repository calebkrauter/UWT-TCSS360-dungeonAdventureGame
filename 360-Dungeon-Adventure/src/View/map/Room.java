package View.map;

import java.awt.image.BufferedImage;

/**
 * @author Makai Marteniz
 * @version 1.0
 */

/**
 * Represents a room.
 */
public class Room {
    /**
     * Represents a reference to the room image.
     */
    private BufferedImage myRoomImage;

    // Note creation of various collision text maps were created by both Makai and James.
    /**
     * Represents a map of collisions.
     */
    private CollisionTile myCollisionMap[][];

    /**
     * Sets the room image.
     * @param theRoomImage
     */
    public void setRoomImage(BufferedImage theRoomImage){
        myRoomImage = theRoomImage;
    }

    /**
     * Gets the room image.
     * @return
     */
    public BufferedImage getRoomImage(){
        return myRoomImage;
    }

    /**
     * Sets the collision map.
     * @param theCollisionMap
     */
    public void setCollisionMap(CollisionTile[][] theCollisionMap){
        myCollisionMap = theCollisionMap;
    }

    /**
     * Gets collision map.
     * @return myCollisionMap
     */
    public CollisionTile[][] getCollisionMap(){
        return myCollisionMap;
    }



}
