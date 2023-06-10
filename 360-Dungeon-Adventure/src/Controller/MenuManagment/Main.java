package Controller.MenuManagment;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

/**
 * @author Caleb Krauter
 * @version 1.0
 */

/**
 * Driver class.
 */
public class Main {
    /**
     * Driver function.
     * @param args
     * @throws IOException
     * @throws UnsupportedAudioFileException
     * @throws LineUnavailableException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException, ClassNotFoundException {

        if (!(new File("game-saves.ser").exists())) {
            new File("game-saves.ser").createNewFile();
        }
//        new SerializeGameSaves().serializeGameSaves();
        new GUI();
    }
}
