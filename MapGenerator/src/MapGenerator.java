import java.awt.*;
import java.util.Random;

public class MapGenerator {


    private String[][] mapLayout;
    private Random random = new Random();
    private int MAX_COLS = 22;
    private int MAX_ROWS = 22;
    final private int MIN_COL_IN_BOUNDS = 1;
    final private int MIN_ROW_IN_BOUNDS = 1;
    final private int MAX_COL_IN_BOUNDS = MAX_COLS - 1;
    final private int MAX_ROW_IN_BOUNDS = MAX_ROWS - 1;

    private int myStartCol = MIN_COL_IN_BOUNDS;
    private int myStartRow = MIN_ROW_IN_BOUNDS;
    private int myEndCol = MAX_COL_IN_BOUNDS;
    private int myEndRow = MAX_ROWS;
    final private int LEFT_WALL = 1;
    final private int BOTTOM_WALL = 2;
    final private int RIGHT_WALL = 3;
    final private int TOP_WALL = 4;
    final private String START = "S";
    final private String END = "E";
    final private String X_PATH = "-";
    final private String Y_PATH = "|";
    final private String INTERSECTION = "O";
    final private String DOOR = "[";
    final private String WALL = "#";




    // | vertical path
    // - horizontal path
    // [ door
    // # wall

    // Idea use recursion to add each type of space in one small funciton.
    public MapGenerator() {
        mapLayout = new String[22][22];
        addWalls();
        randomStart();
        addPath();
        randomEnd();
        printMap();

        System.out.println();
        System.out.println();
        System.out.println(startIsSuroundedByWalls() + " S is surrounded by walls | " + endIsSuroundedByWalls() + " E is surrounded by walls");
        System.out.println();
        System.out.println(" START is by the wall with the code: " + startOrEndByWall(START));
        System.out.println(" END is by the wall with the code: " + startOrEndByWall(END));
    }

    private boolean startIsSuroundedByWalls() {
        if (mapLayout[getMyStartCol() + 1][getMyStartCol()] == "#" && mapLayout[getMyStartCol() - 1][getMyStartCol()] == "#" && mapLayout[getMyStartCol()][getMyStartCol() + 1] == "#" && mapLayout[getMyStartCol() + 1][getMyStartCol() - 1] == "#") {
            return true;
        } else {
            return false;
        }
    }

    private boolean endIsSuroundedByWalls() {
        if (mapLayout[getMyEndCol() + 1][getMyEndCol()] == "#" && mapLayout[getMyEndCol() - 1][getMyEndCol()] == "#" && mapLayout[getMyEndCol()][getMyEndCol() + 1] == "#" && mapLayout[getMyEndCol() + 1][getMyEndCol() - 1] == "#") {
            return true;
        } else {
            return false;
        }
    }
    // Create a function that finds what wall if any that S is close to and returns a value that represents which wall it is by or -1 if none.

    private int startOrEndByWall(String theStartOrEnd) {

        int theCol = 0;
        int theRow = 0;

        if (theStartOrEnd == START) {
            theCol = getMyStartCol();
            theRow = getMyStartRow();
        } else if (theStartOrEnd == END) {
            theCol = getMyEndCol();
            theRow = getMyEndRow();
        }

        if (theCol == MIN_COL_IN_BOUNDS) {
            return LEFT_WALL;
        } else if (theRow == MAX_ROW_IN_BOUNDS) { // S is at the bottom row within the walls
            return BOTTOM_WALL;
        } else if (theCol == MAX_COL_IN_BOUNDS) {
            return RIGHT_WALL;
        } else if (theRow == MIN_ROW_IN_BOUNDS) {
            return TOP_WALL;
        }
        return -1;

    }

    private void addPath() {
        int xDelta = random.nextInt(MIN_COL_IN_BOUNDS, MAX_COL_IN_BOUNDS);
        int yDelta = random.nextInt(MIN_ROW_IN_BOUNDS, MAX_ROW_IN_BOUNDS);
        int chooseDirection = random.nextInt(0, 1);
        boolean xDir = false;
        boolean yDir = false;
        String currentPathDirection = "-";


        // TODO - check if S is surrounded walls. //
        //          then add path of random length to S and set S-Sourounded to true and save path location
        //          elif E has no path
        //          add intersection to random space in prev path referencing its location, then add random length new path to intersection
        //          Do this step recursively to ensure that S links to E.
        // else return

        if (chooseDirection % 2 == 0) {
            xDir = true;
            yDir = false;
            currentPathDirection = "-";
        } else {
            xDir = false;
            yDir = true;
            currentPathDirection = "|";
        }


//        if (startIsSuroundedByWalls()) { // add first path to start at S
//            if (startOrEndByWall(START) == LEFT_WALL) {
//                for (int i = getMyStartCol() + 1; i <= xDelta; i++) {
//                    mapLayout[getMyStartRow()][i] = "-";
//                }
//            } else if (startOrEndByWall(START) == BOTTOM_WALL) {
//                for (int i = yDelta; i <= getMyStartRow() + 1; i++) {
//                    mapLayout[getMyStartCol()][i] = "|";
//                }
//            } else if (startOrEndByWall(START) == RIGHT_WALL) {
//                for (int i = xDelta; i <= getMyStartCol() - 1; i++) {
//                    mapLayout[getMyStartRow()][i] = "-";
//                }
//            } else if (startOrEndByWall(START) == TOP_WALL) {
//                for (int i = yDelta; i <= getMyStartRow() - 1; i++) {
//                    mapLayout[getMyStartCol()][i] = "|";
//                }
//            } else {
//                if (xDir) {
//                    for (int i = getMyStartCol() + 1; i <= xDelta; i++) {
//                        mapLayout[getMyStartRow()][i] = "-";
//                    }
//                } else {
//                    for (int i = yDelta; i <= getMyStartRow() + 1; i++) {
//                        mapLayout[getMyStartCol()][i] = "|";
//                    }
//                }
//            }
//
//        }














        // TODO make sure that the path is greater than zero length
//        for (int cols = 0; cols < xDelta; cols++) {
//            if (randomStartX >= xDelta) {
//                System.out.println("Print from r to l");
//                for (int rows = getMyStartCol() - 1; rows >= xDelta; rows--) {
//                    mapLayout[getMyStartCol()][rows] = "-";
//                }
//            }
//            else {
//                for (int rows = getMyStartCol(); rows < xDelta; rows++) {
//                    mapLayout[getMyStartCol()][rows] = "-";
//                }
//            }

//        }

    }

    private void printMap() {
        for (int cols = 0; cols < MAX_COLS; cols++) {
            System.out.println();
            for (int rows = 0; rows < MAX_ROWS; rows++) {
                System.out.print(mapLayout[cols][rows]);
            }
        }
    }

    // Fill the array with the path elements within the boundary of the wall.
    private void addWalls() {
        for (int cols = 0; cols < MAX_COLS; cols++) {
            for (int rows = 0; rows < MAX_ROWS; rows++) {
            mapLayout[cols][rows] = "#";
            }
        }
    }

    private void randomStart() {
        int randomCol = random.nextInt(MIN_COL_IN_BOUNDS, MAX_COL_IN_BOUNDS);
        int randomRow = random.nextInt(MIN_ROW_IN_BOUNDS, MAX_ROW_IN_BOUNDS);
        mapLayout[randomCol][randomRow] = "S";

        setMyStartCol(randomCol);
        setMyStartRow(randomRow);
    }



    // TODO setup a minimum distacne the end can be from start.
    private void randomEnd() {
        int randomCol = random.nextInt(MIN_COL_IN_BOUNDS, MAX_COL_IN_BOUNDS);
        int randomRow = random.nextInt(MIN_ROW_IN_BOUNDS, MAX_ROW_IN_BOUNDS);

        mapLayout[randomCol][randomRow] = "E";

        setMyEndCol(randomCol);
        setMyEndRow(randomRow);
    }

    private void setMyStartCol(int theCol) {
        myStartCol = theCol;
    }
    private void setMyStartRow(int theRow) {
        myStartRow = theRow;
    }
    private void setMyEndCol(int theCol) {
        myEndCol = theCol;
    }
    private void setMyEndRow(int theRow) {
        myEndRow = theRow;
    }

    public int getMyStartCol() {
        return myStartCol;
    }

    public int getMyStartRow() {
        return myStartRow;
    }

    public int getMyEndCol() {
        return myEndCol;
    }

    public int getMyEndRow() {
        return myEndRow;
    }
}
