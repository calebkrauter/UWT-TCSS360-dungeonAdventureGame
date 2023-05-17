package View.entity;
import java.awt.image.BufferedImage;

// Parent
public class Entity {

    // Player position on world map
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String name;
    public String direction;

    // after how many updates should the sprite change?
    public int spriteCounter = 0;
    // number of sprite images is the max
    public int spriteNum = 1;

    public int hitPoints = 75;

    private int numHealingPotions = 0;

    private int numSpeedPotions = 0;

    private int numKeys = 0;


    // Overriding toString() method of String class
    @Override
    public String toString() {
        return this.name + "_" + this.hitPoints + "_" + this.numHealingPotions + "_" + this.numSpeedPotions + "_" + this.numKeys;
    }


}
