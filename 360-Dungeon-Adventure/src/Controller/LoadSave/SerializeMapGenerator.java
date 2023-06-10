package Controller.LoadSave;

import Model.MapGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author Caleb Krauter
 * @version 1.0
 */

/**
 * Serializes the map.
 */
public class SerializeMapGenerator {

    /**
    *Constructor.
     */
    public SerializeMapGenerator(String theGameStateFile) {
        try {
            serializeMapGenerator(theGameStateFile, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     *Constructor.
     */
    public SerializeMapGenerator() {

    }

    /**
     * Serializes the map generator.
     * @param theGameStateFile
     * @param theEasyMode
     * @throws IOException
     */
    private void serializeMapGenerator(String theGameStateFile, boolean theEasyMode) throws IOException {
        MapGenerator mapGenerator = null;
        if (new File(theGameStateFile).exists() == false) {
            new File(theGameStateFile).createNewFile();
        } else {
            DeserializeMapGenerator deserializeMapGenerator = new DeserializeMapGenerator(theGameStateFile);
            mapGenerator = deserializeMapGenerator.getMyMapGenerator();
        }
        if (mapGenerator == null) {
            mapGenerator = new MapGenerator(theEasyMode);
        }

        CheckFileValidity checkFileValidity = new CheckFileValidity();
        theGameStateFile = checkFileValidity.checkThatFileIsNotSavesFile(theGameStateFile);

        FileOutputStream fileOutputStream = new FileOutputStream(theGameStateFile);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(mapGenerator);
        objectOutputStream.close();
        fileOutputStream.close();

    }
}
