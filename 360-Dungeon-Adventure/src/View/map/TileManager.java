package View.map;

import Controller.GamePanel;
import Model.MapGenerator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TileManager {

    private GamePanel gp;

    private Tile[] tile;
    private String[][] myMapRooms;

    // Map here???
    private final MapGenerator myMapGenerator = new MapGenerator();

    public TileManager(GamePanel gp){

        this.gp = gp;
        // "10" is number of different tiles
        tile = new Tile[10];
        myMapRooms = myMapGenerator.getMap();
        getTileImage();
    }

    private void getTileImage(){

        try{

            // "#" = walls = tile[0]
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File("res/room tiles/wall.png"));

            // "|" = yPath = tile[1]
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("res/room tiles/yPath.png"));

            // "-" = xPath = tile[2]
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("res/room tiles/xPath.png"));

            // "O" = intersection = tile[3]
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new File("res/room tiles/intersection.png"));
//
//            // "S" = start = tile[4]
//            tile[4] = new Tile();
//            tile[4].image = ImageIO.read(new File(""));
//
//            // "E" = end = tile[5]
//            tile[5] = new Tile();
//            tile[5].image = ImageIO.read(new File(""));
//
//            // "[" = door = tile[6]
//            tile[6] = new Tile();
//            tile[6].image = ImageIO.read(new File(""));

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
            int screenX = mapX - gp.myHero.worldX + gp.myHero.getMyScreenX();
            int screenY = mapY - gp.myHero.worldY + gp.myHero.getMyScreenY();

//            if(mapX > gp.m)

            if (myMapRooms[mapRow][mapCol].equals("#")) { // #777474 grey color
                g2.drawImage(tile[0].image, screenX, screenY, gp.ROOM_SIZE, gp.ROOM_SIZE, null);
            }
            if (myMapRooms[mapRow][mapCol].equals("|")) {
                g2.drawImage(tile[1].image, screenX, screenY, gp.ROOM_SIZE, gp.ROOM_SIZE, null);
            }
            if (myMapRooms[mapRow][mapCol].equals("-")) {
                g2.drawImage(tile[2].image, screenX, screenY, gp.ROOM_SIZE, gp.ROOM_SIZE, null);
            }
            if (myMapRooms[mapRow][mapCol].equals("O")) {
                g2.drawImage(tile[3].image, screenX, screenY, gp.ROOM_SIZE, gp.ROOM_SIZE, null);
            }
//            if (myMapRooms[row][col].equals("S")) {
//                g2.drawImage(tile[4].image, x, y, gp.ROOM_SIZE, gp.ROOM_SIZE, null);
//            }
//            if (myMapRooms[row][col].equals("E")) {
//                g2.drawImage(tile[5].image, x, y, gp.ROOM_SIZE, gp.ROOM_SIZE, null);
//            }
//            if (myMapRooms[row][col].equals("[")) {
//                g2.drawImage(tile[6].image, x, y, gp.ROOM_SIZE, gp.ROOM_SIZE, null);
//            }


            // increase column by 1 each time until 16 then reset column count, go to next row.
            mapCol++;
            if (mapCol == gp.mapMaxCol) {
                mapCol = 0;
                mapRow++;
            }
        }
    }

}
