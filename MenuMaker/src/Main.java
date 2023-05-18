import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new GUI();
    }

//    public static void main(String[] args) throws IOException {
//        JFrame frame = buildFrame();
//
//        final BufferedImage image = ImageIO.read(new File("C:\\Users\\Kraut\\Documents\\Workspace\\360-Dungeon-Adventure\\MenuMaker\\mainMenu.png"));
//
//        JPanel pane = new JPanel() {
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                g.drawImage(image, 0, 0, null);
//            }
//        };
//
//
//        frame.add(pane);
//    }
//
//
//    private static JFrame buildFrame() {
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.setSize(200, 200);
//        frame.setVisible(true);
//        return frame;
//    }



}