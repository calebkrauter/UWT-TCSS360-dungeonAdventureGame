
package Components;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.JToggleButton;

public class ComponentGenerator {
    private final int PLAY_GAME = 1;
    private final int LOAD_GAME = 2;
    private final int OPTIONS = 3;
    private final int CREDITS = 4;
    private final int GO_LEFT = 65;
    private final int GO_RIGHT = 99;
    private final int GO_UP = -88;
    private final int GO_DOWN = 90;
    GridBagConstraints[] myConstraints;
    JComponent[] components;
    int myYPos = 150;
    JButton[] buttons;
    String[] menuButtonTitles;
    GridBagConstraints gBC;
    String[] myTitles;
    private int myButtonCode;
    int myPageStart;
    int myInsetTop;
    int myInsetLeft;
    int myInsetBottom;
    int myInsetRight;
    JComponent[][] manyComponents;
    final int myDirectionOfFlow;

    public ComponentGenerator(String[] theTitles, int thePageStart, int theInsetTop, int theInsetLeft, int theInsetBottom, int theInsetRight, int theDirectionOfFlow) {
        this.myTitles = theTitles;
        this.myPageStart = thePageStart;
        this.myInsetTop = theInsetTop;
        this.myInsetLeft = theInsetLeft;
        this.myInsetBottom = theInsetBottom;
        this.myInsetRight = theInsetRight;
        this.myDirectionOfFlow = theDirectionOfFlow;
        this.manyComponents = new JComponent[this.myTitles.length][4];
        this.gBC = new GridBagConstraints();
        this.produceButton();
    }

    public void produceButton() {
        this.components = new JComponent[this.myTitles.length];
        this.myConstraints = new GridBagConstraints[this.myTitles.length];

        for(int i = 0; i < this.myTitles.length; ++i) {
            if (this.myDirectionOfFlow == -88) {
                this.myInsetBottom += 50;
            }

            if (this.myDirectionOfFlow == 65) {
                this.myInsetRight += 50;
            }

            if (this.myDirectionOfFlow == 90) {
                this.myInsetTop += 50;
            }

            if (this.myDirectionOfFlow == 99) {
                this.myInsetLeft += 400;
            }

            this.manyComponents[i][0] = new JButton();
            this.manyComponents[i][1] = new JToggleButton();
            this.manyComponents[i][2] = new JSlider();
            this.manyComponents[i][3] = new JCheckBox();
            this.myConstraints[i] = new GridBagConstraints();
            this.myConstraints[i].ipadx = 50;
            this.myConstraints[i].ipady = 10;
            this.myConstraints[i].insets = new Insets(this.myInsetTop, this.myInsetLeft, this.myInsetBottom, this.myInsetRight);
            this.myConstraints[i].weightx = 0.5;
            this.myConstraints[i].weighty = 0.5;
            this.myConstraints[i].anchor = this.myPageStart;
            this.myConstraints[i].gridx = 1;
            this.myConstraints[i].gridy = 1;
            this.myConstraints[i].gridwidth = 3;
            this.setMyConstraints(this.myConstraints);

            // Easily update buttons with titles
            if (this.manyComponents[i][this.myButtonCode] instanceof JButton) {
                ((JButton) this.manyComponents[i][this.myButtonCode]).setText(this.myTitles[i]);
            }
            if (this.manyComponents[i][this.myButtonCode + 1] instanceof JToggleButton) {
                ((JToggleButton) this.manyComponents[i][this.myButtonCode + 1]).setText(this.myTitles[i]);
            }

            this.setcomponents(this.manyComponents);
        }

    }

    private void setcomponents(JComponent[][] theComponents) {
        this.manyComponents = theComponents;
    }

    private void setMyConstraints(GridBagConstraints[] theConstraints) {
        this.myConstraints = theConstraints;
    }

    public GridBagConstraints[] getMyButtonConstraints() {
        return this.myConstraints;
    }

    public JComponent[][] getComponents() {
        return this.manyComponents;
    }
}
