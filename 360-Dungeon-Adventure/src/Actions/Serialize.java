package Actions;

import Model.MapGenerator;

import java.io.*;
import java.util.ArrayList;

public class Serialize implements Serializable {
    static int i = 0;

    ArrayList<String> myGameSaves;
    static String myGameStateFile;
    public Serialize(String theGameStateFile) {
        myGameSaves = new ArrayList<>();

//        setGameSaves(theGameStateFile);
        try {
            serializeMapGenerator(theGameStateFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Serialize() {
        myGameSaves = new ArrayList<>();

    }

    private void serializeMapGenerator(String theGameStateFile) throws IOException {
        theGameStateFile = checkThatFileIsNotSavesFile(theGameStateFile);

        // In case game saves are accidentally overwritten by a new save game.
        setGameSaves(theGameStateFile);
        MapGenerator user = null;
        user = new MapGenerator();



//        setGameSaves(theGameStateFile);
        FileOutputStream fileOutputStream = new FileOutputStream(theGameStateFile);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);


        objectOutputStream.writeObject(user);
        objectOutputStream.close();
        fileOutputStream.close();
        serializeGameSaves(theGameStateFile);

    }
    public void serializeGameSaves(String theGameStateFile) throws IOException {
        if (myGameSaves == null) {
            myGameSaves = new ArrayList<>();
        }
        theGameStateFile = checkThatFileIsNotSavesFile(theGameStateFile);

        FileOutputStream fileOutputStream = new FileOutputStream("game-saves.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);


        objectOutputStream.writeObject(myGameSaves);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    private String checkThatFileIsNotSavesFile(String theGameStateFile) {
        StringBuilder sb = new StringBuilder();
        if (theGameStateFile.equals("game-saves.ser")) {
            i++;
            sb.append(theGameStateFile);
            int indexOfDot = theGameStateFile.lastIndexOf('.');
            sb.insert(indexOfDot, i);
            theGameStateFile = sb.toString();
//            System.out.println(theGameStateFile);
        }
        return theGameStateFile;
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
