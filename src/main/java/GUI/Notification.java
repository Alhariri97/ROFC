package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author hariri
 */
public class Notification extends JPanel{

    public Notification(String message, int duration, MyPanel panel) {
        JLabel notification = new JLabel(message);
        notification.setForeground(new Color(0, 200, 0));
        notification.setBackground(Color.WHITE);
        notification.setOpaque(true);
        Font font = notification.getFont();
        notification.setFont(font.deriveFont(18f));
        notification.setHorizontalAlignment(JLabel.CENTER);
        notification.setVerticalAlignment(JLabel.CENTER);
        notification.setPreferredSize(new Dimension(300, 50));
        JPanel p = new JPanel();
        p.add(notification);
//        notification.setBounds(50, 50, 200, 50);
        notification.setPreferredSize(new Dimension(299, 209));
        p.setLayout(null);
//        JLayeredPane layer = MyFrame.layer;
        p.setPreferredSize(new Dimension(100, 50));
//        layer.add(p);

//        MyPanel d = MyFrame.centCenter;
//        layer.add(p, Integer.valueOf(499));

        Timer timer = new Timer(duration, (e) -> notification.setVisible(false));
        timer.setRepeats(false);
        timer.start();
    }
}
