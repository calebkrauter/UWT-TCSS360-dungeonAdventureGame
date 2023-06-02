package Actions;

import LoadSave.SerializeGameSaves;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class LoadSaveSelection implements Serializable {
    static int mySaveSelection = 0;
    static int i = 0;
    static boolean mySelectedSave = false;
    static SerializeGameSaves serializeGameSaves;
    ArrayList<String> mySaves;
    public LoadSaveSelection() {
        serializeGameSaves = new SerializeGameSaves();
        mySaves = serializeGameSaves.getGameSaves();
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
        if (serializeGameSaves.getGameSaves() != null) {

            if (i < 0) {
                i = serializeGameSaves.getGameSaves().size() - 1;
            } else if (i > serializeGameSaves.getGameSaves().size() - 1) {
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
