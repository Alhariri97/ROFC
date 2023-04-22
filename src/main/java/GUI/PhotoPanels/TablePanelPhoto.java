package GUI.PhotoPanels;

import GUI.BakeEnd.Basket;
import GUI.MyPanel;
import GUI.minus.TableMinu;
import java.awt.Component;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.border.Border;
import com.mycompany.rofc.Furniture;
import com.mycompany.rofc.Table;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author hariri
 */
public class TablePanelPhoto extends PhotoPanel {

    Table tableItem;

    public TablePanelPhoto(Table item, Basket basket, MyPanel left) {
        super(item, basket, left);
        this.tableItem = item;

    }

    @Override
    protected void middle() {

        if (basket.getSize() == 0) { // means it's a just veiw mode coz the bsket is empty
            System.out.println("View mode, middle Table  ");
            return;
        }

        try {
            System.out.println("fucking middle table");
            TableMinu disckMinu = new TableMinu(tableItem);
            left.removeAll();
            left.repaint();
            left.add(disckMinu);

            disckMinu.submitButton().addActionListener(e -> {

                Furniture.Wood typeChosen = disckMinu.getTypeChosen();
                boolean isChromChosen = disckMinu.getBaseChosen();
                int diameterChseon = disckMinu.getDiameterChosen();

                tableItem.setTypeOfWood(typeChosen);
                tableItem.setDiameter(diameterChseon);
                tableItem.setIsChrome(isChromChosen);
                this.showDetails();
                left.removeAll();
                left.repaint();
                left.revalidate();
            });
        } catch (ParseException ex) {
            Logger.getLogger(TablePanelPhoto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected MyPanel centerComp() {
        System.out.println("print");
        MyPanel n = new MyPanel();
        Furniture.Wood typeChosen = tableItem.getTypeOfWood();
        int diameterChosen = tableItem.getDiameter();
        boolean baseChosenIsChrom = tableItem.isIsChrome();

        n.setLayout(new BoxLayout(n, BoxLayout.Y_AXIS));

        JLabel label1 = new JLabel("Item Diameter: " + diameterChosen);
        JLabel label2 = new JLabel("Item base: " + (baseChosenIsChrom == true ? "Chrome" : "Wooden"));

        label1.setAlignmentX(Component.LEFT_ALIGNMENT);
        label2.setAlignmentX(Component.LEFT_ALIGNMENT);

        n.add(label1);
        n.add(new JLabel(" "));
        n.add(label2);
        Border border = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        n.setBorder(border);
        return n;
    }

}
