package LoadSave;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class DeserializeGameSaves {
    ArrayList<String> myDeserializedGameSaves;

    public DeserializeGameSaves() {

    }

    public void deserializeGameSaves() throws IOException {
        ArrayList<String> deserializedGameSaves = null;

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("game-saves.ser");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            deserializedGameSaves = (ArrayList<String>) objectInputStream.readObject();
            setDeserializedGameSaves(deserializedGameSaves);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        objectInputStream.close();
        fileInputStream.close();
    }
    private void setDeserializedGameSaves(ArrayList<String> theDeserializedGameSaves) {
        myDeserializedGameSaves = theDeserializedGameSaves;
    }

    public ArrayList<String> getDeserializedGameSaves() {
        return myDeserializedGameSaves;
    }
}
