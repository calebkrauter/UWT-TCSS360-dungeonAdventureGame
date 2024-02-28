// Makai Martinez 6/7/2023 TCSS 360 A

package View.map;

import Controller.GameLoop;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RoomManager {

    private GameLoop myGameLoop;

    /**
     * array of room objects made by map generator.
     */
    private Room[] room;

    /**
     * the map made by map generator
     */
    private String[][] myWorldMap;

    // Each text file is 8 x 8 digits.
    int textFileMaxRows = 8;
    int textFileMaxCols = 8;

    private CollisionTile myWallCollisionMap[][];
    private CollisionTile myXPathCollisionMap[][];
    private CollisionTile myYPathCollisionMap[][];
    private CollisionTile myIntersectionCollisionMap[][];
    private CollisionTile myOpenDoorRoomCollisionMap[][];
    private CollisionTile myClosedDoorCollisionMap[][];

    private int collisionWorldMapMaxCols;
    private int collisionWorldMapMaxRows;
    public CollisionTile myCollisionWorldMap[][];


    private Point2D myStartPoint = new Point2D.Float(0, 0);
    private Point2D myEndPoint = new Point2D.Float(0, 0);
    private final List<Point2D> DoorRoomPositions = new ArrayList<Point2D>();
    private final List<Point2D> IntersectionRoomPositions = new ArrayList<Point2D>();
    private final List<Point2D> XPathRoomPositions = new ArrayList<Point2D>();
    private final List<Point2D> YPathRoomPositions = new ArrayList<Point2D>();

    public RoomManager(GameLoop theGameLoop, String[][] theWorldMap){

        this.myGameLoop = theGameLoop;

        // "10" is max number of different tiles
        room = new Room[10];

        // copy of the 2d string array world map from MapGenerator. Will need in multiple methods.
        myWorldMap = theWorldMap;

        // create a collision map of each room with CollisionTile objects.
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("res/TextMaps/walls.txt");
            myWallCollisionMap = interpretTextCollisionRoomMap(inputStream);
            inputStream = new FileInputStream("res/TextMaps/yPath.txt");
            myYPathCollisionMap = interpretTextCollisionRoomMap(inputStream);
            inputStream = new FileInputStream("res/TextMaps/xPath.txt");
            myXPathCollisionMap = interpretTextCollisionRoomMap(inputStream);
            inputStream = new FileInputStream("res/TextMaps/intersectionPath.txt");
            myIntersectionCollisionMap = interpretTextCollisionRoomMap(inputStream);
            inputStream = new FileInputStream("res/TextMaps/openDoorRoom.txt");
            myOpenDoorRoomCollisionMap = interpretTextCollisionRoomMap(inputStream);
            inputStream = new FileInputStream("res/TextMaps/doorRoom.txt");
            myClosedDoorCollisionMap = interpretTextCollisionRoomMap(inputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // load png's for room display and txt files for collision
        setRooms();

        // get room Col and Row coordinates for each room type and store in their variables
        loadRoomPositions();
        collisionWorldMapMaxCols = myGameLoop.myWorldMapMaxCol * textFileMaxCols; // 10 * 8 = 80
        collisionWorldMapMaxRows = myGameLoop.myWorldMapMaxRow * textFileMaxRows; // 10 * 8 = 80

        myCollisionWorldMap = new CollisionTile[collisionWorldMapMaxCols][collisionWorldMapMaxRows];
        createCollisionWorldMap();
//        printCollisionMap();
    }

    /**
     *
     *
     * @param theInputStream the InputStream of text file containing 1's and 0's representing the collision of a room
     * @return CollisionTile[][] a 2D array of Collision tile objects representing the collision in a room type.
     */
    public CollisionTile[][] interpretTextCollisionRoomMap(InputStream theInputStream) {


        int col = 0;
        int row = 0;

        CollisionTile[][] myCollisionMap = new CollisionTile[textFileMaxCols][textFileMaxRows];

        try {
            BufferedReader theBufferedReader = new BufferedReader(new InputStreamReader(theInputStream));

            while (row < textFileMaxRows) {

                String line = theBufferedReader.readLine();
                while (col < textFileMaxCols) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    CollisionTile myCollisionTile = new CollisionTile();
                    Rectangle TileHitBox= new Rectangle(col, row, myGameLoop.COLLISION_TILE_SIZE, myGameLoop.COLLISION_TILE_SIZE);
                    myCollisionTile.setTileHitBox(TileHitBox);

                    if (num == 1) {
                        myCollisionTile.setCollision(true);
                    } else {
                        myCollisionTile.setCollision(false);
                    }

                    myCollisionMap[col][row] = myCollisionTile;

                    col++;
                }
                if (col == textFileMaxCols) {
                    col = 0;
                    row++;
                    System.out.println();
                }
            }
            theBufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return myCollisionMap;
    }

    /**
     * Sets the room's collision map and image.
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
            room[1].setCollisionMap(myYPathCollisionMap); // let this room containing the collisiontile[][] collision map

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
            room[5].setRoomImage(ImageIO.read(new File("res/RoomTiles/openDoorRoom.png")));
            room[5].setCollisionMap(myClosedDoorCollisionMap);

            // "[" = door = tile[6]
            room[6] = new Room();
            room[6].setRoomImage(ImageIO.read(new File("res/RoomTiles/openDoorRoom.png")));
            room[6].setCollisionMap(myOpenDoorRoomCollisionMap);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    public void createCollisionWorldMap() {
        int theRoomType;
        for(int row = 0; row < myGameLoop.myWorldMapMaxRow; row++){
            for(int col = 0; col < myGameLoop.myWorldMapMaxCol; col++){
                if (myWorldMap[row][col].equals("#")) { // reverse row/col here bc thats how the map is made.
                    theRoomType = 0;
                    addRoomToCollisionWorldMap(theRoomType, row, col);
                }
                if (myWorldMap[row][col].equals("|")) {
                    theRoomType = 1;
                    addRoomToCollisionWorldMap(theRoomType, row, col);
                }
                if (myWorldMap[row][col].equals("-")) {
                    theRoomType = 2;
                    addRoomToCollisionWorldMap(theRoomType, row, col);
                }
                if (myWorldMap[row][col].equals("O")) {
                    theRoomType = 3;
                    addRoomToCollisionWorldMap(theRoomType, row, col);
                }
                if (myWorldMap[row][col].equals("S")) {
                    theRoomType = 4;
                    addRoomToCollisionWorldMap(theRoomType, row, col);
                }
                if (myWorldMap[row][col].equals("E")) {
                    theRoomType = 5;
                    addRoomToCollisionWorldMap(theRoomType, row, col);
                }
                if (myWorldMap[row][col].equals("[")) {
                    theRoomType = 6;
                    addRoomToCollisionWorldMap(theRoomType, row, col);
                }
            }
        }
    }

    public void addRoomToCollisionWorldMap(int theRoomType, int theRoomRow, int theRoomCol) {
        int maxCol = textFileMaxCols;   // 8
        int maxRow = textFileMaxRows;   // 8

        maxCol *= (theRoomCol + 1);
        maxRow *= (theRoomRow + 1);

        int i = 0;
        for(int k = theRoomRow * textFileMaxRows  ; k < maxRow; k++) {
            int j = 0;
            for (int l = theRoomCol * textFileMaxCols; l <  maxCol; l++) {
                myCollisionWorldMap[l][k] = room[theRoomType].getCollisionMap()[j][i];
                j++;
            }
            i++;
        }
    }


    /**
     * Prints the world collision map in 1's and 0's in the console. For testing purposes.
     */
    public void printCollisionMap() {
        for(int row = 0; row < collisionWorldMapMaxRows; row++){
            for(int col = 0; col < collisionWorldMapMaxCols; col++) {
                System.out.print(myCollisionWorldMap[col][row].toString());
            }
            System.out.println();
        }
    }

    public CollisionTile[][] getCollisionWorldMap() {
        return myCollisionWorldMap;
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

        while(mapCol < myGameLoop.myWorldMapMaxCol && mapRow < myGameLoop.myWorldMapMaxRow) {

            int mapX = mapCol * myGameLoop.ROOM_SIZE;
            int mapY = mapRow * myGameLoop.ROOM_SIZE;

            // currently the player's position is always at the center of the screen
            // the center is
            int screenX = mapX - myGameLoop.myHero.getWorldX() + myGameLoop.myHero.getScreenX();
            int screenY = mapY - myGameLoop.myHero.getWorldY() + myGameLoop.myHero.getScreenY();


            // create a boundary from the center in both directions based on player
            // screenX or screenY. This is essentially render distance
            if(mapX + myGameLoop.ROOM_SIZE > myGameLoop.myHero.getWorldX() - myGameLoop.myHero.getScreenX() &&
               mapX - myGameLoop.ROOM_SIZE < myGameLoop.myHero.getWorldX() + myGameLoop.myHero.getScreenX() &&
               mapY + myGameLoop.ROOM_SIZE > myGameLoop.myHero.getWorldY() - myGameLoop.myHero.getScreenY() &&
               mapY - myGameLoop.ROOM_SIZE < myGameLoop.myHero.getWorldY() + myGameLoop.myHero.getScreenY()) {

                if (myWorldMap[mapRow][mapCol].equals("#")) { // #777474 grey color
                    g2.drawImage(room[0].getRoomImage(), screenX, screenY, myGameLoop.ROOM_SIZE, myGameLoop.ROOM_SIZE, null);
                }
                if (myWorldMap[mapRow][mapCol].equals("|")) {
                    g2.drawImage(room[1].getRoomImage(), screenX, screenY, myGameLoop.ROOM_SIZE, myGameLoop.ROOM_SIZE, null);
                }
                if (myWorldMap[mapRow][mapCol].equals("-")) {
                    g2.drawImage(room[2].getRoomImage(), screenX, screenY, myGameLoop.ROOM_SIZE, myGameLoop.ROOM_SIZE, null);
                }
                if (myWorldMap[mapRow][mapCol].equals("O")) {
                    g2.drawImage(room[3].getRoomImage(), screenX, screenY, myGameLoop.ROOM_SIZE, myGameLoop.ROOM_SIZE, null);
                }
                if (myWorldMap[mapRow][mapCol].equals("S")) {
                    g2.drawImage(room[4].getRoomImage(), screenX, screenY, myGameLoop.ROOM_SIZE, myGameLoop.ROOM_SIZE, null);
                }
                if (myWorldMap[mapRow][mapCol].equals("E")) {
                    g2.drawImage(room[5].getRoomImage(), screenX, screenY, myGameLoop.ROOM_SIZE, myGameLoop.ROOM_SIZE, null);
                }
                if (myWorldMap[mapRow][mapCol].equals("[")) {
                    g2.drawImage(room[6].getRoomImage(), screenX, screenY, myGameLoop.ROOM_SIZE, myGameLoop.ROOM_SIZE, null);
                }
            }

            // increase column by 1 each time until 16 then reset column count, go to next row.
            mapCol++;
            if (mapCol == myGameLoop.myWorldMapMaxCol) {
                mapCol = 0;
                mapRow++;
            }
        }
    }
    /**
     * Reads through the 2d string array representing the world map.
     * Saves the coordinates of each kind of room into it's designated list or point variable.
     */
    private void loadRoomPositions(){
        for(int i = 0; i < myGameLoop.myWorldMapMaxRow; i++){
            for(int j = 0; j < myGameLoop.myWorldMapMaxCol; j++){
                Point2D thePoint = new Point2D.Float(i, j);
                if (myGameLoop.getWorldMap()[i][j].equals("|")) {
                    YPathRoomPositions.add(thePoint);
                }
                if (myGameLoop.getWorldMap()[i][j].equals("-")) {
                    XPathRoomPositions.add(thePoint);
                }
                if (myGameLoop.getWorldMap()[i][j].equals("O")) {
                    IntersectionRoomPositions.add(thePoint);
                }
                if (myGameLoop.getWorldMap()[i][j].equals("S")) {
                    myStartPoint.setLocation(thePoint);
                }
                if (myGameLoop.getWorldMap()[i][j].equals("E")) {
                    myEndPoint.setLocation(thePoint);
                }
                if (myGameLoop.getWorldMap()[i][j].equals("[")) {
                    DoorRoomPositions.add(thePoint);
                }
            }
        }
    }

    public List<Point2D> getYPathRoomPositions(){
        return YPathRoomPositions;
    }
    public List<Point2D> getXPathRoomPositions(){
        return XPathRoomPositions;
    }
    public List<Point2D> getIntersectionRoomPositions(){
        return IntersectionRoomPositions;
    }
    public List<Point2D> getDoorRoomPositions(){
        return DoorRoomPositions;
    }
    public Point2D getStartPoint(){
        return myStartPoint;
    }
    public Point2D getEndPoint(){
        return myEndPoint;
    }

}
