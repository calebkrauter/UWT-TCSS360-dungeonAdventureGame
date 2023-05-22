package View.map;

import Controller.GamePanel;
import Model.MapGenerator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {

    private GamePanel gp;
    int mapTileNum[][];

    private Tile[] tile;
    //mapTileNum = new int[gp.max]
    private RoomTile[] roomTile;
    private String[][] myMapRooms;

    // Map here???
    private final MapGenerator myMapGenerator = new MapGenerator();

    public TileManager(GamePanel gp){

        this.gp = gp;
        // "10" is number of different tiles
        tile = new Tile[10];
        // tiles used to differentiate floor vs wall for collision
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
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

            // "S" = start = tile[4]
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(new File("res/room tiles/openDoorRoom.png"));

            // "E" = end = tile[5]
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(new File("res/room tiles/closedDoorRoom.png"));

            // "[" = door = tile[6]
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(new File("res/room tiles/closedDoorRoom.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("res/Text Maps/walls.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();

                while(col < gp.maxScreenCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {

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
            if(mapX + gp.ROOM_SIZE> gp.myHero.getWorldX() - gp.myHero.getScreenX() &&
               mapX - gp.ROOM_SIZE < gp.myHero.getWorldX() + gp.myHero.getScreenX() &&
               mapY + gp.ROOM_SIZE > gp.myHero.getWorldY() - gp.myHero.getScreenY() &&
               mapY - gp.ROOM_SIZE < gp.myHero.getWorldY() + gp.myHero.getScreenY()) {

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
                if (myMapRooms[mapRow][mapCol].equals("S")) {
                    g2.drawImage(tile[4].image, screenX, screenY, gp.ROOM_SIZE, gp.ROOM_SIZE, null);
                }
                if (myMapRooms[mapRow][mapCol].equals("E")) {
                    g2.drawImage(tile[5].image, screenX, screenY, gp.ROOM_SIZE, gp.ROOM_SIZE, null);
                }
                if (myMapRooms[mapRow][mapCol].equals("[")) {
                    g2.drawImage(tile[6].image, screenX, screenY, gp.ROOM_SIZE, gp.ROOM_SIZE, null);
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
