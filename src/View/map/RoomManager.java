package View.map;

import Controller.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RoomManager {

    private GamePanel myGamePanel;

    private Room[] room;

    /**
     * EX:
     *
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
    CollisionTile myCollisionWorldMap[][];

    private Point2D myStartPoint = new Point2D.Float(0, 0);

    private Point2D myEndPoint = new Point2D.Float(0, 0);
    private final java.util.List<Point2D> DoorRoomPositions = new ArrayList<Point2D>();
    private final java.util.List<Point2D> IntersectionRoomPositions = new ArrayList<Point2D>();
    private final java.util.List<Point2D> XPathRoomPositions = new ArrayList<Point2D>();
    private final List<Point2D> YPathRoomPositions = new ArrayList<Point2D>();

    public RoomManager(GamePanel theGamePanel,  String[][] theWorldMap){

        this.myGamePanel = theGamePanel;

        // "10" is number of different tiles
        room = new Room[10];

        // copy of the 2d string array world map from MapGenerator. Will need in multiple methods.
        myWorldMap = theWorldMap;

        // load png's for room display and txt files for collision
        setRooms();

        // get room Col and Row coordinates for each room type and store in their variables
        loadRoomPositions();


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
        collisionWorldMapMaxCols = myGamePanel.myWorldMapMaxCol * textFileMaxCols;
        collisionWorldMapMaxRows = myGamePanel.myWorldMapMaxRow * textFileMaxRows;
        myCollisionWorldMap = new CollisionTile[collisionWorldMapMaxCols][collisionWorldMapMaxRows];
        createCollisionWorldMap();
        printCollisionMap();
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
                    Rectangle TileHitBox= new Rectangle(col, row, myGamePanel.COLLISION_TILE_SIZE, myGamePanel.COLLISION_TILE_SIZE);
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
            room[6].setCollisionMap(myClosedDoorCollisionMap);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    public void createCollisionWorldMap() {
        int theRoomType;
        for(int i = 0; i < myGamePanel.myWorldMapMaxRow; i++){
            for(int j = 0; j < myGamePanel.myWorldMapMaxCol; j++){
                if (myWorldMap[i][j].equals("#")) {
                    theRoomType = 0;
                    addRoomToCollisionWorldMap(theRoomType, i, j);
                }
                if (myWorldMap[i][j].equals("|")) {
                    theRoomType = 1;
                    addRoomToCollisionWorldMap(theRoomType, i, j);
                }
                if (myWorldMap[i][j].equals("-")) {
                    theRoomType = 2;
                    addRoomToCollisionWorldMap(theRoomType, i, j);
                }
                if (myWorldMap[i][j].equals("O")) {
                    theRoomType = 3;
                    addRoomToCollisionWorldMap(theRoomType, i, j);
                }
                if (myWorldMap[i][j].equals("S")) {
                    theRoomType = 4;
                    addRoomToCollisionWorldMap(theRoomType, i, j);
                }
                if (myWorldMap[i][j].equals("E")) {
                    theRoomType = 5;
                    addRoomToCollisionWorldMap(theRoomType, i, j);
                }
                if (myWorldMap[i][j].equals("[")) {
                    theRoomType = 6;
                    addRoomToCollisionWorldMap(theRoomType, i, j);
                }
            }
        }
    }

    public void addRoomToCollisionWorldMap(int theRoomType, int theRoomCol, int theRoomRow) {
        for(int k = theRoomCol * textFileMaxCols; k < collisionWorldMapMaxRows; k++) {
            for (int l = theRoomRow * textFileMaxRows; l < collisionWorldMapMaxCols; l++) {
                myCollisionWorldMap[l][k] = room[theRoomType].getCollisionMap()[l][k];
            }
        }
    }


    /**
     * Prints the world collision map in 1's and 0's in the console. For testing purposes.
     */
    public void printCollisionMap() {
        for(int i = 0; i < myGamePanel.myWorldMapMaxRow; i++){
            for(int j = 0; j < myGamePanel.myWorldMapMaxCol; j++) {
                myCollisionWorldMap[i][j].toString();
            }

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

        while(mapCol < myGamePanel.myWorldMapMaxCol && mapRow < myGamePanel.myWorldMapMaxRow) {

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
            if (mapCol == myGamePanel.myWorldMapMaxCol) {
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
        for(int i = 0; i < myGamePanel.myWorldMapMaxRow; i++){
            for(int j = 0; j < myGamePanel.myWorldMapMaxCol; j++){
                Point2D thePoint = new Point2D.Float(i, j);
                if (myGamePanel.getWorldMap()[i][j].equals("|")) {
                    YPathRoomPositions.add(thePoint);
                }
                if (myGamePanel.getWorldMap()[i][j].equals("-")) {
                    XPathRoomPositions.add(thePoint);
                }
                if (myGamePanel.getWorldMap()[i][j].equals("O")) {
                    IntersectionRoomPositions.add(thePoint);
                }
                if (myGamePanel.getWorldMap()[i][j].equals("S")) {
                    myStartPoint.setLocation(thePoint);
                }
                if (myGamePanel.getWorldMap()[i][j].equals("E")) {
                    myEndPoint.setLocation(thePoint);
                }
                if (myGamePanel.getWorldMap()[i][j].equals("[")) {
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
