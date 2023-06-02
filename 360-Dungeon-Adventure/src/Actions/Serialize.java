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



        setGameSaves(theGameStateFile);
        FileOutputStream fileOutputStream = new FileOutputStream(theGameStateFile);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);


        objectOutputStream.writeObject(user);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    public void setGameSaves(String theGameFile) {
        myGameSaves = new ArrayList<>();
        myGameStateFile = theGameFile;
        myGameSaves.add(myGameStateFile);
        for (String val : myGameSaves) {

            System.out.println(val);
        }
    }

    public ArrayList<String> getGameSaves() {
        return myGameSaves;
    }
}
