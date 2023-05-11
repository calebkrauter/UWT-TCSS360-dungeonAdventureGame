import java.util.Random;

public class MapGenerator {

    final private int LEFT_BOUND = 1;
    final private int BOTTOM_BOUND = 2;
    final private int RIGHT_BOUND = 3;
    final private int TOP_BOUND = 4;
    final private int NOT_ADJACENT_TO_BOUND = 99;
    final private int X_DIRECTION = 66;
    final private int Y_DIRECTION = -66;
    final private int MAX_COLS = 100;
    final private int MAX_ROWS = 100;
    final private int MIN_COL_IN_BOUNDS = 1;
    final private int MIN_ROW_IN_BOUNDS = 1;
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



/*
  There is a chance that no doors could be placed, possible bug where a door is placed at a random location on the map.
  Todo - give start and end minimum start distance from each other or static position.
  Todo - add minimum amount of doors = 4.
  TODO - make paths go out from S in the correct direction and into E from the right direction. Possibly Optional depending on map design and collision detection.
 */

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
        addMissingIntersections();
        replaceStartAndEnd();
        addDoorIfNoneExist();
        printMap();
    }

    // TODO - Traverse the 2D array and add intersections where there are intersecting paths without an 'O'

    private void replaceStartAndEnd() {
        mapLayout[getMyStartRow()][getMyStartCol()] = START;
        mapLayout[getMyEndRow()][getMyEndCol()] = END;
    }


    private void addMissingIntersections() {
        for (int cols = 0; cols < MAX_COLS; cols++) {
            for (int rows = 0; rows < MAX_ROWS; rows++) {
                if (mapLayout[rows][cols] == X_PATH && mapLayout[rows + 1][cols] == Y_PATH
                        || mapLayout[rows][cols] == X_PATH && mapLayout[rows - 1][cols] == Y_PATH) {
                    mapLayout[rows][cols] = INTERSECTION;
                }
                if (mapLayout[rows][cols] == Y_PATH && mapLayout[rows][cols + 1] == X_PATH
                        || mapLayout[rows][cols] == Y_PATH && mapLayout[rows][cols - 1] == X_PATH) {
                    mapLayout[rows][cols] = INTERSECTION;
                }
            }
        }
    }

    private void addDoorIfNoneExist() {
        for (int cols = 0; cols < MAX_COLS; cols++) {
            for (int rows = 0; rows < MAX_ROWS; rows++) {
                if (mapLayout[rows][cols] == X_PATH && mapLayout[rows + 1][cols] == END
                        && mapLayout[rows][cols] == X_PATH && mapLayout[rows - 1][cols] == END) {
                    mapLayout[rows][cols] = DOOR;
                }
                if (mapLayout[rows][cols] == Y_PATH && mapLayout[rows][cols + 1] == END
                        || mapLayout[rows][cols] == Y_PATH && mapLayout[rows][cols - 1] == END) {
                    mapLayout[rows][cols] = DOOR;
                }
                if (mapLayout[rows][cols] == INTERSECTION && mapLayout[rows][cols + 1] == END
                        || mapLayout[rows][cols] == INTERSECTION && mapLayout[rows][cols - 1] == END) {
                    mapLayout[rows][cols] = DOOR;
                }
                if (mapLayout[rows][cols] == INTERSECTION && mapLayout[rows + 1][cols] == END
                        || mapLayout[rows][cols] == INTERSECTION && mapLayout[rows - 1][cols] == END) {
                    mapLayout[rows][cols] = DOOR;
                }

                int twoInNine = random.nextInt(0,9);

                if (twoInNine % 9 == 0) {
                    if (mapLayout[rows][cols] == X_PATH && mapLayout[rows + 1][cols] == BOUNDS
                            && mapLayout[rows][cols] == X_PATH && mapLayout[rows - 1][cols] == BOUNDS) {
                        mapLayout[rows][cols] = DOOR;
                    }
                    if (mapLayout[rows][cols] == Y_PATH && mapLayout[rows][cols + 1] == BOUNDS
                            || mapLayout[rows][cols] == Y_PATH && mapLayout[rows][cols - 1] == BOUNDS) {
                        mapLayout[rows][cols] = DOOR;
                    }
                    if (mapLayout[rows][cols] == INTERSECTION && mapLayout[rows][cols + 1] == BOUNDS
                            || mapLayout[rows][cols] == INTERSECTION && mapLayout[rows][cols - 1] == BOUNDS) {
                        mapLayout[rows][cols] = DOOR;
                    }
                    if (mapLayout[rows][cols] == INTERSECTION && mapLayout[rows + 1][cols] == BOUNDS
                            || mapLayout[rows][cols] == INTERSECTION && mapLayout[rows - 1][cols] == BOUNDS) {
                        mapLayout[rows][cols] = DOOR;
                    }
                }
            }
        }
    }
    /*
    case
    1: -|
    2: |-
    3: -
       |
    4: |
       -
     */
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
                mapLayout[getMyEndRow() + 1][getMyEndCol()] == Y_PATH
                || mapLayout[getMyEndRow() - 1][getMyEndCol()] == Y_PATH
                || mapLayout[getMyEndRow()][getMyEndCol() + 1] == X_PATH
                || mapLayout[getMyEndRow()][getMyEndCol() - 1] == X_PATH
                ||  mapLayout[getMyEndRow() + 1][getMyEndCol()] == INTERSECTION
                || mapLayout[getMyEndRow() - 1][getMyEndCol()] == INTERSECTION
                || mapLayout[getMyEndRow()][getMyEndCol() + 1] == INTERSECTION
                || mapLayout[getMyEndRow()][getMyEndCol() - 1] == INTERSECTION
                ||  mapLayout[getMyEndRow() + 1][getMyEndCol()] == DOOR
                || mapLayout[getMyEndRow() - 1][getMyEndCol()] == DOOR
                || mapLayout[getMyEndRow()][getMyEndCol() + 1] == DOOR
                || mapLayout[getMyEndRow()][getMyEndCol() - 1] == DOOR) {
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

    private void produceXPath(int theCol, int theRow, String theTile, boolean theNegDir, int xOrYDirection) {
        recursiveCallsCounter++;
        if (endIsByPath() || recursiveCallsCounter == 50) {
            recursiveCallsCounter = 0;
            return;
        }

        if (theNegDir) {
            int distance = random.nextInt(MIN_COL_IN_BOUNDS, theCol);
            int randomIntersect = random.nextInt(theCol - distance, theCol);
            for (int Col = theCol - distance; Col < theCol; Col++) {
                mapLayout[theRow][Col] = theTile;
            }
            // Place a door on the path by chance
            int doorChance = random.nextInt(0, 9);
            if (doorChance % 9 == 0) {
                int randomDoorSpace = random.nextInt(theCol - distance, theCol);
                setMyCurDoorRoom(theRow, randomDoorSpace);
            }
            setMyCurIntersectionPos(theRow, randomIntersect);
            choosePathGeneration(INTERSECTION, getMyCurIntersectionCol(), getMyCurIntersectionRow());

        } else {
            int distance = random.nextInt(theCol + 1, MAX_COL_IN_BOUNDS);
            int randomIntersect = random.nextInt(theCol, distance);

            for (int Col = theCol + 1; Col < distance; Col++) {
                mapLayout[theRow][Col] = theTile;
           }
            // Place a door on the path by chance
            int doorChance = random.nextInt(0, 9);
            if (doorChance % 9 == 0) {
                int randomDoorSpace = random.nextInt(theCol, distance);
                setMyCurDoorRoom(theRow, randomDoorSpace);
            }
            setMyCurIntersectionPos(theRow, randomIntersect);
            choosePathGeneration(INTERSECTION, getMyCurIntersectionCol(), getMyCurIntersectionRow());
        }

    }
static int recursiveCallsCounter = 0; // A counter used to end recursive calls to free up calls stack.
    private void produceYPath(int theCol, int theRow, String theTile, boolean theNegDir, int xOrYDirection) {
        recursiveCallsCounter++;
        if (endIsByPath() || recursiveCallsCounter == 50) {
            recursiveCallsCounter = 0;
            return;
        }

        if (theNegDir) {
            int distance = random.nextInt(MIN_ROW_IN_BOUNDS, theRow);
            int randomIntersect = random.nextInt(theRow - distance, theRow);
            for (int row = theRow - distance; row < theRow; row++) {
                mapLayout[row][theCol] = theTile;
            }
            // Place a door on the path by chance
            int doorChance = random.nextInt(0, 9);

            if (doorChance % 9 == 0) {
                int randomDoorSpace = random.nextInt(theRow - distance, theRow);
                setMyCurDoorRoom(randomDoorSpace, theCol);
            }
            setMyCurIntersectionPos(randomIntersect, theCol);
            choosePathGeneration(INTERSECTION, getMyCurIntersectionCol(), getMyCurIntersectionRow());
        } else {
            int distance = random.nextInt(theRow + 1, MAX_ROW_IN_BOUNDS);
            int randomIntersect = random.nextInt(theRow, distance);
            for (int row = theRow + 1; row < distance; row++) {
                mapLayout[row][theCol] = theTile;
            }

            // Place a door on the path by chance
            int doorChance = random.nextInt(1, 9);
            if (doorChance % 9 == 0) {
                int randomDoorSpace = random.nextInt(theRow, distance);
                setMyCurDoorRoom(randomDoorSpace, theCol);
            }
            setMyCurIntersectionPos(randomIntersect, theCol);

            choosePathGeneration(INTERSECTION, getMyCurIntersectionCol(), getMyCurIntersectionRow());
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

        int rowOrCol = 0;

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
                        produceXPath(theCol, theRow, X_PATH, xNegDir, X_DIRECTION);
                    } else {
                        produceXPath(theCol, theRow, X_PATH, xNegDir, X_DIRECTION);
                    }
                } else {
                    if (yNegDir) {
                        produceYPath(theCol, theRow, Y_PATH, yNegDir, Y_DIRECTION);
                    } else {
                        produceYPath(theCol, theRow, Y_PATH, yNegDir, Y_DIRECTION);
                    }
                }

                break;
        }
    }

    private void addPath() {
        int xDelta = random.nextInt(MIN_COL_IN_BOUNDS + 1, MAX_COL_IN_BOUNDS);
        int yDelta = random.nextInt(MIN_ROW_IN_BOUNDS, MAX_ROW_IN_BOUNDS);

        if (startIsNotByPath()) {
            choosePathGeneration(START, getMyStartCol(), getMyStartRow());
        } // else throw exception/error
        while (!endIsByPath()) {
            choosePathGeneration(INTERSECTION, getMyCurIntersectionCol(), getMyCurIntersectionRow());
        }

    }

    private void setStartPosition(int theRow, int theCol) {
        mapLayout[theRow][theCol] = START;
        setMyStartRow(theRow);
        setMyStartCol(theCol);
    }

    private void printMap() {
        for (int cols = 0; cols < MAX_COLS; cols++) {
            System.out.println();
            for (int rows = 0; rows < MAX_ROWS; rows++) {
                System.out.print(mapLayout[cols][rows]);
            }
        }
    }

    private void addWalls() {
        for (int cols = 0; cols < MAX_COLS; cols++) {
            for (int rows = 0; rows < MAX_ROWS; rows++) {
            mapLayout[cols][rows] = BOUNDS;
            }
        }
    }

    private void randomStart() {
        int randomCol = random.nextInt(MIN_COL_IN_BOUNDS, MAX_COL_IN_BOUNDS/4);
        int randomRow = random.nextInt(MIN_ROW_IN_BOUNDS, MAX_ROW_IN_BOUNDS/4);
        System.out.println(MAX_COL_IN_BOUNDS/4 + " max col bound for start");


        setStartPosition(randomRow, randomCol);
    }

    private void randomEnd() {
        int randomCol = random.nextInt((MAX_COL_IN_BOUNDS/4) * 4, MAX_COL_IN_BOUNDS);
        int randomRow = random.nextInt((MAX_ROW_IN_BOUNDS/4) * 4 , MAX_ROW_IN_BOUNDS);
        System.out.println((MAX_COL_IN_BOUNDS/4) * 4+ " min col origin for end");
        setEndPosition(randomRow, randomCol);
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

    // For every door made possibly assign it a boolean like use a map or something?
    private void setMyCurDoorRoom(int theRow, int theCol) {
        mapLayout[theRow][theCol] = DOOR;
        setMyCurDoorRoomRow(theRow);
        setMyCurDoorRoomCol(theCol);
    }
    private int myCurDoorRoomRow = 0;
    private int myCurDoorRoomCol = 0;
    private void setMyCurDoorRoomRow(int theRow) {
        myCurDoorRoomRow = theRow;
    }
    private void setMyCurDoorRoomCol (int theCol) {
        myCurDoorRoomCol = theCol;
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
