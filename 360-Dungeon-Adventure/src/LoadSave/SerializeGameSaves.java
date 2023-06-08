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
        theGameStateFile = new CheckFileValidity().checkThatFileIsNotSavesFile(theGameStateFile);
        if (!(deserializeGameSaves.getDeserializedGameSaves().contains(theGameStateFile))) {
            setGameSavesByFile(theGameStateFile);
        }
        FileOutputStream fileOutputStream = new FileOutputStream("game-saves.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);


        objectOutputStream.writeObject(getGameSaves());
        objectOutputStream.close();
        fileOutputStream.close();
    }
    public void deleteSaveAndSerializeRemainingSaves(int theIndex) throws IOException {
        DeserializeGameSaves deserializeGameSaves = new DeserializeGameSaves();
        deserializeGameSaves.deserializeGameSaves();
        myGameSaves = deserializeGameSaves.getDeserializedGameSaves();
        if (myGameSaves == null) {
            myGameSaves = new ArrayList<>();
        }

        if (deserializeGameSaves.getDeserializedGameSaves() != null) {

            if (theIndex < 0 ) {
                theIndex = deserializeGameSaves.getDeserializedGameSaves().size() - 1;
            } else if (theIndex >= deserializeGameSaves.getDeserializedGameSaves().size() - 1) {
                theIndex = 0;
            }
        }
        System.out.println( "index to remove from" + theIndex);

        myGameSaves.remove(theIndex);
        FileOutputStream fileOutputStream = new FileOutputStream("game-saves.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);


        objectOutputStream.writeObject(getGameSaves());
        System.out.println("After deletion size " + getGameSaves().size());
        objectOutputStream.close();
        fileOutputStream.close();
    }
    public void setGameSavesByFile(String theGameFile) {
        myGameSaves.add(theGameFile);
        for (int i = 0; i < myGameSaves.size(); i++) {
            System.out.println(myGameSaves.get(i));
        }

    }

    public void setGameSaves(ArrayList<String> theSaves) {
        myGameSaves = theSaves;
    }
    public ArrayList<String> getGameSaves() {
        return myGameSaves;
    }

}
