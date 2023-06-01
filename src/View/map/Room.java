package View.map;

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

     //= new boolean[textFileMaxCols][textFileMaxRows]

    public void setCollisionMap(boolean[][] theCollisionMap){
        myCollisionMap = theCollisionMap;
    }

    public  CollisionTile[][] getCollisionMap(){
        return myCollisionMap;
    }



}
