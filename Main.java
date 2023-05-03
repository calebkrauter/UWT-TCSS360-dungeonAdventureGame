package main;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {

        JFrame myWindow = new JFrame();
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.setResizable(false);
        myWindow.setTitle("Dungeon Adventure");

        GamePanel myGamePanel = new GamePanel();
        myWindow.add(myGamePanel);
        // so we can see it
        myWindow.pack();

        myWindow.setLocationRelativeTo(null);
        myWindow.setVisible(true);
        myGamePanel.startGameThread();


    }
}
