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
 * The Archer class is a Hero class that sets up its initial information.
 */
public class Archer extends Hero {
    /**
     * Hit chance of this character.
     */
    private int myHitChance = 80;
    /**
     * Minimum damage of this character.
     */
    private int myMinDamage = 35;
    /**
     * Maximum damage of this character.
     */
    private int myMaxDamage = 60;
    /**
     * Name of this character.
     */
    private String myEntityName = "Archer";
    /**
     * Health of this character.
     */
    private int myHealthPoints = 150;
    /**
     * block chance of this character.
     */
    private int myBlockChance = 20; // 20% percent
    /**
     * Chance of special attack.
     */
    private int mySpecialChance = 40; // 40% percent
    /**
     * The special damage.
     */
    private int mySpecialDamage = 100;

    /**
     * Constructor.
     * @param theGP
     * @param theKeyH
     */
    public Archer(GameLoop theGP, KeyHandler theKeyH) {
        super(theGP, theKeyH);
        setDefaultValues();
    }

    /**
     * Sets default values of the character.
     */
    public void setDefaultValues(){
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
        try {
        setImageUp1(ImageIO.read(new File("res/Hero/Archer/ArcherUp1 copy.png")));
        setImageUp2(ImageIO.read(new File("res/Hero/Archer/ArcherUp2 copy.png")));
        setImageDown1(ImageIO.read(new File("res/Hero/Archer/ArcherDown1 copy.png")));
        setImageDown2(ImageIO.read(new File("res/Hero/Archer/ArcherDown2 copy.png")));
        setImagLeft1(ImageIO.read(new File("res/Hero/Archer/ArcherLeft1 copy.png")));
        setImageLeft2(ImageIO.read(new File("res/Hero/Archer/ArcherLeft1 copy.png")));
        setImageRight1(ImageIO.read(new File("res/Hero/Archer/ArcherRight1 copy.png")));
        setImageRight2(ImageIO.read(new File("res/Hero/Archer/ArcherRight1 copy.png")));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
