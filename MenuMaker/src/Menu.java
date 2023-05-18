import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;


// TODO - add all constraints to a method, use recursion or a for loop
//      to generate a new button with the constraints and an array to hold the new data
//      for each button.
// TODO - main menu, options menu, pause menu, loadSaves Menu, character select menu, difficulty and map size menu
public class Menu extends JPanel {
//    BufferedImage image = ImageIO.read(new File("mainMenu.png"));
    Dimension myScreenSize;
    JFrame myJframe;
    BoxLayout bL;
    final int CUR_BUTTON = 0;
    final int CUR_BUTTON_CONSTRAINTS = 1;

    public Menu(Dimension theScreenSize, JFrame theJFrame) throws IOException {
        myScreenSize = theScreenSize;
        myJframe = theJFrame;
        ButtonComponent currentButton = new ButtonComponent();

        this.setLayout(new GridBagLayout());
        this.add(currentButton.getMyButtons()[0], currentButton.getMyButtonConstraints()[0]);
        this.add(currentButton.getMyButtons()[1], currentButton.getMyButtonConstraints()[1]);
        this.add(currentButton.getMyButtons()[2], currentButton.getMyButtonConstraints()[2]);
        this.add(currentButton.getMyButtons()[3], currentButton.getMyButtonConstraints()[3]);

//        newBut.setBounds(this.getBounds().x,this.getBounds().y,100,75);

    }
    final BufferedImage image = ImageIO.read(new File("mainMenu.png"));

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, myJframe.getWidth(), myJframe.getHeight(), null);
        }

    }
