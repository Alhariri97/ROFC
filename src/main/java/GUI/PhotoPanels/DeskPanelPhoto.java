package GUI.PhotoPanels;

import GUI.BakeEnd.Basket;
import GUI.MyPanel;
import GUI.minus.DeskMinu;
import java.awt.Component;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.border.Border;
import com.mycompany.rofc.Desk;
import com.mycompany.rofc.Furniture;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author hariri
 */
public class DeskPanelPhoto extends PhotoPanel {

    Desk deskItem;

    public DeskPanelPhoto(Desk item, Basket basket, MyPanel left) {
        super(item, basket, left);
        this.deskItem = item;
    }

    @Override
    public Furniture getItem() {
        return this.deskItem;
    }

    @Override
    protected void middle() {
        if (basket.getSize() == 0) { // means it's a just veiw mode coz the bsket is empty
            System.out.println("View mode, middle desk  ");
            return;
        }
        try {
            DeskMinu disckMinu = new DeskMinu(deskItem);
            left.removeAll();
            left.repaint();

            left.add(disckMinu);

            disckMinu.submitButton().addActionListener(e -> {
                Furniture.Wood typeChosen = disckMinu.getTypeChosen();
                int numDraws = disckMinu.getDrawsChosen();
                int width = disckMinu.getWidthChosen();
                int depth = disckMinu.getDepthChosen();

                deskItem.setTypeOfWood(typeChosen);
                deskItem.setDepth(depth);
                deskItem.setWidth(width);
                deskItem.setNumOfDrawers(numDraws);
                this.showDetails();
                left.removeAll();
                left.repaint();
                left.revalidate();
            });

        } catch (ParseException ex) {
            Logger.getLogger(ChairPanelPhoto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected MyPanel centerComp() {
        MyPanel n = new MyPanel();
        int numDraws = deskItem.getNumOfDrawers();
        int itemDepth = deskItem.getDepth();
        int itemWidth = deskItem.getWidth();

        n.setLayout(new BoxLayout(n, BoxLayout.Y_AXIS));

        JLabel label1 = new JLabel("Number of draws: " + numDraws);
        JLabel label2 = new JLabel("Item Width: " + itemWidth);
        JLabel label3 = new JLabel("Item Depth: " + itemDepth);

        label1.setAlignmentX(Component.LEFT_ALIGNMENT);
        label2.setAlignmentX(Component.LEFT_ALIGNMENT);
        label3.setAlignmentX(Component.LEFT_ALIGNMENT);

        n.add(label1);
        n.add(new JLabel(" "));
        n.add(label2);
        n.add(new JLabel(" "));
        n.add(label3);
        Border border = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        n.setBorder(border);
        return n;
    }
}
