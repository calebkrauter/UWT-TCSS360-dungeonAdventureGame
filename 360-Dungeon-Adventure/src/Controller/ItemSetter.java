package Controller;

import Model.Item.*;
import View.map.RoomManager;

import javax.imageio.ImageIO;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * @author Makai Martinez
 * A class to create and set position of the game items (doors of all types, keys, pillars, and potions).
 */
public class ItemSetter {
    GameLoop myGameLoop;
    RoomManager myRoomManager;
    /**
     * The total number of items generated thus far.
     */
    private int myNumExistingItems;


    public ItemSetter(GameLoop theGP, RoomManager theRoomManager) {
        this.myGameLoop = theGP;
        this.myRoomManager = theRoomManager;
        myNumExistingItems = 0;
    }

    /**
     * Creates and places a key, a health potion, and a speed potion in the start room.
     */
    public void setStartItems() {
        Point2D thePoint = new Point2D.Float(0, 0);
        thePoint = myRoomManager.getStartPoint();

        myGameLoop.myItems[0] = new HealthPotion();
        myGameLoop.myItems[0].setWorldX((int) thePoint.getX() * myGameLoop.ROOM_SIZE + myGameLoop.ROOM_SIZE / 2 + myGameLoop.TILE_SIZE);
        myGameLoop.myItems[0].setWorldY((int) thePoint.getY() * myGameLoop.ROOM_SIZE + myGameLoop.ROOM_SIZE / 2 - myGameLoop.TILE_SIZE);
        setNumExistingItems(myNumExistingItems + 1);

        myGameLoop.myItems[1] = new Key();
        myGameLoop.myItems[1].setWorldX((int) thePoint.getX() * myGameLoop.ROOM_SIZE + myGameLoop.ROOM_SIZE / 2 - myGameLoop.TILE_SIZE);
        myGameLoop.myItems[1].setWorldY((int) thePoint.getY() * myGameLoop.ROOM_SIZE + myGameLoop.ROOM_SIZE / 2 - myGameLoop.TILE_SIZE);
        setNumExistingItems(myNumExistingItems + 1);

    }

    /**
     * Creates and places a key for each door room and sets each key position to the center of each room.
     */
    public void setKeys() {
        List<Point2D> thePoints;
        thePoints = myRoomManager.getDoorRoomPositions();
        int place = getNumExistingItems();  // I was gonna +1 here but it is accounted for because index starts at 0 and num items starts at 1.
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

    /**
     * Creates and places 4 doors (1 for each direction) for each door room.
     */
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

    /**
     * Creates and places the end room's doors' position and image for the end room.
     */
    public void setEndDoors() {
        Point2D thePoint = new Point2D.Float(0, 0);
        thePoint = myRoomManager.getEndPoint();

        int place = getNumExistingItems();

        myGameLoop.myItems[place] = new EndDoor();
        myGameLoop.myItems[place + 1] = new EndDoor();
        myGameLoop.myItems[place + 2] = new EndDoor();
        myGameLoop.myItems[place + 3] = new EndDoor();

        try {
            myGameLoop.myItems[place].setObjectImage(ImageIO.read(new File("res/Items/Door/RedDoor/RedTopDoor.png")));
            myGameLoop.myItems[place + 1].setObjectImage(ImageIO.read(new File("res/Items/Door/RedDoor/RedBottomDoor.png")));
            myGameLoop.myItems[place + 2].setObjectImage(ImageIO.read(new File("res/Items/Door/RedDoor/RedLeftDoor.png")));
            myGameLoop.myItems[place + 3].setObjectImage(ImageIO.read(new File("res/Items/Door/RedDoor/RedRightDoor.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // below getx and gety are reversed due to how the map is made
        myGameLoop.myItems[place].setWorldY((int) thePoint.getX() *  myGameLoop.ROOM_SIZE);
        myGameLoop.myItems[place].setWorldX((int) thePoint.getY() *  myGameLoop.ROOM_SIZE  + (4 * myGameLoop.TILE_SIZE) - 16);

        myGameLoop.myItems[place + 1].setWorldY((int) thePoint.getX() *  myGameLoop.ROOM_SIZE + (8 * myGameLoop.TILE_SIZE) - 32);
        myGameLoop.myItems[place + 1].setWorldX((int) thePoint.getY() *  myGameLoop.ROOM_SIZE  + (4 * myGameLoop.TILE_SIZE) - 16);

        myGameLoop.myItems[place + 2].setWorldY((int) thePoint.getX() *  myGameLoop.ROOM_SIZE + (4 * myGameLoop.TILE_SIZE) - 16);
        myGameLoop.myItems[place + 2].setWorldX((int) thePoint.getY() *  myGameLoop.ROOM_SIZE);

        myGameLoop.myItems[place + 3].setWorldY((int) thePoint.getX() *  myGameLoop.ROOM_SIZE + (4 * myGameLoop.TILE_SIZE) - 16);
        myGameLoop.myItems[place + 3].setWorldX((int) thePoint.getY() *  myGameLoop.ROOM_SIZE + (8 * myGameLoop.TILE_SIZE) - 32);


        // FOR TESTING
//        System.out.println("index " + place + " of myItems[] is an " + myGameLoop.myItems[place].getObjectName() + " with coordinates: (" + myGameLoop.myItems[place].getWorldX() + ", " + myGameLoop.myItems[place].getWorldY() + ")");
//        System.out.println("index " + (place + 1) + " of myItems[] is an " + myGameLoop.myItems[place + 1].getObjectName() + " with coordinates: (" + myGameLoop.myItems[place + 1].getWorldX() + ", " + myGameLoop.myItems[place + 1].getWorldY() + ")");
//        System.out.println("index " + (place + 2) + " of myItems[] is an " + myGameLoop.myItems[place + 2].getObjectName() + " with coordinates: (" + myGameLoop.myItems[place + 2].getWorldX() + ", " + myGameLoop.myItems[place + 2].getWorldY() + ")");
//        System.out.println("index " + (place + 3) + " of myItems[] is an " + myGameLoop.myItems[place + 3].getObjectName() + " with coordinates: (" + myGameLoop.myItems[place + 3].getWorldX() + ", " + myGameLoop.myItems[place + 3].getWorldY() + ")");

        myNumExistingItems += 4;
        place = myNumExistingItems;

    }

    /**
     * Creates and places 4 pillars in 4 randomly selected rooms.
     */
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

    /**
     * Creates and places 10 health potions in 10 randomly selected rooms.
     */
    public void setHealthPotions() {
        List<Point2D> thePoints;
        thePoints = myRoomManager.getDoorRoomPositions();   // placeholder
        int place = getNumExistingItems();
        int numHealthPotions = 20;

        // get a random number representing room type
        Random rand = new Random();
        for (int j = 0; j < numHealthPotions; j++){

            myGameLoop.myItems[place] = new HealthPotion();

            // generates random numbers in the range 0 to 4 - 1. 0-3 is 4 diff options.
            int roomType = rand.nextInt(4);

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

            myGameLoop.myItems[place].setWorldY((int) thePoint.getX() *  myGameLoop.ROOM_SIZE +  myGameLoop.ROOM_SIZE/2 -  myGameLoop.TILE_SIZE/2 - myGameLoop.TILE_SIZE);
            myGameLoop.myItems[place].setWorldX((int) thePoint.getY() *  myGameLoop.ROOM_SIZE +  myGameLoop.ROOM_SIZE/2 -  myGameLoop.TILE_SIZE/2);

            myNumExistingItems += 1;
            place = myNumExistingItems;
        }

    }

    /**
     * Creates and places 2 speed potions in 2 randomly selected rooms.
     */
    public void setSpeedPotions() {
        List<Point2D> thePoints;
        thePoints = myRoomManager.getDoorRoomPositions();   // placeholder
        int place = getNumExistingItems();
        int numSpeedPotions = 2;

        // get a random number representing room type
        Random rand = new Random();
        for (int j = 0; j < numSpeedPotions; j++){

            myGameLoop.myItems[place] = new SpeedPotion();

            // generates random numbers in the range 0 to 4 - 1. 0-3 is 4 diff options.
            int roomType = rand.nextInt(4);

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

            myGameLoop.myItems[place].setWorldY((int) thePoint.getX() *  myGameLoop.ROOM_SIZE +  myGameLoop.ROOM_SIZE/2 -  myGameLoop.TILE_SIZE/2);
            myGameLoop.myItems[place].setWorldX((int) thePoint.getY() *  myGameLoop.ROOM_SIZE +  myGameLoop.ROOM_SIZE/2 -  myGameLoop.TILE_SIZE/2 + myGameLoop.TILE_SIZE);

            myNumExistingItems += 1;
            place = myNumExistingItems;
        }

    }

    /**
     * Gets the total number of items generated thus far.
     * @return myNumExistingItems
     */
    public int getNumExistingItems() {
        return myNumExistingItems;
    }
    /**
     * Sets the total number of items generated thus far.
     */
    public void setNumExistingItems(int theNumItems) {
        myNumExistingItems = theNumItems;
    }
}