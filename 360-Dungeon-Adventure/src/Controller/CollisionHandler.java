package Controller;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import Controller.MenuManagment.Actions.interactionSound;
import Model.DB.MonsterStatsDB;
import Model.Entity.Archer;
import Model.Entity.Entity;
import View.map.CollisionTile;
import View.map.RoomManager;

import javax.swing.*;
import java.awt.*;

/**
 * @author Makai Martinez
 * @author Caleb Krauter
 * @version 1.0
 */

/**
 * This class handles collisions for the player when the player
 * interacts with walls, enemies and other entities like items.
 */
public class CollisionHandler {
    /**
     * Class to manage rooms.
     */
    private RoomManager myRoomManager;
    /**
     * Starts game loop and updates game data for gameplay.
     */
    private GameLoop myGameLoop;
    /**
     * Boolean used for showing if the dialog box is visible that tells the player they blocked.
     */
    private boolean blockDialogShown = false;
    /**
     * Boolean used for showing if the dialog box is visible.
     */
    private boolean dialogShown = false;
    /**
     * An audio String file used for dying entities.
     */
    private final String DEATH_SOUND = "deathSound.wav";
    /**
     * A boolean telling if the player is out of a fight.
     */
    private boolean getOut = false;
    /**
     * A boolean telling if the player is out of a fight.
     */
    private boolean fightEnd = false;

    /**
     * A constructor for the Collision Handler.
     * @param theGameLoop   the game loop for our game.
     * @param theRM     the room manager for room display.
     */
    public CollisionHandler(GameLoop theGameLoop, RoomManager theRM){
        this.myRoomManager = theRM;
        this.myGameLoop = theGameLoop;
    }

    /**
     * Check if player's hitbox is going to collide with any tiles in the collision map and updates player collision accordingly.
     * @param theEntity Currently only the player would be theEntity but could work for other entities.
     */
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



    /**
     * Check if player's hitbox is going to collide with any Monsters' hitbox's from the entities list. If so then the
     * combat state begins.
     * @param thePlayer Currently only the player would be theEntity but could work for other entities.
     */
    public void checkMonster(Entity thePlayer) {
        for (int i = 0; i < myGameLoop.myEntities.length; i++){

            if(myGameLoop.myEntities[i] != null){

                // get copy of the entity's hitbox position
                Rectangle newHitbox = thePlayer.getHitBox();
                newHitbox.x += thePlayer.getWorldX();
                newHitbox.y += thePlayer.getWorldY();

                // get copy of the item hitbox position
                Rectangle newitemHitbox = myGameLoop.myEntities[i].getHitBox();
                newitemHitbox.x += myGameLoop.myEntities[i].getWorldX();
                newitemHitbox.y += myGameLoop.myEntities[i].getWorldY();

                switch(thePlayer.getDirection()) {  // check entity's direction
                    case "up":
                        newHitbox.y -= thePlayer.getSpeed();
                        if (getOut) {
                            getOut = false;
                            fightEnd = true;
                            break;
                        }
                        fightActionOption(newHitbox, newitemHitbox, thePlayer, i);
                        break;
                    case "down":
                        newHitbox.y += thePlayer.getSpeed();
                        if (getOut) {
                            getOut = false;
                            fightEnd = true;
                            break;
                        }
                        fightActionOption(newHitbox, newitemHitbox, thePlayer, i);
                        break;
                    case "left":
                        newHitbox.x -= thePlayer.getSpeed();
                        if (getOut) {
                            getOut = false;
                            fightEnd = true;
                            break;
                        }
                        fightActionOption(newHitbox, newitemHitbox, thePlayer, i);

                        break;
                    case "right":
                        newHitbox.x += thePlayer.getSpeed();
                        if (getOut) {
                            getOut = false;
                            fightEnd = true;
                            break;
                        }
                        fightActionOption(newHitbox, newitemHitbox, thePlayer, i);
                        break;
                }

                // reset to default values
                newHitbox.x = thePlayer.getHitboxDefaultX();
                newHitbox.y = thePlayer.getHitboxDefaultY();
                thePlayer.setHitBox(newHitbox);

                if (myGameLoop.myEntities[i] != null) {

                    newitemHitbox.x = myGameLoop.myEntities[i].getHitboxDefaultX();
                    newitemHitbox.y = myGameLoop.myEntities[i].getHitboxDefaultY();
                    myGameLoop.myEntities[i].setHitBox(newitemHitbox);
                }
            }
        }
    }

    /**
     * Starts popup fight when requirements met.
     * @param newHitbox
     * @param newitemHitbox
     * @param thePlayer
     * @param i
     */
    private void fightActionOption(Rectangle newHitbox, Rectangle newitemHitbox, Entity thePlayer, int i){
        if(newHitbox.intersects(newitemHitbox)) {
            if (myGameLoop.myEntities[i].getCollision()) {
                thePlayer.setCollision(true);
                if (!dialogShown && !blockDialogShown) {
                    popUpFight(thePlayer, i);
                } else {
                    myGameLoop.setGameState(myGameLoop.PLAY_STATE);

                }
            }
        }
    }

    /**
     * Timer used for delaying when the game unpauses.
     */
    private void startTimer() {
        dialogShown = true;

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Code to be executed after 5 seconds
                dialogShown = false;
                blockDialogShown = false;
                getOut = false;
                fightEnd = false;

                myGameLoop.setGameState(myGameLoop.PLAY_STATE);
                timer.cancel(); // Cancel the timer after it expires
            }
        }, 3000);
    }

    /**
     * Starts popup fight.
     * @param thePlayer
     * @param theMonster
     */
    private void popUpFight(Entity thePlayer, int theMonster) {
        dialogShown = true;
        int fightOption = JOptionPane.showOptionDialog(
                null,
                "DO YOU WISH TO FIGHT? Your health: " + thePlayer.getHealth() + "\nMONSTER "
                        + myGameLoop.myEntities[theMonster].getEntityName() + " health: "
                        + myGameLoop.myEntities[theMonster].getHealth() + "\nYour hit chance: " + thePlayer.getHitChance() + ", your damage is between " + thePlayer.getMinDamage() + " and " + thePlayer.getMaxDamage()
                        + "\n" + myGameLoop.myEntities[theMonster].getEntityName() + " damage is between " +  myGameLoop.myEntities[theMonster].getMinDamage() + " and " + myGameLoop.myEntities[theMonster].getMaxDamage(),
                "3 second cool down on approaching this MONSTER.",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                null
        );

        // Process the selected option
        if (fightOption == JOptionPane.YES_OPTION) {
            doFight(thePlayer, myGameLoop.myEntities, theMonster);
            System.out.println("FIGHT");
            dialogShown = false;

        } else if (fightOption == JOptionPane.NO_OPTION) {
            thePlayer.setCollision(false);
            startTimer();
            myGameLoop.setGameState(myGameLoop.PLAY_STATE);
        }
    }

    /**
     * Starts the fight and does the damage situations.
     * @param thePlayer
     * @param myEntities
     * @param theMonster
     */
    private void doFight(Entity thePlayer, Entity[] myEntities, int theMonster) {
        getOut = false;

        blockDialogShown = false;
        Random random = new Random();
        int chance = random.nextInt(0, 15);
        // Hit chance could be 80 or 70, so now there is a 7 or 8 out of 10 chance to hit enemy,
        // the rest 2 or 3 to 10 is enemy attack chance and 10 to 12 is block chance.
        int playerDamage = new Random().nextInt(thePlayer.getMinDamage(), thePlayer.getMaxDamage());
        int monsterDamage = new Random().nextInt(myGameLoop.myEntities[theMonster].getMinDamage(), myGameLoop.myEntities[theMonster].getMaxDamage());
        if (chance <= thePlayer.getHitChance()/10) {
            // Updates a gremlin health when it gets hit.
            MonsterStatsDB monsterStatsDB = new MonsterStatsDB();
            monsterStatsDB.setMonsterStat(monsterStatsDB.GREMLIN, monsterStatsDB.DEFAULT_HEALTH, 45);
            System.out.println(myEntities[theMonster].getEntityName());
            myEntities[theMonster].setHealth(myEntities[theMonster].getHealth() - playerDamage);
            if (myEntities[theMonster].getHealth() <= 0) {
                myGameLoop.myEntities[theMonster] = null;
                new interactionSound(DEATH_SOUND);
                new JOptionPane().showMessageDialog(null, "YOU DEFEATED THE MONSTER!!!", "YOU WON.", JOptionPane.PLAIN_MESSAGE);
                thePlayer.setCollision(false);
                myGameLoop.setGameState(myGameLoop.PAUSE_STATE);
                startTimer();
            }
        } else if (chance > thePlayer.getHitChance()/10 && chance <= 12){
            // Chance that enemy hits player
            thePlayer.setHealth(thePlayer.getHealth() - monsterDamage);
            if (thePlayer.getHealth() <= 0) {
                new interactionSound(DEATH_SOUND);
                new JOptionPane().showMessageDialog(null, "GAME OVER, to respawn load game and find your save.", "YOU LOST.", JOptionPane.PLAIN_MESSAGE);
                myGameLoop.setGameState(myGameLoop.PLAY_STATE);
                System.exit(0);
            }
        } else {
            blockDialogShown = true;
            dialogShown = true;
            // Block
            int leaveOption = JOptionPane.showOptionDialog(
                    null,
                    "You lucky adventurer, you blocked, would you like to leave?",
                    "3 second cool down on approaching this MONSTER.",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    null,
                    null
            );
            if (leaveOption == JOptionPane.YES_OPTION) {
                thePlayer.setCollision(false);
                startTimer();
            } else {
                doFight(thePlayer, myGameLoop.myEntities, theMonster);
                System.out.println("FIGHT");
                dialogShown = false;
                myGameLoop.setGameState(myGameLoop.PLAY_STATE);
            }
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
