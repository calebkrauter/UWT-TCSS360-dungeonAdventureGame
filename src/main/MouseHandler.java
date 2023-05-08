package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse Clicked!");
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Mouse Entered!");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Mouse Exited!");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("Mouse Dragged!");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println("Mouse Moved!");
    }
}
