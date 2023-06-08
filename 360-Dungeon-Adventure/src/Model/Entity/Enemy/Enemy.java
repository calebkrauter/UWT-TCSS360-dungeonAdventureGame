package Model.Entity.Enemy;

import Controller.GameLoop;
import Model.Entity.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Enemy extends Entity {


    private BufferedImage myEnemyImage;
    private boolean myCollision = false;
    private int myBlockChance;

    private final int TILE_SIZE = 48;
    GameLoop myGameLoop;

    public Enemy (){

        // can be changed, we don't want hitbox as big as character
        Rectangle solidHitBox = new Rectangle(0, 0, 3 * TILE_SIZE, 3 * TILE_SIZE);
        setHitBox(solidHitBox);

        setDefaultValues();
    }

        // set the Hero's default values.
    public void setDefaultValues(){

        setSpeed(2);
        //starting direction can vary.
        setDirection("down");
        setCollision(true);
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
