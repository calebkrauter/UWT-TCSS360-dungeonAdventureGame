package Controller;

import Model.Item.HealthPotion;
import Model.Item.Key;
import Model.Item.SpeedPotion;

import java.awt.geom.Point2D;

public class ItemSetter {
        GamePanel myGamePanel;
        MapManager myMapManager;

        public ItemSetter(GamePanel theGP, MapManager theMapManager) {
            this.myGamePanel = theGP;
            this.myMapManager = theMapManager;
        }

        public void setObject() {
            Point2D thePoint = new Point2D.Float(0, 0);
            thePoint = myMapManager.getStartPoint();
            myGamePanel.myItems[0] = new Key();
            myGamePanel.myItems[0].setWorldX((int) thePoint.getX() * myGamePanel.ROOM_SIZE + myGamePanel.ROOM_SIZE/2 - myGamePanel.TILE_SIZE/2);
            myGamePanel.myItems[0].setWorldY((int) thePoint.getY() * myGamePanel.ROOM_SIZE + myGamePanel.ROOM_SIZE/2 - myGamePanel.TILE_SIZE/2);

            myGamePanel.myItems[1] = new Key();
            myGamePanel.myItems[1].setWorldX(2 * myGamePanel.ROOM_SIZE);
            myGamePanel.myItems[1].setWorldY(2 * myGamePanel.ROOM_SIZE);

            myGamePanel.myItems[3] = new SpeedPotion();
            myGamePanel.myItems[3].setWorldX(3 * myGamePanel.ROOM_SIZE + myGamePanel.ROOM_SIZE/2 - myGamePanel.TILE_SIZE);
            myGamePanel.myItems[3].setWorldY(3 * myGamePanel.ROOM_SIZE + myGamePanel.ROOM_SIZE/2 - myGamePanel.TILE_SIZE);

            myGamePanel.myItems[4] = new HealthPotion();
            myGamePanel.myItems[4].setWorldX(4 * myGamePanel.ROOM_SIZE + myGamePanel.ROOM_SIZE/2 - myGamePanel.TILE_SIZE);
            myGamePanel.myItems[4].setWorldY(4 * myGamePanel.ROOM_SIZE + myGamePanel.ROOM_SIZE/2 - myGamePanel.TILE_SIZE);


        }
    }