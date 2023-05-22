package Actions;

import javax.swing.*;
import MenuManagment.*;
import ChangeMenuAttributes.*;

import java.io.IOException;

public class OptionsButtonAction {
    MenuManager menuManager;
    JComponent[] myMainMenuComponents;

    public OptionsButtonAction(JComponent[] theMainMenuComponents) throws IOException {
        menuManager = new MenuManager();
        myMainMenuComponents = theMainMenuComponents;
        optionsAction(myMainMenuComponents);
    }

    private void optionsAction(JComponent[] theMainMenuComponents) throws IOException {
        new DisableMenu(theMainMenuComponents);
//        new AddOptionsMenu();
        System.out.println("OPTIONS");
    }
}