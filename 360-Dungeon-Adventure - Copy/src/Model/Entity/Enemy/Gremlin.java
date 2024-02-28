//package Model.Entity.Enemy;
//
//import Controller.GameLoop;
//
//import javax.imageio.ImageIO;
//import java.io.File;
//import java.io.IOException;
//
//public class Gremlin extends Enemy {
//    public Gremlin() {
//        setDefaultValues();
//    }
//
//    public void setDefaultValues() {
//        setEntityName("Gremlin");
//        try {
//            setImageDown1(ImageIO.read(new File("res/Monster/Gremlin.png")));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        setHitChance(80);   // percent
//        setMinDamage(30);   // point system
//        setMaxDamage(60);   // point system
//        setHealth(150); // point system
//    }
//}