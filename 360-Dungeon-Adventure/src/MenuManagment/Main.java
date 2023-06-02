package MenuManagment;

import Actions.Deserialize;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException, ClassNotFoundException {

        new GUI();
//        Serialize serialize = new Serialize();
//        if (!(serialize.getGameSaves().isEmpty()) || serialize.getGameSaves() != null) {
            new Deserialize().deserializeLoadSaveSelector();
//        }
    }
}
