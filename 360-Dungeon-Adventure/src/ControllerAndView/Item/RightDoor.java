package ControllerAndView.Item;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class RightDoor extends ParentItem{
    public RightDoor() {
        setObjectName("RightDoor");
        try {
            setObjectImage(ImageIO.read(new File("res/Items/Door/RightDoor.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setCollision(true);
    }
}
