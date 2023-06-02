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

        FileInputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileInputStream("game-saves.ser");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(fileOutputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
}
