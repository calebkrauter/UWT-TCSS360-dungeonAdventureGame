import javax.swing.*;
import java.awt.*;

public class ButtonComponent {

    final private int PLAY_GAME = 1;
    final private int LOAD_GAME = 2;
    final private int OPTIONS = 3;
    final private int CREDITS = 4;
    GridBagConstraints[] myConstraints;
    JButton[] myButtons;
    int myYPos = 150;

    JButton[] buttons;
    String[] menuButtonTitles;
    GridBagConstraints gBC;
    public ButtonComponent() {
        gBC = new GridBagConstraints();
        menuButtonTitles = new String[]{"Play Game", "Load Game", "Options", "Credits"};
        produceButton(menuButtonTitles);
    }

    private void produceButton(String[] theButtonTitles) {
        myButtons = new JButton[theButtonTitles.length];
        myConstraints = new GridBagConstraints[theButtonTitles.length];

        for (int i = 0; i < theButtonTitles.length; i++) {
            setMyYPos(myYPos);

            myConstraints[i] = new GridBagConstraints();
            myConstraints[i].ipadx = 50;
            myConstraints[i].ipady = 10;
            myConstraints[i].insets = new Insets(getMyYPos(), 0, 0, 0);
            myConstraints[i].weightx = 0.5;
            myConstraints[i].weighty = 0.5;
            myConstraints[i].anchor = GridBagConstraints.PAGE_START;
            myConstraints[i].gridx = 1;
            myConstraints[i].gridy = 1;
            myConstraints[i].gridwidth = 3;

            setMyConstraints(myConstraints);

            myButtons[i] = new JButton(menuButtonTitles[i]);
            setMyButtons(myButtons);
        }
    }
    private void setMyYPos(int theYPos) {
        myYPos = theYPos + 50;
    }

    private int getMyYPos() {
        return myYPos;
    }
    private void setMyButtons(JButton[] theButtons) {
        myButtons = theButtons;
    }

    private void setMyConstraints(GridBagConstraints[] theConstraints) {
        myConstraints = theConstraints;
    }

    public GridBagConstraints[] getMyButtonConstraints() {
        return myConstraints;
    }
    public JButton[] getMyButtons() {
        return myButtons;
    }
}
