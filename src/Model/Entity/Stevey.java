package Model.Entity;

import Controller.GameLoop;
import Controller.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Makai Marteniz
 * @version 1.0
 */

/**
 * A class that represents and sets up data for the hero called Stevey.
 */
public class Stevey extends Hero {
    /**
     * Hit chance.
     */
    private int myHitChance = 80; // 80% percent
    /**
     * Min damage.
     */
    private int myMinDamage = 30;
    /**
     * Max damage.
     */
    private int myMaxDamage = 55;
    /**
     * Name.
     */
    private String myEntityName = "Stevey";
    /**
     * HP.
     */
    private int myHealthPoints = 75;
    /**
     * Block chance.
     */
    private int myBlockChance = 20; // 20% percent
    /**
     * Special chance.
     */
    private int mySpecialChance = 20; // 90% percent
    /**
     * Special Damage.
     */
    private int mySpecialDamage = 90;

    /**
     * Constructor.
     * @param theGP
     * @param theKeyH
     */
    public Stevey(GameLoop theGP, KeyHandler theKeyH) {
        super(theGP, theKeyH);
        setDefaultValues();
    }

    /**
     * Sets default data.
     */
    public void setDefaultValues(){
        setWorldX(myGameLoop.myWorldMapMaxCol / 2);
        setWorldY(myGameLoop.myWorldMapMaxRow / 2);
        setSpeed(getDefaultSpeed());
        setDirection("down");

        setHitChance(myHitChance); // 80% percent
        setMinDamage(myMinDamage);
        setMaxDamage(myMaxDamage);
        setEntityName(myEntityName);
        setHealth(myHealthPoints);
        setBlockChance(myBlockChance); // 20% percent
        setSpecialChance(mySpecialChance); // 40% percent
        setSpecialDamage(mySpecialDamage);
        try {
            setImageUp1(ImageIO.read(new File("res/Hero/Stevey/SteveyUp1 copy.png")));
            setImageUp2(ImageIO.read(new File("res/Hero/Stevey/SteveyUp2 copy.png")));
            setImageDown1(ImageIO.read(new File("res/Hero/Stevey/SteveyDown1 copy.png")));
            setImageDown2(ImageIO.read(new File("res/Hero/Stevey/SteveyDown2 copy.png")));
            setImagLeft1(ImageIO.read(new File("res/Hero/Stevey/SteveyLeft1 copy.png")));
            setImageLeft2(ImageIO.read(new File("res/Hero/Stevey/SteveyLeft1 copy.png")));
            setImageRight1(ImageIO.read(new File("res/Hero/Stevey/SteveyRight1 copy.png")));
            setImageRight2(ImageIO.read(new File("res/Hero/Stevey/SteveyRight1 copy.png")));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
