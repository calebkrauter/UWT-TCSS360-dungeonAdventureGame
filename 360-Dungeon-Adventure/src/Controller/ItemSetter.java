package Controller;

import Model.Item.*;
import View.map.RoomManager;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Random;

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

    public void setPillars() {
        List<Point2D> thePoints;
        thePoints = myRoomManager.getDoorRoomPositions();
        int place = getNumExistingItems();

        // create 4 pillars
        myGameLoop.myItems[place] = new Pillar();
        myGameLoop.myItems[place + 1] = new Pillar();
        myGameLoop.myItems[place + 2] = new Pillar();
        myGameLoop.myItems[place + 3] = new Pillar();

        // pillars can be in any room except start and end. This leaves
        // xPath, Ypath, intersections, and door rooms (4 types)
        // get a random number representing room type
        Random rand = new Random();
        for (int i = 0; i < 4; i++){
            int roomType = rand.nextInt(4); // generates random numbers in the range 0 to 4 - 1.
            // 0-3 is 4 diff options
            int roomNumber = 0;
            if(roomType == 0) { // door rooms
                // set the list on points equal to the room type
                thePoints = myRoomManager.getDoorRoomPositions();
            }
            else if(roomType == 1) { // xpath
                thePoints = myRoomManager.getXPathRoomPositions();
            }
            else if(roomType == 2) { // ypath
                thePoints = myRoomManager.getYPathRoomPositions();
            }
            else if(roomType == 3) { // intersection
                thePoints = myRoomManager.getIntersectionRoomPositions();
            }
            // We now have a list of points representing the different rooms of a randomly selected room type

            // Next get a random point in the list
            roomNumber = rand.nextInt(thePoints.size());
            Point2D thePoint = thePoints.get(roomNumber);

            myGameLoop.myItems[place].setWorldY((int) thePoint.getX() *  myGameLoop.ROOM_SIZE +  myGameLoop.ROOM_SIZE/2 -  myGameLoop.TILE_SIZE/2 + myGameLoop.TILE_SIZE);
            myGameLoop.myItems[place].setWorldX((int) thePoint.getY() *  myGameLoop.ROOM_SIZE +  myGameLoop.ROOM_SIZE/2 -  myGameLoop.TILE_SIZE/2);

            myNumExistingItems += 1;
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