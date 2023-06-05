package View.map;

import java.awt.image.BufferedImage;

public class Room {

    private BufferedImage myRoomImage;

    boolean myCollisionMap[][];

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

    public boolean[][] getCollisionMap(){
        return myCollisionMap;
    }



}
