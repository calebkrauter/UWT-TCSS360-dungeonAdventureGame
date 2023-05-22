package ChangeMenuAttributes;

import javax.swing.JComponent;

public class DisableMenu {
    JComponent[] myMenu;

    public DisableMenu(JComponent[] theMenu) {
        this.myMenu = theMenu;
        this.disableMenu();
    }

    private void disableMenu() {
        for(int i = 0; i < this.myMenu.length; ++i) {
            this.myMenu[i].setEnabled(false);
            this.myMenu[i].setVisible(false);
        }

    }
}