package Model.Item;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Key extends ParentItem {

    public Key() {
        setObjectName("Key");
        try {
            setObjectImage(ImageIO.read(new File("res/Items/Key.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
