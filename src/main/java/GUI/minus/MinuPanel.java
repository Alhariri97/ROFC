package GUI.minus;

import GUI.MyPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.ToolTipManager;
import javax.swing.border.Border;
import com.mycompany.rofc.Furniture;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author hariri
 */
public class MinuPanel extends MyPanel {

    JRadioButton oak;
    JRadioButton walnut;
    JButton submit;
    JTextField idField;
    public JPanel middlePanel;
    JPanel textPanel;
    JSpinner spinnerQuantity;
    JPanel quantityPanel;
JPanel buttonPanel;
    public MinuPanel() throws ParseException {
        super();
        middlePanel = new JPanel();
        oak = new JRadioButton("Oak");
        walnut = new JRadioButton("Walnut.");
        ButtonGroup group = new ButtonGroup();
        group.add(oak);
        group.add(walnut);
        JPanel woodPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        woodPanel.add(new JLabel("Type of wood: "));
        woodPanel.add(oak);
        woodPanel.add(walnut);
        oak.doClick();

        idField = new JTextField();
        textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        textPanel.add(new JLabel("Enter the ID ?"));
        idField.setPreferredSize(new Dimension(40, 20));
        textPanel.add(idField);

        middlePanel.add(textPanel);
        idField.setToolTipText("Enter ID here max 3 Digits");
        ToolTipManager.sharedInstance().setInitialDelay(0); // set delay to 0ms

        idField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                idField.setBorder(null);
            }
        });

        submit = new JButton("Submit");

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        middlePanel.add(woodPanel);
        middlePanel.add(oak);
        middlePanel.add(walnut);

        SpinnerNumberModel modelQuantity = new SpinnerNumberModel(1, 1, 9, 1);
        spinnerQuantity = new JSpinner(modelQuantity);
        quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        quantityPanel.add(new JLabel("how many would you like?"));
        quantityPanel.add(spinnerQuantity);

         buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(submit);

        this.add(middlePanel, BorderLayout.NORTH);
        this.add(quantityPanel);

        this.add(buttonPanel);
        this.revalidate();
        this.repaint();
    }

    public int getQuanChosen() {
        return (int) spinnerQuantity.getValue();
    }

    public Furniture.Wood getTypeChosen() {
        return oak.isSelected() ? Furniture.Wood.o : Furniture.Wood.w;
    }

    public JButton submitButton() {
        return submit;
    }

    public int getIdChosen() {
        Object value = idField.getText();
        try {
            idField.setText(null);
            return Integer.parseInt((String) value);

        } catch (NumberFormatException e) {
            Border redBorder = BorderFactory.createLineBorder(Color.RED);
            idField.setBorder(redBorder);
            return -1;
        }
    }

}
