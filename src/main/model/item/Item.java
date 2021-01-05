package model.item;

import model.list.ShoppingList;

import java.io.Serializable;


public abstract class Item implements Serializable {

    protected String itemName;

    protected float price;

    protected boolean purchased;

    protected ShoppingList shoppingList;

    public Item(String itemName, float price) {
        this.itemName = itemName;
        this.price = price;
        purchased = false;
    }


    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public String getItemName() {
        return itemName;
    }

    public float getPrice() {
        return price;
    }


    public boolean isPurchased() {
        return purchased;
    }


    public void setShoppingList(ShoppingList shoppingList) {
        if (this.shoppingList != shoppingList) {
            this.shoppingList = shoppingList;
            shoppingList.addItem(this);
        }
    }

    public abstract void setPurchased();

}
