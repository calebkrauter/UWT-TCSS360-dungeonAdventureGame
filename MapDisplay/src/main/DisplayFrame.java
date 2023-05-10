package main;

import tile.MapManager;

import javax.swing.*;
import java.awt.*;

public class DisplayFrame extends JFrame {

    public int mapSizeCol;
    public int mapSizeRow;
    MapManager newMap = new MapManager(this);

    DisplayFrame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(850,850);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;

        newMap.draw(g2D);
    }
}
