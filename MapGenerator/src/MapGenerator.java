import java.awt.*;
import java.util.Random;

public class MapGenerator {


    private String[][] mapLayout;
    private Random random = new Random();
    private int maxCols = 22;
    private int maxRows = 22;
    private int minColInWalls = 1;
    private int minRowInWalls = 1;
    private int maxColInWalls = maxCols - 1;
    private int maxRowInWalls = maxRows - 1;

    private int myStartCol = minColInWalls;
    private int myStartRow = minRowInWalls;
    private int myEndCol = maxColInWalls;
    private int myEndRow = maxRows;


    
    // | vertical path
    // - horizontal path
    // [ door
    // = wall

    // Idea use recursion to add each type of space in one small funciton.
    public MapGenerator() {
        mapLayout = new String[22][22];
        addWalls();
        randomStart();
        addPath();
        randomEnd();
        printMap();
    }

    private void addPath() {
        int xDelta = random.nextInt(minColInWalls, maxColInWalls);
        int yDelta = random.nextInt(minRowInWalls, maxRowInWalls);
        int chooseDirection = random.nextInt(0, 1);
        boolean xDir = false;
        boolean yDir = false;


        // TODO - check if S is surrounded.
        //          if it is not then add path of random length to S and set S-Sourounded to true and save path location
        //          elif E has no path
        //          add intersection to random space in prev path referencing its location, then add random length new path to intersection
        //          Do this step recursively to ensure that S links to E.
        // else return
















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
        for (int cols = 0; cols < maxCols; cols++) {
            System.out.println();
            for (int rows = 0; rows < maxRows; rows++) {
                System.out.print(mapLayout[cols][rows]);
            }
        }
    }

    // Fill the array with the path elements within the boundary of the wall.
    private void addWalls() {
        for (int cols = 0; cols < maxCols; cols++) {
            for (int rows = 0; rows < maxRows; rows++) {
            mapLayout[cols][rows] = "#";
            }
        }
    }

    private void randomStart() {
        int randomCol = random.nextInt(minColInWalls, maxColInWalls);
        int randomRow = random.nextInt(minRowInWalls, maxRowInWalls);
        mapLayout[randomCol][randomRow] = "S";

        setMyStartCol(randomCol);
        setMyStartRow(randomRow);
    }



    // TODO setup a minimum distacne the end can be from start.
    private void randomEnd() {
        int randomCol = random.nextInt(minColInWalls, maxColInWalls);
        int randomRow = random.nextInt(minRowInWalls, maxRowInWalls);

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
