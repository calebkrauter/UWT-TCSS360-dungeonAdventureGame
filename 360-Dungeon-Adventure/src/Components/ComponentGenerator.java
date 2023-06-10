
package Components;

import javax.swing.*;
import java.awt.*;

/**
 * @author Caleb Krauter
 * @version 1.0
 */

/**
 * Generates components for menus.
 */
public class ComponentGenerator {

    /**
     * The constraints array.
     */
    private GridBagConstraints[] myConstraints;
    /**
     * The components.
     */
    private JComponent[] components;
    /**
     * The grid bag constraints.
     */
    private GridBagConstraints gBC;
    /**
     * The titles.
     */
    private String[] myTitles;
    /**
     * The button code.
     */
    private int myButtonCode;
    /**
     * The page start location.
     */
    private int myPageStart;
    /**
     * An inset.
     */
    private int myInsetTop;
    /**
     * An inset.
     */
    private int myInsetLeft;
    /**
     * An inset.
     */
    private int myInsetBottom;
    /**
     * An inset.
     */
    private int myInsetRight;
    /**
     * The list of components.
     */
    private JComponent[][] myManyComponents;
    /**
     * The direction of button flow.
     */
    private final int myDirectionOfFlow;

    public ComponentGenerator(String[] theTitles, int thePageStart, int theInsetTop, int theInsetLeft, int theInsetBottom, int theInsetRight, int theDirectionOfFlow) {
        int numOfTitles = 6;
        this.myTitles = theTitles;
        this.myPageStart = thePageStart;
        this.myInsetTop = theInsetTop;
        this.myInsetLeft = theInsetLeft;
        this.myInsetBottom = theInsetBottom;
        this.myInsetRight = theInsetRight;
        this.myDirectionOfFlow = theDirectionOfFlow;
        this.myManyComponents = new JComponent[this.myTitles.length][numOfTitles];
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
                this.myInsetLeft += 300;
            }

            this.myManyComponents[i][0] = new JButton();
            this.myManyComponents[i][1] = new JToggleButton();
            this.myManyComponents[i][2] = new JSlider();
            this.myManyComponents[i][3] = new JCheckBox();
            this.myManyComponents[i][4] = new JTextField();
            this.myManyComponents[i][5] = new JLabel();
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
            // TODO
            // For future, TELL don't ASK regarding instanceof...
            if (this.myManyComponents[i][this.myButtonCode] instanceof JButton) {
                ((JButton) this.myManyComponents[i][this.myButtonCode]).setText(this.myTitles[i]);
            }
            if (this.myManyComponents[i][this.myButtonCode + 1] instanceof JToggleButton) {
                ((JToggleButton) this.myManyComponents[i][this.myButtonCode + 1]).setText(this.myTitles[i]);
            }
            if (this.myManyComponents[i][4] instanceof JTextField) {
                ((JTextField) this.myManyComponents[i][4]).setText(this.myTitles[i]);
            }
            if (this.myManyComponents[i][5] instanceof JLabel) {
                ((JLabel) this.myManyComponents[i][5]).setText(this.myTitles[i]);
            }

            this.setcomponents(this.myManyComponents);
        }

    }

    private void setcomponents(JComponent[][] theComponents) {
        this.myManyComponents = theComponents;
    }

    private void setMyConstraints(GridBagConstraints[] theConstraints) {
        this.myConstraints = theConstraints;
    }

    public GridBagConstraints[] getMyButtonConstraints() {
        return this.myConstraints;
    }

    public JComponent[][] getComponents() {
        return this.myManyComponents;
    }
}
