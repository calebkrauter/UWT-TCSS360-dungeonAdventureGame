import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;

public class ButtonComponent extends JButton {

    final private int PLAY_GAME = 1;
    final private int LOAD_GAME = 2;
    final private int OPTIONS = 3;
    final private int CREDITS = 4;

    JButton[] buttons;
    String[] menuButtonTitles;
    public ButtonComponent() {
        menuButtonTitles = new String[]{"Play Game", "Load Game", "Options", "Credits"};
        produceButton(PLAY_GAME);
        this.setText("HELLO THERE");
    }

    private JButton produceButton(int theButtonCode) {
        this.setText(menuButtonTitles[theButtonCode]);
        this.setSize(100, 70);
        this.setEnabled(true);
        Action action = new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
        this.setAction(action);
        return this;
    }
}
