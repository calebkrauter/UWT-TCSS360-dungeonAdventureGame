package Controller;

import Model.entity.Entity;
import View.map.CollisionTile;
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
        int hitboxLeftCol = hitboxLeftWorldX/ myGamePanel.COLLISION_TILE_SIZE;
        int hitboxRightCol = hitboxRightWorldX/ myGamePanel.COLLISION_TILE_SIZE;
        int hitboxTopRow = hitboxTopWorldY/ myGamePanel.COLLISION_TILE_SIZE;
        int hitboxBottomRow = hitboxBottomWorldY/ myGamePanel.COLLISION_TILE_SIZE;

        CollisionTile tileNum1, tileNum2;

        switch(theEntity.getDirection()) {
            case "up":
                // we use + entity.speed to predict where the hitbox will move
                hitboxTopRow = (hitboxTopWorldY - theEntity.getSpeed()) / myGamePanel.COLLISION_TILE_SIZE;

                // now we can find out what tile the player is trying to step into
                // 2 possible tiles
                tileNum1 = myRoomManager.getCollisionWorldMap()[hitboxLeftCol][hitboxTopRow];
                tileNum2 = myRoomManager.getCollisionWorldMap()[hitboxRightCol][hitboxTopRow];

                if (tileNum1.getCollision() || tileNum2.getCollision()){
                    theEntity.collisionActive = true;
                }
                break;
            case "down":
                hitboxBottomRow = (hitboxBottomWorldY + theEntity.getSpeed()) / myGamePanel.COLLISION_TILE_SIZE;
                tileNum1 = myRoomManager.getCollisionWorldMap()[hitboxLeftCol][hitboxBottomRow];
                tileNum2 = myRoomManager.getCollisionWorldMap()[hitboxRightCol][hitboxBottomRow];

                if (tileNum1.getCollision() || tileNum2.getCollision()){
                    theEntity.collisionActive = true;
                }
                break;
            case "left":
                hitboxLeftCol = (hitboxLeftWorldX - theEntity.getSpeed()) / myGamePanel.COLLISION_TILE_SIZE;
                tileNum1 = myRoomManager.getCollisionWorldMap()[hitboxLeftCol][hitboxTopRow];
                tileNum2 = myRoomManager.getCollisionWorldMap()[hitboxLeftCol][hitboxBottomRow];

                if (tileNum1.getCollision() || tileNum2.getCollision()){
                    theEntity.collisionActive = true;
                }
                break;
            case "right":
                hitboxRightCol = (hitboxRightWorldX + theEntity.getSpeed()) / myGamePanel.COLLISION_TILE_SIZE;
                tileNum1 = myRoomManager.getCollisionWorldMap()[hitboxRightCol][hitboxTopRow];
                tileNum2 = myRoomManager.getCollisionWorldMap()[hitboxRightCol][hitboxBottomRow];

                if (tileNum1.getCollision() || tileNum2.getCollision()){
                    theEntity.collisionActive = true;
                }
                break;
        }

    }
}
