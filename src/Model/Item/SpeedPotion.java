package Model.Item;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class SpeedPotion extends ParentItem {

    public SpeedPotion() {
        setObjectName("Speed Potion");
        try {
            setObjectImage(ImageIO.read(new File("res/Items/SpeedPotion.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
