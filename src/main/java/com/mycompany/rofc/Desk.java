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
public class Desk extends Furniture {

    private int width;
    private int depth;
    private int height = 85;
    private int numOfDrawers;

    public Desk() {
        super();
        this.depth = 50;
        this.width = 50;

        this.numOfDrawers = 2;
        this.itmePrice = calculatePrice();

    }

    public Desk(int idNum, Wood wod, int qty, int depth, int width, int numOfDrawers) {
        super( wod, qty, idNum);
        this.depth = depth;
        this.width = width;
        this.numOfDrawers = numOfDrawers;
        this.itmePrice = calculatePrice();

    }

    @Override
    public double calculatePrice() {
        double pricePerUnit = typeOfWood == Furniture.Wood.o ? 0.05 : 0.03;
        double price = ((height + width + depth) * 14) + (double) ((depth * width) * pricePerUnit) + ((double) (numOfDrawers * 7.25));
        return price;
    }

    @Override
    public String toString(){
    
        return "id: " + this.id + ", type Of wood: " + this.typeOfWood + ", itmePrice: "+ itmePrice + " quanitiy: " + 
                this.quantity +", width:" + width+ ", depth "+ depth + ", height "+ height+ ", numOfDrawers" + numOfDrawers ;
    }
    
    
    public int getWidth() {
        return width;
    }

    public int getDepth() {
        return depth;
    }

    public int getHeight() {
        return height;
    }

    public int getNumOfDrawers() {
        return numOfDrawers;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setNumOfDrawers(int numOfDrawers) {
        this.numOfDrawers = numOfDrawers;
    }

    public double getItmePrice() {

        this.itmePrice = calculatePrice();
        return itmePrice;
    }
}
