package View.entity;
import java.awt.image.BufferedImage;

// Parent
public class Entity {

    // Player position on world map
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    // after how many updates should the sprite change?
    public int spriteCounter = 0;
    // number of sprite images is the max
    public int spriteNum = 1;

}
