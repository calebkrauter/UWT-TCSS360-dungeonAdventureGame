package Actions;

import ChangeMenuAttributes.DisableMenu;
import ChangeMenuAttributes.EnableMenu;

import javax.swing.*;

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