package Controller;

import View.map.RoomManager;

import java.awt.geom.Point2D;
import java.util.List;

public class EntitySetter {

    GameLoop myGameLoop;
    RoomManager myRoomManager;
    private int myNumEntitys;

    public EntitySetter(GameLoop theGP, RoomManager theRoomManager) {
        this.myGameLoop = theGP;
        this.myRoomManager = theRoomManager;
        myNumEntitys = 0;
    }

    /**
     * Sets player's position to center of start room.
     */
    public void setHero() {
        Point2D thePoint = new Point2D.Float(0, 0);
        thePoint = myRoomManager.getStartPoint();
        myGameLoop.myHero.setWorldX((int) thePoint.getX() * myGameLoop.ROOM_SIZE + myGameLoop.ROOM_SIZE / 2 - myGameLoop.TILE_SIZE / 2);
        myGameLoop.myHero.setWorldY((int) thePoint.getX() * myGameLoop.ROOM_SIZE + myGameLoop.ROOM_SIZE / 2 - myGameLoop.TILE_SIZE / 2);
    }

    /**
     * Sets ogres' positions to center of intersection rooms.
     */
    public void setOgres() {
        List<Point2D> thePoints;
        thePoints = myRoomManager.getIntersectionRoomPositions();
        int place = getNumEntitys();  // i was gonna +1 here but it is accounted for because index starts at 0 and num items starts at 1.
        for (int i = 0; i < thePoints.size(); i++) {
            Point2D thePoint = thePoints.get(i);

            myGameLoop.myEntities[place] = new Ogre();

            // below getx and gety are reversed due to how the map is made
            myGameLoop.myEntities[place].setWorldY((int) thePoint.getX() *  myGameLoop.ROOM_SIZE +  myGameLoop.ROOM_SIZE/2 -  myGameLoop.TILE_SIZE/2);
            myGameLoop.myEntities[place].setWorldX((int) thePoint.getY() *  myGameLoop.ROOM_SIZE +  myGameLoop.ROOM_SIZE/2 -  myGameLoop.TILE_SIZE/2);

            myNumEntitys += 1;
            place = myNumEntitys;

        }
    }

    /**
     * Sets Skeletons' positions to center of XPath rooms.
     */
    public void setSkeletons() {
        List<Point2D> thePoints;
        thePoints = myRoomManager.getXPathRoomPositions();
        int place = getNumEntitys();  // i was gonna +1 here but it is accounted for because index starts at 0 and num items starts at 1.
        for (int i = 0; i < thePoints.size(); i++) {
            Point2D thePoint = thePoints.get(i);

            myGameLoop.myEntities[place] = new Skeleton();

            // below getx and gety are reversed due to how the map is made
            myGameLoop.myEntities[place].setWorldY((int) thePoint.getX() *  myGameLoop.ROOM_SIZE +  myGameLoop.ROOM_SIZE/2 -  myGameLoop.TILE_SIZE/2);
            myGameLoop.myEntities[place].setWorldX((int) thePoint.getY() *  myGameLoop.ROOM_SIZE +  myGameLoop.ROOM_SIZE/2 -  myGameLoop.TILE_SIZE/2);

            myNumEntitys += 1;
            place = myNumEntitys;

        }
    }

    public void setGremlins() {
        List<Point2D> thePoints;
        thePoints = myRoomManager.getDoorRoomPositions();
        int place = getNumEntitys();  // i was gonna +1 here but it is accounted for because index starts at 0 and num items starts at 1.
        for (int i = 0; i < thePoints.size(); i++) {
            Point2D thePoint = thePoints.get(i);

            myGameLoop.myEntities[place] = new Gremlin();
            myGameLoop.myEntities[place + 1] = new Gremlin();


            // below getx and gety are reversed due to how the map is made
            myGameLoop.myEntities[place].setWorldY((int) thePoint.getX() *  myGameLoop.ROOM_SIZE +  myGameLoop.ROOM_SIZE/2 -  myGameLoop.TILE_SIZE/2);
            myGameLoop.myEntities[place].setWorldX((int) thePoint.getY() *  myGameLoop.ROOM_SIZE +  myGameLoop.ROOM_SIZE/2 -  2 * myGameLoop.TILE_SIZE);

            myGameLoop.myEntities[place + 1].setWorldY((int) thePoint.getX() *  myGameLoop.ROOM_SIZE +  myGameLoop.ROOM_SIZE/2 -  myGameLoop.TILE_SIZE/2);
            myGameLoop.myEntities[place + 1].setWorldX((int) thePoint.getY() *  myGameLoop.ROOM_SIZE +  myGameLoop.ROOM_SIZE/2 +  myGameLoop.TILE_SIZE);

            myNumEntitys += 2;
            place = myNumEntitys;

        }
    }


    public int getNumEntitys() {
        return myNumEntitys;
    }

    public void setNumEntitys(int theNumEntitys) {
        myNumEntitys = theNumEntitys;
    }
}

