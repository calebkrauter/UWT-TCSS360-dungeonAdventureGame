package Controller.LoadSave;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Caleb Krauter
 * @version 1.0
 */

/**
 * Checks file validity.
 */
public class CheckFileValidity {

    /**
     * Index.
     */
    static private int i = 0;
    /**
     * Max length of the file name.
     */
    static private int maxFileNameLength = 20;

    /**
     * Constructor.
     */
    public CheckFileValidity() {

    }

    /**
     * Checks length of file name.
     * @param theGameSaveName
     * @return new file name.
     */
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
            for (int i = 0; i < fileStringArray.length; i++) {
                sb.append(fileStringArray[i]);
            }
        }
        maxFileNameLength = 12;
        return sb.toString();
    }

    /**
     * Check that file doesn't exist.
     * @param theGameSaveName
     */
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

    /**
     * Check that file is not game saves file.
     * @param theGameStateFile
     * @return
     */
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
