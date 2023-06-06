package Controller;

import Model.Item.*;
import View.map.RoomManager;

import java.awt.geom.Point2D;
import java.util.List;

public class ItemSetter {
    GameLoop myGameLoop;
    RoomManager myRoomManager;

    private int myNumExistingItems;

    public ItemSetter(GameLoop theGP, RoomManager theRoomManager) {
        this.myGameLoop = theGP;
        this.myRoomManager = theRoomManager;
        myNumExistingItems = 0;
    }

    public void setStartItems() {
        Point2D thePoint = new Point2D.Float(0, 0);
        thePoint = myRoomManager.getStartPoint();

        myGameLoop.myItems[0] = new SpeedPotion();
        myGameLoop.myItems[0].setWorldX((int) thePoint.getX() * myGameLoop.ROOM_SIZE + myGameLoop.ROOM_SIZE / 2 + myGameLoop.TILE_SIZE);
        myGameLoop.myItems[0].setWorldY((int) thePoint.getY() * myGameLoop.ROOM_SIZE + myGameLoop.ROOM_SIZE / 2 + myGameLoop.TILE_SIZE);
        setNumExistingItems(myNumExistingItems + 1);

        myGameLoop.myItems[1] = new HealthPotion();
        myGameLoop.myItems[1].setWorldX((int) thePoint.getX() * myGameLoop.ROOM_SIZE + myGameLoop.ROOM_SIZE / 2 + myGameLoop.TILE_SIZE);
        myGameLoop.myItems[1].setWorldY((int) thePoint.getY() * myGameLoop.ROOM_SIZE + myGameLoop.ROOM_SIZE / 2 - myGameLoop.TILE_SIZE);
        setNumExistingItems(myNumExistingItems + 1);

        myGameLoop.myItems[2] = new Key();
        myGameLoop.myItems[2].setWorldX((int) thePoint.getX() * myGameLoop.ROOM_SIZE + myGameLoop.ROOM_SIZE / 2 - myGameLoop.TILE_SIZE);
        myGameLoop.myItems[2].setWorldY((int) thePoint.getY() * myGameLoop.ROOM_SIZE + myGameLoop.ROOM_SIZE / 2 - myGameLoop.TILE_SIZE);
        setNumExistingItems(myNumExistingItems + 1);

    }

    public void setKeys() {
        List<Point2D> thePoints;
        thePoints = myRoomManager.getDoorRoomPositions();
        int place = getNumExistingItems();  // i was gonna +1 here but it is accounted for because index starts at 0 and num items starts at 1.
        for (int i = 0; i < thePoints.size(); i++) {
            Point2D thePoint = thePoints.get(i);

            myGameLoop.myItems[place] = new Key();

            // below getx and gety are reversed due to how the map is made
            myGameLoop.myItems[place].setWorldY((int) thePoint.getX() *  myGameLoop.ROOM_SIZE +  myGameLoop.ROOM_SIZE/2 -  myGameLoop.TILE_SIZE/2);
            myGameLoop.myItems[place].setWorldX((int) thePoint.getY() *  myGameLoop.ROOM_SIZE +  myGameLoop.ROOM_SIZE/2 -  myGameLoop.TILE_SIZE/2);

            myNumExistingItems += 1;
            place = myNumExistingItems;

        }
    }

    public void setDoors() {
        List<Point2D> thePoints;
        thePoints = myRoomManager.getDoorRoomPositions();
        int place = getNumExistingItems();

        for (int i = 0; i < thePoints.size(); i++) {
            Point2D thePoint = thePoints.get(i);

            myGameLoop.myItems[place] = new TopDoor();
            myGameLoop.myItems[place + 1] = new BottomDoor();
            myGameLoop.myItems[place + 2] = new LeftDoor();
            myGameLoop.myItems[place + 3] = new RightDoor();


            // below getx and gety are reversed due to how the map is made
            myGameLoop.myItems[place].setWorldY((int) thePoint.getX() *  myGameLoop.ROOM_SIZE);
            myGameLoop.myItems[place].setWorldX((int) thePoint.getY() *  myGameLoop.ROOM_SIZE  + (4 * myGameLoop.TILE_SIZE) - 16);

            myGameLoop.myItems[place + 1].setWorldY((int) thePoint.getX() *  myGameLoop.ROOM_SIZE + (8 * myGameLoop.TILE_SIZE) - 32);
            myGameLoop.myItems[place + 1].setWorldX((int) thePoint.getY() *  myGameLoop.ROOM_SIZE  + (4 * myGameLoop.TILE_SIZE) - 16);

            myGameLoop.myItems[place + 2].setWorldY((int) thePoint.getX() *  myGameLoop.ROOM_SIZE + (4 * myGameLoop.TILE_SIZE) - 16);
            myGameLoop.myItems[place + 2].setWorldX((int) thePoint.getY() *  myGameLoop.ROOM_SIZE);

            myGameLoop.myItems[place + 3].setWorldY((int) thePoint.getX() *  myGameLoop.ROOM_SIZE + (4 * myGameLoop.TILE_SIZE) - 16);
            myGameLoop.myItems[place + 3].setWorldX((int) thePoint.getY() *  myGameLoop.ROOM_SIZE + (8 * myGameLoop.TILE_SIZE) - 32);

            myNumExistingItems += 4;
            place = myNumExistingItems;

        }


    }

    public int getNumExistingItems() {
        return myNumExistingItems;
    }

    public void setNumExistingItems(int theNumItems) {
        myNumExistingItems = theNumItems;
    }
}