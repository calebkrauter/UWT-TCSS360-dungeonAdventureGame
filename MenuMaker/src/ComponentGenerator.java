import javax.swing.*;
import java.awt.*;

public class ComponentGenerator {

    final private int PLAY_GAME = 1;
    final private int LOAD_GAME = 2;
    final private int OPTIONS = 3;
    final private int CREDITS = 4;
    // THESE CONSTANTS I am using elsewhere. Should they be public, how should I deal with them? ENUMS?
    final private int GO_LEFT = 65;
    final private int GO_RIGHT = 99;
    final private int GO_UP = -88;
    final private int GO_DOWN = 90;
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
        myTitles = theTitles;
        myPageStart = thePageStart;
        myInsetTop = theInsetTop;
        myInsetLeft = theInsetLeft;
        myInsetBottom = theInsetBottom;
        myInsetRight = theInsetRight;
        myDirectionOfFlow = theDirectionOfFlow;
        manyComponents = new JComponent[myTitles.length][4];

        gBC = new GridBagConstraints();
        produceButton();
    }


    public void produceButton() {
        components = new JComponent[myTitles.length];
        myConstraints = new GridBagConstraints[myTitles.length];

        for (int i = 0; i < myTitles.length; i++) {
            if (myDirectionOfFlow == GO_UP) {
                myInsetBottom += 50;
            }
            if (myDirectionOfFlow == GO_LEFT) {
                myInsetRight += 50;
            }
            if (myDirectionOfFlow == GO_DOWN) {
                myInsetTop += 50;
            }
            if (myDirectionOfFlow == GO_RIGHT) {
                myInsetLeft += 50;
            }
            manyComponents[i][0] = new JButton();
            manyComponents[i][1] = new JToggleButton();
            manyComponents[i][2] = new JSlider();
            manyComponents[i][3] = new JCheckBox();

            myConstraints[i] = new GridBagConstraints();

            myConstraints[i].ipadx = 50;
            myConstraints[i].ipady = 10;
            myConstraints[i].insets = new Insets(myInsetTop, myInsetLeft, myInsetBottom, myInsetRight);
            myConstraints[i].weightx = 0.5;
            myConstraints[i].weighty = 0.5;
            myConstraints[i].anchor = myPageStart;
            myConstraints[i].gridx = 1;
            myConstraints[i].gridy = 1;
            myConstraints[i].gridwidth = 3;

            setMyConstraints(myConstraints);


            if (manyComponents[i][myButtonCode] instanceof JSlider || manyComponents[i][myButtonCode] instanceof JCheckBox) {
                components[i].setToolTipText(myTitles[i]);

            } else if (manyComponents[i][myButtonCode] instanceof JToggleButton) {
                ((JToggleButton) manyComponents[i][myButtonCode]).setText(myTitles[i]);
            } else if (manyComponents[i][myButtonCode] instanceof JButton){
                ((JButton) manyComponents[i][myButtonCode]).setText(myTitles[i]);

            }
            setcomponents(manyComponents);
        }
    }

    private void setcomponents(JComponent[][] theComponents) {
        manyComponents = theComponents;
    }

    private void setMyConstraints(GridBagConstraints[] theConstraints) {
        myConstraints = theConstraints;
    }

    public GridBagConstraints[] getMyButtonConstraints() {
        return myConstraints;
    }
    public JComponent[][] getComponents() {
        return manyComponents;
    }
}
