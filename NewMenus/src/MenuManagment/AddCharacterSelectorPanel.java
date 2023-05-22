package MenuManagment;

import Actions.HeroSelection;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.JPanel;

public class AddCharacterSelectorPanel extends JPanel {
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    private static final Dimension SCREEN_SIZE;
    HeroSelection heroSelection = new HeroSelection();

    public AddCharacterSelectorPanel() throws IOException {
    }

    static {
        SCREEN_SIZE = KIT.getScreenSize();
    }
}
