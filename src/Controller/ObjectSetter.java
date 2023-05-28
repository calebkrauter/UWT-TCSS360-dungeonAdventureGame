package Controller;

import Model.Object.Key;

public class ObjectSetter {
        GamePanel myGamePanel;
        public ObjectSetter(GamePanel theGP) {
            this.myGamePanel = theGP;
        }

        public void setObject() {
            myGamePanel.myObjects[0] = new Key();
            myGamePanel.myObjects[0].setWorldX(10);
            myGamePanel.myObjects[0].setWorldY(10);

        }
    }
