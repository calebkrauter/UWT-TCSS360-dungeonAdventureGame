package Actions;

import Model.MapGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Serialize {

    static ArrayList<String> myGameSaves;
    String myGameStateFile;
    public Serialize(String theGameStateFile) {
        setGameSaves(theGameStateFile);
        try {
            serialize(theGameStateFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Serialize() {

    }

    private void serialize(String theGameStateFile) throws IOException {

        MapGenerator user = null;
        user = new MapGenerator();




        FileOutputStream fileOutputStream = new FileOutputStream(theGameStateFile);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);


        objectOutputStream.writeObject(user);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    public void setGameSaves(String theGameFile) {
        myGameStateFile = theGameFile;
        myGameSaves.add(myGameStateFile);
    }

    public ArrayList<String> getGameSaves() {
        return myGameSaves;
    }
}
