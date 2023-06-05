package View.map;

import Controller.GamePanel;
import Controller.MapManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class RoomManager {

    private GamePanel myGamePanel;
    private MapManager myMapManager;

    private Room[] room;

    /**
     * EX:
     *
     */
    private String[][] myWorldMap;

    private int myCollisionMapTileNum[][];
    int mapTileNum[][];
    String[] textMaps = new String[]{"res/TextMaps/walls.txt", "res/TextMaps/yPath.txt", "res/TextMaps/xPath.txt", "res/TextMaps/intersectionPath.txt",
            "res/TextMaps/openDoorRoom.txt", "res/TextMaps/doorRoom.txt"};

    private boolean myWallCollisionMap[][];
    private boolean myXPathCollisionMap[][];
    private boolean myYPathCollisionMap[][];
    private boolean myIntersectionCollisionMap[][];
    private boolean myOpenDoorRoomCollisionMap[][];
    private boolean myClosedDoorCollisionMap[][];
    private int myWorldCollisionMap[][];



    public RoomManager(GamePanel theGamePanel, MapManager theMapManager){

        this.myGamePanel = theGamePanel;
        this.myMapManager = theMapManager;

        // "10" is number of different tiles
        room = new Room[10];

        // copy of the 2d string array world map from MapGenerator. Will need in multiple methods.
        myWorldMap = theMapManager.getMap();

        // load png's and  for room display
        setRooms();

        // create a boolean map of each room tile to assign to rectangles or integer positions
        myWallCollisionMap = loadBooleanMap(textMaps[0]);
        myYPathCollisionMap = loadBooleanMap(textMaps[1]);
        myXPathCollisionMap = loadBooleanMap(textMaps[2]);
        myIntersectionCollisionMap = loadBooleanMap(textMaps[3]);
        myOpenDoorRoomCollisionMap = loadBooleanMap(textMaps[4]);
        myClosedDoorCollisionMap = loadBooleanMap(textMaps[5]);
    }

//    public void setCollisionMapTileNum(int x, int y, int num){
//        myCollisionMapTileNum[x][y] = num;
//    }
//
//    public int getCollisionMapTileNum(int x, int y){
//        return myCollisionMapTileNum[x][y];
//    }

    /**
     * Each text file is 25 x 25 digits.
     *
     * @param thePathName pathname of textfile contaioning 1's and 0's representing the collision of a room
     * @return 2D boolean array representing collision in given room
     */
    public boolean[][] loadBooleanMap(String thePathName) {
        int textFileMaxRows = 25;
        int textFileMaxCols = 25;
        int col = 0;
        int row = 0;
        boolean myCollisionMap[][] = new boolean[textFileMaxCols][textFileMaxRows];

        try {
            InputStream is = getClass().getResourceAsStream(thePathName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            while (row < textFileMaxRows) {

                String line = br.readLine();
                while (col < textFileMaxCols) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;

                    boolean hasCollision;
                    if (num == 1) {
                        hasCollision = true;
                    } else {
                        hasCollision = false;
                    }

                    myCollisionMap[col][row] = hasCollision;

                    System.out.print(myCollisionMap[col][row] + " ");

                    col++;
                }
                if (col == textFileMaxCols) {
                    col = 0;
                    row++;
                    System.out.println();
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return myCollisionMap;

    }

    /**
     * Sets the room's collision map and image.
     *
     */
    private void setRooms(){

        try{

            // "#" = walls = tile[0]
            room[0] = new Room();
            room[0].setRoomImage(ImageIO.read(new File("res/RoomTiles/wall.png")));
            room[0].setCollisionMap(myWallCollisionMap);

            // "|" = yPath = tile[1]
            room[1] = new Room();
            room[1].setRoomImage(ImageIO.read(new File("res/RoomTiles/yPath.png")));
            room[1].setCollisionMap(myYPathCollisionMap);

            // "-" = xPath = tile[2]
            room[2] = new Room();
            room[2].setRoomImage(ImageIO.read(new File("res/RoomTiles/xPath.png")));
            room[2].setCollisionMap(myXPathCollisionMap);


            // "O" = intersection = tile[3]
            room[3] = new Room();
            room[3].setRoomImage(ImageIO.read(new File("res/RoomTiles/intersection.png")));
            room[3].setCollisionMap(myIntersectionCollisionMap);

            // "S" = start = tile[4]
            room[4] = new Room();
            room[4].setRoomImage(ImageIO.read(new File("res/RoomTiles/openDoorRoom.png")));
            room[4].setCollisionMap(myOpenDoorRoomCollisionMap);

            // "E" = end = tile[5]
            room[5] = new Room();
            room[5].setRoomImage(ImageIO.read(new File("res/RoomTiles/closedDoorRoom.png")));
            room[4].setCollisionMap(myClosedDoorCollisionMap);

            // "[" = door = tile[6]
            room[6] = new Room();
            room[6].setRoomImage(ImageIO.read(new File("res/RoomTiles/closedDoorRoom.png")));
            room[4].setCollisionMap(myClosedDoorCollisionMap);


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

        while(mapCol < myGamePanel.mapMaxCol && mapRow < myGamePanel.mapMaxRow) {

            int mapX = mapCol * myGamePanel.ROOM_SIZE;
            int mapY = mapRow * myGamePanel.ROOM_SIZE;

            // currently the player's position is always at the center of the screen
            // the center is
            int screenX = mapX - myGamePanel.myHero.getWorldX() + myGamePanel.myHero.getScreenX();
            int screenY = mapY - myGamePanel.myHero.getWorldY() + myGamePanel.myHero.getScreenY();


            // create a boundary from the center in both directions based on player
            // screenX or screenY. This is essentially render distance
            if(mapX + myGamePanel.ROOM_SIZE > myGamePanel.myHero.getWorldX() - myGamePanel.myHero.getScreenX() &&
               mapX - myGamePanel.ROOM_SIZE < myGamePanel.myHero.getWorldX() + myGamePanel.myHero.getScreenX() &&
               mapY + myGamePanel.ROOM_SIZE > myGamePanel.myHero.getWorldY() - myGamePanel.myHero.getScreenY() &&
               mapY - myGamePanel.ROOM_SIZE < myGamePanel.myHero.getWorldY() + myGamePanel.myHero.getScreenY()) {

                if (myWorldMap[mapRow][mapCol].equals("#")) { // #777474 grey color
                    g2.drawImage(room[0].getRoomImage(), screenX, screenY, myGamePanel.ROOM_SIZE, myGamePanel.ROOM_SIZE, null);
                }
                if (myWorldMap[mapRow][mapCol].equals("|")) {
                    g2.drawImage(room[1].getRoomImage(), screenX, screenY, myGamePanel.ROOM_SIZE, myGamePanel.ROOM_SIZE, null);
                }
                if (myWorldMap[mapRow][mapCol].equals("-")) {
                    g2.drawImage(room[2].getRoomImage(), screenX, screenY, myGamePanel.ROOM_SIZE, myGamePanel.ROOM_SIZE, null);
                }
                if (myWorldMap[mapRow][mapCol].equals("O")) {
                    g2.drawImage(room[3].getRoomImage(), screenX, screenY, myGamePanel.ROOM_SIZE, myGamePanel.ROOM_SIZE, null);
                }
                if (myWorldMap[mapRow][mapCol].equals("S")) {
                    g2.drawImage(room[4].getRoomImage(), screenX, screenY, myGamePanel.ROOM_SIZE, myGamePanel.ROOM_SIZE, null);
                }
                if (myWorldMap[mapRow][mapCol].equals("E")) {
                    g2.drawImage(room[5].getRoomImage(), screenX, screenY, myGamePanel.ROOM_SIZE, myGamePanel.ROOM_SIZE, null);
                }
                if (myWorldMap[mapRow][mapCol].equals("[")) {
                    g2.drawImage(room[6].getRoomImage(), screenX, screenY, myGamePanel.ROOM_SIZE, myGamePanel.ROOM_SIZE, null);
                }
            }

            // increase column by 1 each time until 16 then reset column count, go to next row.
            mapCol++;
            if (mapCol == myGamePanel.mapMaxCol) {
                mapCol = 0;
                mapRow++;
            }
        }
    }

}
