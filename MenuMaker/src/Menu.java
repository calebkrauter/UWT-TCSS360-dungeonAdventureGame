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
    BoxLayout bL;
    GridBagConstraints gBC;
    GridBagConstraints gBC2;
    GridBagConstraints gBC3;
    public Menu(Dimension theScreenSize, JFrame theJFrame) throws IOException {
        myScreenSize = theScreenSize;
        myJframe = theJFrame;
        ButtonComponent currentButton = new ButtonComponent();
        gBC = new GridBagConstraints();
        gBC2 = new GridBagConstraints();
        gBC3 = new GridBagConstraints();
        JButton newBut = new JButton("START");
        JButton secondB = new JButton("LOAD");
        JButton thirdB = new JButton("OPTIONS");

        this.setLayout(new GridBagLayout());

        gBC.ipadx = 50;
        gBC.ipady = 10;
        gBC.insets = new Insets(200, 0, 0, 0);
        gBC.weightx = 0.5;
        gBC.weighty = 0.5;
        gBC.anchor = GridBagConstraints.PAGE_START;
        gBC.gridx = 1;
        gBC.gridy = 1;
        gBC.gridwidth = 3;
        this.add(newBut, gBC);
        gBC2.ipadx = 50;
        gBC2.ipady = 10;
        gBC2.insets = new Insets(250, 0, 0, 0);
        gBC2.weightx = 0.5;
        gBC2.weighty = 0.5;
        gBC2.anchor = GridBagConstraints.PAGE_START;
        gBC2.gridx = 1;
        gBC2.gridy = 1;
        gBC2.gridwidth = 3;
        this.add(secondB, gBC2);
        gBC3.ipadx = 50;
        gBC3.ipady = 10;
        gBC3.insets = new Insets(300, 0, 0, 0);
        gBC3.weightx = 0.5;
        gBC3.weighty = 0.5;
        gBC3.anchor = GridBagConstraints.PAGE_START;
        gBC3.gridx = 1;
        gBC3.gridy = 1;
        gBC3.gridwidth = 3;
        this.add(thirdB, gBC3);
//        newBut.setBounds(this.getBounds().x,this.getBounds().y,100,75);

    }
    final BufferedImage image = ImageIO.read(new File("mainMenu.png"));

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, myJframe.getWidth(), myJframe.getHeight(), null);
        }

    }
