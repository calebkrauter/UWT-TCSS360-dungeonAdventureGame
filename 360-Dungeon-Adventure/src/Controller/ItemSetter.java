package Controller;

import Model.Item.*;
import View.map.RoomManager;

import java.awt.geom.Point2D;
import java.util.List;

public class ItemSetter {
    GamePanel myGamePanel;
    RoomManager myRoomManager;

    private int myNumExistingItems;

    public ItemSetter(GamePanel theGP, RoomManager theRoomManager) {
        this.myGamePanel = theGP;
        this.myRoomManager = theRoomManager;
        myNumExistingItems = 0;
    }

    public void setStartItems() {
        Point2D thePoint = new Point2D.Float(0, 0);
        thePoint = myRoomManager.getStartPoint();

        myGamePanel.myItems[0] = new SpeedPotion();
        myGamePanel.myItems[0].setWorldX((int) thePoint.getX() * myGamePanel.ROOM_SIZE + myGamePanel.ROOM_SIZE / 2 + myGamePanel.TILE_SIZE);
        myGamePanel.myItems[0].setWorldY((int) thePoint.getY() * myGamePanel.ROOM_SIZE + myGamePanel.ROOM_SIZE / 2 + myGamePanel.TILE_SIZE);
        setNumExistingItems(myNumExistingItems + 1);

        myGamePanel.myItems[1] = new HealthPotion();
        myGamePanel.myItems[1].setWorldX((int) thePoint.getX() * myGamePanel.ROOM_SIZE + myGamePanel.ROOM_SIZE / 2 + myGamePanel.TILE_SIZE);
        myGamePanel.myItems[1].setWorldY((int) thePoint.getY() * myGamePanel.ROOM_SIZE + myGamePanel.ROOM_SIZE / 2 - myGamePanel.TILE_SIZE);
        setNumExistingItems(myNumExistingItems + 1);

        myGamePanel.myItems[2] = new Key();
        myGamePanel.myItems[2].setWorldX((int) thePoint.getX() * myGamePanel.ROOM_SIZE + myGamePanel.ROOM_SIZE / 2 - myGamePanel.TILE_SIZE);
        myGamePanel.myItems[2].setWorldY((int) thePoint.getY() * myGamePanel.ROOM_SIZE + myGamePanel.ROOM_SIZE / 2 - myGamePanel.TILE_SIZE);
        setNumExistingItems(myNumExistingItems + 1);

    }

    public void setKeys() {
        List<Point2D> thePoints;
        thePoints = myRoomManager.getDoorRoomPositions();
        int place = getNumExistingItems();  // i was gonna +1 here but it is accounted for because index starts at 0 and num items starts at 1.
        for (int i = 0; i < thePoints.size(); i++) {
            Point2D thePoint = thePoints.get(i);

            myGamePanel.myItems[place] = new Key();

            // below getx and gety are reversed due to how the map is made
            myGamePanel.myItems[place].setWorldY((int) thePoint.getX() *  myGamePanel.ROOM_SIZE +  myGamePanel.ROOM_SIZE/2 -  myGamePanel.TILE_SIZE/2);
            myGamePanel.myItems[place].setWorldX((int) thePoint.getY() *  myGamePanel.ROOM_SIZE +  myGamePanel.ROOM_SIZE/2 -  myGamePanel.TILE_SIZE/2);

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

            myGamePanel.myItems[place] = new TopDoor();
            myGamePanel.myItems[place + 1] = new BottomDoor();
            myGamePanel.myItems[place + 2] = new LeftDoor();
            myGamePanel.myItems[place + 3] = new RightDoor();


            // below getx and gety are reversed due to how the map is made
            myGamePanel.myItems[place].setWorldY((int) thePoint.getX() *  myGamePanel.ROOM_SIZE);
            myGamePanel.myItems[place].setWorldX((int) thePoint.getY() *  myGamePanel.ROOM_SIZE  + (4 * myGamePanel.TILE_SIZE) - 16);

            myGamePanel.myItems[place + 1].setWorldY((int) thePoint.getX() *  myGamePanel.ROOM_SIZE + (8 * myGamePanel.TILE_SIZE) - 32);
            myGamePanel.myItems[place + 1].setWorldX((int) thePoint.getY() *  myGamePanel.ROOM_SIZE  + (4 * myGamePanel.TILE_SIZE) - 16);

            myGamePanel.myItems[place + 2].setWorldY((int) thePoint.getX() *  myGamePanel.ROOM_SIZE + (4 * myGamePanel.TILE_SIZE) - 16);
            myGamePanel.myItems[place + 2].setWorldX((int) thePoint.getY() *  myGamePanel.ROOM_SIZE);

            myGamePanel.myItems[place + 3].setWorldY((int) thePoint.getX() *  myGamePanel.ROOM_SIZE + (4 * myGamePanel.TILE_SIZE) - 16);
            myGamePanel.myItems[place + 3].setWorldX((int) thePoint.getY() *  myGamePanel.ROOM_SIZE + (8 * myGamePanel.TILE_SIZE) - 32);

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