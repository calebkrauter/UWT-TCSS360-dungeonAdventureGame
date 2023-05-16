package View;

import Controller.GamePanel;
import Controller.MouseHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuButton {

    MouseHandler myMouseHandler;
    GamePanel gp;

    public static final int B_DEFAULT_WIDTH = 190;
    public static final int B_DEFAULT_HEIGHT = 45;
    private static GamePanel myGamePanel;
    public static final int B_WIDTH = (int) (B_DEFAULT_WIDTH * myGamePanel.SCALE);
    public static final int B_HEIGHT = (int) (B_DEFAULT_HEIGHT * myGamePanel.SCALE);


    private boolean mouseOver;
    private boolean mousePressed;
    private int myXPos;
    private int myYPos;
    private int index;
    private int myRowIndex;
    private int xOffset = B_WIDTH / 2;
    private BufferedImage[] images;
    private Rectangle myHitBox;
//    Private myGamestate state;

    private MenuButton(int xPos, int yPos, int rowIndex) {  // add ", GameState state"
        this.myXPos = xPos;
        this.myYPos = yPos;
        this.myRowIndex = rowIndex;
        //        this.state = state;
        this.LoadImage();
        initializeHitBox();


    }

    private void initializeHitBox() {
        myHitBox = new Rectangle(myXPos - xOffset, myYPos, B_WIDTH, B_HEIGHT);
    }

    private void LoadImage() {
        images = new BufferedImage[3]; // array of size 3 for 3 buttons
        try {
            BufferedImage temporaryimage = ImageIO.read(new File("res/Buttons/blueSheet.png"));
            for(int i = 0; i < images.length; i++) {
                images[i] = temporaryimage.getSubimage(i * B_DEFAULT_WIDTH, myRowIndex * B_DEFAULT_HEIGHT, B_DEFAULT_WIDTH, B_DEFAULT_HEIGHT);


            }

        } catch(IOException e) {
            e.printStackTrace();
        }



    }

    public Rectangle getHitBox(){
        return myHitBox;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(images[index], myXPos - xOffset, myYPos, B_WIDTH, B_HEIGHT, null);

    }

//    public void update() {
//        index = 0;
//        if(myMouseHandler.mouseEntered() = true) {
//            index = 1;
//        }
//        if
//    }

//    public void applyGamestate() {
//        Gamestate.state = state;
//    }

    public void resetBooleans() {
        mouseOver = false;
        mousePressed = false;
    }

}