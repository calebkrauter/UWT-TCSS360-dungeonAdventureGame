package Controller;


import Model.DB.MonsterStatsDB;
import Model.Entity.Enemy.Monster;
import View.map.RoomManager;

import java.awt.geom.Point2D;
import java.util.List;
/**
 * @author Caleb Krauter
 * @author Makai
 * @version  1.0
 */

/**
 * Sets entities.
 */
public class EntitySetter {
    /**
     * The game loop class reference.
     */
    private GameLoop myGameLoop;
    /**
     * The reference to the room manager class.
     */
    private RoomManager myRoomManager;
    /**
     * The number of entities.
     */
    private int myNumEntitys;
    /**
     * The reference to the monster stats database.
     */
    private MonsterStatsDB myMonsterStatsDB;

    /**
     * The constructor.
     * @param theGP
     * @param theRoomManager
     */
    public EntitySetter(GameLoop theGP, RoomManager theRoomManager) {
        this.myGameLoop = theGP;
        this.myRoomManager = theRoomManager;
        myNumEntitys = 0;
        myMonsterStatsDB = new MonsterStatsDB();
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

            myGameLoop.myEntities[place] = new Monster(myMonsterStatsDB.getMonsterStat(myMonsterStatsDB.OGRE, myMonsterStatsDB.MONSTER_TYPE), myMonsterStatsDB);

            // below getx and gety are reversed due to how the map is made
            myGameLoop.myEntities[place].setWorldX((int) thePoint.getY() *  myGameLoop.ROOM_SIZE +  myGameLoop.ROOM_SIZE/2 -  myGameLoop.TILE_SIZE/2);
            myGameLoop.myEntities[place].setWorldY((int) thePoint.getX() *  myGameLoop.ROOM_SIZE +  myGameLoop.ROOM_SIZE/2 -  myGameLoop.TILE_SIZE/2);
            myNumEntitys += 1;
            place = myNumEntitys;

        }
    }

    /**
     * Creates and sets 4 skeletons' positions in the end room (the boss)
     */
    public void setSkeletons() {
        Point2D thePoint = myRoomManager.getEndPoint();
        int place = getNumEntitys();  // i was gonna +1 here but it is accounted for because index starts at 0 and num items starts at 1.
        myGameLoop.myEntities[place] = new Monster(myMonsterStatsDB.getMonsterStat(myMonsterStatsDB.SKELTON, myMonsterStatsDB.MONSTER_TYPE), myMonsterStatsDB);
        myGameLoop.myEntities[place + 1] = new Monster(myMonsterStatsDB.getMonsterStat(myMonsterStatsDB.SKELTON, myMonsterStatsDB.MONSTER_TYPE), myMonsterStatsDB);
        myGameLoop.myEntities[place + 2] = new Monster(myMonsterStatsDB.getMonsterStat(myMonsterStatsDB.SKELTON, myMonsterStatsDB.MONSTER_TYPE), myMonsterStatsDB);
        myGameLoop.myEntities[place + 3] = new Monster(myMonsterStatsDB.getMonsterStat(myMonsterStatsDB.SKELTON, myMonsterStatsDB.MONSTER_TYPE), myMonsterStatsDB);

        myGameLoop.myEntities[place] = new Monster(myMonsterStatsDB.getMonsterStat(myMonsterStatsDB.SKELTON, myMonsterStatsDB.MONSTER_TYPE), myMonsterStatsDB);
        // Top
        myGameLoop.myEntities[place].setWorldY((int) thePoint.getX() * myGameLoop.ROOM_SIZE + myGameLoop.TILE_SIZE);
        myGameLoop.myEntities[place].setWorldX((int) thePoint.getY() * myGameLoop.ROOM_SIZE + myGameLoop.ROOM_SIZE/2 - myGameLoop.TILE_SIZE/2);
        // Bottom
        myGameLoop.myEntities[place + 1].setWorldY((int) thePoint.getX() *  myGameLoop.ROOM_SIZE + myGameLoop.ROOM_SIZE - (2 * myGameLoop.TILE_SIZE));
        myGameLoop.myEntities[place + 1].setWorldX((int) thePoint.getY() *  myGameLoop.ROOM_SIZE + myGameLoop.ROOM_SIZE/2 - myGameLoop.TILE_SIZE/2);
        // left
        myGameLoop.myEntities[place + 2].setWorldY((int) thePoint.getX() *  myGameLoop.ROOM_SIZE + myGameLoop.ROOM_SIZE/2 - myGameLoop.TILE_SIZE/2);
        myGameLoop.myEntities[place + 2].setWorldX((int) thePoint.getY() *  myGameLoop.ROOM_SIZE + myGameLoop.TILE_SIZE);
        // right
        myGameLoop.myEntities[place + 3].setWorldY((int) thePoint.getX() *  myGameLoop.ROOM_SIZE + myGameLoop.ROOM_SIZE/2 - myGameLoop.TILE_SIZE/2);
        myGameLoop.myEntities[place + 3].setWorldX((int) thePoint.getY() *  myGameLoop.ROOM_SIZE + myGameLoop.ROOM_SIZE - (2 * myGameLoop.TILE_SIZE));

        myNumEntitys += 4;
        place = myNumEntitys;

    }

    /**
     * Sets gremlins.
     */
    public void setGremlins() {
        List<Point2D> thePoints;
        thePoints = myRoomManager.getDoorRoomPositions();
        int place = getNumEntitys();  // i was gonna +1 here but it is accounted for because index starts at 0 and num items starts at 1.
        for (int i = 0; i < thePoints.size(); i++) {
            Point2D thePoint = thePoints.get(i);

            myGameLoop.myEntities[place] = new Monster(myMonsterStatsDB.getMonsterStat(myMonsterStatsDB.GREMLIN, myMonsterStatsDB.MONSTER_TYPE), myMonsterStatsDB);
            myGameLoop.myEntities[place + 1] = new Monster(myMonsterStatsDB.getMonsterStat(myMonsterStatsDB.GREMLIN, myMonsterStatsDB.MONSTER_TYPE), myMonsterStatsDB);


            // below getx and gety are reversed due to how the map is made
            myGameLoop.myEntities[place].setWorldY((int) thePoint.getX() *  myGameLoop.ROOM_SIZE +  myGameLoop.ROOM_SIZE/2 -  myGameLoop.TILE_SIZE/2);
            myGameLoop.myEntities[place].setWorldX((int) thePoint.getY() *  myGameLoop.ROOM_SIZE +  myGameLoop.ROOM_SIZE/2 -  2 * myGameLoop.TILE_SIZE);

            myGameLoop.myEntities[place + 1].setWorldY((int) thePoint.getX() *  myGameLoop.ROOM_SIZE +  myGameLoop.ROOM_SIZE/2 -  myGameLoop.TILE_SIZE/2);
            myGameLoop.myEntities[place + 1].setWorldX((int) thePoint.getY() *  myGameLoop.ROOM_SIZE +  myGameLoop.ROOM_SIZE/2 +  myGameLoop.TILE_SIZE);

            myNumEntitys += 2;
            place = myNumEntitys;

        }
    }

    /**
     * Gets num of entities.
     * @return
     */
    public int getNumEntitys() {
        return myNumEntitys;
    }
}

