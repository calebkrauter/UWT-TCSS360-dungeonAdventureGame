package LoadSave;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CheckFileValidity {

    static private int i = 0;
    static private int alreadyExists = 0;
    static private int maxFileNameLength = 20;

    public CheckFileValidity() {

    }
    public String checkValidLength(String theGameSaveName) {
        char[] fileStringArray = theGameSaveName.toCharArray();
        StringBuilder sb = new StringBuilder();
        if (fileStringArray.length >= maxFileNameLength) {
            for (int i = 0; i < fileStringArray.length; i++) {
                if (i < maxFileNameLength) {
                    sb.append(fileStringArray[i]);
                }
            }
        } else {
            for (int i = 0; i < fileStringArray.length-1; i++) {
                sb.append(fileStringArray[i]);
            }
        }
        maxFileNameLength = 12;
        return sb.toString();
    }

    public void checkAlreadyExists(String theGameSaveName) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(theGameSaveName);
        sb2.append(".ser");

        if (new File(sb2.toString()).exists()) {
            DeserializeGameSaves deserializeGameSaves = new DeserializeGameSaves();
            try {
                deserializeGameSaves.deserializeGameSaves();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ArrayList<String> saves = deserializeGameSaves.getDeserializedGameSaves();
            SerializeGameSaves serializeGameSaves = new SerializeGameSaves();

            for (int i = 0; i < saves.size(); i++) {
                if (sb2.toString() == saves.get(i)) {
                    saves.remove(i);
                }
            }
            for (int i = 0; i < saves.size(); i++) {
                try {
                    serializeGameSaves.serializeGameSaves(saves.get(i));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public String checkThatFileIsNotSavesFile(String theGameStateFile) {
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
}
