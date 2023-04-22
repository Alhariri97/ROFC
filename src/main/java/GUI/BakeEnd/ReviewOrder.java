/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.BakeEnd;

import GUI.MyPanel;
import GUI.PhotoPanels.ChairPanelPhoto;
import GUI.PhotoPanels.DeskPanelPhoto;
import GUI.PhotoPanels.TablePanelPhoto;
import com.mycompany.rofc.Chair;
import com.mycompany.rofc.Desk;
import com.mycompany.rofc.Furniture;
import com.mycompany.rofc.Furniture.Wood;
import com.mycompany.rofc.Table;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 *
 * @author hariri
 */
public class ReviewOrder extends MyPanel {

    private final MyPanel left;
    private final MyPanel center;

    private String fileName;
    private JButton review;
    private final JButton choosePc = new JButton("Choose from PC");
    private final JButton chooseWrite = new JButton("Write file name");
    private final JButton chooseRecet = new JButton("Choose from recent recet");

    private final JPanel mainPanel = new JPanel();
    private final JPanel container = new JPanel();
    private final JPanel inCon = new JPanel();
    private final Basket basket = new Basket();

    public ReviewOrder(MyPanel left, MyPanel center) {
        this.left = left;
        this.center = center;
        addOptions();

    }

    private void addOptions() {

        String currentPath = System.getProperty("user.dir");
        System.out.println("Current Path: " + currentPath);

        mainPanel.setPreferredSize(new Dimension(280, 200));

        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        mainPanel.add(chooseRecet);
        mainPanel.add(choosePc);
        mainPanel.add(chooseWrite);
        mainPanel.add(container);
        review = new JButton("Review");

        receiptsAction();
        pcAction();
        writeAction();
        review.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reviewAction();
            }
        });

        left.add(mainPanel);
        left.repaint();
        left.revalidate();
    }

    private void receiptsAction() {

        chooseRecet.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                container.removeAll();
                container.repaint();
                inCon.removeAll();
                inCon.repaint();
                mainPanel.remove(review);
                mainPanel.repaint();
                File folder = new File("receipts/");
                File[] listOfFiles = folder.listFiles();
                if (folder.exists() && listOfFiles.length > 0) {

                    JComboBox monthComboBox = new JComboBox<>(new String[]{"Recent receipts"});
                    for (File file : listOfFiles) {
                        String name = file.getName();
                        if (file.isFile()) {
                            monthComboBox.addItem(name);
                            System.out.println(name);
                        }
                    }
                    monthComboBox.add(new JLabel("Chose a file"));
                    container.add(monthComboBox);

                    mainPanel.add(inCon);

                    container.repaint();
                    container.revalidate();

                    monthComboBox.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            container.removeAll();
                            if (monthComboBox.getSelectedIndex() != 0) {
                                inCon.removeAll();
                                String selectedItema = (String) monthComboBox.getSelectedItem();
                                JLabel fileLabel = new JLabel("" + selectedItema);
                                fileLabel.setForeground(Color.GREEN);
                                inCon.add(fileLabel);
                                String currentPath = System.getProperty("user.dir");

                                Path path = Paths.get(currentPath + "/receipts/" + selectedItema);
                                fileName = currentPath + "/receipts/" + selectedItema;
                                System.out.println(path);
                                if (validReceipt(path)) {
                                    System.out.println("valid is all");
                                    mainPanel.add(review);
                                }
//                                mainPanel.add(review);
                                inCon.repaint();
                                inCon.revalidate();
                            }
                        }
                    });

                }

            }
        });
    }

    private void pcAction() {
        choosePc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                container.removeAll();
                container.repaint();
                inCon.removeAll();
                inCon.repaint();
                mainPanel.remove(review);
                mainPanel.repaint();

                System.out.println("Chhose pc");
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println(selectedFile + " <><<<<<<<");
                    inCon.removeAll();
                    fileName = selectedFile + "";
                    Path path = Paths.get(fileName);
                    System.out.println(path + " this is path ");
                    if (validReceipt(path)) {
                        System.out.println(fileName);
                        JLabel fileLabel = new JLabel(fileName);
                        fileLabel.setForeground(Color.GREEN);
                        inCon.add(fileLabel);
                        mainPanel.add(inCon);
                        mainPanel.add(review);
                    }

                    inCon.repaint();
                    inCon.revalidate();
                    container.repaint();
                    container.revalidate();

                }

            }
        });
    }

    private void writeAction() {

        chooseWrite.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                container.removeAll();
                container.repaint();
                inCon.removeAll();
                inCon.repaint();
                mainPanel.remove(review);
                mainPanel.repaint();

                JTextField textField = new JTextField();
                textField.setPreferredSize(new Dimension(180, 20));

                JButton find = new JButton("Find");

                container.add(textField);
                container.add(find);
                container.repaint();
                container.revalidate();
                mainPanel.repaint();
                mainPanel.revalidate();
                find.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        inCon.removeAll();
                        mainPanel.remove(review);
                        mainPanel.repaint();
                        boolean fileFound = false;
                        System.out.println(textField.getText());
                        String nameGiven = textField.getText();

                        String currentPath = System.getProperty("user.dir");
                        File directory = new File(currentPath + "/receipts/"); // Replace with your directory path
                        File[] files = directory.listFiles();
                        System.out.println("hii");
                        if (files != null) {
                            for (File file : files) {
                                System.out.println(file.getName());
                                if (file.getName().equals(nameGiven)) {
                                    System.out.println("Strart here the if ");
                                    fileFound = true;
                                    System.out.println("Here after file found = true");
                                    inCon.removeAll();
                                    System.out.println("Here remove all");

                                    JLabel fileLabel = new JLabel(nameGiven);
                                    System.out.println("fuck here j");
                                    Path path = Paths.get(currentPath + "/receipts/" + nameGiven);
                                    fileName = currentPath + "/receipts/" + nameGiven;
                                    System.out.println(" after path");
                                    if (validReceipt(path)) {
                                        mainPanel.add(review);
                                        fileLabel.setForeground(Color.GREEN);
                                        inCon.add(fileLabel);
                                        mainPanel.add(inCon);
                                        fileName = nameGiven + " ";
                                        System.out.println(fileName);
                                        mainPanel.add(review);
                                        inCon.repaint();
                                        inCon.revalidate();
                                        container.repaint();
                                        container.revalidate();
                                    }
                                }
                            }
                        }
                        if (!fileFound) {
                            JLabel notFound = new JLabel("No such file");
                            notFound.setForeground(Color.red);
                            inCon.add(notFound);
                            mainPanel.add(inCon);
                            inCon.repaint();
                            inCon.revalidate();
                            container.repaint();
                            container.revalidate();
                        }
                    }
                });

            }
        });

    }

    private boolean validReceipt(Path path) {

//        Path path = Paths.get("path/to/your/file.pdf");
        try (InputStream inputStream = new FileInputStream(fileName)) {

            String contentType = Files.probeContentType(path);

            byte[] header = new byte[4];
            inputStream.read(header, 0, 4);
            String fileHeader = new String(header, StandardCharsets.US_ASCII);
            if (fileHeader.equals("%PDF") && contentType != null && contentType.equals("application/pdf")) {
                // The file is a PDF file
                System.out.println("file is a pdf from the new function");
                System.out.println("The file is a PDF.");
                return true;
            } else {
//                System.out.println("The file is not a PDF.");
                JLabel notFound = new JLabel("The file is not a PDF");
                notFound.setForeground(Color.red);
                inCon.add(notFound);
                mainPanel.add(inCon);
                return false;
            }

        } catch (IOException e) {
            System.err.println("An error occurred while checking the file type: " + e.getMessage());
        }
        System.out.println("valid Receipt ");
        return false;
    }

    private void reviewAction() {
        try {
            File file = new File(fileName);
            PDDocument document = PDDocument.load(file);

            // Get the total number of pages
            // Extract text from each page
            PDFTextStripper stripper = new PDFTextStripper();
            String content = stripper.getText(document);
            System.out.println(" { \n" + content + "\n }");
            // Extract the card details using regular expressions
            Pattern cardPattern = Pattern.compile("Card Holder Name: (.+)\nCard Number: (.+)\nCard CVV: (.+)\nExpier date: (.+)");
            Matcher cardMatcher = cardPattern.matcher(content);

            if (cardMatcher.find()) {
                String cardHolderName = cardMatcher.group(1);
                String cardNumber = cardMatcher.group(2);
                String cardCVV = cardMatcher.group(3);
                String cardExpDate = cardMatcher.group(4);
                System.out.println("Card Holder Name: " + cardHolderName);
                System.out.println("Card Number: " + cardNumber);
                System.out.println("Card CVV: " + cardCVV);
                System.out.println("Expier date: " + cardExpDate);
                left.removeAll();
                JPanel leftContainer = new JPanel();
                leftContainer.add((new JLabel("Oreder card details: ")));
                leftContainer.setPreferredSize(new Dimension(280, 200));
                JLabel nameLabel = new JLabel("Card Holder Name: " + cardHolderName);
                JPanel name = new JPanel();
                name.add(nameLabel);
                leftContainer.add(name);

                JLabel numberLabel = new JLabel("Card Number: " + cardNumber);
                JPanel number = new JPanel();
                number.add(numberLabel);
                leftContainer.add(number);

                JLabel cvvLabel = new JLabel("Card CVV: " + cardCVV);
                JPanel cvv = new JPanel();
                cvv.add(cvvLabel);
                leftContainer.add(cvv);

                JLabel expierLabel = new JLabel("Expier date: " + cardExpDate);
                JPanel expier = new JPanel();
                expier.add(expierLabel);
                leftContainer.add(expier);

                JButton close = new JButton("Close review");

                leftContainer.add(close);
                left.add(leftContainer);
                left.repaint();
                left.revalidate();

                close.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Close");
                        left.removeAll();
                        center.removeAll();
                        left.repaint();
                        center.repaint();
                    }
                }
                );

            }

            Pattern itemPattern = Pattern.compile("(Chair|Table|Desk), Item ID: (\\d+), Type of wood: (\\w+),(.+?)Price: (\\d+\\.\\d+)");
            Matcher itemMatcher = itemPattern.matcher(content);
            while (itemMatcher.find()) {

                String itemType = itemMatcher.group(1);

                String itemID = itemMatcher.group(2);
                int itemId = Integer.parseInt(itemID);

                String woodType = itemMatcher.group(3);
                Wood typeOfWood = (woodType.equals("Oak") ? Furniture.Wood.o : Furniture.Wood.w);

                String otherDetails = itemMatcher.group(4);
                String input = otherDetails.trim();

                String itemPrice = itemMatcher.group(5);

                if (itemType.equals("Chair")) {
                    System.out.println("add Chair");
                    String[] parts = input.split(":");
                    String answer = parts[1].trim();

                    Chair chair = new Chair(itemId, typeOfWood, 1, answer.equals("Yes") ? true : false);
                    ChairPanelPhoto chairPhoto = new ChairPanelPhoto(chair, basket, left);
                    center.add(chairPhoto);
                } else if (itemType.equals("Desk")) {
                    System.out.println("add Desk");
                    String[] parts = input.split(",");
                    String drawsString = parts[0].split(":")[1].trim();
                    String widthString = parts[1].split(":")[1].trim();
                    String depthString = parts[2].split(":")[1].trim();
                    int draws = Integer.parseInt(drawsString);
                    int width = Integer.parseInt(widthString);
                    int depth = Integer.parseInt(depthString);

                    Desk desk = new Desk(itemId, typeOfWood, 1, depth, width, draws);

                    DeskPanelPhoto a = new DeskPanelPhoto(desk, basket, left);
                    center.add(a);

                } else if (itemType.equals("Table")) {
                    System.out.println("add Table");

                    String[] parts = input.split(",");
                    String diameterString = parts[0].split(":")[1].trim();
                    String isCromeString = parts[1].split(":")[1].trim();

                    int diameter = Integer.parseInt(diameterString);
                    boolean isChrom = isCromeString.equals("Chrom") ? true : false;

                    Table table = new Table(itemId, true, isChrom, diameter, typeOfWood, 1);
                    TablePanelPhoto tablePhoto = new TablePanelPhoto(table, basket, left);
                    center.add(tablePhoto);
                }

            }
            // Close the PDF file
            document.close();
        } catch (IOException ex) {
            Logger.getLogger(ReviewOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
