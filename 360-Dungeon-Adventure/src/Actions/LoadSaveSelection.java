package Actions;

import Controller.LoadSave.DeserializeGameSaves;

import java.io.IOException;
/**
 * @author Caleb Krauter
 * @version 1.0
 */

/**
 * Load save selector.
 */
public class LoadSaveSelection {
    /**
     * The save selection.
     */
    private static int mySaveSelection = 0;
    /**
     * Index for selecting the save.
     */
    private static int i = 0;
    /**
     * Deserialize game saves reference.
     */
    private static DeserializeGameSaves deserializeGameSaves;

    /**
     * Constructor.
     * @throws IOException
     */
    public LoadSaveSelection() throws IOException {
        deserializeGameSaves = new DeserializeGameSaves();
        deserializeGameSaves.deserializeGameSaves();
    }

    /**
     * The selector for saves.
     * @param theGoLeft
     * @param theGoRight
     * @throws IOException
     */
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

    /**
     * To ensure that the index stays in bounds.
     * @param theI
     * @return index
     * @throws IOException
     */
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

    /**
     * Sets the save selection.
     * @param theSaveSelection
     */
    private void setLoadSaveSelection(int theSaveSelection) {
        mySaveSelection = theSaveSelection;
    }

    /**
     * Gets the selection.
     * @return
     */
    public int getLoadSaveSelection() {
        return mySaveSelection;
    }
}
