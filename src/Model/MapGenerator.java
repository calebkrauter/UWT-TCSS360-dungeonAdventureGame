package Model;

import java.util.Random;

public class MapGenerator {

    final private int LEFT_BOUND = 1;
    final private int BOTTOM_BOUND = 2;
    final private int RIGHT_BOUND = 3;
    final private int TOP_BOUND = 4;
    final private int NOT_ADJACENT_TO_BOUND = 99;
    private int myMaxRows = 10;
    private int myMaxCols = 10;
    final private int MIN_COL_IN_BOUNDS = 1;
    final private int MIN_ROW_IN_BOUNDS = 1;
    final private int MAX_COL_IN_BOUNDS = myMaxCols - 1;
    final private int MAX_ROW_IN_BOUNDS = myMaxRows - 1;
    final private int MIN_NUM_OF_DOORS = 4;
    private int myStartCol = MIN_COL_IN_BOUNDS;
    private int myStartRow = MIN_ROW_IN_BOUNDS;
    private int myEndCol = MAX_COL_IN_BOUNDS;
    private int myEndRow = myMaxRows;
    private int myCurIntersectionCol = 0;
    private int myCurIntersectionRow = 0;
    private int myCurDoorRoomRow = 0;
    private int myCurDoorRoomCol = 0;
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

    // | vertical path
    // - horizontal path
    // [ door
    // # wall

    /**
     * MapGenrator Constructor
     */
    public MapGenerator() {
        mapLayout = new String[myMaxRows][myMaxCols];
        addBoundSymbols();
        setStart();
        setEnd();
        generatePaths();
        if (myEasyMode) {
            ensureValidPath();
        }
        addMissingSymbols();
        replaceStartAndEnd();
        printMap();
    }

    /**
     * Generates a straightforward valid path from start to end with a door in between.
     */
    private void ensureValidPath() {
        for (int i = getMyStartCol() + 1; i <= getMyEndCol(); i++) {
            mapLayout[getMyStartRow()][i] = X_PATH;
        }
        setMyCurDoorRoom(getMyStartRow(), getMyEndCol());
        for (int i = getMyStartRow() + 1; i < getMyEndRow(); i++) {
            mapLayout[i][getMyEndCol()] = Y_PATH;
        }
    }

    /**
     * Replaces the start and end symbols on the map to ensure that it is viewable.
     */
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
        for (int cols = 0; cols < myMaxCols; cols++) {
            for (int rows = 0; rows < myMaxRows; rows++) {
                if (mapLayout[rows][cols] == theSymbol) {
                    count++;
                }

            }
        }
        return count;
    }

    /**
     * Ensures that there are no missing symbols like doors or intersections.
     */
    private void addMissingSymbols() {

        int doorCount = symbolCounter(DOOR);

        for (int cols = 0; cols < myMaxCols; cols++) {
            for (int rows = 0; rows < myMaxRows; rows++) {
                addMissingIntersections(rows, cols);
                addMinimumNumOfDoors(doorCount, rows, cols);
            }
        }
    }

    /**
     * Adds missing intersections.
     * @param theRows
     * @param theCols
     */
    private void addMissingIntersections(int theRows, int theCols) {
        if (mapLayout[theRows][theCols] == X_PATH && mapLayout[theRows + 1][theCols] == Y_PATH
                || mapLayout[theRows][theCols] == X_PATH && mapLayout[theRows - 1][theCols] == Y_PATH
                || mapLayout[theRows][theCols] == Y_PATH && mapLayout[theRows][theCols + 1] == X_PATH
                || mapLayout[theRows][theCols] == Y_PATH && mapLayout[theRows][theCols - 1] == X_PATH) {
            mapLayout[theRows][theCols] = INTERSECTION;
        }
    }

    /**
     * Adds doors in the case that the minimum doors requirement is not met.
     * @param theDoorCount keeps track of the amount of doors present.
     * @param theRows current row on the map.
     * @param theCols current col on the map.
     */
    private void addMinimumNumOfDoors(int theDoorCount, int theRows, int theCols) {
        if (mapLayout[theRows][theCols] == INTERSECTION) {
            if (theDoorCount <= MIN_NUM_OF_DOORS && symbolByWhichBound(myCurIntersectionRow, myCurIntersectionCol) != LEFT_BOUND) {
                mapLayout[theRows][theCols + 1] = DOOR;
                theDoorCount++;
            }
            else if (theDoorCount <= MIN_NUM_OF_DOORS && symbolByWhichBound(myCurDoorRoomRow, myCurDoorRoomCol) != RIGHT_BOUND) {
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

    /**
     * Check if the end is by a valid path symbol.
     * @return true if by a valid path, false otherwise.
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

    /**
     * Find which bound a given symbol is adjacent to and return a value accordingly.
     * @param theRow the current row.
     * @param theCol the current col.
     * @return an int value indicating which if any bound the symbol is adjacent to.
     */
    private int symbolByWhichBound(int theRow, int theCol) {
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

    /**
     * Produce an x path based on the parameters and by managing helper methods.
     * @param theCol the current col of the current symbol.
     * @param theRow the current row of the current symbol.
     * @param theSymbol the current symbol.
     * @param theNegDir the direction, negative or positive relative to a 2D array.
     */
    private void produceXPath(int theCol, int theRow, String theSymbol, boolean theNegDir) {
        recursiveCallsCounter++;
        // The recursiveCallsCounter is used as a base case to reduce the size of the call stack to eliminate a stack overflow.
        if (endIsByPath()|| recursiveCallsCounter == 50) {
            recursiveCallsCounter = 0;
            return;
        }
        if (theNegDir) {
            int distance = random.nextInt(MIN_COL_IN_BOUNDS, theCol);
            int randomIntersect = random.nextInt(theCol - distance, theCol);
            addPath(theCol - distance, theCol, theRow, theCol, theSymbol);
            addDoorToPath(theCol - distance, theCol, theRow, theCol, theSymbol);
            setMyCurIntersectionPos(theRow, randomIntersect);
            choosePathGeneration(getMyCurIntersectionCol(), getMyCurIntersectionRow());
        } else {
            int distance = random.nextInt(theCol + 1, MAX_COL_IN_BOUNDS);
            int randomIntersect = random.nextInt(theCol, distance);
            addPath(theCol + 1, distance, theRow, theCol, theSymbol);
            addDoorToPath(theCol, distance, theRow, theCol, theSymbol);
            setMyCurIntersectionPos(theRow, randomIntersect);
            choosePathGeneration(getMyCurIntersectionCol(), getMyCurIntersectionRow());
        }

    }

    /**
     * Produce a y+ path based on the parameters and by managing helper methods.
     * @param theCol the current col of the current symbol.
     * @param theRow the current row of the current symbol.
     * @param theSymbol the current symbol.
     * @param theNegDir the direction, negative or positive relative to a 2D array.
     */
    private void produceYPath(int theCol, int theRow, String theSymbol, boolean theNegDir) {
        recursiveCallsCounter++;
        // The recursiveCallsCounter is used as a base case to reduce the size of the call stack to eliminate a stack overflow.
        if (endIsByPath() || recursiveCallsCounter == 50) {

            recursiveCallsCounter = 0;
            return;
        }
        if (theNegDir) {
            int distance = random.nextInt(MIN_ROW_IN_BOUNDS, theRow);
            int randomIntersect = random.nextInt(theRow - distance, theRow);
            addPath(theRow - distance, theRow, theRow, theCol, theSymbol);
            addDoorToPath(theRow - distance, theRow, theRow, theCol, theSymbol);
            setMyCurIntersectionPos(randomIntersect, theCol);
            choosePathGeneration(getMyCurIntersectionCol(), getMyCurIntersectionRow());
        } else {
            int distance = random.nextInt(theRow + 1, MAX_ROW_IN_BOUNDS);
            int randomIntersect = random.nextInt(theRow, distance);
            addPath(theRow + 1, distance, theRow, theCol, theSymbol);
            addDoorToPath(theRow, distance, theRow, theCol, theSymbol);
            setMyCurIntersectionPos(randomIntersect, theCol);
            choosePathGeneration(getMyCurIntersectionCol(), getMyCurIntersectionRow());
        }
    }

    /**
     * Helper method to add an x or y path based on the parameters.
     * @param theStartIndex the first index in the for loop.
     * @param theLastIndex the last index in the for loop.
     * @param theRow the current row of the current symbol.
     * @param theCol the current col of the current symbol.
     * @param theSymbol the current symbol.
     */
    private void addPath(int theStartIndex, int theLastIndex, int theRow, int theCol, String theSymbol) {
        for (int i = theStartIndex; i <= theLastIndex; i++) {
            if (theSymbol == X_PATH) {
                mapLayout[theRow][i] = theSymbol;
            } else if (theSymbol == Y_PATH) {
                mapLayout[i][theCol] = theSymbol;
            }
        }
    }

    /**
     * Adds a door by chance to the current path.
     * @param theOrigin the origin for a random number generator.
     * @param theBound the bound for a random number generator.
     * @param theRow the current row of the current symbol.
     * @param theCol the current col of the current symbol.
     * @param theSymbol the current symbol.
     */
    private void addDoorToPath(int theOrigin, int theBound, int theRow, int theCol, String theSymbol) {
        int doorChance = random.nextInt(1, 10);
        if (10 % doorChance == 0) {
            int randomDoorSpace = random.nextInt(theOrigin, theBound);
            if (theSymbol == X_PATH) {
                setMyCurDoorRoom(theRow, randomDoorSpace);
            } else if (theSymbol == Y_PATH) {
                setMyCurDoorRoom(randomDoorSpace, theCol);
            }
        }
    }

    /**
     * Choose a path to generate.
     * @param theCol the current col of the current symbol.
     * @param theRow the current row of the current symbol.
     */
    private void choosePathGeneration(int theCol, int theRow) {
        int chooseXorY = random.nextInt(0, 2);
        int choosePosOrNeg = random.nextInt(0, 2);
        boolean xNegDir = false;
        boolean yNegDir = false;
        String currentPathDirection = "";

        if (chooseXorY % 2 == 0) {
            currentPathDirection = X_PATH;
            if (choosePosOrNeg % 2 == 0) {
                xNegDir = false;
            } else {
                xNegDir = true;
            }
        } else {
            currentPathDirection = Y_PATH;
            if (choosePosOrNeg % 2 == 0) {
                yNegDir = false;
            } else {
                yNegDir = true;
            }

        }
        switch (symbolByWhichBound(theRow, theCol)) {
            case LEFT_BOUND:
                xNegDir = false;
                produceXPath(theCol, theRow, X_PATH, xNegDir);
                break;
            case BOTTOM_BOUND:

                yNegDir = true;
                produceYPath(theCol, theRow, Y_PATH, yNegDir);
                break;
            case RIGHT_BOUND:

                xNegDir = true;
                produceXPath(theCol, theRow, X_PATH, xNegDir);
                break;
            case TOP_BOUND:

                yNegDir = false;
                produceYPath(theCol, theRow, Y_PATH, yNegDir);
                break;
            case NOT_ADJACENT_TO_BOUND:

                if (currentPathDirection == X_PATH) {
                    if (xNegDir) {
                        produceXPath(theCol, theRow, X_PATH, xNegDir);
                    } else {
                        produceXPath(theCol, theRow, X_PATH, xNegDir);
                    }
                } else {
                    if (yNegDir) {
                        produceYPath(theCol, theRow, Y_PATH, yNegDir);
                    } else {
                        produceYPath(theCol, theRow, Y_PATH, yNegDir);
                    }
                }
                break;
        }
    }

    /**
     * Generates paths using recursion and looping.
     */
    private void generatePaths() {
        choosePathGeneration(getMyStartCol(), getMyStartRow());
        if (myEasyMode) {
            choosePathGeneration(getMyCurIntersectionCol(), getMyCurIntersectionRow());
        } else {
            // A while loop is used to continue the recursion after the base case is met if the end is not reached by a valid path.
            while (!endIsByPath()) {
                choosePathGeneration(getMyCurIntersectionCol(), getMyCurIntersectionRow());
            }
        }
    }

    /**
     * Sets the starting symbol position and updates the map layout.
     * @param theRow the current row.
     * @param theCol the current col.
     */
    private void setStartPosition(int theRow, int theCol) {
        mapLayout[theRow][theCol] = START;
        setMyStartRow(theRow);
        setMyStartCol(theCol);
    }

    /**
     * Prints out the map.
     */
    private void printMap() {
        for (int rows = 0; rows < myMaxRows; rows++) {
            System.out.println();
            for (int cols = 0; cols < myMaxCols; cols++) {
                System.out.print(mapLayout[rows][cols]);
            }
        }
    }

    /**
     * Adds bounds.
     */
    private void addBoundSymbols() {
        for (int cols = 0; cols < myMaxCols; cols++) {
            for (int rows = 0; rows < myMaxRows; rows++) {
                mapLayout[cols][rows] = BOUNDS;
            }
        }
    }

    /**
     * Choose a location and set the start location.
     */
    private void setStart() {
        setStartPosition(MIN_ROW_IN_BOUNDS + 1, MIN_COL_IN_BOUNDS + 1);
    }

    /**
     *  Choose a location and set the end location.
     */
    private void setEnd() {
        setEndPosition(MAX_ROW_IN_BOUNDS - 2, MAX_COL_IN_BOUNDS - 2);
    }



    /**
     * set the end position.
     * @param theRow the current row of the end symbol.
     * @param theCol the current col of the end symbol.
     */
    private void setEndPosition(int theRow, int theCol) {
        mapLayout[theRow][theCol] = END;
        setMyEndCol(theCol);
        setMyEndRow(theRow);
    }

    /**
     * Set the start col.
     * @param theCol the current col.
     */
    private void setMyStartCol(int theCol) {
        myStartCol = theCol;
    }


    /**
     * Set the start row.
     * @param theRow the current row.
     */
    private void setMyStartRow(int theRow) {
        myStartRow = theRow;
    }

    /**
     * Set the end col.
     * @param theCol the current col.
     */
    private void setMyEndCol(int theCol) {
        myEndCol = theCol;
    }

    /**
     * Set the end row.
     * @param theRow the current row.
     */
    private void setMyEndRow(int theRow) {
        myEndRow = theRow;
    }

    /**
     * Set the current intersection.
     * @param theRow the current row.
     * @param theCol the current col.
     */
    private void setMyCurIntersectionPos(int theRow, int theCol) {
        mapLayout[theRow][theCol] = INTERSECTION;
        setMyCurIntersectionRow(theRow);
        setMyCurIntersectionCol(theCol);
    }

    /**
     * Set the current intersection symbol col.
     * @param theCol the current col.
     */
    private void setMyCurIntersectionCol(int theCol) {
        myCurIntersectionCol = theCol;
    }

    /**
     * Set the current intersection symbol row.
     * @param theRow the current row.
     */
    private void setMyCurIntersectionRow(int theRow) {
        myCurIntersectionRow = theRow;
    }

    /**
     * Set the current door-room's position.
     * @param theRow the current row.
     * @param theCol the current col.
     */
    private void setMyCurDoorRoom(int theRow, int theCol) {
        mapLayout[theRow][theCol] = DOOR;
        setMyCurDoorRoomRow(theRow);
        setMyCurDoorRoomCol(theCol);
    }

    /**
     * Set current door symbol row.
     * @param theRow the current row.
     */
    private void setMyCurDoorRoomRow(int theRow) {
        myCurDoorRoomRow = theRow;
    }

    /**
     * Set current door symbol col.
     * @param theCol the current col.
     */
    private void setMyCurDoorRoomCol (int theCol) {
        myCurDoorRoomCol = theCol;
    }

    /**
     * Get the start col.
     * @return myStartCol
     */
    public int getMyStartCol() {
        return myStartCol;
    }

    /**
     * Get the start row.
     * @return myStartRow
     */
    public int getMyStartRow() {
        return myStartRow;
    }

    /**
     * Get the end col.
     * @return myEndCol
     */
    public int getMyEndCol() {
        return myEndCol;
    }

    /**
     * Get the end row.
     * @return myEndRow
     */
    public int getMyEndRow() {
        return myEndRow;
    }

    /**
     * Get current intersection col.
     * @return myCurIntersectionCol
     */
    public int getMyCurIntersectionCol() {
        return myCurIntersectionCol;
    }

    /**
     * Get current intersection row.
     * @return myCurIntersectionRow
     */
    private int getMyCurIntersectionRow() {
        return myCurIntersectionRow;
    }

    /**
     * Return the map layout.
     * @return mapLayout
     */
    public String[][] getMap() {
        return mapLayout;
    }

    /**
     * Gets the max rows.
     * @return myMaxRows
     */
    public int getMyMaxRows() {
        return myMaxRows;
    }

    /**
     * Gets the max cols.
     * @return myMaxCols
     */
    public int getMyMaxCols() {
        return myMaxCols;
    }
}