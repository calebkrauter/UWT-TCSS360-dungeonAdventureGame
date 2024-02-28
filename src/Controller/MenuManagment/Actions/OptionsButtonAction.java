package Controller.MenuManagment.Actions;

import Controller.MenuManagment.ChangeMenuAttributes.DisableMenu;

import javax.swing.*;
import java.io.IOException;
/**
 * @author Caleb Krauter
 * @version 1.0
 */

/**
 * The options button action.
 */
public class OptionsButtonAction {

    /**
     * The components for the main menu.
     */
    private JComponent[] myMainMenuComponents;

    /**
     * The constructor for Options Button.
     * @param theMainMenuComponents
     * @throws IOException
     */
    public OptionsButtonAction(JComponent[] theMainMenuComponents) throws IOException {
        myMainMenuComponents = theMainMenuComponents;
        optionsAction(myMainMenuComponents);
    }

    /**
     * The action for Options Button.
     * @param theMainMenuComponents
     * @throws IOException
     */
    private void optionsAction(JComponent[] theMainMenuComponents) {
        new DisableMenu(theMainMenuComponents);
    }
}