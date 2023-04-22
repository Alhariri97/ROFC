/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.minus;

import java.awt.FlowLayout;
import java.text.ParseException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import com.mycompany.rofc.Chair;
import com.mycompany.rofc.Furniture;

/**
 *
 * @author hariri
 */
public class ChairMinu extends MinuPanel {

    JCheckBox check;

    public ChairMinu() throws ParseException {

        super();
        check = new JCheckBox();
        check.setFocusable(false);

        JPanel checkPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        checkPanel.add(new JLabel("Armsets? "));
        checkPanel.add(check);

        this.middlePanel.add(Box.createVerticalStrut(10));
        this.middlePanel.add(checkPanel);
        this.middlePanel.revalidate();
        this.middlePanel.repaint();

    }

    public ChairMinu(Chair chair) throws ParseException {
        this();

        boolean wood = chair.getTypeOfWood() == Furniture.Wood.w;

        this.walnut.setSelected(wood);
        check.setSelected(chair.isArmRests());
        this.middlePanel.remove(textPanel);
        this.middlePanel.remove(idField);

        TitledBorder border = BorderFactory.createTitledBorder("Edit Chair id:" + chair.getIdNum());
        this.setBorder(border);
        this.remove(quantityPanel);
        JButton cancel = new JButton("Cancel");
        this.buttonPanel.add(cancel);

        cancel.addActionListener(e -> {
            JPanel parent = (JPanel) this.getParent();
            parent.remove(this);
            parent.repaint();
        });
    }

    public boolean getArmChosen() {

        return check.isSelected();
    }

}
