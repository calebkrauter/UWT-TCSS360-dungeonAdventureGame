package ControllerAndView.map;

import java.awt.image.BufferedImage;

public class Room {

    private BufferedImage myRoomImage;

    private CollisionTile myCollisionMap[][];

    public void setRoomImage(BufferedImage theRoomImage){
        myRoomImage = theRoomImage;
    }

    public BufferedImage getRoomImage(){
        return myRoomImage;
    }

    public void setCollisionMap(CollisionTile[][] theCollisionMap){
        myCollisionMap = theCollisionMap;
    }

    public  CollisionTile[][] getCollisionMap(){
        return myCollisionMap;
    }



}
