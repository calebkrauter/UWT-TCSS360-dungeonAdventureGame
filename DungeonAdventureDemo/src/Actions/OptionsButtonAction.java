package Actions;

import ChangeMenuAttributes.DisableMenu;
import MenuManagment.MenuManager;

import javax.swing.*;
import java.io.IOException;

public class OptionsButtonAction {
    private MenuManager menuManager;
    private JComponent[] myMainMenuComponents;

    public OptionsButtonAction(JComponent[] theMainMenuComponents) throws IOException {
        myMainMenuComponents = theMainMenuComponents;
        optionsAction(myMainMenuComponents);
    }

    private void optionsAction(JComponent[] theMainMenuComponents) throws IOException {
        new DisableMenu(theMainMenuComponents);
//        new AddOptionsMenu();
        System.out.println("OPTIONS");
    }
}