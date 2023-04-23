/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import GUI.BakeEnd.Basket;
import GUI.BakeEnd.ClickMessage;
import GUI.Buttons.MyButtonListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

/**
 *
 * @author hariri
 */
public class MyFrame extends JFrame {
    MySideButton addChairBut;
    MySideButton addTabelBut;
    MySideButton addDiskBut;
    MySideButton clearAllBut;
    MySideButton totalPriceBut;
    MySideButton SummaryBut;
    MySideButton placeOrderBut;
    MySideButton reviewOrderBut;

    MyPanel centerContent;
    public static MyPanel centCenter;
    public static MyPanel left;
    public static Basket basket = new Basket();
    static MyFrame frame ;

    MyFrame() {
        super();
        frame = this;
        left = new MyPanel();
        left.setBackground(Color.CYAN);
        left.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
        left.setPreferredSize(new Dimension(290, 0));

        centerContent = new MyPanel();
        centerContent.setBackground(Color.white);
        centerContent.setLayout(new BorderLayout(10, 10));
        centerContent.add(left, BorderLayout.WEST);
        centCenter = new MyPanel();
        centCenter.setBackground(Color.PINK);
        centCenter.setLayout(new FlowLayout());
        centerContent.add(centCenter, BorderLayout.CENTER);
        centerContent.addMouseListener(new ClickMessage("you clicked empty space on the display panel!"));

//        MyButtonListener listener
        addChairBut = new MySideButton("Add Chair");
        addDiskBut = new MySideButton("Add Disk");
        addTabelBut = new MySideButton("Add Table");
        clearAllBut = new MySideButton("Clear All");
        totalPriceBut = new MySideButton("Total Price");
        SummaryBut = new MySideButton("Summary");
        placeOrderBut = new MySideButton("Place Order");
        reviewOrderBut = new MySideButton("Review Order");

        MyButtonListener listener = new MyButtonListener();
        listener.setAddChairBut(addChairBut);
        listener.setAddDiskBut(addDiskBut);
        listener.setAddTabelBut(addTabelBut);
        listener.setClearAllBut(clearAllBut);
        listener.setTotalPriceBut(totalPriceBut);
        listener.setSummaryBut(SummaryBut);
        listener.setPlaceOrderBut(placeOrderBut);
        listener.setReviewOrderBut(reviewOrderBut);

        listener.setCentCenter(centCenter);
        listener.setLeft(left);
        listener.setBasket(basket);

        addChairBut.addActionListener(listener);
        addTabelBut.addActionListener(listener);
        addDiskBut.addActionListener(listener);
        clearAllBut.addActionListener(listener);
        totalPriceBut.addActionListener(listener);
        SummaryBut.addActionListener(listener);
        placeOrderBut.addActionListener(listener);
        reviewOrderBut.addActionListener(listener);

// Start of the side bar
        MyPanel sideBar = new MyPanel();
        sideBar.setBackground(Color.gray);
        sideBar.setPreferredSize(new Dimension(200, 100));
        sideBar.add(addChairBut);
        sideBar.add(addDiskBut);
        sideBar.add(addTabelBut);
        sideBar.add(clearAllBut);
        sideBar.add(totalPriceBut);
        sideBar.add(SummaryBut);
        sideBar.add(placeOrderBut);
        sideBar.add(reviewOrderBut);
        sideBar.addMouseListener(new ClickMessage("you clicked empty space in side bar!"));

        
        
        this.add(sideBar, BorderLayout.WEST);
        this.add(centerContent, BorderLayout.CENTER);
        this.setMinimumSize(new Dimension(800, 500));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setTitle("ROFC APP");
        this.setVisible(true);
        this.addMouseListener(new ClickMessage("you clicked empty space!"));

        ImageIcon logo = new ImageIcon("image/logo-black.jpeg");
        this.setIconImage(logo.getImage());

    }

}
