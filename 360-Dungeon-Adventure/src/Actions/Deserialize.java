package Actions;

import Model.MapGenerator;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Deserialize {
    MapGenerator myMapGenerator;
    public Deserialize(String theGameStateFile) {
        try {
            deserialize(theGameStateFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void deserialize(String theGameStateFile) throws IOException, ClassNotFoundException {

        MapGenerator theMapGenerator = null;
        FileInputStream fileInputStream = new FileInputStream(theGameStateFile);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        theMapGenerator = (MapGenerator) objectInputStream.readObject();
        setMapGenerator(theMapGenerator);
        fileInputStream.close();
        objectInputStream.close();
    }

    private void setMapGenerator(MapGenerator theMapGenerator) {
        myMapGenerator = theMapGenerator;
    }

    public MapGenerator getMyMapGenerator() {
        return myMapGenerator;
    }

}
