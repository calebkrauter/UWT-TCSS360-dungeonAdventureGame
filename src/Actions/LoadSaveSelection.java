package Actions;

import LoadSave.DeserializeGameSaves;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class LoadSaveSelection implements Serializable {
    static int mySaveSelection = 0;
    static int i = 0;
    static boolean mySelectedSave = false;
    static DeserializeGameSaves deserializeGameSaves;
    ArrayList<String> mySaves;
    public LoadSaveSelection() throws IOException {
        deserializeGameSaves = new DeserializeGameSaves();
        deserializeGameSaves.deserializeGameSaves();
        mySaves = deserializeGameSaves.getDeserializedGameSaves();
    }

    public void loadSaveSelection(boolean goLeft, boolean goRight) throws IOException {
        if (goLeft) {
            --i;
            containInBounds(i);
            System.out.println(i);

            this.setLoadSaveSelection(i);
        }

        if (goRight) {
            ++i;
            containInBounds(i);
            System.out.println(i);

            this.setLoadSaveSelection(i);
        }

    }
    public int containInBounds(int theI) throws IOException {
        deserializeGameSaves.deserializeGameSaves();
        if (deserializeGameSaves.getDeserializedGameSaves() != null) {

            if (theI < 0 || i < 0) {
                i = deserializeGameSaves.getDeserializedGameSaves().size() - 1;
            } if (i > deserializeGameSaves.getDeserializedGameSaves().size() - 1) {
                i = 0;
            }
        }
        return i;

    }
    private void setLoadSaveSelection(int theSaveSelection) {
        mySaveSelection = theSaveSelection;
    }

    public int getLoadSaveSelection() {
        return mySaveSelection;
    }

    public void setLoadSaveSelected(boolean theSelectedSave) {
        mySelectedSave = theSelectedSave;
    }

    public boolean getLoadSaveSelected() {
        return mySelectedSave;
    }





}
