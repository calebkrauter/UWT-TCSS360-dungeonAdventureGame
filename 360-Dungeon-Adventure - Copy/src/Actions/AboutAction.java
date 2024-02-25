package Actions;

import javax.swing.*;
import java.io.IOException;

public class AboutAction {
    public AboutAction() throws IOException {
        this.aboutAction();
    }

    private void aboutAction() {
            JOptionPane.showMessageDialog(new JFrame(),
                    "The Lost Pillars is created by Caleb K., Makai M. and James L.\n\n" +
                    "GOAL:\n" +
                    "The player is intended to traverse the randomized maze in \n" +
                            "search for the lost pillars of O.O.P. \n" +
                    "while encountering dangerous foes along the path. \n" +
                            "After all necessary items are collected, traverse to the end! \n\n" +
                    "Tip: You can find locked save rooms along the way.\n" +
                    "\n\nHave fun Matey");
    }

}