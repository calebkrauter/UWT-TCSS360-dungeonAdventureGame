import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class Menu extends JPanel {
//    BufferedImage image = ImageIO.read(new File("mainMenu.png"));
    Dimension myScreenSize;
    JFrame myJframe;
    public Menu(Dimension theScreenSize, JFrame theJFrame) throws IOException {
        myScreenSize = theScreenSize;
        myJframe = theJFrame;
        ButtonComponent currentButton = new ButtonComponent();
//        currentButton.setText("hi");
//        currentButton.setBackground(Color.WHITE);
//        currentButton.setSize(500, 500);
        //        currentButton.setLocation(0, 500);
        add(currentButton);

    }
    final BufferedImage image = ImageIO.read(new File("mainMenu.png"));

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, myJframe.getWidth(), myJframe.getHeight(), null);
        }

    }
