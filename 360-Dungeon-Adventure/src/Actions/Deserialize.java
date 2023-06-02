package Actions;

import Model.MapGenerator;

import java.io.*;
import java.util.ArrayList;

public class Deserialize {
    MapGenerator myMapGenerator;
    ArrayList<String> myDeserializedGameSaves;
    public Deserialize(String theGameStateFile) {
        try {
            deserializeMapGenerator(theGameStateFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Deserialize() {

    }
    private void deserializeMapGenerator(String theGameStateFile) throws IOException, ClassNotFoundException {
        MapGenerator theMapGenerator = null;
        FileInputStream fileInputStream = new FileInputStream(theGameStateFile);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        theMapGenerator = (MapGenerator) objectInputStream.readObject();
        setMapGenerator(theMapGenerator);
        fileInputStream.close();
        objectInputStream.close();
    }
    public void deserializeLoadSaveSelector() throws IOException {
        ArrayList<String> deserializedGameSaves = null;

        FileInputStream fileOutputStream = new FileInputStream("game-saves.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileOutputStream);

        try {
            deserializedGameSaves = (ArrayList<String>) objectInputStream.readObject();
            setDeserializedGameSaves(deserializedGameSaves);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
//
//        for (int i = 0; i < gameSaves.size(); i++) {
//
//            serialize.setGameSaves(gameSaves.get(i));
//        }
        objectInputStream.close();
        fileOutputStream.close();
    }

    private void setDeserializedGameSaves(ArrayList<String> theDeserializedGameSaves) {
        myDeserializedGameSaves = theDeserializedGameSaves;
    }

    public ArrayList<String> getDeserializedGameSaves() {
        return myDeserializedGameSaves;
    }
    private void setMapGenerator(MapGenerator theMapGenerator) {
        myMapGenerator = theMapGenerator;
    }

    public MapGenerator getMyMapGenerator() {
        return myMapGenerator;
    }

}
