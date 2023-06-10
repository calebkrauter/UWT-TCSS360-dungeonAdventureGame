package Controller.LoadSave;

import Model.MapGenerator;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @author Caleb Krauter
 * @version 1.0
 */

/**
 * Deserializes the map.
 */
public class DeserializeMapGenerator {
    /**
     * Reference to the map generator.
     */
    private MapGenerator myMapGenerator;

    /**
     * Constructor.
     * @param theGameStateFile
     */
    public DeserializeMapGenerator(String theGameStateFile) {
        try {
            deserializeMapGenerator(theGameStateFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deserializes the map generator.
     * @param theGameStateFile
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void deserializeMapGenerator(String theGameStateFile) throws IOException, ClassNotFoundException {
        MapGenerator theMapGenerator = null;
        FileInputStream fileInputStream = new FileInputStream(theGameStateFile);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        theMapGenerator = (MapGenerator) objectInputStream.readObject();
        setMapGenerator(theMapGenerator);
        fileInputStream.close();
        objectInputStream.close();
    }
    /**
     * Sets the map generator reference.
     * @param theMapGenerator
     */
    private void setMapGenerator(MapGenerator theMapGenerator) {
        myMapGenerator = theMapGenerator;
    }
    /**
     * Gets the map generator.
     * @return
     */
    public MapGenerator getMyMapGenerator() {
        return myMapGenerator;
    }

}
