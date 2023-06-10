package Controller.LoadSave;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
/**
 * @author Caleb Krauter
 * @version 1.0
 */

/**
 * Deserializes the game saves.
 */
public class DeserializeGameSaves {
    /**
     * The saves.
     */
    private ArrayList<String> myDeserializedGameSaves;

    /**
     * The constructor.
     */
    public DeserializeGameSaves() {

    }

    /**
     * Deserializes the saves.
     * @throws IOException
     */
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

    /**
     * Sets the game saves.
     * @param theDeserializedGameSaves
     */
    private void setDeserializedGameSaves(ArrayList<String> theDeserializedGameSaves) {
        myDeserializedGameSaves = theDeserializedGameSaves;
    }

    /**
     * Gets the saves.
     * @return
     */
    public ArrayList<String> getDeserializedGameSaves() {
        return myDeserializedGameSaves;
    }
}
