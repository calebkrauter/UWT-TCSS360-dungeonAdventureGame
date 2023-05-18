import javax.swing.*;
import java.awt.*;

public class Menu {

    JFrame myJFrame = new JFrame();
    public Menu() {
        loadGui();
    }

    void loadGui() {
        myJFrame.setLocation(SCREEN_SIZE.width / 2 - myJFrame.getWidth() / 2,
                SCREEN_SIZE.height / 2 - myJFrame.getHeight() / 2);
        myJFrame.pack();
        myJFrame.setDefaultCloseOperation(myJFrame.EXIT_ON_CLOSE);
        myJFrame.setSize(850, 850);
        myJFrame.setMinimumSize(new Dimension(750, 750));
        myJFrame.add(myBackgroundPanel);
//        myJFrame.setIconImage(new ImageIcon(POOPY_IMAGE_ICON).getImage());
//        myJFrame.setJMenuBar(menuBar);
        myJFrame.setVisible(true);

    }

}
