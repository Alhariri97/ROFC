package GUI.BakeEnd;

import java.util.ArrayList;
import com.mycompany.rofc.Furniture;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author hariri
 */
public class Basket {

    ArrayList<Furniture> items = new ArrayList<>();

    public void addItem(Furniture e) {
        if (items.size() < 9) {
            items.add(e);
            System.out.println("Add one item to the basket");
        } else {
            System.out.println("the basket is full");
        }
    }

    public void deleteItem(int id) {

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getIdNum() == id) {
                items.remove(i);
                break;
            }
        }
    }

    public Furniture getItem(int id) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getIdNum() == id) {
                return items.get(i);

            }
        }
        return null;
    }

        public ArrayList<Furniture> getAll() {
        return items;
    }

    public void clearAll() {
        items.clear();
    }

    public void seeAll() {

        for (Furniture item : items) {
            System.out.println(item.toString());

        }

    }

    public int getSize() {
        return items.size();
    }

    public boolean isItemExist(int idNum) {
        for (Furniture item : items) {
            if (item.getIdNum() == idNum) {
                return true;
            }
        }
        return false;
    }

}
