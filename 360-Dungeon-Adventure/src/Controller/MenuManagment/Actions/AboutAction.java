package Controller.MenuManagment.Actions;

import javax.swing.*;
import java.io.IOException;
/**
 * @author Caleb Krauter
 * @version 1.0
 */

/**
 * A class that makes a pop up to tell about the game and the developers.
 */
public class AboutAction {
    /**
     * Constructor for the about action.
     * @throws IOException
     */
    public AboutAction() throws IOException {
        this.aboutAction();
    }

    /**
     * Displays the about action.
     */
    private void aboutAction() {
            JOptionPane.showMessageDialog(new JFrame(),
                    "The Lost Pillars is created by Caleb Krauter, Makai Marteniz and James Lee.\n\n" +
                    "GOAL:\n" +
                    "The player is intended to traverse the randomized maze in \n" +
                            "search of the lost pillars of O.O.P.\n" +
                            "After finding the pillars, traverse to the end room and\n" +
                            "defeat the bosses and replace the locked doors with the pillars to escape.\n" +
                    "Tip: You can find locked rooms along the way.\n" +
                            "Your health will not increase past 100 so be careful when you pick up health potions." +
                            "There are ten health potions around the map and one randomly placed speed potion.\n\n" +
                            "GAMEPLAY : WASD for move, ESC for pause." +
                            "Most importantly, if you die, your game resets. This game hard mode, you may reload your save\n" +
                            "but your save data will only contain the map as it was from the start." +
                    "\n\nHave fun!");
    }

}