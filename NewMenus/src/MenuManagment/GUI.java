
package MenuManagment;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.JFrame;

public class GUI {
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    private static final Dimension SCREEN_SIZE;
    private JFrame myJFrame = new JFrame();
    private MenuManager newMenu;

    public GUI() throws IOException {
        this.newMenu = new MenuManager(SCREEN_SIZE, this.myJFrame);
        this.loadGui();
    }

    void loadGui() throws IOException {
        this.myJFrame.setLocation(SCREEN_SIZE.width / 2 - this.myJFrame.getWidth() / 2, SCREEN_SIZE.height / 2 - this.myJFrame.getHeight() / 2);
        JFrame var10001 = this.myJFrame;
        this.myJFrame.setDefaultCloseOperation(3);
        this.myJFrame.setSize(850, 850);
        this.myJFrame.setMinimumSize(new Dimension(750, 750));
        this.myJFrame.add(this.newMenu);
        this.myJFrame.pack();
        this.myJFrame.setVisible(true);
    }

    public JFrame getJFrame() {
        return this.myJFrame;
    }

    static {
        SCREEN_SIZE = KIT.getScreenSize();
    }
}
