/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.PhotoPanels;

import GUI.BakeEnd.Basket;
import GUI.BakeEnd.ClickMessage;
import GUI.MyFrame;
import GUI.MyPanel;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import com.mycompany.rofc.Chair;
import com.mycompany.rofc.Desk;
import com.mycompany.rofc.Furniture;
import com.mycompany.rofc.Table;

/**
 *
 * @author hariri
 */
public abstract class PhotoPanel extends MyPanel {

    protected Furniture furnitureItem;
    protected Basket basket;
    protected MyPanel left;
    protected JPanel pricePanel;
    protected String name;
    protected JLabel itemLabel;

    public PhotoPanel(Furniture item, Basket basket, MyPanel left) {

        super();
        if(MyFrame.basket.getSize()==0){
            
        this.addMouseListener(new ClickMessage("View mode you can't edit or delete!"));
            }else{
        this.addMouseListener(new ClickMessage("click right to delete and middle to change!"));
        
        }
            

        this.basket = basket;
        this.left = left;
        this.furnitureItem = item;
        int width = 250;
        if (item instanceof Chair) {
            System.out.println("a is a Chair");
            name = "chair";
        } else if (item instanceof Table) {
            System.out.println("a is a Table");
            name = "table";
        } else if (item instanceof Desk) {
            System.out.println("a is a Desk");
            width = 463;
            name = "desk";
        } else {
            name = "default";
        }
        String fileName = "image/" + name + ".jpg";
        this.setPreferredSize(new Dimension(width, 306));

        itemLabel = new JLabel();
        itemLabel.setIcon(new ImageIcon(new ImageIcon(fileName).getImage().getScaledInstance(width, 306, Image.SCALE_DEFAULT)));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.setBorder(null);
        this.add(itemLabel);
        this.addMouseListener(new MouseAdapter() { // i used mousea Adapter coz i only need mouse click event and don't want the other methodes from the abstrat
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    leftButton();
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    rightButton();
                }
                if (e.getButton() == MouseEvent.BUTTON2) {
                    middle();
                }

                PhotoPanel.this.revalidate();
                PhotoPanel.this.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            }
        });
    }

    public Furniture getItem() {
        return this.furnitureItem;
    }

    private void leftButton() {

        if (this.getComponents().length == 1) {
            showDetails();
        } else {
            this.removeAll();
            this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
            this.setBorder(null);
            this.add(itemLabel);
        }
    }

    private void rightButton() {
        // Delete the item :)
        if (basket.getSize() == 0) {
            System.out.println("you cant do so its a view mode ");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            Container parent = SwingUtilities.getAncestorOfClass(Container.class, this);
            basket.deleteItem(furnitureItem.getIdNum());
            left.removeAll();

            parent.remove(this);
            parent.revalidate();
            parent.repaint();
        }
    }

    protected void showDetails() {
        this.removeAll();
        this.setLayout(new BorderLayout());

        this.remove(itemLabel);

        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(00, 70));
        titlePanel.setLayout(new GridLayout(2, 1));
        this.add(titlePanel, BorderLayout.NORTH);

        JPanel center = centerComp();
        titlePanel.add(new JLabel("item ID: " + furnitureItem.getIdNum()));
        titlePanel.add(new JLabel("Type of wood: " + (furnitureItem.getTypeOfWood() == Furniture.Wood.w ? "Walnut" : "Oak")));
        TitledBorder border = BorderFactory.createTitledBorder((name.substring(0, 1).toUpperCase() + name.substring(1)) + (basket.getSize() == 0 ? " View Mode" : ""));
        this.setBorder(border);
        titlePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.add(center, BorderLayout.WEST);

        pricePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pricePanel.add(new JLabel("Item price: " + furnitureItem.calculatePrice()));
        this.add(pricePanel, BorderLayout.SOUTH);
    }

    protected abstract void middle();

    protected abstract MyPanel centerComp();

}
