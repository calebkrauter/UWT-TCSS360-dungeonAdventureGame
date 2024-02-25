package Controller.MenuManagment.Actions;

import Controller.MenuManagment.ChangeMenuAttributes.DisableMenu;
import Controller.MenuManagment.ChangeMenuAttributes.EnableMenu;

import javax.swing.*;
/**
 * @author Caleb Krauter
 * @version 1.0
 */

/**
 * Class for the go back action in menus.
 */
public class GoBackAction {
    /**
     * The menu to disable.
     */
    private JComponent[] myMenuToDisable;
    /**
     * The menu to enable.
     */
    private JComponent[] myMenuToEnable;

    /**
     * A constructor.
     * @param theMenuToDisable
     * @param theMenuToEnable
     */
    public GoBackAction(JComponent[] theMenuToDisable, JComponent[] theMenuToEnable) {
        myMenuToDisable = theMenuToDisable;
        myMenuToEnable = theMenuToEnable;
        backAction();
    }

    /**
     * The back action.
     */
    private void backAction() {
        new DisableMenu(myMenuToDisable);
        new EnableMenu(myMenuToEnable);
    }
}