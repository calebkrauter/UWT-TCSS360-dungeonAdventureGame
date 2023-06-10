
package Actions;

import java.io.IOException;
/**
 * @author Caleb Krauter
 * @version 1.0
 */

/**
 * A class for selecting the hero.
 */
public class HeroSelection {
    /**
     * The selection.
     */
    private static int myHeroSelection = 0;
    /**
     * An index used for selection.
     */
    private static int i = 0;
    /**
     * Variable used to see if a hero is selected.
     */
    private static boolean mySelectedHero = false;

    /**
     * Constructor.
     */
    public HeroSelection() {

    }

    /**
     * Hero selector.
     * @param theGoLeft
     * @param theGoRight
     * @throws IOException
     */
    public HeroSelection(boolean theGoLeft, boolean theGoRight) throws IOException {
        if (theGoLeft) {
            --i;
            this.containInBounds();
            this.setHeroSelection(i);
        }

        if (theGoRight) {
            ++i;
            this.containInBounds();
            this.setHeroSelection(i);
        }

    }

    /**
     * Ensure index is in bounds.
     */
    private void containInBounds() {
        if (i < 0) {
            i = 2;
        } else if (i > 2) {
            i = 0;
        }

    }

    /**
     * Setter for hero selection.
     * @param theHeroSelection
     */
    private void setHeroSelection(int theHeroSelection) {
        myHeroSelection = theHeroSelection;
    }

    /**
     * Getter for hero selection.
     * @return
     */
    public int getHeroSelection() {
        return myHeroSelection;
    }

    /**
     * Sets hero.
     * @param theSelectedHero
     */
    public void setHeroSelected(boolean theSelectedHero) {
        mySelectedHero = theSelectedHero;
    }

}
