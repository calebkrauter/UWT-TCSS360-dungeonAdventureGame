package Model.Item;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class HealthPotion extends ParentItem {

    public HealthPotion() {
        setObjectName("Health Potion");
        try {
            setObjectImage(ImageIO.read(new File("res/Object/HealthPotion.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
