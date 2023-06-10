package Model.Entity;

import Controller.GameLoop;
import Controller.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The Archer is a Hero.
 */
public class Archer extends Hero {
    private int myHitChance = 80; // 80% percent
    private int myMinDamage = 35;
    private int myMaxDamage = 60;
    private String myEntityName = "Archer";
    private int myHealthPoints = 150;
    private int myBlockChance = 20; // 20% percent
    private int mySpecialChance = 40; // 40% percent
    private int mySpecialDamage = 100;
    private BufferedImage up1;
    private BufferedImage up2;
    private BufferedImage down1;
    private BufferedImage down2;
    private BufferedImage left1;
    private BufferedImage left2;
    private BufferedImage right1;
    private BufferedImage right2;

    public Archer(GameLoop theGP, KeyHandler theKeyH) {
        super(theGP, theKeyH);
        setDefaultValues();
    }
    public void setDefaultValues(){

        // Start of the copied code:

        // Will eventually be set to center of start room. This would be the coordinate of the room
        // times the room size plus half the room size on both x and y.
        // EX: StartRoom = [1, 3], worldX = (1 * 400) + 200, worldY = (3 * 400) + 200
        setWorldX(myGameLoop.myWorldMapMaxCol / 2);
        setWorldY(myGameLoop.myWorldMapMaxRow / 2);
        setSpeed(getDefaultSpeed());
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
