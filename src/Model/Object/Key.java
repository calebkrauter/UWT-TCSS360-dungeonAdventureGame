package Model.Object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Key extends ParentObject{

    public Key() {
        setObjectName("Key");
        try {
            setObjectImage(ImageIO.read(new File("res/Object/Key.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
