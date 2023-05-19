import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GUI {
    /**
     * KIT used for getting info about screen size.
     */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    /**
     * SCREEN_SIZE screen size.
     */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    JFrame myJFrame = new JFrame();
    MenuManager newMenu;
    public GUI() throws IOException {

        newMenu = new MenuManager(SCREEN_SIZE, myJFrame);
        loadGui();
    }

    void loadGui() throws IOException {
        myJFrame.setLocation(SCREEN_SIZE.width / 2 - myJFrame.getWidth() / 2,
                SCREEN_SIZE.height / 2 - myJFrame.getHeight() / 2);
        myJFrame.setDefaultCloseOperation(myJFrame.EXIT_ON_CLOSE);
        myJFrame.setSize(850, 850);
        myJFrame.setMinimumSize(new Dimension(750, 750));
        myJFrame.add(newMenu);
        myJFrame.pack();

//        myJFrame.setIconImage(new ImageIcon(POOPY_IMAGE_ICON).getImage());
//        myJFrame.setJMenuBar(menuBar);
        myJFrame.setVisible(true);

    }

}
