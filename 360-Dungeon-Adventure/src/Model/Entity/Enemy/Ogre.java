//package Model.Entity.Enemy;
//
//import Controller.GameLoop;
//
//import javax.imageio.ImageIO;
//import java.io.File;
//import java.io.IOException;
//
//public class Ogre extends Enemy {
//    public Ogre( ) {
//        super();
//        setDefaultValues();
//    }
//
//    public void setDefaultValues() {
//        setEntityName("Ogre");
//        try {
//            setImageDown1(ImageIO.read(new File("res/Monster/Ogre.png")));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        setHitChance(80);   // percent
//        setMinDamage(30);   // point system
//        setMaxDamage(55);   // point system
//        setHealth(100); // point system
//
//    }
//}
