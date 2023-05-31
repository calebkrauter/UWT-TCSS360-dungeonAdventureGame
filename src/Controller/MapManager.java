package Controller;

import Model.MapGenerator;

import java.io.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that extends MapGenerator to give other classes information about the map
 * Such as coordinates of Room Types
 */
public class MapManager extends MapGenerator {

    private final String[][] theMap;
    private GamePanel gp;
    private Point2D myStartPoint = new Point2D.Float(0, 0);

    private Point2D myEndPoint = new Point2D.Float(0, 0);
    private final List<Point2D> DoorRoomPositions = new ArrayList<Point2D>();
    private final List<Point2D> IntersectionRoomPositions = new ArrayList<Point2D>();

    private final List<Point2D> SaveRoomPositions = new ArrayList<Point2D>();
    private final List<Point2D> XPathRoomPositions = new ArrayList<Point2D>();
    private final List<Point2D> YPathRoomPositions = new ArrayList<Point2D>();

    myWorldCollisionMap




    public MapManager() {
        theMap = getMap();
        interpretMap();
        myWorldCollisionMap = createCollisionWorldMap();
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


