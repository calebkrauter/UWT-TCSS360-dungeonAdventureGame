package Controller.MenuManagment.ChangeMenuAttributes;

import javax.swing.*;
/**
 * @author Caleb Krauter
 * @version 1.0
 */

/**
 * Enables a menu.
 */
public class EnableMenu {
    /**
     * A menu.
     */
    private JComponent[] myMenu;

    /**
     * Constructor.
     * @param theMenu
     */
    public EnableMenu(JComponent[] theMenu) {
        this.myMenu = theMenu;
        this.enableMenu();
    }

    /**
     * Enables a menu.
     */
    private void enableMenu() {
        for(int i = 0; i < this.myMenu.length; ++i) {
            this.myMenu[i].setEnabled(true);
            this.myMenu[i].setVisible(true);
        }

    }
}