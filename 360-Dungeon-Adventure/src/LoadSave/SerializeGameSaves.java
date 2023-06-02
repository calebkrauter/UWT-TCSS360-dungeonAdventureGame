package LoadSave;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class SerializeGameSaves implements Serializable {
    ArrayList<String> myGameSaves;
    static String myGameStateFile;

    public SerializeGameSaves() {
        myGameSaves = new ArrayList<>();
//        setGameSaves(theGameStateFile);

    }

    public void serializeGameSaves(String theGameStateFile) throws IOException {
        if (myGameSaves == null) {
            myGameSaves = new ArrayList<>();
        }
        theGameStateFile = new CheckFileNotEqualToSavesFile().checkThatFileIsNotSavesFile(theGameStateFile);
        setGameSaves(theGameStateFile);
        FileOutputStream fileOutputStream = new FileOutputStream("game-saves.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);


        objectOutputStream.writeObject(myGameSaves);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    public void setGameSaves(String theGameFile) {
        myGameStateFile = theGameFile;
        myGameSaves.add(myGameStateFile);
        for (int i = 0; i < myGameSaves.size(); i++) {
            System.out.println(myGameSaves.get(i));
        }

    }

    public ArrayList<String> getGameSaves() {
        return myGameSaves;
    }

}
