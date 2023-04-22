package GUI.BakeEnd;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;
import com.mycompany.rofc.Furniture;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mycompany.rofc.Chair;
import com.mycompany.rofc.Desk;
import com.mycompany.rofc.Table;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
public class OrderForm extends JDialog {

    private final JLabel cardHolderNameLabel;
    private final JTextField cardHolderNameField;
    private final JLabel cardNumberLabel;
    private final JLabel expirationDateLabel;
    private final JPanel expirationDatePanel;
    private final JComboBox<String> monthComboBox;
    private final JComboBox<Integer> yearComboBox;
    private final JLabel cvvLabel;
    private final JTextField cvvField;
    private final JButton submitButton;
    private final JButton cancelButton;

    Basket basket;
    JFormattedTextField cardNumberField;

    public OrderForm(Basket basket) throws ParseException {
        super();
        this.basket = basket;
        setLocationRelativeTo(getParent());

        double totalPrice = 0.0;
        for (Furniture item : basket.getAll()) {
            totalPrice += item.calculatePrice();
        }
        this.setTitle("Payment of: " + totalPrice);

        cardHolderNameLabel = new JLabel("Cardholder Name:");
        cardHolderNameField = new JTextField();
        Border defaultBorder = cardHolderNameField.getBorder();
        cardHolderNameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                cardHolderNameField.setBorder(defaultBorder);
            }
        });
        cardNumberLabel = new JLabel("Card Number:");

        MaskFormatter formatter = new MaskFormatter("###-###-###-###");
        cardNumberField = new JFormattedTextField(formatter);
        cardNumberField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                cardNumberField.setBorder(defaultBorder);
            }
        });
        expirationDateLabel = new JLabel("Expiration Date:");
        monthComboBox = new JComboBox<>(new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"});
        yearComboBox = new JComboBox<>(new Integer[]{2024, 2025, 2026, 2027});
        cvvLabel = new JLabel("CVV:");
        MaskFormatter formatterCcv = new MaskFormatter("###");
        cvvField = new JFormattedTextField(formatterCcv);
        cvvField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                cvvField.setBorder(defaultBorder);
            }
        });
        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");

        expirationDatePanel = new JPanel(new GridLayout(1, 2));
        expirationDatePanel.add(monthComboBox);
        expirationDatePanel.add(yearComboBox);

        JPanel mainPanel = new JPanel(new GridLayout(4, 2));
        mainPanel.add(cardHolderNameLabel);
        mainPanel.add(cardHolderNameField);
        mainPanel.add(cardNumberLabel);
        mainPanel.add(cardNumberField);
        mainPanel.add(expirationDateLabel);
        mainPanel.add(expirationDatePanel);

        mainPanel.add(cvvLabel);
        mainPanel.add(cvvField);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);

        Container contentPane = getContentPane();
        contentPane.add(mainPanel, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        submitButton.addActionListener(e -> {
            System.out.println("submit");
            try {
                submitMethod();
            } catch (IOException ex) {
                Logger.getLogger(OrderForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        cancelButton.addActionListener(e -> dispose());
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    // Getter methods for accessing field values
    private String getCardHolderName() {
        return cardHolderNameField.getText();
    }

    private String getCardNumber() {
        return cardNumberField.getText();
    }

    private String getCvv() {
        return cvvField.getText();
    }

    private String getExpireDate() {
        return yearComboBox.getSelectedItem() + " " + monthComboBox.getSelectedItem();
    }

    // Verfing methods
    private boolean isValidName() {
        String input = getCardHolderName().trim();
        Pattern pattern = Pattern.compile("^\\p{L}+(['-]\\p{L}+)*\\.?(\\s\\p{L}+(['-]\\p{L}+)*\\.?)+$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return true;
        }
        System.out.println("Invalid name format");
        cardHolderNameField.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        return false;
    }

    private boolean isValidCarNum() {
        String input = getCardNumber().replaceAll("-", "").replaceAll(" ", "");

        if (input.length() == 12) {
            return true;
        } else {
            cardNumberField.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            return false;

        }
    }

    private boolean isValidCCV() {
        String input = getCvv().replaceAll(" ", "");
        if (input.length() == 3) {
            return true;
        } else {

            cvvField.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            cvvField.setOpaque(true);
            return false;
        }
    }

    private boolean AreAllValid() {
        boolean AreAllValid = (isValidCarNum() & isValidName() & isValidCCV());
        return AreAllValid;
    }

//    Writers
    private String writeChair(Chair chair) {
        String isItArm = chair.isArmRests() ? "Yes" : "No";
        return ", ArmSetes: " + isItArm;
    }

    private String writeDesk(Desk desk) {
        String deskString = ",  Number of Draws: " + desk.getNumOfDrawers() + ", Width: " + desk.getWidth() + ", Depth: " + desk.getDepth();
        return deskString;
    }

    private String writeTable(Table table) {

        String tableString = ", Diameter: " + table.getDiameter() + ", Base type: " + (table.isIsChrome() ? "Chrom" : "Wood");
        return tableString;
    }
//    

    private void submitMethod() throws IOException {

        if (AreAllValid()) {
            System.out.println(cvvField.getText());
            exportToPdf(basket.getAll());
        }
    }

    private void exportToPdf(ArrayList<Furniture> items) {
        System.out.println("Function Starts");

        File folder = new File("receipts");

        if (!folder.exists()) {
            boolean result = folder.mkdir();
            if (result) {
                System.out.println("Folder created successfully.");
            } else {
                System.out.println("Failed to create folder.");
            }
        }

        try {

            LocalDateTime now = LocalDateTime.now(); //get the time now
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"); //fromat 
            String formattedDate = now.format(formatter);

            try (PDDocument document = new PDDocument() // open a new pdf document.
                    ) {
                PDImageXObject logoImage = PDImageXObject.createFromFile("image/logo-black.jpeg", document); // get image.
                PDPage page = new PDPage(PDRectangle.A4); //set the page width and height
                document.addPage(page); // add the page to the document
                try ( // create a new content stream for the page
                        PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    float logoWidth = logoImage.getWidth();
                    float logoHeight = logoImage.getHeight();
                    float scale = 0.12f; // change the photo size by
                    contentStream.drawImage(logoImage, 20, (page.getTrimBox().getHeight() - 100), logoWidth * scale, logoHeight * scale);
                    contentStream.setFont(PDType1Font.SYMBOL, 12);

                    addLine(contentStream, "ROFC Receipt", 240, 30, 18); // Title

                    float xs = page.getTrimBox().getWidth() - (float) 100; //
                    addLine(contentStream, "Time order was placed", xs - 40, 50, 10);

                    addLine(contentStream, formattedDate, xs - 19, 70, 10);

                    addLine(contentStream, "Card Details used for this order.", 10, 100, 10);

                    String nameOncard = "Card Holder Name: " + getCardHolderName();
                    addLine(contentStream, nameOncard, 10, 120, 10);
                    String cardNumber = "Card Number: " + getCardNumber();
                    addLine(contentStream, cardNumber, 10, 140, 10);
                    String cardCvv = "Card CVV: " + getCvv();
                    addLine(contentStream, cardCvv, 10, 160, 10);
                    String cardDate = "Expier date: " + getExpireDate();
                    addLine(contentStream, cardDate, 10, 180, 10);
                    addLine(contentStream, "Items ordered: ", 10, 200, 10);

                    double total = 0.0;
                    int ys = 250;
                    for (Furniture item : items) {
                        total += item.calculatePrice();
                        String itemDet = "";
                        //

                        switch (item.getClass().getSimpleName()) {
                            case "Table" -> {
                                Table table = (Table) item;
                                itemDet = writeTable(table);
                            }
                            case "Chair" -> {
                                Chair chair = (Chair) item;
                                itemDet = writeChair(chair);
                            }
                            case "Desk" -> {
                                Desk desk = (Desk) item;
                                itemDet = writeDesk(desk);
                            }
                        }

                        String sharebleAtributes = item.getClass().getSimpleName() + ", Item ID: " + item.getIdNum() + ", Type of wood: "
                                + (item.getTypeOfWood() == Furniture.Wood.w ? "Walnut" : "Oak")
                                + itemDet;

                        total += item.calculatePrice();
                        String price = "Price: " + item.calculatePrice();
                        addLine(contentStream, sharebleAtributes, 15, ys, 8);
                        addLine(contentStream, price, xs, ys, 8);
                        ys += 20;

                    }

                    addLine(contentStream, "-".repeat(30), xs, ys, 8);
                    addLine(contentStream, "Total: " + total + "£", xs, ys + 20, 8);
                }
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyyMMddHHmmss"); //fromat
                String formattedDate2 = now.format(formatter2);

                document.save("receipts/ROFC_" + formattedDate2 + ".pdf");
            } // get image. // get image.
            this.dispose();
            System.out.println("Pdf has written correctly");
        } catch (IOException e) {
            System.out.println("Error");
        }

    }

    private void addLine(PDPageContentStream contentStream, String text, float xs, int down, int font) throws IOException {

        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, font);
        contentStream.newLineAtOffset(xs, 841 - down);
        contentStream.showText(text);
        contentStream.endText();

    }

}
