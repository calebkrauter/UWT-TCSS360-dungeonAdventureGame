import java.util.Random;

public class MapGenerator {

    final private int LEFT_BOUND = 1;
        final private int BOTTOM_BOUND = 2;
        final private int RIGHT_BOUND = 3;
        final private int TOP_BOUND = 4;
        final private int NOT_ADJACENT_TO_BOUND = 99;
        final private int X_DIRECTION = 66;
        final private int Y_DIRECTION = -66;
        final private int MAX_COLS = 50;
        final private int MAX_ROWS = 50;
        final private int MIN_COL_IN_BOUNDS = 1;
        final private int MIN_ROW_IN_BOUNDS = 1;
        final private int MAX_COL_IN_BOUNDS = MAX_COLS - 1;
        final private int MAX_ROW_IN_BOUNDS = MAX_ROWS - 1;
        final private int MIN_NUM_OF_DOORS = 4;
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
        static int recursiveCallsCounter = 0; // A counter used to end recursive calls to free up calls stack.
        private boolean myEasyMode = true;




/*
  TODO - code cleanup and make code more dynamic and modular.
  TODO - add getter for 2d MAP array
  TODO - add setter and getter for  rows, cols, and map size.
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
        if (myEasyMode) {
            ensureValidPath();
        }
        addMissingSymbols();
        replaceStartAndEnd();
        printMap();
    }

    private void ensureValidPath() {
            System.out.println("Ensured valid path");
            for (int i = getMyStartCol() + 1; i <= getMyEndCol(); i++) {
                mapLayout[getMyStartRow()][i] = X_PATH;
            }
            setMyCurDoorRoom(getMyStartRow(), getMyEndCol());
            for (int i = getMyStartRow() + 1; i < getMyEndRow(); i++) {
                mapLayout[i][getMyEndCol()] = Y_PATH;
            }
        }
    // TODO - Traverse the 2D array and add intersections where there are intersecting paths without an 'O'

    private void replaceStartAndEnd() {
        mapLayout[getMyStartRow()][getMyStartCol()] = START;
        mapLayout[getMyEndRow()][getMyEndCol()] = END;
    }

    /**
     * Counts the number of times that a symbol is present in the map.
     * @param theSymbol
     * @return the number of times theSymbol is found in the map.
     */
    private int symbolCounter(String theSymbol) {
        int count = 0;
        for (int cols = 0; cols < MAX_COLS; cols++) {
            for (int rows = 0; rows < MAX_ROWS; rows++) {
                if (mapLayout[rows][cols] == theSymbol) {
                    count++;
                }

            }
        }
        return count;
    }
    private void addMissingSymbols() {

        int doorCount = symbolCounter(DOOR);

            for (int cols = 0; cols < MAX_COLS; cols++) {
                for (int rows = 0; rows < MAX_ROWS; rows++) {
                    addMissingIntersections(rows, cols);
                    addMinimumNumOfDoors(doorCount, rows, cols);
                }
            }
        }
    private void addMissingIntersections(int theRows, int theCols) {
        if (mapLayout[theRows][theCols] == X_PATH && mapLayout[theRows + 1][theCols] == Y_PATH
                || mapLayout[theRows][theCols] == X_PATH && mapLayout[theRows - 1][theCols] == Y_PATH
                || mapLayout[theRows][theCols] == Y_PATH && mapLayout[theRows][theCols + 1] == X_PATH
                || mapLayout[theRows][theCols] == Y_PATH && mapLayout[theRows][theCols - 1] == X_PATH) {
            mapLayout[theRows][theCols] = INTERSECTION;
        }
    }
    private void addMinimumNumOfDoors(int theDoorCount, int theRows, int theCols) {
        if (mapLayout[theRows][theCols] == INTERSECTION) {
            if (theDoorCount <= MIN_NUM_OF_DOORS && symbolByWhichBound(mapLayout[theRows][theCols + 1], myCurIntersectionRow, myCurIntersectionCol) != LEFT_BOUND) {
                mapLayout[theRows][theCols + 1] = DOOR;
                theDoorCount++;
            }

            // It is  not clear to me why if I put a door at row col-1 it is out of bounds and the condition is not helping.
            // So i changed it to col + 1 and I have no issues with doors out of bounds so far. TEsT this.
            else if (theDoorCount <= MIN_NUM_OF_DOORS && symbolByWhichBound(mapLayout[theRows][theCols - 1], myCurDoorRoomRow, myCurDoorRoomCol) != RIGHT_BOUND) {
                mapLayout[theRows][theCols - 1] = DOOR;
                theDoorCount++;
            }
            if (theDoorCount <= MIN_NUM_OF_DOORS) {
                mapLayout[getMyEndRow()][getMyEndCol() - 1] = DOOR;
                theDoorCount++;
            }
            if (theDoorCount <= MIN_NUM_OF_DOORS) {
                mapLayout[getMyEndRow() - 1][getMyEndCol()] = DOOR;
                theDoorCount++;
            }
            if (theDoorCount <= MIN_NUM_OF_DOORS) {
                mapLayout[getMyStartRow()][getMyStartCol() + 1] = DOOR;
                theDoorCount++;
            }
            if (theDoorCount <= MIN_NUM_OF_DOORS) {
                mapLayout[getMyStartRow() + 1][getMyStartCol()] = DOOR;
                theDoorCount++;
            }
        }
    }

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

    private int symbolByWhichBound(String theSymbol, int theRow, int theCol) {
        if (theCol == MIN_COL_IN_BOUNDS) {
            return LEFT_BOUND;
        } else if (theRow == MAX_ROW_IN_BOUNDS) {
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
            if (endIsByPath()|| recursiveCallsCounter == 50) {
                recursiveCallsCounter = 0;
                return;
            }
            int randomDoorSpace = 0;
            if (theNegDir) {

                /*
                neg x
                    - distance uses MIN COL and theCol
                    - randomIntersect uses theCol - distance and the col
                    - the for loop uses theCol - distance and the col
                    - mapLayout uses theRow and i
                    - randomDoorSpace uses theCol - distance and the col
                    - setmyintersection uses the row and randomintersect

                 */
                int distance = random.nextInt(MIN_COL_IN_BOUNDS, theCol);
                int randomIntersect = random.nextInt(theCol - distance, theCol);
                for (int Col = theCol - distance; Col <= theCol; Col++) {
                    mapLayout[theRow][Col] = theTile;
                }
                // Place a door on the path by chance
                int doorChance = random.nextInt(1, 10);
                    if (10 % doorChance == 0) {
                        randomDoorSpace = random.nextInt(theCol - distance, theCol);
                        setMyCurDoorRoom(theRow, randomDoorSpace);
                    }
                setMyCurIntersectionPos(theRow, randomIntersect);
                choosePathGeneration(INTERSECTION, getMyCurIntersectionRow(), getMyCurIntersectionCol());

            } else {
                /*
                pos X
                    - distance uses theCol + 1 and Max Col in bounds
                    - randomIntersect uses theCol and the distance
                    - the for loop uses theCol + 1 and the distance
                    - mapLayout uses theRow and i
                    - randomDoorSpace uses theCol and the distance
                    - setmyintersection uses the row and randomintersect

                 */
                int distance = random.nextInt(theCol + 1, MAX_COL_IN_BOUNDS);
                int randomIntersect = random.nextInt(theCol, distance);

                for (int Col = theCol + 1; Col <= distance; Col++) {
                    mapLayout[theRow][Col] = theTile;
                }
                // Place a door on the path by chance
                int doorChance = random.nextInt(1, 10);
                    if (10 % doorChance == 0) {
                    randomDoorSpace = random.nextInt(theCol, distance);
                    setMyCurDoorRoom(theRow, randomDoorSpace);
                }
                setMyCurIntersectionPos(theRow, randomIntersect);
                choosePathGeneration(INTERSECTION, getMyCurIntersectionCol(), getMyCurIntersectionRow());
            }

        }

        private void produceYPath(int theCol, int theRow, String theTile, boolean theNegDir, int xOrYDirection) {
            recursiveCallsCounter++;
            if (endIsByPath() || recursiveCallsCounter == 50) {
                recursiveCallsCounter = 0;
                return;
            }
            int randomDoorSpace = 0;

            if (theNegDir) {

               /*
                neg Y
                    - distance uses MIN_ROW_IN_BOUNDS and theRow
                    - randomIntersect uses theRow - distance and theRow
                    - the for loop uses theRow - distance and theRow
                    - mapLayout uses i and theCol
                    - randomDoorSpace uses theRow - distance and theRow
                    - setmyintersection uses randomDoorSpace and  theCol

                 */
                int distance = random.nextInt(MIN_ROW_IN_BOUNDS, theRow);
                int randomIntersect = random.nextInt(theRow - distance, theRow);
                for (int row = theRow - distance; row <= theRow; row++) {
                    mapLayout[row][theCol] = theTile;
                }
                // Place a door on the path by chance
                int doorChance = random.nextInt(1, 10);

                    if (10 % doorChance == 0) {
                    randomDoorSpace = random.nextInt(theRow - distance, theRow);
                    setMyCurDoorRoom(randomDoorSpace, theCol);
                }
                setMyCurIntersectionPos(randomIntersect, theCol);
                choosePathGeneration(INTERSECTION, getMyCurIntersectionCol(), getMyCurIntersectionRow());
            } else {

                /*
                pos Y
                    - distance uses theRow + 1 and MAX_ROW_IN_BOUNDS
                    - randomIntersect uses theRow and distance
                    - the for loop uses theRow + 1 and distance
                    - mapLayout uses i and theCol
                    - randomDoorSpace uses theRow and distance
                    - setmyintersection uses randomDoorSpace and theCol

                 */
                int distance = random.nextInt(theRow + 1, MAX_ROW_IN_BOUNDS);
                int randomIntersect = random.nextInt(theRow, distance);
                for (int row = theRow + 1; row <= distance; row++) {
                    mapLayout[row][theCol] = theTile;
                }

                // Place a door on the path by chance
                int doorChance = random.nextInt(1, 10);

                    if (10 % doorChance == 0) {
                        randomDoorSpace = random.nextInt(theRow, distance);
                        setMyCurDoorRoom(randomDoorSpace, theCol);
                    }
                setMyCurIntersectionPos(randomIntersect, theCol);

                choosePathGeneration(INTERSECTION, getMyCurIntersectionCol(), getMyCurIntersectionRow());
            }
        }

        private void producePath(String theSymbol, int theDistance, int theInitialIndex, int theRow, int theCol, boolean theNegXOrYDir) {
            recursiveCallsCounter++;
            if (endIsByPath() || recursiveCallsCounter == 50) {
                recursiveCallsCounter = 0;
                return;
            }

            for (int i = theInitialIndex; i <= theDistance; i++) {
                    if (theSymbol == Y_PATH) {
                        appendToPath(theSymbol, i, theCol);
                    } else if (theSymbol == X_PATH) {
                        appendToPath(theSymbol, theRow, i);
                    }
                }

            int randomDoorSpace = 0;
            int randomIntersect = 0;
            if (theSymbol == Y_PATH) {
                if (theNegXOrYDir) {
                    randomDoorSpace = random.nextInt(theInitialIndex, theRow);
                    randomIntersect = random.nextInt(theInitialIndex, theRow);
                } else {
                    randomDoorSpace = random.nextInt(theRow, theDistance);
                    randomIntersect = random.nextInt(theRow, theDistance);
                }
                addDoor(randomDoorSpace, theCol);
                addIntersection(randomIntersect, theCol);
            } else if (theSymbol == X_PATH) {
                if (theNegXOrYDir) {
                    randomDoorSpace = random.nextInt(theInitialIndex, theCol);
                    randomIntersect = random.nextInt(theInitialIndex, theCol);
                } else {
                    randomDoorSpace = random.nextInt(theCol, theDistance);
                    randomIntersect = random.nextInt(theCol, theDistance);
                }
                addDoor(theRow, randomDoorSpace);
                addIntersection(theRow, randomIntersect);
            }
            choosePathGeneration(INTERSECTION, getMyCurIntersectionRow(), getMyCurIntersectionCol());
        }

        private void appendToPath(String theSymbol, int theRow, int theCol) {
            mapLayout[theRow][theCol] = theSymbol;
        }
        private void addIntersection(int theRow, int theCol) {
            System.out.println(theRow + " Intersection ROW");
            System.out.println(theCol + " Intersection COL");

            setMyCurIntersectionPos(theRow, theCol);
        }

        private void addDoor(int theRow, int theCol) {
            int doorChance = random.nextInt(1, 10);

            if (10 % doorChance == 0) {
                System.out.println(theRow + " DOOR ROW");
                System.out.println(theCol + " DOOR COL");
                setMyCurDoorRoom(theRow, theCol);
            }

        }

    private void choosePathGeneration(String theTile, int theRow, int theCol) {

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

        int distance = 0;
        int initialIndex = 0;
        int randomIntersect = 0;
        int randomDoorSpace = 0;
        switch (symbolByWhichBound(theTile, theRow, theCol)) {
            case LEFT_BOUND: // X POS
                xNegDir = false;
                initialIndex = theCol + 1;
                distance = random.nextInt(initialIndex, MAX_COL_IN_BOUNDS);
                producePath(X_PATH, distance, initialIndex, theRow, theCol, xNegDir);
                break;
            case BOTTOM_BOUND: // Y NEG
                yNegDir = true;
                distance = random.nextInt(MIN_ROW_IN_BOUNDS, theRow);
                initialIndex = theRow - distance;
                producePath(Y_PATH, distance, initialIndex, theRow, theCol, yNegDir);
                break;
            case RIGHT_BOUND: // X NEG
                xNegDir = true;
                distance = random.nextInt(MIN_COL_IN_BOUNDS, theCol);
                initialIndex = theCol - distance;
                producePath(X_PATH, distance, initialIndex, theRow, theCol, xNegDir);
                break;
            case TOP_BOUND: // Y POS
                yNegDir = false;
                initialIndex = theRow + 1;
                distance = random.nextInt(initialIndex, MAX_ROW_IN_BOUNDS);
                producePath(Y_PATH, distance, initialIndex, theRow, theCol, yNegDir);
                break;
            case NOT_ADJACENT_TO_BOUND:

                if (currentPathDirection == X_PATH) {
                    if (xNegDir) {// X NEG
                        xNegDir = true;
                        distance = random.nextInt(MIN_COL_IN_BOUNDS, theCol);
                        initialIndex = theCol - distance;
                        producePath(X_PATH, distance, initialIndex, theRow, theCol, xNegDir);
                    } else {// X POS
                        xNegDir = false;
                        initialIndex = theCol + 1;
                        distance = random.nextInt(initialIndex, MAX_COL_IN_BOUNDS);
                        producePath(X_PATH, distance, initialIndex, theRow, theCol, xNegDir);
                    }
                } else {
                    if (yNegDir) {// Y NEG
                        yNegDir = true;
                        distance = random.nextInt(MIN_ROW_IN_BOUNDS, theRow);
                        initialIndex = theRow - distance;
                        producePath(Y_PATH, distance, initialIndex, theRow, theCol, yNegDir);
                    } else {// Y POS
                        yNegDir = false;
                        initialIndex = theRow + 1;
                        distance = random.nextInt(initialIndex, MAX_ROW_IN_BOUNDS);
                        producePath(Y_PATH, distance, initialIndex, theRow, theCol, yNegDir);
                    }
                }
                break;
        }





//        switch (symbolByWhichBound(theTile, theRow, theCol)) {
//            case LEFT_BOUND: // X POS
//                xNegDir = false;
//                produceXPath(theCol, theRow, X_PATH, xNegDir, X_DIRECTION);
//                break;
//            case BOTTOM_BOUND: // Y NEG
//
//                yNegDir = true;
//                produceYPath(theCol, theRow, Y_PATH, yNegDir, Y_DIRECTION);
//                break;
//            case RIGHT_BOUND: // X NEG
//
//                xNegDir = true;
//                produceXPath(theCol, theRow, X_PATH, xNegDir, X_DIRECTION);
//                break;
//            case TOP_BOUND: // Y POS
//
//                yNegDir = false;
//                produceYPath(theCol, theRow, Y_PATH, yNegDir, Y_DIRECTION);
//                break;
//            case NOT_ADJACENT_TO_BOUND:
//
//                if (currentPathDirection == X_PATH) {
//                    if (xNegDir) {// X NEG
//                        produceXPath(theCol, theRow, X_PATH, xNegDir, X_DIRECTION);
//                    } else {// X POS
//                        produceXPath(theCol, theRow, X_PATH, xNegDir, X_DIRECTION);
//                    }
//                } else {
//                    if (yNegDir) {// Y NEG
//                        produceYPath(theCol, theRow, Y_PATH, yNegDir, Y_DIRECTION);
//                    } else {// Y POS
//                        produceYPath(theCol, theRow, Y_PATH, yNegDir, Y_DIRECTION);
//                    }
//                }
//
//                break;
//        }
    }

    private void addPath() {
        int xDelta = random.nextInt(MIN_COL_IN_BOUNDS + 1, MAX_COL_IN_BOUNDS);
        int yDelta = random.nextInt(MIN_ROW_IN_BOUNDS, MAX_ROW_IN_BOUNDS);

        if (startIsNotByPath()) {
            choosePathGeneration(START, getMyStartRow(), getMyStartCol());
        } // else throw exception/error

        if (myEasyMode) {
            choosePathGeneration(INTERSECTION, getMyCurIntersectionRow(), getMyCurIntersectionCol());

        } else {

            while (!endIsByPath()) {
                choosePathGeneration(INTERSECTION, getMyCurIntersectionRow(), getMyCurIntersectionCol());
            }
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
        int randomRow = 0;
        int randomCol = 0;
        if (MAX_COL_IN_BOUNDS > 20 && MAX_ROW_IN_BOUNDS > 20) {
            randomRow = random.nextInt(MIN_ROW_IN_BOUNDS, MAX_ROW_IN_BOUNDS/4);
            randomCol = random.nextInt(MIN_COL_IN_BOUNDS, MAX_COL_IN_BOUNDS/4);
        } else {
            randomRow = MIN_ROW_IN_BOUNDS + 1;
            randomCol = MIN_COL_IN_BOUNDS + 1;
        }




        setStartPosition(randomRow, randomCol);
    }


    private void randomEnd() {
        int randomRow = 0;
        int randomCol = 0;
        if (MAX_COL_IN_BOUNDS > 20 && MAX_ROW_IN_BOUNDS > 20) {
            randomRow = random.nextInt((MAX_ROW_IN_BOUNDS/4) * 4 - 1 , MAX_ROW_IN_BOUNDS);
            randomCol = random.nextInt((MAX_COL_IN_BOUNDS/4) * 4 - 1, MAX_COL_IN_BOUNDS);
        } else {
            randomRow = MAX_ROW_IN_BOUNDS - 2;
            randomCol = MAX_COL_IN_BOUNDS - 2;
        }
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
    private int getMyCurDoorRoomRow() {
        return myCurDoorRoomRow;
    }
    private int getMyCurDoorRoomCol() {
        return myCurDoorRoomCol;
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
