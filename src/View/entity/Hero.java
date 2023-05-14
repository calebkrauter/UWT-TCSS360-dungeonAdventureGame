package View.entity;

import controller.GamePanel;
import controller.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Hero extends Entity {

    KeyHandler keyH;
    GamePanel gp;


    public Hero (GamePanel theGP, KeyHandler theKeyH){
        this.gp = theGP;
        this.keyH = theKeyH;

        setDefaultValues();
        getHeroImage();
    }

    // set the Hero's default values.
    public void setDefaultValues(){

        x = 100;
        y = 100;
        speed = 4;
        //starting direction can vary.
        direction = "down";

    }



    public void getHeroImage() {

        try {
            // only have two sprite pngs so its the same two in each direction. Need to make more
            // Need to make more later on.
            up1 = ImageIO.read(new File("/Users/makaimartinez/Desktop/360-Dungeon-Adventure/res/hero/up1.png"));
            up2 = ImageIO.read(new File("/Users/makaimartinez/Desktop/360-Dungeon-Adventure/res/hero/up2.png"));
            down1 = ImageIO.read(new File("/Users/makaimartinez/Desktop/360-Dungeon-Adventure/res/hero/down1.png"));
            down2 = ImageIO.read(new File("/Users/makaimartinez/Desktop/360-Dungeon-Adventure/res/hero/down2.png"));
            left1 = ImageIO.read(new File("/Users/makaimartinez/Desktop/360-Dungeon-Adventure/res/hero/left1.png"));
            left2 = ImageIO.read(new File("/Users/makaimartinez/Desktop/360-Dungeon-Adventure/res/hero/left1.png"));
            right1 = ImageIO.read(new File("/Users/makaimartinez/Desktop/360-Dungeon-Adventure/res/hero/right1.png"));
            right2 = ImageIO.read(new File("/Users/makaimartinez/Desktop/360-Dungeon-Adventure/res/hero/right1.png"));

        }catch(IOException e) {
            e.printStackTrace();
        }

    }


    // change player position. Important to remember that:
    // X value increases when going to the right
    // Y value increases when going downward
    // gets called 60 times a seconds in the game loop (GamePanel class)
    public void update(){

        // this if statement allows our character to be frozen when
        // we are stationary. Counter doesnt increase unless we press a key.
        if(keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true) {

            if(keyH.upPressed == true){
                direction = "up";
                y -= speed;
            }
            else if(keyH.downPressed == true){
                direction = "down";
                y += speed;
            }
            else if(keyH.leftPressed == true){
                direction = "left";
                x -= speed;
            }
            else if(keyH.rightPressed == true){
                direction = "right";
                x += speed;
            }
            spriteCounter++;
            // every 12 frames player image changes
            if(spriteCounter > 12) {
                if(spriteNum == 1){
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }



    }

    public void draw(Graphics2D g2){
//        test rectangle
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        if (direction.equals("up")){
            if(spriteNum == 1){
                image = up1;
            } else if (spriteNum == 2){
                image = up2;
            }
        }
        else if (direction.equals("down")){
            if(spriteNum == 1){
                image = down1;
            } else if (spriteNum == 2){
                image = down2;
            }
        }
        else if (direction.equals("left")){
            if(spriteNum == 1){
                image = left1;
            } else if (spriteNum == 2){
                image = left2;
            }
        }
        else if (direction.equals("right")){
            if(spriteNum == 1){
                image = right1;
            } else if (spriteNum == 2){
                image = right2;
            }
        }
        // image, position, dimensions, ImageObserver
        g2.drawImage(image, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);


    }

}
