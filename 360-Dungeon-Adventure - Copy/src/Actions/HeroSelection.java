
package Actions;

import java.io.IOException;

public class HeroSelection {
    private static int myHeroSelection = 0;
    private static int i = 0;
    private static boolean mySelectedHero = false;
    public HeroSelection() {

    }

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

    private void containInBounds() {
        if (i < 0) {
            i = 2;
        } else if (i > 2) {
            i = 0;
        }

    }

    private void setHeroSelection(int theHeroSelection) {
        myHeroSelection = theHeroSelection;
    }

    public int getHeroSelection() {
        return myHeroSelection;
    }

    public void setHeroSelected(boolean theSelectedHero) {
        mySelectedHero = theSelectedHero;
    }

    public boolean getHeroSelected() {
        return mySelectedHero;
    }

}
