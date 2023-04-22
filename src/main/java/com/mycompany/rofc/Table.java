package com.mycompany.rofc;

import com.mycompany.rofc.Furniture;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author hariri
 */
public class Table extends Furniture {

    private boolean isCircular;
    private boolean isChrome;
    private int diameter;

    public Table() {
        this.isCircular = false;
        this.isChrome = false;
        this.diameter = 50;
        this.itmePrice = calculatePrice();

    }

    public Table(int idNum, boolean isCircular, boolean isChrome, int diameter, Wood wod, int qty) {
        super( wod, qty, idNum);
        this.isCircular = isCircular;
        this.isChrome = isChrome;
        this.diameter = diameter;
        this.itmePrice = calculatePrice();
    }

    @Override
    public double calculatePrice() {

        double price;
        double pricePerUnit = typeOfWood == Furniture.Wood.o ? 0.05 : 0.03;
        double priceOfBase = isChrome == true ? 50.00 : 40.00;
        price = ((this.diameter * this.diameter) * pricePerUnit) + priceOfBase;

        return price;
    }

    
    @Override
    public String toString(){
    
        return "id: " + this.id + ", type Of wood: " + this.typeOfWood + ", itmePrice: "+ itmePrice + " quanitiy: " + this.quantity +", circular:" + isCircular+ ", chrome "+ isChrome + ", diameter "+ diameter ;
    }
    
    public double getItmePrice() {
        this.itmePrice = calculatePrice();
        return itmePrice;
    }

    public void setIsCircular(boolean isCircular) {
        this.isCircular = isCircular;
    }

    public void setIsChrome(boolean isChrome) {
        this.isChrome = isChrome;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public boolean isIsCircular() {
        return isCircular;
    }

    public boolean isIsChrome() {
        return isChrome;
    }

    public int getDiameter() {
        return diameter;
    }

}
