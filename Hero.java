package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.security.Key;


public class Hero extends Entity {

    KeyHandler keyH;
    GamePanel gp;


    public Hero (GamePanel theGP, KeyHandler theKeyH){
        this.gp = theGP;
        this.keyH = theKeyH;

        setDefaultValues();
    }

    // set the Hero's default values.
    public void setDefaultValues(){

        x = 100;
        y = 100;
        speed = 4;

    }

    // change player position. Important to remember that:
    // X value increases when going to the right
    // Y value increases when going downward
    public void update(){
        if(keyH.upPressed == true){
            y -= speed;
        }
        else if(keyH.downPressed == true){
            y += speed;
        }
        else if(keyH.leftPressed == true){
            x -= speed;
        }
        else if(keyH.rightPressed == true){
            x += speed;
        }
    }

    public void draw(Graphics2D g2){

        g2.setColor(Color.white);

        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
    }

}
