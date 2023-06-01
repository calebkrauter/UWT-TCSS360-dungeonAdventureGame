package Actions;

import javax.swing.*;

public class CreditsAction {
    public CreditsAction() {
        this.creditsAction();
    }

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