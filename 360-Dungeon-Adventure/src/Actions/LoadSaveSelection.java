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
            containInBounds();
            this.setLoadSaveSelection(i);
        }

        if (goRight) {
            ++i;
            containInBounds();
            this.setLoadSaveSelection(i);
        }

    }
    private void containInBounds() {
        if (deserializeGameSaves.getDeserializedGameSaves() != null) {

            if (i < 0) {
                i = deserializeGameSaves.getDeserializedGameSaves().size() - 1;
                System.out.println(i);
            } else if (i > deserializeGameSaves.getDeserializedGameSaves().size() - 1) {
                i = 0;
                System.out.println(i);

            }
        }

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
