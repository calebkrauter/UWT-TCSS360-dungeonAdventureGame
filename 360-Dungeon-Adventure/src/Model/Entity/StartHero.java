package Model.Entity;

import Controller.GameLoop;
import Controller.KeyHandler;

/**
 * @author Makai Marteniz
 * @version 1.0
 */

/**
 * Start Hero sets up data for the Starter hero that we call FRED.
 */
public class StartHero extends Hero {
    /**
     * Hit chance.
     */
    private int myHitChance = 70; // 70% percent
    /**
     * Min damage.
     */
    private int myMinDamage = 25;
    /**
     * Max damage.
     */
    private int myMaxDamage = 45;
    /**
     * A name of the hero called FRED.
     */
    private String myEntityName = "Starter";
    /**
     * The HP.
     */
    private int myHealthPoints = 75;
    /**
     * Block chance.
     */
    private int myBlockChance = 30; // 30% percent
    /**
     * Special Chance.
     */
    private int mySpecialChance = 20; // 30% percent
    /**
     * Special Damage.
     */
    private int mySpecialDamage = 80;

    /**
     * Constructor.
     * @param theGP
     * @param theKeyH
     */
    public StartHero(GameLoop theGP, KeyHandler theKeyH) {
        super(theGP, theKeyH);
        setDefaultValues();
    }

    /**
     * Sets default values.
     */
    public void setDefaultValues(){

        setHeroImages();
        setWorldX(myGameLoop.myWorldMapMaxCol / 2);
        setWorldY(myGameLoop.myWorldMapMaxRow / 2);
        setSpeed(getDefaultSpeed());
        //starting direction can vary.
        setDirection("down");

        setHitChance(myHitChance); // 80% percent
        setMinDamage(myMinDamage);
        setMaxDamage(myMaxDamage);
        setEntityName(myEntityName);
        setHealth(myHealthPoints);
        setBlockChance(myBlockChance); // 20% percent
        setSpecialChance(mySpecialChance); // 40% percent
        setSpecialDamage(mySpecialDamage);
    }
}
