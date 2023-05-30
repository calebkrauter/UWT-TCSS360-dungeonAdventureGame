package View.map;

import Controller.GamePanel;
import Controller.MapManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class RoomManager {

    private GamePanel gp;
    private MapManager myMapManager;

    private Room[] room;
    private String[][] myMapRooms;


    public RoomManager(GamePanel theGamePanel, MapManager theMapManager){

        this.gp = theGamePanel;
        this.myMapManager = theMapManager;
        // "10" is number of different tiles
        room = new Room[10];
        myMapRooms = theMapManager.getMap();
        setRoomImages();
    }

    private void setRoomImages(){

        try{

            // "#" = walls = tile[0]
            room[0] = new Room();
            room[0].image = ImageIO.read(new File("res/RoomTiles/wall.png"));

            // "|" = yPath = tile[1]
            room[1] = new Room();
            room[1].image = ImageIO.read(new File("res/RoomTiles/yPath.png"));

            // "-" = xPath = tile[2]
            room[2] = new Room();
            room[2].image = ImageIO.read(new File("res/RoomTiles/xPath.png"));

            // "O" = intersection = tile[3]
            room[3] = new Room();
            room[3].image = ImageIO.read(new File("res/RoomTiles/intersection.png"));

            // "S" = start = tile[4]
            room[4] = new Room();
            room[4].image = ImageIO.read(new File("res/RoomTiles/openDoorRoom.png"));

            // "E" = end = tile[5]
            room[5] = new Room();
            room[5].image = ImageIO.read(new File("res/RoomTiles/closedDoorRoom.png"));

            // "[" = door = tile[6]
            room[6] = new Room();
            room[6].image = ImageIO.read(new File("res/RoomTiles/closedDoorRoom.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // had to do this interpreting because we did not do numbers.
    // "#" = walls = tile[0]
    // "|" = yPath = tile[1]
    // "-" = xPath = tile[2]
    // "O" = intersection = tile[3]
    // "S" = start = tile[4]
    // "E" = end = tile[5]
    // "[" = door = tile[6]
    public void draw (Graphics2D g2) {

        int mapCol = 0;
        int mapRow = 0;

        while(mapCol < gp.mapMaxCol && mapRow < gp.mapMaxRow) {

            int mapX = mapCol * gp.ROOM_SIZE;
            int mapY = mapRow * gp.ROOM_SIZE;

            // currently the player's position is always at the center of the screen
            // the center is
            int screenX = mapX - gp.myHero.getWorldX() + gp.myHero.getScreenX();
            int screenY = mapY - gp.myHero.getWorldY() + gp.myHero.getScreenY();


            // create a boundary from the center in both directions based on player
            // screenX or screenY. This is essentially render distance
            if(mapX + gp.ROOM_SIZE > gp.myHero.getWorldX() - gp.myHero.getScreenX() &&
               mapX - gp.ROOM_SIZE < gp.myHero.getWorldX() + gp.myHero.getScreenX() &&
               mapY + gp.ROOM_SIZE > gp.myHero.getWorldY() - gp.myHero.getScreenY() &&
               mapY - gp.ROOM_SIZE < gp.myHero.getWorldY() + gp.myHero.getScreenY()) {

                if (myMapRooms[mapRow][mapCol].equals("#")) { // #777474 grey color
                    g2.drawImage(room[0].image, screenX, screenY, gp.ROOM_SIZE, gp.ROOM_SIZE, null);
                }
                if (myMapRooms[mapRow][mapCol].equals("|")) {
                    g2.drawImage(room[1].image, screenX, screenY, gp.ROOM_SIZE, gp.ROOM_SIZE, null);
                }
                if (myMapRooms[mapRow][mapCol].equals("-")) {
                    g2.drawImage(room[2].image, screenX, screenY, gp.ROOM_SIZE, gp.ROOM_SIZE, null);
                }
                if (myMapRooms[mapRow][mapCol].equals("O")) {
                    g2.drawImage(room[3].image, screenX, screenY, gp.ROOM_SIZE, gp.ROOM_SIZE, null);
                }
                if (myMapRooms[mapRow][mapCol].equals("S")) {
                    g2.drawImage(room[4].image, screenX, screenY, gp.ROOM_SIZE, gp.ROOM_SIZE, null);
                }
                if (myMapRooms[mapRow][mapCol].equals("E")) {
                    g2.drawImage(room[5].image, screenX, screenY, gp.ROOM_SIZE, gp.ROOM_SIZE, null);
                }
                if (myMapRooms[mapRow][mapCol].equals("[")) {
                    g2.drawImage(room[6].image, screenX, screenY, gp.ROOM_SIZE, gp.ROOM_SIZE, null);
                }
            }

            // increase column by 1 each time until 16 then reset column count, go to next row.
            mapCol++;
            if (mapCol == gp.mapMaxCol) {
                mapCol = 0;
                mapRow++;
            }
        }
    }

}
