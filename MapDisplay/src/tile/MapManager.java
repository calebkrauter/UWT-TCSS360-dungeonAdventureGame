package tile;

import main.DisplayFrame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class MapManager {
    DisplayFrame df;
    String mapString;
    public Tile[] tile;

    public MapManager(DisplayFrame df) {
        this.df = df;
        tile = new Tile[3];
        getTileImage();
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].tileImage = ImageIO.read(getClass().getResourceAsStream("/png/FloorTile.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // # = wall/bounds, cant pass,
    // - xPath
    // | ypath
    // O intersection may go in any direction
    // S is start and
    // E is end.
    // [ is a room with a door.
    public void loadMap() {
        if (mapString.equals("#")) {

        }
    }

    public void draw(Graphics2D g2D) {
//        g2D.drawImage(tile[0].tileImage, 32, 32, 32,32, null);
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        }
    }

