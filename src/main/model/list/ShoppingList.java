package model.list;

import model.item.Item;

import java.io.Serializable;
import java.util.ArrayList;

public class ShoppingList implements Serializable {
    private String slistName;
    private ArrayList<Item> slist;
    private float totalPrice;


    public ShoppingList(String slistName) {
        this.slistName = slistName;
        this.slist = new ArrayList<>();
        this.totalPrice = 0;
    }

    public void addItem(Item item) {
        if (!slist.contains(item)) {
            slist.add(item);
            item.setShoppingList(this);
        }
    }

    public void calcTotalPrice() {
        float newTotalPrice = 0;
        for (Item item: slist) {
            if (!item.isPurchased()) {
                newTotalPrice += item.getPrice();
            }
            setTotalPrice(newTotalPrice);
        }
    }

    public String getListName() {
        return slistName;
    }

    public ArrayList<Item> getSlist() {
        return slist;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float newTotalPrice) {
        this.totalPrice = newTotalPrice;
    }
}
