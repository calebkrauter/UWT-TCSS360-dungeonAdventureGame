package Actions;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class LoadSaveSelection implements Serializable {
    static int mySaveSelection = 0;
    static int i = 0;
    static boolean mySelectedSave = false;
    static Serialize serialize;
    ArrayList<String> mySaves;
    public LoadSaveSelection() {
        serialize = new Serialize();
        mySaves = serialize.getGameSaves();
    }

    public void loadSaveSelection(boolean goLeft, boolean goRight) throws IOException {
        if (goLeft) {
            --i;
            this.containInBounds();
            this.setLoadSaveSelection(i+1);
        }

        if (goRight) {
            ++i;
            this.containInBounds();
            this.setLoadSaveSelection(i+1);
        }

    }
    private void containInBounds() {
        if (serialize.getGameSaves() != null) {

            if (i < 0) {
                i = serialize.getGameSaves().size() - 1;
            } else if (i > serialize.getGameSaves().size() - 1) {
                i = 0;
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
