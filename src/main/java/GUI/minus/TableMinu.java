/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.minus;

/**
 *
 * @author hariri
 */
import java.awt.FlowLayout;
import java.text.ParseException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import com.mycompany.rofc.Furniture;
import com.mycompany.rofc.Table;

public class TableMinu extends MinuPanel {

    JRadioButton wooden;
    JRadioButton chrome;
    JSpinner diameter;

    public TableMinu() throws ParseException {
        super();

        wooden = new JRadioButton("wooden");
        chrome = new JRadioButton("chrome.");
        ButtonGroup group = new ButtonGroup();
        group.add(chrome);
        group.add(wooden);
        JPanel woodPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        woodPanel.add(new JLabel("What base would you prefere: "));
        woodPanel.add(wooden);
        woodPanel.add(chrome);
        chrome.doClick();
        this.middlePanel.add(Box.createVerticalStrut(10));
        this.middlePanel.add(woodPanel);
        this.middlePanel.add(chrome);
        this.middlePanel.add(wooden);

        SpinnerNumberModel modelWidth = new SpinnerNumberModel(100, 50, 400, 5);
        diameter = new JSpinner(modelWidth);
        JPanel diameterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        diameterPanel.add(new JLabel("What diameter?"));
        diameterPanel.add(diameter);
        this.middlePanel.add(Box.createVerticalStrut(10));
        this.middlePanel.add(diameterPanel);
    }

    public TableMinu(Table table) throws ParseException {
        this();

        boolean wood = table.getTypeOfWood() == Furniture.Wood.w;
        this.walnut.setSelected(wood);
        this.chrome.setSelected(table.isIsChrome());
        this.diameter.setValue(table.getDiameter());
        this.wooden.setSelected(!table.isIsChrome());
        this.middlePanel.remove(textPanel);
        this.middlePanel.remove(idField);

        TitledBorder border = BorderFactory.createTitledBorder("Edit Desk id:" + table.getIdNum());
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

    public int getDiameterChosen() {
        return (int) diameter.getValue();
    }

    public boolean getBaseChosen() {
        return chrome.isSelected() ? true : false;
    }
}
