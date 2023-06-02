package LoadSave;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SerializeGameSaves {
    ArrayList<String> myGameSaves;
    static String myGameStateFile;

    public SerializeGameSaves() {
//        setGameSaves(theGameStateFile);

    }

    public void serializeGameSaves(String theGameStateFile) throws IOException {
        DeserializeGameSaves deserializeGameSaves = new DeserializeGameSaves();
        deserializeGameSaves.deserializeGameSaves();
        myGameSaves = deserializeGameSaves.getDeserializedGameSaves();
        if (myGameSaves == null) {
            myGameSaves = new ArrayList<>();
        }
        theGameStateFile = new CheckFileNotEqualToSavesFile().checkThatFileIsNotSavesFile(theGameStateFile);
        setGameSaves(theGameStateFile);
        FileOutputStream fileOutputStream = new FileOutputStream("game-saves.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);


        objectOutputStream.writeObject(getGameSaves());
        objectOutputStream.close();
        fileOutputStream.close();
    }

    public void setGameSaves(String theGameFile) {
        myGameSaves.add(theGameFile);
        for (int i = 0; i < myGameSaves.size(); i++) {
            System.out.println(myGameSaves.get(i));
        }

    }

    public ArrayList<String> getGameSaves() {
        return myGameSaves;
    }

}
