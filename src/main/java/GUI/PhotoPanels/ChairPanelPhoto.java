/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.PhotoPanels;

import GUI.BakeEnd.Basket;
import GUI.MyPanel;
import GUI.minus.ChairMinu;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import com.mycompany.rofc.Chair;
import com.mycompany.rofc.Furniture;

/**
 *
 * @author hariri
 */
public class ChairPanelPhoto extends PhotoPanel {

    Chair chairItem;
    MyPanel n;
    boolean arm;
    Furniture.Wood typeChosen;

    public ChairPanelPhoto(Chair item, Basket basket, MyPanel left) {
        super(item, basket, left);
        this.chairItem = item;
    }

    @Override
    protected MyPanel centerComp() {
        n = new MyPanel();
        String v = chairItem.isArmRests() == true ? "yes " : "no";
        n.add(new JLabel("Armsets: " + v, JLabel.LEFT));
        return n;
    }

    @Override
    protected void middle() {

        if (basket.getSize() == 0) { // means it's a just veiw mode coz the bsket is empty
            System.out.println("miidle chair. it's view mode you can't get middle ");
            return;
        }

        try {
            ChairMinu chairMinu = new ChairMinu(chairItem);
            left.removeAll();
            left.repaint();
            left.add(chairMinu);

            chairMinu.submitButton().addActionListener(e -> {
                typeChosen = chairMinu.getTypeChosen();
                arm = chairMinu.getArmChosen();
                chairItem.setArmRests(arm);
                chairItem.setTypeOfWood(typeChosen);
                this.showDetails();
                left.removeAll();
                left.repaint();
                left.revalidate();
            });

        } catch (ParseException ex) {
            Logger.getLogger(ChairPanelPhoto.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
