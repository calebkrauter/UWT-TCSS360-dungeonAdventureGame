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
                    "The Lost Pillars is created by Caleb K., Makai M. and James L.\n\n" +
                    "GOAL:\n" +
                    "The player is intended to traverse the randomized maze in \n" +
                            "search for the lost pillars of O.O.P. \n" +
                    "while encountering dangerous foes along the path. \n" +
                            "After all necessary items are collected, traverse to the end! \n\n" +
                    "Tip: You can find locked rooms along the way.\n" +
                            "MORE TIPS... The moving glitch after combat can be averted by\n" +
                            "moving in the direction that the player is forced.\n" +
                            "For example after leaving a battle, if the player is forced in a specific direction," +
                            "\n press the key to move in that direction and let go.\n" +
                            "Also pick up as many health potions as possible and play defensive to beat the bosses.\n" +
                            "There are ten health potions around the map and one randomly placed speed potion.\n" +
                            "GAMEPLAY : WASD for move, ESC for pause." +
                    "\n\nHave fun Matey");
    }

}