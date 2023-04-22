/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.rofc;

/**
 *
 * @author hariri
 */
public class Tests {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Desk desk1 = new Desk();
        Desk desk2 = new Desk(2, Furniture.Wood.o, 1, 30, 30, 3);

        System.out.println(desk1.itmePrice);
        System.out.println(desk2.itmePrice);
        System.out.println(desk1.getIdNum());
        
    }

}
