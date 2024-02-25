package Actions;

import ChangeMenuAttributes.DisableMenu;
import ChangeMenuAttributes.EnableMenu;

import javax.swing.*;

public class GoBackAction {
    private JComponent[] myMenuToDisable;
    private JComponent[] myMenuToEnable;

    public GoBackAction(JComponent[] theMenuToDisable, JComponent[] theMenuToEnable) {
        myMenuToDisable = theMenuToDisable;
        myMenuToEnable = theMenuToEnable;
        backAction();
    }

    private void backAction() {
        new DisableMenu(myMenuToDisable);
        new EnableMenu(myMenuToEnable);
    }
}