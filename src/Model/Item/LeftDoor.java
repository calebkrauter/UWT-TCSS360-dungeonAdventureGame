package Model.Item;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class LeftDoor extends ParentItem{
    public LeftDoor() {
        setObjectName("LeftDoor");
        try {
            setObjectImage(ImageIO.read(new File("res/Items/Door/LeftDoor.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setCollision(true);
    }
}
