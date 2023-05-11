import java.util.Random;

public class MapGenerator {

    final private int MIN_COL_IN_BOUNDS = 1;
    final private int MIN_ROW_IN_BOUNDS = 1;
    final private int LEFT_BOUND = 1;
    final private int BOTTOM_BOUND = 2;
    final private int RIGHT_BOUND = 3;
    final private int TOP_BOUND = 4;
    final private int NOT_ADJACENT_TO_BOUND = 99;
    final private int X_DIRECTION = 66;
    final private int Y_DIRECTION = -66;
    final private int MAX_COLS = 25;
    final private int MAX_ROWS = 25;
    final private int MAX_COL_IN_BOUNDS = MAX_COLS - 1;
    final private int MAX_ROW_IN_BOUNDS = MAX_ROWS - 1;
    private int myStartCol = MIN_COL_IN_BOUNDS;
    private int myStartRow = MIN_ROW_IN_BOUNDS;
    private int myEndCol = MAX_COL_IN_BOUNDS;
    private int myEndRow = MAX_ROWS;
    private int myCurIntersectionCol = 0;
    private int myCurIntersectionRow = 0;
    final private String START = "S";
    final private String END = "E";
    final private String X_PATH = "-";
    final private String Y_PATH = "|";
    final private String INTERSECTION = "O";
    final private String DOOR = "[";
    final private String BOUNDS = "#";
    private String[][] mapLayout;
    private Random random = new Random();





    // | vertical path
    // - horizontal path
    // [ door
    // # wall

    // Idea use recursion to add each type of space in one small funciton.
    public MapGenerator() {
        mapLayout = new String[MAX_ROWS][MAX_COLS];
        addWalls();
        randomStart();
        randomEnd();
        addPath();
        printMap();
//
//        System.out.println();
//        System.out.println();
//        System.out.println(startIsNotByPath() + " S is surrounded by walls | " + endIsByPath() + " E is surrounded by walls");
//        System.out.println();
//        System.out.println(" START is by the BOUNDS with the code: " + startOrEndByWall(START));
//        System.out.println(" END is by the BOUNDS with the code: " + startOrEndByWall(END));
    }

    // TODO - Traverse the 2D array and add intersections where there are intersecting paths without an 'O'

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


    /*
     0 1 2 3 4
   0 # # # # #
   1 # # # # #
   2 # # # # #
   3 # E # # #
   4 # # # # #
     */

    private boolean endIsByPath() {
        if (
                mapLayout[getMyEndRow() + 1][getMyEndCol()] != BOUNDS
                || mapLayout[getMyEndRow() - 1][getMyEndCol()] != BOUNDS
                || mapLayout[getMyEndRow()][getMyEndCol() + 1] != BOUNDS
                || mapLayout[getMyEndRow()][getMyEndCol() - 1] != BOUNDS) {
            return true;
        } else {
            return false;
        }
    }
    // Create a function that finds what BOUNDS if any that S is close to and returns a value that represents which BOUNDS it is by or -1 if none.

    private int startOrEndByWall(String theStartOrEnd) {

        int theCol = 0;
        int theRow = 0;

        if (theStartOrEnd == START) {
            theCol = getMyStartCol();
            theRow = getMyStartRow();
        } else if (theStartOrEnd == END) {
            theCol = getMyEndCol();
            theRow = getMyEndRow();
        } else if (theStartOrEnd == INTERSECTION) {
            theCol = getMyCurIntersectionCol();
            theRow = getMyCurIntersectionRow();
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



    // If necessary - do a code rewrite- Break up each path building direction into its own method.
    // TODO - Make start and End have a minimum distance in the x and y direction to make the maze more difficult.
    // TODO - Don't allow the start or end to be overwritten
    // TODO BUG - make sure that intersections generate no matter the length of the random path.
    // TODO BUG - bound must be greater than origin for any random number generators specifically for randomIntersect.
    // TODO PRIORITY - When an intersection is created, we need to do a check to see if it is by a wall. Then we need to choose the correct direction.
    // TO do this we will need to keep track of the current intersection position using setters and getters.
    // consolidate the switch in addWalls into a method to be used by START and INTERSECTION


    private void produceXPath(int theCol, int theRow, String theTile, boolean theNegDir, int xOrYDirection) {
        if (endIsByPath()) {
            return;
        }

        if (theNegDir) {
            System.out.println(MIN_COL_IN_BOUNDS + "Min col in bounds");
            System.out.println(theCol - 1 + "the col bound");
            int distance = random.nextInt(MIN_COL_IN_BOUNDS, theCol);
            int randomIntersect = random.nextInt(theCol - distance, theCol);
            for (int Col = theCol - distance; Col < theCol; Col++) {
                mapLayout[theRow][Col] = theTile;
            }
            setMyCurIntersectionPos(theRow, randomIntersect);
            choosePathGeneration(INTERSECTION, getMyCurIntersectionCol(), getMyCurIntersectionRow());
//            produceYPath(getMyCurIntersectionCol(), getMyCurIntersectionRow(), Y_PATH, false, 0);

        } else {
//            if (theCol < MAX_COL_IN_BOUNDS) {
//                int distance = random.nextInt(theCol + 1, MAX_COL_IN_BOUNDS);
//            } else go in other dir
            System.out.println(MAX_COL_IN_BOUNDS + "Max col in bounds");
            System.out.println(theCol + 1 + "the col origin");
            int distance = random.nextInt(theCol + 1, MAX_COL_IN_BOUNDS);
            int randomIntersect = random.nextInt(theCol, distance);

            for (int Col = theCol + 1; Col < distance; Col++) {
                mapLayout[theRow][Col] = theTile;
           }
            setMyCurIntersectionPos(theRow, randomIntersect);
            choosePathGeneration(INTERSECTION, getMyCurIntersectionCol(), getMyCurIntersectionRow());
//            produceYPath(getMyCurIntersectionCol(), getMyCurIntersectionRow(), Y_PATH, false, 0);

        }


    }
//static int value = 0;
    private void produceYPath(int theCol, int theRow, String theTile, boolean theNegDir, int xOrYDirection) {
        if (endIsByPath()) {
            return;
        }

        if (theNegDir) {
            System.out.println(MIN_ROW_IN_BOUNDS + "Min col in bounds");
            System.out.println(theRow - 1 + "the col bound");
            int distance = random.nextInt(MIN_ROW_IN_BOUNDS, theRow);
            int randomIntersect = random.nextInt(theRow - distance, theRow);
            for (int row = theRow - distance; row < theRow; row++) {
                mapLayout[row][theCol] = theTile;
            }
            setMyCurIntersectionPos(randomIntersect, theCol);
            choosePathGeneration(INTERSECTION, getMyCurIntersectionCol(), getMyCurIntersectionRow());
//            produceXPath(getMyCurIntersectionCol(), getMyCurIntersectionRow(), X_PATH, false, 0);
        } else {
            System.out.println(MAX_ROW_IN_BOUNDS + "Max col in bounds");
            System.out.println(theRow + 1 + "the col origin");
            int distance = random.nextInt(theRow + 1, MAX_ROW_IN_BOUNDS);
            int randomIntersect = random.nextInt(theRow, distance);
            for (int row = theRow + 1; row < distance; row++) {
                mapLayout[row][theCol] = theTile;
            }
            setMyCurIntersectionPos(randomIntersect, theCol);
            choosePathGeneration(INTERSECTION, getMyCurIntersectionCol(), getMyCurIntersectionRow());
//            produceXPath(getMyCurIntersectionCol(), getMyCurIntersectionRow(), X_PATH, false, 0);
        }
    }




    private void choosePathGeneration(String theTile, int theCol, int theRow) {

        int chooseXorY = random.nextInt(0, 2);
        int choosePosOrNeg = random.nextInt(0, 2);
        boolean xDir = false;
        boolean yDir = false;
        boolean xNegDir = false;
        boolean yNegDir = false;
        String currentPathDirection = X_PATH;

        // The general algorithm
        //          - check if S is surrounded walls. //
        //          then add path of random length to S and set S-Sourounded to true and save path location
        //          elif E has no path
        //          add intersection to random space in prev path referencing its location, then add random length new path to intersection
        //          Do this step recursively to ensure that S links to E.
        // else return

        // TODO - consolidate to a method. Fucntion is to decide what direction a path should be produced and give it a starting row or column.
        int rowOrCol = 0;
//        System.out.println(chooseXorY);
//        System.out.println(choosePosOrNeg);
        if (chooseXorY % 2 == 0) {
            xDir = true;
            yDir = false;
            currentPathDirection = X_PATH;
            if (choosePosOrNeg % 2 == 0) {
                xNegDir = false;
                rowOrCol = theCol + 1;
            } else {
                xNegDir = true;
                rowOrCol = theCol - 1;
            }
        } else {
            xDir = false;
            yDir = true;
            currentPathDirection = Y_PATH;
            if (choosePosOrNeg % 2 == 0) {
                yNegDir = false;
                rowOrCol = theRow + 1;
            } else {
                yNegDir = true;
                rowOrCol = theRow - 1;
            }

        }
        switch (startOrEndByWall(theTile)) {
            case LEFT_BOUND:
                xNegDir = false;
                produceXPath(theCol, theRow, X_PATH, xNegDir, X_DIRECTION);
                break;
            case BOTTOM_BOUND:

                yNegDir = true;
                produceYPath(theCol, theRow, Y_PATH, yNegDir, Y_DIRECTION);
                break;
            case RIGHT_BOUND:

                xNegDir = true;
                produceXPath(theCol, theRow, X_PATH, xNegDir, X_DIRECTION);
                break;
            case TOP_BOUND:

                yNegDir = false;
                produceYPath(theCol, theRow, Y_PATH, yNegDir, Y_DIRECTION);
                break;
            case NOT_ADJACENT_TO_BOUND:

                if (currentPathDirection == X_PATH) {
                    if (xNegDir) {
                        System.out.println("neg dir XPATH");
                        System.out.println(theCol);
                        System.out.println(theRow);
                        System.out.println(getMyCurIntersectionCol() + " Intersection Col");
                        System.out.println(getMyCurIntersectionRow() + " Intersection Row");
                        produceXPath(theCol, theRow, X_PATH, xNegDir, X_DIRECTION);
                    } else {
                        System.out.println("pos dir XPATH");
                        System.out.println(theCol);
                        System.out.println(theRow);
                        System.out.println(getMyCurIntersectionCol() + " Intersection Col");
                        System.out.println(getMyCurIntersectionRow() + " Intersection Row");
                        produceXPath(theCol, theRow, X_PATH, xNegDir, X_DIRECTION);
                    }
                } else {
                    if (yNegDir) {
                        System.out.println("neg dir YPATH");
                        System.out.println(theCol);
                        System.out.println(theRow);
                        System.out.println(getMyCurIntersectionCol() + " Intersection Col");
                        System.out.println(getMyCurIntersectionRow() + " Intersection Row");
                        produceYPath(theCol, theRow, Y_PATH, yNegDir, Y_DIRECTION);
                    } else {
                        System.out.println("pos dir YPATH");
                        System.out.println(theCol);
                        System.out.println(theRow);
                        System.out.println(getMyCurIntersectionCol() + " Intersection Col");
                        System.out.println(getMyCurIntersectionRow() + " Intersection Row");
                        produceYPath(theCol, theRow, Y_PATH, yNegDir, Y_DIRECTION);
                    }
                }

                break;
        }
    }

    private void addPath() {
        int xDelta = random.nextInt(MIN_COL_IN_BOUNDS + 1, MAX_COL_IN_BOUNDS);
        int yDelta = random.nextInt(MIN_ROW_IN_BOUNDS, MAX_ROW_IN_BOUNDS);


//        for (int i = getMyStartCol() + 1; i < xDelta; i++) {
//            mapLayout[getMyStartRow()][i] = "-";
//        }


        if (startIsNotByPath()) {
            choosePathGeneration(START, getMyStartCol(), getMyStartRow());
         // TODO consolidate this switch block into a method to be used by START and INTERSECTION

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
            mapLayout[cols][rows] = BOUNDS;
            }
        }
    }

    private void randomStart() {
        int randomCol = random.nextInt(MIN_COL_IN_BOUNDS, MAX_COL_IN_BOUNDS);
        int randomRow = random.nextInt(MIN_ROW_IN_BOUNDS, MAX_ROW_IN_BOUNDS);

        setStartPosition(randomCol, randomRow);
    }

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
    private void setMyCurIntersectionPos(int theRow, int theCol) {
        mapLayout[theRow][theCol] = INTERSECTION;
        setMyCurIntersectionRow(theRow);
        setMyCurIntersectionCol(theCol);
    }
    private void setMyCurIntersectionCol(int theCol) {
        myCurIntersectionCol = theCol;
    }
    private void setMyCurIntersectionRow(int theRow) {
        myCurIntersectionRow = theRow;
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

    public int getMyCurIntersectionCol() {
        return myCurIntersectionCol;
    }
    public int getMyCurIntersectionRow() {
        return myCurIntersectionRow;
    }
}
