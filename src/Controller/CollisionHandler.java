package Controller;

import Model.entity.Entity;
import View.map.RoomManager;

public class CollisionHandler {
    private RoomManager myRoomManager;
    private GamePanel myGamePanel;
    public CollisionHandler(GamePanel theGP, RoomManager theRM){
        this.myRoomManager = theRM;
        this.myGamePanel = theGP;
    }
    public void checkTile(Entity theEntity){
        // we need world x and y of players hitbox
        // 4 points to check
        // left x
        int  hitboxLeftWorldX = theEntity.getWorldX() + theEntity.getHitBox().x;
        // right x
        int  hitboxRightWorldX = theEntity.getWorldX() + theEntity.getHitBox().x + theEntity.getHitBox().width;
        // top y
        int  hitboxTopWorldY = theEntity.getWorldY() + theEntity.getHitBox().y;
        // bottom y
        int  hitboxBottomWorldY = theEntity.getWorldY() + theEntity.getHitBox().y + theEntity.getHitBox().height;

        // based on coordinates, find Col and Row
        int hitboxLeftCol = hitboxLeftWorldX/ myGamePanel.TILE_SIZE;
        int hitboxRightCol = hitboxRightWorldX/ myGamePanel.TILE_SIZE;
        int hitboxTopRow = hitboxTopWorldY/ myGamePanel.TILE_SIZE;
        int hitboxBottomRow = hitboxBottomWorldY/ myGamePanel.TILE_SIZE;

        int tileNum1, tileNum2;

        switch(theEntity.getDirection()) {
            case "up":
                // we use + entity.speed to predict where the hitbox will move
                hitboxTopRow = (hitboxTopWorldY - theEntity.getSpeed()) / myGamePanel.TILE_SIZE;

                // now we can find out what tile the player is trying to step into
                // 2 possible tiles
                // this does not yet work because we do not have an underlying collision map of rectangles or int's.
                //tileNum1 = myGamePanel.myRoomManager.getWorldCollisionMap

                //tileNum2 = myGamePanel.myRoomManager.getCollisionMapTileNum(new int[hitboxRightCol][hitboxTopRow]);
                break;
            case "down":
                break;
            case "left":
                break;
            case "right":
                break;
        }

    }
}
