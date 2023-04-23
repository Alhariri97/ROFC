/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.BakeEnd;

/**
 *
 * @author hariri
 */


import GUI.GUI;
import GUI.MyFrame;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.Timer;

public class ClickMessage implements MouseListener, MouseMotionListener {

    String message;
    Point location;

    public ClickMessage(String a) {
        this.message = a;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) { // check if left mouse button is pressed
            Component c = e.getComponent();
            location = e.getLocationOnScreen();
            showMessage(c, this.message);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    public void showMessage(Component component, String message) {
        JDialog dialog = new JDialog();
        dialog.setUndecorated(true);
        JLabel label = new JLabel(message);
        dialog.add(label);
        dialog.pack();
        location.translate(0, -20);
        dialog.setLocation(location);
        dialog.setVisible(true);
        Timer timer = new Timer(1000, e -> dialog.dispose());
        timer.setRepeats(false);
        timer.start();

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

}
