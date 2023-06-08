package Model.Entity;

import Controller.GameLoop;
import Controller.KeyHandler;


public class StartHero extends Hero {
    private int myHitChance = 70; // 70% percent
    private int myMinDamage = 25;
    private int myMaxDamage = 45;
    private String myEntityName = "Starter";
    private int myHealthPoints = 75;
    private int myBlockChance = 30; // 30% percent
    private int mySpecialChance = 30; // 30% percent
    private int mySpecialDamage = 80;

    public StartHero(GameLoop theGP, KeyHandler theKeyH) {
        super(theGP, theKeyH);
        setDefaultValues();
    }
    public void setDefaultValues(){

        // Start of the copied code:
        setHeroImages();
        // Will eventually be set to center of start room. This would be the coordinate of the room
        // times the room size plus half the room size on both x and y.
        // EX: StartRoom = [1, 3], worldX = (1 * 400) + 200, worldY = (3 * 400) + 200
        setWorldX(myGameLoop.myWorldMapMaxCol / 2);
        setWorldY(myGameLoop.myWorldMapMaxRow / 2);
        setSpeed(4);
        //starting direction can vary.
        setDirection("down");

        // The code above is copied from the "hero" class method with the
        // same name "setDefaultValues". Trying to figure out why getDirection is
        // null and crashing the game.

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
