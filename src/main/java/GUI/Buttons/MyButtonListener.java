/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Buttons;

import GUI.BakeEnd.Basket;
import GUI.NotificationDialog;
import GUI.BakeEnd.OrderForm;
import GUI.BakeEnd.ReviewOrder;
import GUI.MyPanel;
import GUI.MySideButton;
import GUI.PhotoPanels.ChairPanelPhoto;
import GUI.PhotoPanels.DeskPanelPhoto;
import GUI.PhotoPanels.TablePanelPhoto;
import GUI.minus.ChairMinu;
import GUI.minus.DeskMinu;
import GUI.minus.TableMinu;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import com.mycompany.rofc.Chair;
import com.mycompany.rofc.Desk;
import com.mycompany.rofc.Furniture;
import com.mycompany.rofc.Furniture.Wood;
import com.mycompany.rofc.Table;
import java.awt.Color;

/**
 *
 * @author hariri
 */
public class MyButtonListener implements ActionListener {

    private MySideButton addChairBut;
    private MySideButton addDiskBut;
    private MySideButton addTabelBut;
    private MySideButton clearAllBut;
    private MySideButton totalPriceBut;
    private MySideButton SummaryBut;
    private MySideButton placeOrderBut;
    private MySideButton reviewOrderBut;
    private MyPanel left;
    private MyPanel centCenter;
    private Basket basket;

    private boolean review = false;

    public MyButtonListener() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (review) {
            int confirm = JOptionPane.showConfirmDialog(null, "This will exit the current veiw mode?", "Exit veiew ", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.out.println("you were vew");
                centCenter.removeAll();
                left.removeAll();
                left.repaint();
                centCenter.repaint();
                review = false;

            } else {
                return;
            }
        }
        if (e.getSource() == addChairBut) {
            chairMethod();
        } else if (e.getSource() == addDiskBut) {
            diskMethod();
        } else if (e.getSource() == addTabelBut) {
            tableMethod();
        } else if (e.getSource() == clearAllBut) {
            clearAllMethod();
        } else if (e.getSource() == totalPriceBut) {
            totalMethod();
        } else if (e.getSource() == SummaryBut) {
            summaryMethod();
        } else if (e.getSource() == placeOrderBut) {
            try {
                placeOrderMethod();
            } catch (ParseException ex) {
                Logger.getLogger(MyButtonListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == reviewOrderBut) {
            int confirm = JOptionPane.showConfirmDialog(null, "You will lose all of your items in the current basket if there is any!, are you sure?", "Go View mode", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                review = true;
                basket.clearAll();
                centCenter.removeAll();
                centCenter.repaint();
                reviewOrderMethod();

            }
        }

    }

    //    settres so i don't keep passing these arguments to the constructor whene its used
    public void setLeft(MyPanel left) {
        this.left = left;
    }

    public void setCentCenter(MyPanel centCenter) {
        this.centCenter = centCenter;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public void setAddChairBut(MySideButton addChairBut) {
        this.addChairBut = addChairBut;
    }

    public void setAddDiskBut(MySideButton addDiskBut) {
        this.addDiskBut = addDiskBut;
    }

    public void setAddTabelBut(MySideButton addTabelBut) {
        this.addTabelBut = addTabelBut;
    }

    public void setClearAllBut(MySideButton clearAllBut) {
        this.clearAllBut = clearAllBut;
    }

    public void setPlaceOrderBut(MySideButton placeOrderBut) {
        this.placeOrderBut = placeOrderBut;
    }

    public void setTotalPriceBut(MySideButton totalPriceBut) {
        this.totalPriceBut = totalPriceBut;
    }

    public void setSummaryBut(MySideButton SummaryBut) {
        this.SummaryBut = SummaryBut;
    }

    public void setReviewOrderBut(MySideButton reviewOrderBut) {
        this.reviewOrderBut = reviewOrderBut;
    }

    private void chairMethod() {
        try {
            ChairMinu panel = new ChairMinu();
            left.removeAll();
            left.add(panel);
            left.revalidate();
            left.repaint();

            panel.submitButton().addActionListener(e2 -> {
                int idInput = panel.getIdChosen();
                if (idInput <= -1) {
                    System.out.println("Wrong id " + idInput);
                } else {

                    int quantity = panel.getQuanChosen();
                    Wood typeChosen = panel.getTypeChosen();
                    boolean arm = panel.getArmChosen();
                    int idChosen = idInput;
                    for (int i = 0; i < quantity; i++) {
                        if (basket.getSize() < 9) {
                            if (!basket.isItemExist(idChosen)) {
                                Chair chair = new Chair(idChosen, typeChosen, quantity, arm);
                                System.out.println(chair.getIdNum() + " this is the id");
                                basket.addItem(chair);
                                ChairPanelPhoto pan = new ChairPanelPhoto(chair, basket, left);
                                centCenter.add(pan);
                                left.removeAll();
                                left.revalidate();
                                left.repaint();
                                idChosen++;

                                new NotificationDialog("Chari added successful", 3000, GUI.GUI.CORRECT_COLOR);

                            } else {
                                JOptionPane.showMessageDialog(null, "ID number " + idChosen + " Already exists,Please rewrite a different number or delete the one in basket.", "Id number", JOptionPane.WARNING_MESSAGE);
                                break;

                            }

                        } else {
                            System.out.println("the Basket is full");
                            JOptionPane.showMessageDialog(null, "Sorry, the basket is full,Please checkout or delete itmes.", "Basket Full", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                    }

                }

            });

        } catch (ParseException ex) {
            System.out.println(ex);
        }
    }

    private void diskMethod() {
        //        left.setLayout(layout);
        try {

            DeskMinu desk = new DeskMinu();
            left.removeAll();
            left.add(desk);
            left.revalidate();
            left.repaint();
            desk.submitButton().addActionListener(e2 -> {
                int idInput = desk.getIdChosen();
                if (idInput <= -1) {
                    System.out.println("Wrong id " + idInput);
                } else {
                    int quantity = desk.getQuanChosen();
                    Wood typeChosen = desk.getTypeChosen();
                    int depthChosen = desk.getDepthChosen();
                    int widthChosen = desk.getWidthChosen();
                    int drawsChosen = desk.getDrawsChosen();
                    int idChosen = idInput;

                    for (int i = 0; i < quantity; i++) {
                        if (basket.getSize() < 9) {
                            if (!basket.isItemExist(idChosen)) {
                                Desk deskClass = new Desk(idChosen, typeChosen, quantity, depthChosen, widthChosen, drawsChosen);
                                basket.addItem(deskClass);
                                DeskPanelPhoto deskPhoto = new DeskPanelPhoto(deskClass, basket, left);
                                centCenter.add(deskPhoto);
                                left.removeAll();
                                centCenter.revalidate();
                                centCenter.repaint();
                                left.revalidate();
                                left.repaint();
                                idChosen++;
                                new NotificationDialog("Desk added successful", 3000, GUI.GUI.CORRECT_COLOR);

                            } else {
                                JOptionPane.showMessageDialog(null, "ID number " + idChosen + " Already exists,Please rewrite a different number or delete the one in basket.", "Id number", JOptionPane.WARNING_MESSAGE);
                                break;
                            }

                        } else {
                            System.out.println("the Basket is full");
                            JOptionPane.showMessageDialog(null, "Sorry, the basket is full and cannot hold any more items.", "Basket Full", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                    }

                }

            }
            );
        } catch (ParseException ex) {
            System.out.println(ex);
        }

    }

    private void tableMethod() {
        try {
            //
            TableMinu tabel = new TableMinu();
            left.removeAll();
            left.add(tabel);
            left.revalidate();
            left.repaint();
            tabel.submitButton().addActionListener(e2 -> {
                int idInput = tabel.getIdChosen();
                if (idInput <= -1) {
                    System.out.println("Wrong id " + idInput);
                } else {
                    int quantity = tabel.getQuanChosen();
                    Wood typeChosen = tabel.getTypeChosen();
                    boolean baseChosen = tabel.getBaseChosen();
                    int diameterChosen = tabel.getDiameterChosen();
                    int idChosen = idInput;

                    for (int i = 0; i < quantity; i++) {
                        if (basket.getSize() < 9) {
                            if (!basket.isItemExist(idChosen)) {
                                Table tableClass = new Table(idChosen, true, baseChosen, diameterChosen, typeChosen, 1);

                                basket.addItem(tableClass);
                                TablePanelPhoto tablePhoto = new TablePanelPhoto(tableClass, basket, left);
                                centCenter.add(tablePhoto);
                                left.removeAll();
                                centCenter.revalidate();
                                centCenter.repaint();
                                left.revalidate();
                                left.repaint();
                                idChosen++;
                                new NotificationDialog("Table added successful", 2000, GUI.GUI.CORRECT_COLOR);

                            } else {
                                JOptionPane.showMessageDialog(null, "ID number " + idChosen + " Already exists,Please rewrite a different number or delete the one in basket.", "Id number", JOptionPane.WARNING_MESSAGE);
                                break;
                            }

                        } else {
                            System.out.println("the Basket is full");
                            JOptionPane.showMessageDialog(null, "Sorry, the basket is full and cannot hold any more items.", "Basket Full", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                    }

                }

            }
            );
        } catch (ParseException ex) {
            System.out.println(ex);
        }

    }

    private void clearAllMethod() {

        if (basket.getSize() >= 1) { // if there is itmes in basket
            //            JOptionPane.showMessageDialog(null, "Item added to basket", "Notification", JOptionPane.INFORMATION_MESSAGE);

            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete all items in the basket?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.out.println("clear all method started");
                basket.clearAll();
                left.removeAll();
                centCenter.removeAll();

                left.repaint();
                left.revalidate();
                centCenter.repaint();
                centCenter.revalidate();
                new NotificationDialog("Basket cleared", 2000, GUI.GUI.CORRECT_COLOR);

            }

        } else {
            new NotificationDialog("Basket is already empty", 1000, GUI.GUI.INFO_COLOR);

        }
    }

    private void totalMethod() {
        if (basket.getSize() >= 1) {

            this.left.removeAll();
            MyPanel mainPanel = new MyPanel();
            int padding = 10;
            mainPanel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

            double totalPrice = 0.0;
            for (Furniture item : basket.getAll()) {
                totalPrice += item.calculatePrice();
            }

            JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel totall = new JLabel("Total Price: " + totalPrice + "£");
            p1.add(totall);
            mainPanel.add(p1);
            this.left.add(mainPanel);
            left.repaint();
            left.revalidate();
        } else {
            new NotificationDialog("Your basket is empty, No total to show!", 1000, GUI.GUI.INFO_COLOR);

        }

    }

    private void summaryMethod() {
        if (basket.getSize() >= 1) {

            this.left.removeAll();
            MyPanel mainPanel = new MyPanel();
            int padding = 10;
            mainPanel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            double totalPrice = 0.0;
            ArrayList<Furniture> list = basket.getAll();
            Collections.sort(list);

            for (Furniture furniture : list) {
                System.out.println(furniture.calculatePrice());
            }
            for (Furniture item : list) {
                totalPrice += item.calculatePrice();
                String type = item.getClass().toString();
                String name = type.substring(type.lastIndexOf('.') + 1);
                JPanel childPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
                String s = "Item " + name + " ID: " + item.getIdNum() + "/  price: " + item.calculatePrice();
                System.out.println(s);
                JLabel label1 = new JLabel(s);
                childPanel1.add(label1);
                mainPanel.add(childPanel1);
            }

            JPanel dashPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            String numOfItemsString = "------------- " + "Num of Items: " + basket.getSize();
            String totalPriceString = "Total Price: " + totalPrice + "£";
            JLabel dash = new JLabel(numOfItemsString);
            JLabel totall = new JLabel(totalPriceString);
            System.out.println("");
            dashPanel.add(dash);
            totalPanel.add(totall);
            mainPanel.add(dashPanel);
            mainPanel.add(totalPanel);
            System.out.println(numOfItemsString);
            System.out.println(totalPriceString);
            this.left.add(mainPanel);
            left.repaint();
            left.revalidate();

            // I did both print the summary to the councel and 
            //  add Summary to the GUI coz i'm not sure what the ICA means by: 
            //"summary should be output to the console", is to the console enough or with GUI!
        } else {

            new NotificationDialog("Your basket is empty, No summary to display!", 1000, GUI.GUI.INFO_COLOR);

        }
    }

    private void placeOrderMethod() throws ParseException {
        OrderForm order = new OrderForm(basket);
        order.setVisible(true);
    }

    private void reviewOrderMethod() {
        left.removeAll();
        ReviewOrder reviewOrder = new ReviewOrder(left, centCenter);
        reviewOrder.setVisible(true);

    }
}
