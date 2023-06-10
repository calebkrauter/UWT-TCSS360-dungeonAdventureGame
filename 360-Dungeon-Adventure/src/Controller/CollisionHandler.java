package Controller;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import Actions.interactionSound;
import Controller.DB.MonsterStatsDB;
import Model.Entity.Entity;
import View.map.CollisionTile;
import View.map.RoomManager;

import javax.swing.*;
import java.awt.*;

/**
 * @author Makai Martinez
 * @author Caleb Krauter
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
     * A constructor for
     * @param theGP
     * @param theRM
     */
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
    public boolean checkMonster(Entity theEntity) {
        int index = 999;
        boolean characterCollide = false;
        if (fightEnd) {
            startTimer(theEntity);
        }
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
                        if (getOut) {
                            getOut = false;
                            fightEnd = true;
                            break;
                        }
                        fightActionOption(newHitbox, newitemHitbox, theEntity, i, characterCollide);
                        break;
                    case "down":
                        newHitbox.y += theEntity.getSpeed();
                        if (getOut) {
                            getOut = false;
                            fightEnd = true;
                            break;
                        }
                        fightActionOption(newHitbox, newitemHitbox, theEntity, i, characterCollide);
                        break;
                    case "left":
                        newHitbox.x -= theEntity.getSpeed();
                        if (getOut) {
                            getOut = false;
                            fightEnd = true;
                            break;
                        }
                        fightActionOption(newHitbox, newitemHitbox, theEntity, i, characterCollide);

                        break;
                    case "right":
                        newHitbox.x += theEntity.getSpeed();
                        if (getOut) {
                            getOut = false;
                            fightEnd = true;
                            break;
                        }
                        fightActionOption(newHitbox, newitemHitbox, theEntity, i, characterCollide);
                        break;
                }

                // reset to default values
                newHitbox.x = theEntity.getHitboxDefaultX();
                newHitbox.y = theEntity.getHitboxDefaultY();
                theEntity.setHitBox(newHitbox);

                if (myGameLoop.myEntities[i] != null) {

                    newitemHitbox.x = myGameLoop.myEntities[i].getHitboxDefaultX();
                    newitemHitbox.y = myGameLoop.myEntities[i].getHitboxDefaultY();
                    myGameLoop.myEntities[i].setHitBox(newitemHitbox);
                }

            }
        }


        return fightEnd;
    }

    private void fightActionOption(Rectangle newHitbox, Rectangle newitemHitbox, Entity theEntity, int i, boolean characterCollide){

        if(newHitbox.intersects(newitemHitbox)) {
            if (myGameLoop.myEntities[i].getCollision()) {
                myGameLoop.setCombat(true);
                theEntity.setCollision(true);
                characterCollide = true;
                myGameLoop.setGameState(myGameLoop.PAUSE_STATE);
//                startTimer(theEntity);
                if (!dialogShown && !blockDialogShown) {
                    popUpFight(theEntity, i);
                } else {
                    myGameLoop.setGameState(myGameLoop.PLAY_STATE);

                }
            }
        }
    }
    private void startTimer(Entity theEntity) {
        dialogShown = true;

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Code to be executed after 5 seconds
                dialogShown = false;
                blockDialogShown = false;
                getOut = false;
                getOut = false;
                fightEnd = false;
//                KeyHandler keyHandler = new KeyHandler();
//                keyHandler.upPressed = false;
//                keyHandler.leftPressed = false;
//                keyHandler.rightPressed = false;
//                keyHandler.downPressed = false;
                myGameLoop.setGameState(myGameLoop.PLAY_STATE);
                myGameLoop.setCombat(false);
                timer.cancel(); // Cancel the timer after it expires


            }
        }, 3000);
    }

    private boolean leave = false;


    private void popUpFight(Entity theEntity, int i) {

        leave = false;
        //
//        while (theEntity.getHealth() > 0 || myGameLoop.myEntities[i].getHealth() > 0 || !leave) {
            leave = false;
            dialogShown = true;
            int fightOption = JOptionPane.showOptionDialog(
                    null,
                    "DO YOU WISH TO FIGHT? Your health: " + theEntity.getHealth() + "\nMONSTER "
                            + myGameLoop.myEntities[i].getEntityName() + " health: "
                            + myGameLoop.myEntities[i].getHealth() + "\nYour hit chance: " + theEntity.getHitChance() + ", your damage is between " + theEntity.getMinDamage() + " and " + theEntity.getMaxDamage()
                            + "\n" + myGameLoop.myEntities[i].getEntityName() + " damage is between " +  myGameLoop.myEntities[i].getMinDamage() + " and " + myGameLoop.myEntities[i].getMaxDamage(),
                    "3 second cool down on approaching this MONSTER.",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    null,
                    null
            );

            // Process the selected option
            if (fightOption == JOptionPane.YES_OPTION) {
                leave = false;

                doFight(theEntity, myGameLoop.myEntities, i);
                System.out.println("FIGHT");
                dialogShown = false;

            } else if (fightOption == JOptionPane.NO_OPTION) {
                leave = true;
                theEntity.setCollision(false);
                startTimer(theEntity);
                myGameLoop.setCombat(false);
                myGameLoop.setGameState(myGameLoop.PLAY_STATE);
            }
        }

    private void doFight(Entity theEntity, Entity[] myEntities, int i) {
        getOut = false;

        blockDialogShown = false;
        Random random = new Random();
        int chance = random.nextInt(0, 15);
        // TODO use a whlie loop to decrease health in enemy or player until one is dead.
        // Hit chance could be 80 or 70, so now there is a 7 or 8 out of 10 chance to hit enemy,
        // the rest 2 or 3 to 10 is enemy attack chance and 10 to 12 is block chance.
        int playerDamage = new Random().nextInt(theEntity.getMinDamage(), theEntity.getMaxDamage());
        int monsterDamage = new Random().nextInt(myGameLoop.myEntities[i].getMinDamage(), myGameLoop.myEntities[i].getMaxDamage());
        if (chance <= theEntity.getHitChance()/10) {
            // Updates a gremlin health when it gets hit.
            MonsterStatsDB monsterStatsDB = new MonsterStatsDB();
            monsterStatsDB.setMonsterStat(monsterStatsDB.GREMLIN, monsterStatsDB.DEFAULT_HEALTH, 45);
            System.out.println(myEntities[i].getEntityName());
            myEntities[i].setHealth(myEntities[i].getHealth() - playerDamage);
            if (myEntities[i].getHealth() <= 0) {
                myGameLoop.myEntities[i] = null;
                new interactionSound(DEATH_SOUND);
                new JOptionPane().showMessageDialog(null, "YOU DEFEATED THE MONSTER!!!", "YOU WON.", JOptionPane.PLAIN_MESSAGE);
                leave = true;
                theEntity.setCollision(false);
                myGameLoop.setCombat(false);
                myGameLoop.setGameState(myGameLoop.PAUSE_STATE);
                startTimer(theEntity);


            }
            theEntity.setSpeed(0);

        } else if (chance > theEntity.getHitChance()/10 && chance <= 12){
            // Chance that enemy hits player
            theEntity.setHealth(theEntity.getHealth() - monsterDamage);
            if (theEntity.getHealth() <= 0) {

                new interactionSound(DEATH_SOUND);
                new JOptionPane().showMessageDialog(null, "GAME OVER, to respawn load game and find your save.", "YOU LOST.", JOptionPane.PLAIN_MESSAGE);
                myGameLoop.setCombat(false);
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
                leave = true;
                theEntity.setCollision(false);
                startTimer(theEntity);
            } else {
                leave = false;

                doFight(theEntity, myGameLoop.myEntities, i);
                System.out.println("FIGHT");
                dialogShown = false;
                myGameLoop.setCombat(false);
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
