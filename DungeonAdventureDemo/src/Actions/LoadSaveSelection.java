package Actions;

import LoadSave.DeserializeGameSaves;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class LoadSaveSelection implements Serializable {
    private static int mySaveSelection = 0;
    private static int i = 0;
    private static boolean mySelectedSave = false;
    private static DeserializeGameSaves deserializeGameSaves;
    private ArrayList<String> mySaves;
    public LoadSaveSelection() throws IOException {
        deserializeGameSaves = new DeserializeGameSaves();
        deserializeGameSaves.deserializeGameSaves();
        mySaves = deserializeGameSaves.getDeserializedGameSaves();
    }

    public void loadSaveSelection(boolean theGoLeft, boolean theGoRight) throws IOException {
        if (theGoLeft) {
            --i;
            containInBounds(i);
            System.out.println(i);

            this.setLoadSaveSelection(i);
        }

        if (theGoRight) {
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
}
