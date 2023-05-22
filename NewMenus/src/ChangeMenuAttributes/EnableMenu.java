package ChangeMenuAttributes;

import javax.swing.JComponent;

public class EnableMenu {
    JComponent[] myMenu;

    public EnableMenu(JComponent[] theMenu) {
        this.myMenu = theMenu;
        this.enableMenu();
    }

    private void enableMenu() {
        for(int i = 0; i < this.myMenu.length; ++i) {
            this.myMenu[i].setEnabled(true);
            this.myMenu[i].setVisible(true);
        }

    }
}