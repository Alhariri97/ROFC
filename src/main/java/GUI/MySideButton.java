package GUI;

import java.awt.Dimension;
import javax.swing.JButton;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author hariri
 */
public class MySideButton extends JButton {

    MySideButton(String text) {
        super(text);
        setPreferredSize(new Dimension(180, 80));
        setFocusPainted(false);

    }
}
