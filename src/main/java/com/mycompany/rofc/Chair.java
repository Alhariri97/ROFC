/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rofc;

import javax.swing.ImageIcon;

/**
 *
 * @author hariri
 */
public class Chair extends Furniture {

    private boolean armRests;

    public Chair() {
        super();
        this.armRests = false;
        this.image = new ImageIcon("chair.jpg");
        this.itmePrice = calculatePrice();

    }

    public Chair(int idNum, Wood wood, int qty, boolean arm) {
        super(wood, qty, idNum);
        this.armRests = arm;
        this.image = new ImageIcon("chair.jpg");
        this.itmePrice = calculatePrice();

    }

    @Override
    public double calculatePrice() {

        int units = this.armRests ? 1500 + 250 : 1500;
        double price = this.typeOfWood == Furniture.Wood.o ? units * 0.05 : units * 0.03;
        return price;
    }

    @Override
    public String toString() {

        return "id: " + this.id + ", type Of wood: " + this.typeOfWood + ", itmePrice: " + itmePrice + " quanitiy: " + this.quantity + ", armRests:" + armRests;
    }

    public double getItmePrice() {
        this.itmePrice = calculatePrice();
        return itmePrice;
    }

    public void setArmRests(boolean armRests) {
        this.armRests = armRests;
    }

    public boolean isArmRests() {
        return armRests;
    }

}
