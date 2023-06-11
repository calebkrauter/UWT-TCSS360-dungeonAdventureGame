package Model.Entity.Enemy;

import Model.DB.MonsterStatsDB;
import Controller.EntitySetter;
import Controller.GameLoop;
import Model.Entity.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Makai Marteniz
 * @author Caleb Krauter
 * @version 1.0
 */
public class Monster extends Entity {


    private BufferedImage myEnemyImage;
    private boolean myCollision = false;
    private int myBlockChance;

    private final int TILE_SIZE = 48;
    GameLoop myGameLoop;
    private String myMonsterType;
    private MonsterStatsDB myMonsterStatsDB;
    private EntitySetter myEntitySetter;
    public Monster(Object theMonsterType, MonsterStatsDB theMonsterStatsDB){
        myMonsterType = (String) theMonsterType;
        myMonsterStatsDB = theMonsterStatsDB;
        // can be changed, we don't want hitbox as big as character
        Rectangle solidHitBox = new Rectangle(0, 0, TILE_SIZE, TILE_SIZE);
        setHitBox(solidHitBox);
        setDefaultValues();
    }

    public void setDefaultValues(){
        String skeletonName = (String) myMonsterStatsDB.getMonsterStat(myMonsterStatsDB.SKELTON, myMonsterStatsDB.MONSTER_TYPE);
        String ogreName = (String) myMonsterStatsDB.getMonsterStat(myMonsterStatsDB.OGRE, myMonsterStatsDB.MONSTER_TYPE);
        String gremlinName = (String) myMonsterStatsDB.getMonsterStat(myMonsterStatsDB.GREMLIN, myMonsterStatsDB.MONSTER_TYPE);
        if (myMonsterType.equals(skeletonName)) {
            setSkeletonStats();
        }else if (myMonsterType.equals(ogreName)) {
            setOgreStats();
        }else if (myMonsterType.equals(gremlinName)) {
            setGremlinStats();
        }

        setSpeed(2);
        //starting direction can vary.
        setDirection("down");
        setCollision(true);
    }

    private void setSkeletonStats() {
        setEntityName((String) myMonsterStatsDB.getMonsterStat(myMonsterStatsDB.SKELTON, myMonsterStatsDB.MONSTER_TYPE));
        try {
            setImageDown1(ImageIO.read(new File((String) myMonsterStatsDB.getMonsterStat(myMonsterStatsDB.SKELTON, myMonsterStatsDB.IMAGE_FILE))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setHitChance(Integer.valueOf((String) myMonsterStatsDB.getMonsterStat(myMonsterStatsDB.SKELTON, myMonsterStatsDB.HIT_CHANCE)));   // percent
        setMinDamage(Integer.valueOf((String)myMonsterStatsDB.getMonsterStat(myMonsterStatsDB.SKELTON, myMonsterStatsDB.MIN_DAMAGE)));   // point system
        setMaxDamage(Integer.valueOf((String)myMonsterStatsDB.getMonsterStat(myMonsterStatsDB.SKELTON, myMonsterStatsDB.MAX_DAMAGE)));   // point system
        setHealth(Integer.valueOf((String) myMonsterStatsDB.getMonsterStat(myMonsterStatsDB.SKELTON, myMonsterStatsDB.DEFAULT_HEALTH))); // point system
    }

    private void setOgreStats() {
        setEntityName((String) myMonsterStatsDB.getMonsterStat(myMonsterStatsDB.OGRE, myMonsterStatsDB.MONSTER_TYPE));
        try {
            setImageDown1(ImageIO.read(new File((String) myMonsterStatsDB.getMonsterStat(myMonsterStatsDB.OGRE, myMonsterStatsDB.IMAGE_FILE))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setHitChance(Integer.valueOf((String) myMonsterStatsDB.getMonsterStat(myMonsterStatsDB.OGRE, myMonsterStatsDB.HIT_CHANCE)));   // percent
        setMinDamage(Integer.valueOf((String) myMonsterStatsDB.getMonsterStat(myMonsterStatsDB.OGRE, myMonsterStatsDB.MIN_DAMAGE)));   // point system
        setMaxDamage(Integer.valueOf((String) myMonsterStatsDB.getMonsterStat(myMonsterStatsDB.OGRE, myMonsterStatsDB.MAX_DAMAGE)));   // point system
        setHealth(Integer.valueOf((String) myMonsterStatsDB.getMonsterStat(myMonsterStatsDB.OGRE, myMonsterStatsDB.DEFAULT_HEALTH))); // point system
    }

    private void setGremlinStats() {
        setEntityName((String) myMonsterStatsDB.getMonsterStat(myMonsterStatsDB.GREMLIN, myMonsterStatsDB.MONSTER_TYPE));
        try {
            setImageDown1(ImageIO.read(new File((String) myMonsterStatsDB.getMonsterStat(myMonsterStatsDB.GREMLIN, myMonsterStatsDB.IMAGE_FILE))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setHitChance(Integer.valueOf((String) myMonsterStatsDB.getMonsterStat(myMonsterStatsDB.GREMLIN, myMonsterStatsDB.HIT_CHANCE)));   // percent
        setMinDamage(Integer.valueOf((String) myMonsterStatsDB.getMonsterStat(myMonsterStatsDB.GREMLIN, myMonsterStatsDB.MIN_DAMAGE)));   // point system
        setMaxDamage(Integer.valueOf((String) myMonsterStatsDB.getMonsterStat(myMonsterStatsDB.GREMLIN, myMonsterStatsDB.MAX_DAMAGE)));   // point system
        setHealth(Integer.valueOf((String) myMonsterStatsDB.getMonsterStat(myMonsterStatsDB.GREMLIN, myMonsterStatsDB.DEFAULT_HEALTH))); // point system
    }
    public void setCollision(boolean theCollision) {
        myCollision = theCollision;
    }

    public boolean getCollision() {
        return myCollision;
    }

    public void setBlockChance(int theChance){
            myBlockChance = theChance;
        }
    public int getBlockChance(){
            return myBlockChance;
        }
}
