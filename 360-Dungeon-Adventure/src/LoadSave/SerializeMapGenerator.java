package LoadSave;

import Model.MapGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializeMapGenerator {

    public SerializeMapGenerator(String theGameStateFile, int theRows, int theCols, boolean theEasyMode) {

//        setGameSaves(theGameStateFile);
        try {
            serializeMapGenerator(theGameStateFile, theRows, theCols, theEasyMode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public SerializeMapGenerator(String theGameStateFile) {

//        setGameSaves(theGameStateFile);
        try {
            // Passing in dummy values for the rows and cols because they won't get used in this call.
            serializeMapGenerator(theGameStateFile, 10, 10, false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public SerializeMapGenerator() {

    }

    private void serializeMapGenerator(String theGameStateFile, int theRows, int theCols, boolean theEasyMode) throws IOException {
        MapGenerator mapGenerator = null;
        if (new File(theGameStateFile).exists() == false) {
            new File(theGameStateFile).createNewFile();
        } else {
            DeserializeMapGenerator deserializeMapGenerator = new DeserializeMapGenerator(theGameStateFile);
            mapGenerator = deserializeMapGenerator.getMyMapGenerator();
        }
        if (mapGenerator == null) {
            mapGenerator = new MapGenerator(theRows, theCols, theEasyMode);
        }

        CheckFileValidity checkFileValidity = new CheckFileValidity();
        theGameStateFile = checkFileValidity.checkThatFileIsNotSavesFile(theGameStateFile);
        // In case game saves are accidentally overwritten by a new save game.



//        setGameSaves(theGameStateFile);
        FileOutputStream fileOutputStream = new FileOutputStream(theGameStateFile);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);


        objectOutputStream.writeObject(mapGenerator);
        objectOutputStream.close();
        fileOutputStream.close();

    }



}
