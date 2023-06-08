package Model.Item;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Pillar extends ParentItem{

    public Pillar() {
        setObjectName("Pillar");
        try {
            setObjectImage(ImageIO.read(new File("res/Items/Pillar.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
