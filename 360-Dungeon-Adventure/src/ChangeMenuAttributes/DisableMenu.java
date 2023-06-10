package ChangeMenuAttributes;

import javax.swing.*;
/**
 * @author Caleb Krauter
 * @version 1.0
 */

/**
 * Disable menu class.
 */
public class DisableMenu {

    /**
     * The menu.
     */
    private JComponent[] myMenu;

    /**
     * Constructor.
     * @param theMenu
     */
    public DisableMenu(JComponent[] theMenu) {
        this.myMenu = theMenu;
        this.disableMenu();
    }

    /**
     * Disables a menu.
     */
    private void disableMenu() {
        for(int i = 0; i < this.myMenu.length; ++i) {
            this.myMenu[i].setEnabled(false);
            this.myMenu[i].setVisible(false);
        }

    }
}