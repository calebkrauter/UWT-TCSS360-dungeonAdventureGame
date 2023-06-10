package Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {

    private boolean mouseClicked;
    private boolean mousePressed;
    private boolean mouseReleased;
    private boolean mouseEntered;
    private boolean mouseExited;
    private boolean mouseDragged;
    private boolean mouseMoved;
    private boolean mouseOver;


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        System.out.println("Mouse Entered!");
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        System.out.println("Mouse Exited!");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
//        System.out.println("Mouse Dragged!");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
//        System.out.println("Mouse Moved!");
    }
    //    @Override
//    public void mouseOver(MouseEvent e) {
//
//    }


    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }




}
