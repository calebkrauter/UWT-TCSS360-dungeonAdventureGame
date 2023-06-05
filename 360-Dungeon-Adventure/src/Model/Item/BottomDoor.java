package Model.Item;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class BottomDoor extends ParentItem{

    public BottomDoor() {
        setObjectName("BottomDoor");
        try {
            setObjectImage(ImageIO.read(new File("res/Items/Door/BottomDoor.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
