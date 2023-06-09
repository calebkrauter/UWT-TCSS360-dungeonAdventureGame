package Controller;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import Controller.DB.MonsterStatsDB;
import Model.Entity.Entity;
import View.map.CollisionTile;
import View.map.RoomManager;

import javax.swing.*;
import java.awt.*;

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

                if (tileNum1.getCollision() || tileNum2.getCollision()) {
                    theEntity.setCollision(true);
                }
                break;
            case "down":
                hitboxBottomRow = (hitboxBottomWorldY + theEntity.getSpeed()) / myGameLoop.COLLISION_TILE_SIZE;
                tileNum1 = myRoomManager.getCollisionWorldMap()[hitboxLeftCol][hitboxBottomRow];
                tileNum2 = myRoomManager.getCollisionWorldMap()[hitboxRightCol][hitboxBottomRow];

                if (tileNum1.getCollision() || tileNum2.getCollision()) {
                    theEntity.setCollision(true);
                }
                break;
            case "left":
                hitboxLeftCol = (hitboxLeftWorldX - theEntity.getSpeed()) / myGameLoop.COLLISION_TILE_SIZE;
                tileNum1 = myRoomManager.getCollisionWorldMap()[hitboxLeftCol][hitboxTopRow];
                tileNum2 = myRoomManager.getCollisionWorldMap()[hitboxLeftCol][hitboxBottomRow];

                if (tileNum1.getCollision() || tileNum2.getCollision()) {
                    theEntity.setCollision(true);
                }
                break;
            case "right":
                hitboxRightCol = (hitboxRightWorldX + theEntity.getSpeed()) / myGameLoop.COLLISION_TILE_SIZE;
                tileNum1 = myRoomManager.getCollisionWorldMap()[hitboxRightCol][hitboxTopRow];
                tileNum2 = myRoomManager.getCollisionWorldMap()[hitboxRightCol][hitboxBottomRow];

                if (tileNum1.getCollision() || tileNum2.getCollision()) {
                    theEntity.setCollision(true);
                }
                break;
        }
    }

    boolean dialogShown = false;
    // TODO update names in this method
    public boolean checkMonster(Entity theEntity, boolean isPlayer) {
        int index = 999;
        boolean characterCollide = false;
        for (int i = 0; i < myGameLoop.myEntities.length; i++){

            if(myGameLoop.myEntities[i] != null){

                // DO NOT USE "SET..." ON ANY OF THE DATA BELOW. ONLY USE GET.

                // get copy of the entity's hitbox position
                Rectangle newHitbox = theEntity.getHitBox();
                newHitbox.x += theEntity.getWorldX();
                newHitbox.y += theEntity.getWorldY();

                // get copy of the item hitbox position
                Rectangle newitemHitbox = myGameLoop.myEntities[i].getHitBox();
                newitemHitbox.x += myGameLoop.myEntities[i].getWorldX();
                newitemHitbox.y += myGameLoop.myEntities[i].getWorldY();

                switch(theEntity.getDirection()) {  // check entity's direction
                    case "up":
                        newHitbox.y -= theEntity.getSpeed();
                        fightActionOption(newHitbox, newitemHitbox, theEntity, i, characterCollide);
                        break;
                    case "down":
                        newHitbox.y += theEntity.getSpeed();
                        fightActionOption(newHitbox, newitemHitbox, theEntity, i, characterCollide);
                        break;
                    case "left":
                        newHitbox.x -= theEntity.getSpeed();
                        fightActionOption(newHitbox, newitemHitbox, theEntity, i, characterCollide);

                        break;
                    case "right":
                        newHitbox.x += theEntity.getSpeed();
                        fightActionOption(newHitbox, newitemHitbox, theEntity, i, characterCollide);
                        break;
                }

                // reset to default values
                newHitbox.x = theEntity.getHitboxDefaultX();
                newHitbox.y = theEntity.getHitboxDefaultY();
                theEntity.setHitBox(newHitbox);

                newitemHitbox.x = myGameLoop.myEntities[i].getHitboxDefaultX();
                newitemHitbox.y = myGameLoop.myEntities[i].getHitboxDefaultY();
                myGameLoop.myEntities[i].setHitBox(newitemHitbox);

            }
        }


        return characterCollide;
    }

    private void fightActionOption(Rectangle newHitbox, Rectangle newitemHitbox, Entity theEntity, int i, boolean characterCollide){
        if(newHitbox.intersects(newitemHitbox)) {
            if (myGameLoop.myEntities[i].getCollision()) {
                theEntity.setCollision(true);
                characterCollide = true;
                if (!dialogShown) {
                    popUpFight(theEntity, i);
                }
            }
        }
    }
    private void startTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Code to be executed after 5 seconds
                dialogShown = false;
                timer.cancel(); // Cancel the timer after it expires
            }
        }, 3000);
    }
    private void popUpFight(Entity theEntity, int i) {
        dialogShown = true;
        int fightOption = JOptionPane.showOptionDialog(
                null,
                "DO YOU WISH TO FIGHT?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                null
        );

        // Process the selected option
        if (fightOption == JOptionPane.YES_OPTION) {
            doFight(theEntity, myGameLoop.myEntities, i);
            System.out.println("FIGHT");
            startTimer();
        } else if (fightOption == JOptionPane.NO_OPTION) {
            theEntity.setCollision(false);
            startTimer();
        }
    }
    private void doFight(Entity theEntity, Entity[] myEntities, int i) {
        Random random = new Random();
        int chance = random.nextInt(0, 5);
        // TODO use a whlie loop to decrease health in enemy or player until one is dead.
        if (chance > 2) {
            // Updates a gremlin health when it gets hit.
            MonsterStatsDB monsterStatsDB = new MonsterStatsDB();
            monsterStatsDB.setMonsterStat(monsterStatsDB.GREMLIN, monsterStatsDB.DEFAULT_HEALTH, 45);
            System.out.println(myEntities[i].getEntityName());
        } else {
            // Updates player health when it gets hit.
            theEntity.setHealth(56);
        }
    }


    /**
     *
     * Check if player is hitting a item, if it is return index of object.
     *
     * @param theEntity
     * @param isPlayer
     * @return
     */
    public int checkItem(Entity theEntity, boolean isPlayer){
        int index = 999;

        for (int i = 0; i < myGameLoop.myItems.length; i++){

            if(myGameLoop.myItems[i] != null){

                // DO NOT USE "SET..." ON ANY OF THE DATA BELOW. ONLY USE GET.

                // get copy of the entity's hitbox position
                Rectangle newHitbox = theEntity.getHitBox();
                newHitbox.x += theEntity.getWorldX();
                newHitbox.y += theEntity.getWorldY();

                // get copy of the item hitbox position
                Rectangle newitemHitbox = myGameLoop.myItems[i].getItemHitbox();
                newitemHitbox.x += myGameLoop.myItems[i].getWorldX();
                newitemHitbox.y += myGameLoop.myItems[i].getWorldY();

                switch(theEntity.getDirection()) {  // check entity's direction
                    case "up":
                        newHitbox.y -= theEntity.getSpeed();
                        if(newHitbox.intersects(newitemHitbox)) {
                            if (myGameLoop.myItems[i].getCollision()) {
                                theEntity.setCollision(true);
                            }
                            // this is so that non-playable characters like monsters don't pick up items.
                            if (isPlayer) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        newHitbox.y += theEntity.getSpeed();
                        if(newHitbox.intersects(newitemHitbox)) {
                            if (myGameLoop.myItems[i].getCollision()) {
                                theEntity.setCollision(true);
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        newHitbox.x -= theEntity.getSpeed();
                        if(newHitbox.intersects(newitemHitbox)) {
                            if (myGameLoop.myItems[i].getCollision()) {
                                theEntity.setCollision(true);
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        newHitbox.x += theEntity.getSpeed();
                        if(newHitbox.intersects(newitemHitbox)) {
                            if (myGameLoop.myItems[i].getCollision()) {
                                theEntity.setCollision(true);
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                        break;
                }

                // reset to default values
                newHitbox.x = theEntity.getHitboxDefaultX();
                newHitbox.y = theEntity.getHitboxDefaultY();
                theEntity.setHitBox(newHitbox);

                newitemHitbox.x = myGameLoop.myItems[i].getHitboxDefaultX();
                newitemHitbox.y = myGameLoop.myItems[i].getHitboxDefaultY();
                myGameLoop.myItems[i].setItemHitbox(newitemHitbox);

            }
        }
        return index;
    }
}
