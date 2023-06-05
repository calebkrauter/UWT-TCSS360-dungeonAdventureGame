package LoadSave;

import Model.MapGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializeMapGenerator {

    public SerializeMapGenerator(String theGameStateFile) {

//        setGameSaves(theGameStateFile);
        try {
            serializeMapGenerator(theGameStateFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public SerializeMapGenerator() {

    }

    private void serializeMapGenerator(String theGameStateFile) throws IOException {
        MapGenerator user = null;
        if (new File(theGameStateFile).exists() == false) {
            new File(theGameStateFile).createNewFile();
        } else {
            DeserializeMapGenerator deserializeMapGenerator = new DeserializeMapGenerator(theGameStateFile);
            user = deserializeMapGenerator.getMyMapGenerator();
            System.out.println("Keep current map and serialize");

        }
        if (user == null) {
            System.out.println("MAKE NEW MAP CUZ OLD IS LOST");
            user = new MapGenerator();
        }

        CheckFileValidity checkFileValidity = new CheckFileValidity();
        theGameStateFile = checkFileValidity.checkThatFileIsNotSavesFile(theGameStateFile);
        // In case game saves are accidentally overwritten by a new save game.



//        setGameSaves(theGameStateFile);
        FileOutputStream fileOutputStream = new FileOutputStream(theGameStateFile);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);


        objectOutputStream.writeObject(user);
        objectOutputStream.close();
        fileOutputStream.close();

    }



}
