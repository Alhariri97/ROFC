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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import com.mycompany.rofc.Desk;
import com.mycompany.rofc.Furniture;

/**
 * d
 * d
 * d
 *
 * @author hariri
 */
public class DeskMinu extends MinuPanel {

    JSpinner spinnerWidth;
    JSpinner spinnerDepth;
    JSpinner spinnerDraws;

    public DeskMinu() throws ParseException {
        super();

        SpinnerNumberModel modelDraws = new SpinnerNumberModel(1, 1, 4, 1);
        spinnerDraws = new JSpinner(modelDraws);

        SpinnerNumberModel modelWidth = new SpinnerNumberModel(100, 50, 200, 5);
        spinnerWidth = new JSpinner(modelWidth);
        SpinnerNumberModel modelDepth = new SpinnerNumberModel(100, 50, 200, 5);
        spinnerDepth = new JSpinner(modelDepth);

        JPanel drawsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        drawsPanel.add(new JLabel("how many Draws? "));
        drawsPanel.add(spinnerDraws);

        JPanel widthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        widthPanel.add(new JLabel("What width?"));
        widthPanel.add(spinnerWidth);

        JPanel depthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        depthPanel.add(new JLabel("What Depth"));
        depthPanel.add(spinnerDepth);

        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        this.middlePanel.add(Box.createVerticalStrut(10));
        this.middlePanel.add(drawsPanel);
        this.middlePanel.add(Box.createVerticalStrut(10));
        this.middlePanel.add(widthPanel);
        this.middlePanel.add(Box.createVerticalStrut(10));
        this.middlePanel.add(depthPanel);
        this.middlePanel.add(Box.createVerticalStrut(10));
        this.middlePanel.add(quantityPanel);
        this.middlePanel.add(Box.createVerticalStrut(10));
        this.middlePanel.revalidate();
        this.middlePanel.repaint();

    }

    public DeskMinu(Desk desk) throws ParseException {
        this();

        boolean wood = desk.getTypeOfWood() == Furniture.Wood.w;
        this.walnut.setSelected(wood);
        this.spinnerDepth.setValue(desk.getDepth());
        this.spinnerWidth.setValue(desk.getWidth());
        this.spinnerDraws.setValue(desk.getNumOfDrawers());
        this.middlePanel.remove(textPanel);
        this.middlePanel.remove(idField);
       
        TitledBorder border = BorderFactory.createTitledBorder("Edit Desk id:" + desk.getIdNum());
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

    public int getWidthChosen() {
        return (int) spinnerWidth.getValue();
    }

    public int getDepthChosen() {
        return (int) spinnerDepth.getValue();
    }

    public int getDrawsChosen() {
        return (int) spinnerDraws.getValue();
    }

}
