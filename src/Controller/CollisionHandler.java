package Controller;

import Model.Entity.Entity;
import View.map.CollisionTile;
import View.map.RoomManager;

public class CollisionHandler {
    private RoomManager myRoomManager;
    private GameLoop myGameLoop;
    public CollisionHandler(GameLoop theGP, RoomManager theRM){
        this.myRoomManager = theRM;
        this.myGameLoop = theGP;
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
        int hitboxLeftCol = hitboxLeftWorldX/ myGameLoop.COLLISION_TILE_SIZE;
        int hitboxRightCol = hitboxRightWorldX/ myGameLoop.COLLISION_TILE_SIZE;
        int hitboxTopRow = hitboxTopWorldY/ myGameLoop.COLLISION_TILE_SIZE;
        int hitboxBottomRow = hitboxBottomWorldY/ myGameLoop.COLLISION_TILE_SIZE;

        CollisionTile tileNum1, tileNum2;

        switch(theEntity.getDirection()) {
            case "up":
                // we use + Entity.speed to predict where the hitbox will move
                hitboxTopRow = (hitboxTopWorldY - theEntity.getSpeed()) / myGameLoop.COLLISION_TILE_SIZE;

                // now we can find out what tile the player is trying to step into
                // 2 possible tiles
                tileNum1 = myRoomManager.getCollisionWorldMap()[hitboxLeftCol][hitboxTopRow];
                tileNum2 = myRoomManager.getCollisionWorldMap()[hitboxRightCol][hitboxTopRow];

                if (tileNum1.getCollision() || tileNum2.getCollision()){
                    theEntity.setCollision(true);
                }
                break;
            case "down":
                hitboxBottomRow = (hitboxBottomWorldY + theEntity.getSpeed()) / myGameLoop.COLLISION_TILE_SIZE;
                tileNum1 = myRoomManager.getCollisionWorldMap()[hitboxLeftCol][hitboxBottomRow];
                tileNum2 = myRoomManager.getCollisionWorldMap()[hitboxRightCol][hitboxBottomRow];

                if (tileNum1.getCollision() || tileNum2.getCollision()){
                    theEntity.setCollision(true);
                }
                break;
            case "left":
                hitboxLeftCol = (hitboxLeftWorldX - theEntity.getSpeed()) / myGameLoop.COLLISION_TILE_SIZE;
                tileNum1 = myRoomManager.getCollisionWorldMap()[hitboxLeftCol][hitboxTopRow];
                tileNum2 = myRoomManager.getCollisionWorldMap()[hitboxLeftCol][hitboxBottomRow];

                if (tileNum1.getCollision() || tileNum2.getCollision()){
                    theEntity.setCollision(true);
                }
                break;
            case "right":
                hitboxRightCol = (hitboxRightWorldX + theEntity.getSpeed()) / myGameLoop.COLLISION_TILE_SIZE;
                tileNum1 = myRoomManager.getCollisionWorldMap()[hitboxRightCol][hitboxTopRow];
                tileNum2 = myRoomManager.getCollisionWorldMap()[hitboxRightCol][hitboxBottomRow];

                if (tileNum1.getCollision() || tileNum2.getCollision()){
                    theEntity.setCollision(true);
                }
                break;
        }

    }
}
