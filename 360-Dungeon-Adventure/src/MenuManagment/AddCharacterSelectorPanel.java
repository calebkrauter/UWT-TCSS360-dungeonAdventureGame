package MenuManagment;

import Actions.HeroSelection;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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
