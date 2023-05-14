package View.tile;

import controller.GamePanel;
import View.Map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TileManager {

    public GamePanel gp;

    public Tile[] tile;
    public String[][] myMapRooms;

    // Map here???
    public Map myMap = new Map();

    public TileManager(GamePanel gp){

        this.gp = gp;
        // "10" is number of different tiles
        tile = new Tile[10];
        myMapRooms = myMap.getMap();

        getTileImage();
    }

    private void getTileImage(){
        try{

            // testing an xpath room View.tile
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File("/Users/makaimartinez/Desktop/360-Dungeon-Adventure/res/tiles/xPath.png"));


            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("/Users/makaimartinez/Desktop/360-Dungeon-Adventure/res/tiles/yPath.png"));
//            View.tile[1] = new Tile();
//            View.tile[1].image = ImageIO.read(new File("/Users/makaimartinez/Desktop/360-Dungeon-Adventure/res/tiles/wall1.png"));
//
//            View.tile[2] = new Tile();
//            View.tile[2].image = ImageIO.read(new File("/Users/makaimartinez/Desktop/360-Dungeon-Adventure/res/tiles/water1.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMap () {


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

        // using 5 because that is the width and height of the current test map
        int mapWidth = 5;
        int mapLength = 5;
        int roomSize = 400;




        while(col < mapWidth && row < mapLength) {

            // had to do this interpreting because we did not do numbers.


            if (myMapRooms[col][row].equals("-")) {
                g2.drawImage(tile[0].image, x, y, roomSize, roomSize, null);
            }
            if (myMapRooms[col][row].equals("|")) {
                g2.drawImage(tile[1].image, x, y, roomSize, roomSize, null);
            }

            // increase column by 1 each time until 16 then reset column count, go to next row.
            col++;
            x += roomSize;
            if (col == mapWidth) {
                col = 0;
                x = 0;
                row++;
                y += roomSize;
            }
        }
    }

}
