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
                "Who are we? Caleb, Makai and James\n\n" +
                        "What is this?\n" +
                        "LOST PILLARS OF OOP is a JR project.\n" +
                        "Course: ABET Accredited Software Development and Quality Assurance.\n" +
                        "University Washington Tacoma.\n\n" +
                        "Who did what?:\n" +
                        "Backend:\n" +
                        "Procedural Map generation -> Caleb\n" +
                        "Player Movement -> Makai\n" +
                        "Collision Detection -> Makai, James\n\n" +
                        "Frontend:\n" +
                        "Character Design -> Makai\n" +
                        "Map Design -> James\n" +
                        "Menu Design and development -> Caleb\n\n" +
                        "Music written and produced by Caleb\n\n" +
                        "Thanks For Playing!\n\n" +
                        "Follow us at:\n" +
                        "GITHUB:" +
                        "Caleb - https://github.com/calebkrauter\n" +
                        "Makai - https://github.com/makaimartinez\n" +
                        "James - https://github.com/jamesylee3\n\n" +
                        "Youtube: https://www.youtube.com/@calebkrauterdev\n" +
                        "Check me out on YT - Caleb Krauter"
        );
    }
}