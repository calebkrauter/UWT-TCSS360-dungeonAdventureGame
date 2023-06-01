package Actions;

import ChangeMenuAttributes.DisableMenu;
import ChangeMenuAttributes.EnableMenu;

import javax.swing.*;

public class BackToMainMenuAction {
    JComponent[] myMenuToDisable;
    JComponent[] myMenuToEnable;

    public BackToMainMenuAction(JComponent[] theMenuToDisable, JComponent[] theMenuToEnable) {
        this.myMenuToDisable = theMenuToDisable;
        this.myMenuToEnable = theMenuToEnable;
        this.backToMainMenuAction();
    }

    private void backToMainMenuAction() {
        new DisableMenu(this.myMenuToDisable);
        new EnableMenu(this.myMenuToEnable);
    }
}