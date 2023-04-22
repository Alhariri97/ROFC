/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rofc;

import java.util.UUID;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author hariri
 */
public abstract class Furniture implements Comparable<Furniture> {

//    protected UUID id;
    public enum Wood {
        o,
        w
    }
    protected int id;

    protected double itmePrice;
    protected int quantity;
    protected ImageIcon image;

    Wood typeOfWood;

    public Furniture() {
//        this.id = id = UUID.randomUUID();

        typeOfWood = Wood.o;
        this.quantity = 1;
        this.image = new ImageIcon("default.jpg");

    }

    public Furniture(Wood wod, int qty, int idNum) {
        this.id = idNum;
        this.quantity = qty;
        this.typeOfWood = wod;
        this.image = new ImageIcon("default.jpg");
    }

    public abstract double calculatePrice();

    public int getIdNum() {
        return id;
    }

    public Wood getTypeOfWood() {
        return typeOfWood;
    }

    public int getQuantity() {
        return quantity;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setTypeOfWood(Wood typeOfWood) {
        this.typeOfWood = typeOfWood;
    }

    public void setItmePrice(double itmePrice) {
        this.itmePrice = itmePrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    @Override
    public int compareTo(Furniture other) {
        return Double.compare(this.calculatePrice(), other.calculatePrice());
    }

}
