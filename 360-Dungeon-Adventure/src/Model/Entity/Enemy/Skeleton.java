package Model.Entity.Enemy;

import Controller.GameLoop;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Skeleton extends Enemy {
    public Skeleton() {
        setDefaultValues();
    }

    public void setDefaultValues() {
        setEntityName("Skeleton");
        try {
            setImageDown1(ImageIO.read(new File("res/Monster/Skeleton.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setHitChance(50);   // percent
        setMinDamage(50);   // point system
        setMaxDamage(65);   // point system
        setHealth(200); // point system
    }
}
