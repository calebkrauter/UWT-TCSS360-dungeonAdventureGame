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
    final private int LEFT_BOUND = 1;
    final private int BOTTOM_BOUND = 2;
    final private int RIGHT_BOUND = 3;
    final private int TOP_BOUND = 4;
    final private int NOT_ADJACENT_TO_BOUND = 99;
    final private String START = "S";
    final private String END = "E";
    final private String X_PATH = "-";
    final private String Y_PATH = "|";
    final private String INTERSECTION = "O";
    final private String DOOR = "[";
    final private String WALL = "#";
    final private int X_DIRECTION = 66;
    final private int Y_DIRECTION = -66;





    // | vertical path
    // - horizontal path
    // [ door
    // # wall

    // Idea use recursion to add each type of space in one small funciton.
    public MapGenerator() {
        mapLayout = new String[22][22];
        addWalls();
        randomStart();
        randomEnd();
        addPath();
        printMap();

        System.out.println();
        System.out.println();
        System.out.println(startIsNotByPath() + " S is surrounded by walls | " + endIsNotByPath() + " E is surrounded by walls");
        System.out.println();
        System.out.println(" START is by the wall with the code: " + startOrEndByWall(START));
        System.out.println(" END is by the wall with the code: " + startOrEndByWall(END));
    }

    // TODO this is broken
    private boolean startIsNotByPath() {
        if (mapLayout[getMyStartRow() + 1][getMyStartCol()] != Y_PATH
                && mapLayout[getMyStartRow() - 1][getMyStartCol()] != Y_PATH
                && mapLayout[getMyStartRow()][getMyStartCol() + 1] != X_PATH
                && mapLayout[getMyStartRow()][getMyStartCol() - 1] != X_PATH) {
            return true;
        } else {
            return false;
        }
    }

    private boolean endIsNotByPath() {
        if (mapLayout[getMyEndRow() + 1][getMyEndCol()] != Y_PATH
                && mapLayout[getMyEndRow() - 1][getMyEndCol()] != Y_PATH
                && mapLayout[getMyEndRow()][getMyEndCol() + 1] != X_PATH
                && mapLayout[getMyEndRow()][getMyEndCol() - 1] != X_PATH) {
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
            return LEFT_BOUND;
        } else if (theRow == MAX_ROW_IN_BOUNDS) { // S is at the bottom row within the walls
            return BOTTOM_BOUND;
        } else if (theCol == MAX_COL_IN_BOUNDS) {
            return RIGHT_BOUND;
        } else if (theRow == MIN_ROW_IN_BOUNDS) {
            return TOP_BOUND;
        }
        return NOT_ADJACENT_TO_BOUND;

    }

    private void produceXPath(int theCol, int theRow, String theTile, boolean theInvertedDirection, int xOrYDirection) {
        int distance = random.nextInt(MIN_COL_IN_BOUNDS, MAX_COL_IN_BOUNDS);

        for (int Col = getMyStartCol() + 1; Col < 10; Col++) {
            mapLayout[getMyStartRow()][Col] = "-";
        }

    }

    private void produceYPath(int theCol, int theRow, String theTile, boolean theInvertedDirection, int xOrYDirection) {
        int distance = random.nextInt(MIN_COL_IN_BOUNDS, MAX_COL_IN_BOUNDS);

        for (int row = getMyStartRow() + 1; row < distance; row++) {
            mapLayout[row][getMyStartCol()] = "|";
        }
    }

    private void addPath() {
        int xDelta = random.nextInt(MIN_COL_IN_BOUNDS + 1, MAX_COL_IN_BOUNDS);
        int yDelta = random.nextInt(MIN_ROW_IN_BOUNDS, MAX_ROW_IN_BOUNDS);
        int chooseXorY = random.nextInt(0, 2);
        int choosePosOrNeg = random.nextInt(0, 2);
        boolean xDir = false;
        boolean yDir = false;
        boolean xNegDir = false;
        boolean yNegDir = false;
        String currentPathDirection = X_PATH;

        // TODO - check if S is surrounded walls. //
        //          then add path of random length to S and set S-Sourounded to true and save path location
        //          elif E has no path
        //          add intersection to random space in prev path referencing its location, then add random length new path to intersection
        //          Do this step recursively to ensure that S links to E.
        // else return

        // TODO - consolidate to a method. Fucntion is to decide what direction a path should be produced and give it a starting row or column.
        int rowOrCol = 0;
        System.out.println(chooseXorY);
        System.out.println(choosePosOrNeg);
        if (chooseXorY % 2 == 0) {
            xDir = true;
            yDir = false;
            currentPathDirection = X_PATH;
            if (choosePosOrNeg % 2 == 0) {
                xNegDir = false;
                rowOrCol = getMyStartCol() + 1;
            } else {
                xNegDir = true;
                rowOrCol = getMyStartCol() - 1;
            }
        } else {
            xDir = false;
            yDir = true;
            currentPathDirection = Y_PATH;
            if (choosePosOrNeg % 2 == 0) {
                yNegDir = false;
                rowOrCol = getMyStartRow() + 1;
            } else {
                yNegDir = true;
                rowOrCol = getMyStartRow() - 1;
            }

        }

//        for (int i = getMyStartCol() + 1; i < xDelta; i++) {
//            mapLayout[getMyStartRow()][i] = "-";
//        }
        if (startIsNotByPath()) {
            switch (startOrEndByWall(START)) {
                case LEFT_BOUND:
                    xNegDir = false;
                    produceXPath(getMyStartCol() + 1, getMyStartRow(), X_PATH, xNegDir, X_DIRECTION);
                    break;
                case BOTTOM_BOUND:

                    yNegDir = true;
                    produceYPath(getMyStartCol(),getMyStartRow() - 1, Y_PATH, yNegDir, Y_DIRECTION);
                    break;
                case RIGHT_BOUND:

                    xNegDir = true;
                    produceXPath(getMyStartCol() - 1, getMyStartRow(), X_PATH, xNegDir, X_DIRECTION);
                    break;
                case TOP_BOUND:

                    yNegDir = false;
                    produceYPath(getMyStartCol(),getMyStartRow() + 1, Y_PATH, yNegDir, Y_DIRECTION);
                    break;
                case NOT_ADJACENT_TO_BOUND:

                    if (currentPathDirection == X_PATH) {
                        produceXPath(rowOrCol, getMyStartRow(), X_PATH, xNegDir, X_DIRECTION);
                    } else {
                        produceYPath(getMyStartCol(), rowOrCol, Y_PATH, yNegDir, Y_DIRECTION);
                    }

                    break;
            }
            // no cases, then choose direction and go a random distance
        }

    }

    private void setStartPosition(int theRow, int theCol) {
        mapLayout[theRow][theCol] = START;
        setMyStartCol(theCol);
        setMyStartRow(theRow);
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

        setStartPosition(randomCol, randomRow);
    }


    // TODO setup a minimum distacne the end can be from start.

    private void randomEnd() {
        int randomCol = random.nextInt(MIN_COL_IN_BOUNDS, MAX_COL_IN_BOUNDS);
        int randomRow = random.nextInt(MIN_ROW_IN_BOUNDS, MAX_ROW_IN_BOUNDS);

        setEndPosition(randomCol, randomRow);
    }
    private void setEndPosition(int theRow, int theCol) {
        mapLayout[theRow][theCol] = END;
        setMyEndCol(theCol);
        setMyEndRow(theRow);
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
