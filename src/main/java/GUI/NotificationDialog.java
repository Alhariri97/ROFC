/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author hariri
 */


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.Timer;

public final class NotificationDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    public NotificationDialog( String message, int duration, Color color) {
        super(MyFrame.frame);
        // Set the dialog properties
        setUndecorated(true);
        setResizable(false);
        setLocationRelativeTo(MyFrame.frame);
        
        // Create the message label
        JLabel label = new JLabel(message);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        getContentPane().add(label);
        
        // Set the dialog size
        Dimension size = label.getPreferredSize();
        size.width += 40;
        size.height += 30;
        setSize(size);
        label.setForeground(color);
        
        Timer timer = new Timer(duration, e -> setVisible(false));
        timer.setRepeats(false);
        timer.start();
        showNotification();
    }
    
    public void showNotification() {
        
        setLocation(new Point(900, 100));
        setVisible(true);
    }

}


