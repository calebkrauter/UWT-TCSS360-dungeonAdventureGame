
package Actions;

import java.io.IOException;

public class HeroSelection {
    static int myHeroSelection = 0;
    static int i = 1;

    public HeroSelection() {
    }

    public HeroSelection(boolean goLeft, boolean goRight) throws IOException {
        if (goLeft) {
            --i;
            this.containInBounds();
            this.setHeroSelection(i);
            System.out.println(i);
        }

        if (goRight) {
            ++i;
            this.containInBounds();
            this.setHeroSelection(i);
            System.out.println(i);
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
}
