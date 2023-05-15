package View.tile;

import controller.GamePanel;
import controller.MapGenerator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

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
            tile[0].image = ImageIO.read(new File("/Users/makaimartinez/Desktop/360-Dungeon-Adventure/res/tiles/wall.png"));

            // "|" = yPath = tile[1]
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("/Users/makaimartinez/Desktop/360-Dungeon-Adventure/res/tiles/yPath.png"));

            // "-" = xPath = tile[2]
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("/Users/makaimartinez/Desktop/360-Dungeon-Adventure/res/tiles/xPath.png"));

            // "O" = intersection = tile[3]
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new File("/Users/makaimartinez/Desktop/360-Dungeon-Adventure/res/tiles/intersection.png"));
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

    public void draw (Graphics2D g2) {
        // code from video example with 48 pixel View.tile size.
//        g2.drawImage(View.tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(View.tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(View.tile[2].image, 96, 0, gp.tileSize, gp.tileSize, null);

        // View.tile automation.
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;


        // how many 48 x 48 tiles can fit on the game window, the game window size
        // depends on GamePanel class variables maxScreenCol, maxScreenRow.
        // all three are made and stored only in GamePanel Class.
//        while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
        // anywhere there is "room size" there should be a call to gp.TILE_SIZE

        while(col < gp.mapWidth && row < gp.mapHeight) {

            // had to do this interpreting because we did not do numbers.
            // "#" = walls = tile[0]
            // "|" = yPath = tile[1]
            // "-" = xPath = tile[2]
            // "O" = intersection = tile[3]
            // "S" = start = tile[4]
            // "E" = end = tile[5]
            // "[" = door = tile[6]

            if (myMapRooms[row][col].equals("#")) { // #777474 grey color
                g2.drawImage(tile[0].image, x, y, gp.ROOM_SIZE, gp.ROOM_SIZE, null);
            }
            if (myMapRooms[row][col].equals("|")) {
                g2.drawImage(tile[1].image, x, y, gp.ROOM_SIZE, gp.ROOM_SIZE, null);
            }
            if (myMapRooms[row][col].equals("-")) {
                g2.drawImage(tile[2].image, x, y, gp.ROOM_SIZE, gp.ROOM_SIZE, null);
            }
//            if (myMapRooms[row][col].equals("O")) {
//                g2.drawImage(tile[3].image, x, y, gp.ROOM_SIZE, gp.ROOM_SIZE, null);
//            }
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
            col++;
            x += gp.ROOM_SIZE;
            if (col == gp.mapWidth) {
                col = 0;
                x = 0;
                row++;
                y += gp.ROOM_SIZE;
            }
        }
    }

}
