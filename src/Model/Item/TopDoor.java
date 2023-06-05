package Model.Item;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class TopDoor extends ParentItem{
    public TopDoor() {
        setObjectName("TopDoor");
        try {
            setObjectImage(ImageIO.read(new File("res/Items/Door/TopDoor.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
