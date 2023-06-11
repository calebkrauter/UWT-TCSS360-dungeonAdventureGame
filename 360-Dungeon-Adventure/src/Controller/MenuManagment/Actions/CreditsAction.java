package Controller.MenuManagment.Actions;

import javax.swing.*;
/**
 * @author Caleb Krauter
 * @version 1.0
 */

/**
 * A popup to display credits.
 */
public class CreditsAction {
    /**
     * A constructor.
     */
    public CreditsAction() {
        creditsAction();
    }

    /**
     * A method to pop up the credits.
     */
    private void creditsAction() {
        JOptionPane.showMessageDialog(new JFrame(),
                "Who are we? Caleb, Makai and James, students of UWT as of 2023\n\n" +
                        "Who did what?:\n" +
                        "Backend:\n" +
                        "Map generation -> Caleb\n" +
                        "Player Movement -> Makai\n" +
                        "Collision Detection -> James, Makai\n\n" +
                        "Frontend:\n" +
                        "Character Design -> Makai\n" +
                        "Map Design -> James\n" +
                        "Menu Design and development -> Caleb\n\n" +
                        "Music written and produced by Caleb Krauter\n\n" +
                        "Thanks For Playing!\n\n" +
                        "Follow us at:\n" +
                        "GITHUB PLACEHOLDER - Caleb, Makai, James\n" +
                        "PLACEHOLDER YT/INSTA - Caleb, Makai, James");
    }
}