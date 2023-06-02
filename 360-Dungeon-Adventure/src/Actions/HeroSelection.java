
package Actions;

import java.io.IOException;

public class HeroSelection {
    static int myHeroSelection = 0;
    static int i = 1;
    static boolean mySelectedHero = false;
    public HeroSelection() {

    }

    public HeroSelection(boolean goLeft, boolean goRight) throws IOException {
        if (goLeft) {
            --i;
            this.containInBounds();
            this.setHeroSelection(i);
        }

        if (goRight) {
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

    public int getMyHeroSelection() {
        return myHeroSelection;
    }

    public void setHeroSelected(boolean theSelectedHero) {
        mySelectedHero = theSelectedHero;
    }

    public boolean getHeroSelected() {
        return mySelectedHero;
    }

}
