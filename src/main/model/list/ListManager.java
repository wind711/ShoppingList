package model.list;

import model.item.InStoreItem;
import model.item.Item;
import model.item.OnlineItem;

import java.io.*;
import java.util.HashMap;

public class ListManager implements Savable, Loadable, Serializable {

    private HashMap<String, ShoppingList> mainList;


    public ListManager() {
        mainList = new HashMap<>();
    }

    public HashMap<String, ShoppingList> getMainList() {
        return mainList;
    }

    public ShoppingList makeSList(String slistName) {
        ShoppingList shoppingList = new ShoppingList(slistName);
        mainList.put(slistName, shoppingList);
        return shoppingList;
    }

    public void addOnlineItemToSList(String itemName, float price, String url, ShoppingList slist) {
        Item item = new OnlineItem(itemName, price, url);
        slist.addItem(item);
    }

    public void addInStoreItemToSList(String itemName, float price, String storeName, boolean re, ShoppingList slist) {
        Item item = new InStoreItem(itemName, price, storeName, re);
        slist.addItem(item);
    }

    @Override
    public void load(String userName) throws IOException, ClassNotFoundException {
        String fileName = userName + ".bin";
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
        mainList = (HashMap<String, ShoppingList>) is.readObject();
    }

    @Override
    public void save(String userName) throws IOException {
        String fileName = userName + ".bin";
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
        os.writeObject(mainList);
        os.close();
    }
}

