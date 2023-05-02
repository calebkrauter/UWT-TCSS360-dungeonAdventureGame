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



    
    // | vertical path
    // - horizontal path
    // [ door
    // = wall

    // Idea use recursion to add each type of space in one small funciton.
    public MapGenerator() {
        mapLayout = new String[22][22];
        addWalls();
        randomStart();
        randomEnd();
        printMap();
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

        setStartLocation(randomCol, randomRow);
    }

    private Dimension myStartLocation = new Dimension(minColInWalls, minRowInWalls);
    private Dimension myEndLocation = new Dimension(maxColInWalls, maxRowInWalls);


    // TODO setup a minimum distacne the end can be from start.
    private void randomEnd() {
        int randomCol = random.nextInt(minColInWalls, maxColInWalls);
        int randomRow = random.nextInt(minRowInWalls, maxRowInWalls);

        mapLayout[randomCol][randomRow] = "E";

        setEndLocation(randomCol, randomRow);
    }

    private Dimension getStartLocation() {
        return myStartLocation;
    }
    private Dimension getEndLocation() {
        return myEndLocation;
    }

    private void setStartLocation(int theCol, int theRow) {
        myStartLocation = new Dimension(theCol, theRow);
    }

    private void setEndLocation(int theCol, int theRow) {
        myEndLocation = new Dimension(theCol, theRow);
    }
}
