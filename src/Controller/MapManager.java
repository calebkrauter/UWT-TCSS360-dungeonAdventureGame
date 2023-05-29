package Controller;

import Model.MapGenerator;

import java.io.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class MapManager extends MapGenerator {

    private final String[][] theMap;
    private GamePanel gp;
    int mapTileNum[][];
    private Point2D myStartPoint = new Point2D.Float(0, 0);

    private Point2D myEndPoint = new Point2D.Float(0, 0);
    private final List<Point2D> DoorRoomPositions = new ArrayList<Point2D>();
    private final List<Point2D> IntersectionRoomPositions = new ArrayList<Point2D>();

    private final List<Point2D> SaveRoomPositions = new ArrayList<Point2D>();
    private final List<Point2D> XPathRoomPositions = new ArrayList<Point2D>();
    private final List<Point2D> YPathRoomPositions = new ArrayList<Point2D>();

    String[] textMaps = new String[]{"res/TextMaps/walls.txt", "res/TextMaps/yPath.txt", "res/TextMaps/xPath.txt", "res/TextMaps/intersectionPath.txt",
            "res/TextMaps/openDoorRoom.txt", "res/TextMaps/doorRoom.txt"};

    public MapManager() {
        theMap = getMap();
        interpretMap();
//        for (String textMap : textMaps) {
//            loadBooleanMap(textMap);
//        }
    }


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
     * Reads through the 2d string array representing the world map.
     * Saves the coordinates of each kind of room into it's designated list or point variable.
     */
    private void interpretMap(){
        for(int i = 0; i < getMyMaxRows(); i++){
            for(int j = 0; j < getMyMaxCols(); j++){
                Point2D thePoint = new Point2D.Float(i, j);
                if (theMap[i][j].equals("|")) {
                    YPathRoomPositions.add(thePoint);
                }
                if (theMap[i][j].equals("-")) {
                    XPathRoomPositions.add(thePoint);
                }
                if (theMap[i][j].equals("O")) {
                    IntersectionRoomPositions.add(thePoint);
                }
                if (theMap[i][j].equals("S")) {
                    myStartPoint.setLocation(thePoint);
                }
                if (theMap[i][j].equals("E")) {
                    myEndPoint.setLocation(thePoint);
                }
                if (theMap[i][j].equals("[")) {
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


