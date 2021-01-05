package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.item.*;
import model.list.ListManager;
import model.list.ShoppingList;

import java.io.IOException;
import java.util.Map;


public class MenuController {

    @FXML
    public MenuItem save;

    @FXML
    public MenuItem reload;

    @FXML
    public Button createShoppingList;

    @FXML
    public Button addOnlineItem;

    @FXML
    public Button addRegularItem;

    @FXML
    public Button trueReoccurring;

    @FXML
    public Button falseReoccurring;

    @FXML
    public Button deleteShoppingList;

    @FXML
    public TextField listName;

    @FXML
    public TextField onlineItemUrl;

    @FXML
    public TextField inStoreItemName;

    @FXML
    public TextField inStoreItemPrice;

    @FXML
    public TextField inStoreItemStore;

    @FXML
    public ListView itemListView = new ListView();

    @FXML
    public ListView shoppingListView = new ListView();


    private ShoppingList slist;
    private Boolean reoccurring = false;
    public ObservableList observableList = FXCollections.observableArrayList();
    public ObservableList observableItemList = FXCollections.observableArrayList();


    @FXML
    public void shoppingListMouseClicked() {
        shoppingListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                observableItemList.clear();
                final int selected = observableList.indexOf(shoppingListView.getSelectionModel().getSelectedItem());
                if (selected != -1) {
                    String key = (String) shoppingListView.getSelectionModel().getSelectedItem();
                    slist = StartList.listManager.getMainList().get(key);
                    for (Item i: StartList.listManager.getMainList().get(key).getSlist()) {
                        if (!observableItemList.contains(i)) {
                            observableItemList.add(i.getItemName());
                            itemListView.setItems(observableItemList);
                        }
                    }
                    listMouseClickTester(key);
                }
            }
        });
    }

    private void listMouseClickTester(String key) {
        System.out.println("clicked on " + shoppingListView.getSelectionModel().getSelectedItem());
        StartList.listManager.getMainList().get(key).calcTotalPrice();
        ShoppingList list = StartList.listManager.getMainList().get(key);
        System.out.println("Total Price $CDN: " + StartList.listManager.getMainList().get(key).getTotalPrice());
        System.out.println("These are the items in: " + list.getListName());
        for (int i = 0; i < list.getSlist().size(); i++) {
            String name = list.getSlist().get(i).getItemName();
            float price = list.getSlist().get(i).getPrice();
            if (list.getSlist().get(i) instanceof OnlineItem) {
                String url = ((OnlineItem) list.getSlist().get(i)).geturl();
                System.out.println(i + 1 + ". " + name + "\n\t" + "CDN$ " + price + "\n\t" + url + "\n");
            } else if (list.getSlist().get(i) instanceof InStoreItem) {
                String storeName = ((OnlineItem) list.getSlist().get(i)).geturl();
                System.out.println(i + 1 + ". " + name + "\n\t" + "CDN$ " + price + "\n\t" + storeName + "\n");
            }
        }
        list.calcTotalPrice();
        System.out.println("The total price of this shopping list is: " + "CDN$ " + list.getTotalPrice());
    }


    @FXML
    public void shoppingListMouseDeleteClicked() {
        deleteShoppingList.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final int selected = observableList.indexOf(shoppingListView.getSelectionModel().getSelectedItem());
                if (selected != -1) {
                    Object object = shoppingListView.getSelectionModel().getSelectedItem();
                    String key = (String) observableList.get(observableList.indexOf(object));
                    observableList.remove(observableList.indexOf(object));
                    StartList.listManager.getMainList().remove(key);
                    System.out.println("Deleted " + key);
                    System.out.println("Index " + observableList.indexOf(object));
                }
            }
        });
    }

    @FXML
    public void shoppingItemMouseClicked() {
        itemListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                final int selected = observableItemList.indexOf(itemListView.getSelectionModel().getSelectedItem());
                if (selected != -1) {
                    int index = observableItemList.indexOf(itemListView.getSelectionModel().getSelectedItem());
                    Item item = slist.getSlist().get(index);
                    System.out.println(index);
                    System.out.println(item.getItemName());
                    System.out.println(item.getPrice());
                    System.out.println(item.getShoppingList().getListName());
                }
            }
        });
    }


    @FXML
    public void createShoppingListClicked() {
        slist = StartList.listManager.makeSList(listName.getText());
        if (!observableList.contains(listName.getText())) {
            observableList.add(listName.getText());
            shoppingListView.setItems(observableList);
            System.out.println(listName.getText() + " created");
        } else {
            System.out.println(listName.getText() + " overwritten");
        }
    }

    @FXML
    public void addOnlineItemClicked() {
        String itemName;
        float itemPrice;
        String url = onlineItemUrl.getText();
        try {
            itemName = ScrapeName.amazonName(url);
        } catch (Exception e) {
            itemName = "Name Not Found";
        }
        try {
            itemPrice = ScrapePrice.amazonPrice(url);
        } catch (Exception e) {
            itemPrice = 0.0f;
        }
        StartList.listManager.addOnlineItemToSList(itemName, itemPrice, url, slist);
        System.out.println(itemName + " " + itemPrice + " " + url);
        onlineItemUrl.clear();
    }

    @FXML
    public void true_reoccurringClicked() {
        reoccurring = true;
    }

    @FXML
    public void false_reoccurringClicked() {
        reoccurring = false;
    }

    @FXML
    public void addInStoreItemClicked() {
        String itemName = inStoreItemName.getText();
        float itemPrice;
        String storeName = inStoreItemStore.getText();
        try {
            itemPrice = Float.parseFloat(inStoreItemPrice.getText());
            StartList.listManager.addInStoreItemToSList(itemName, itemPrice, storeName, reoccurring, slist);
            System.out.println(itemName + " " + itemPrice + " " + storeName + " " + " " + reoccurring);
            reoccurring = false;
            inStoreItemName.clear();
            inStoreItemPrice.clear();
            inStoreItemStore.clear();
        } catch (Exception e) {
            inStoreItemPrice.clear();
            System.out.println("Can't be parsed");
        }
    }

    @FXML
    public void saveClicked() throws IOException {
        StartList.listManager.save(LoginController.usernameString);
        System.out.println(LoginController.usernameString + " saved");
    }

    @FXML
    public void reloadClicked() {
        try {
            StartList.listManager = new ListManager();
            StartList.listManager.load(LoginController.usernameString);
            slist = StartList.listManager.makeSList(listName.getText());

            for (Map.Entry<String,ShoppingList> entry : StartList.listManager.getMainList().entrySet()) {
                if (!observableList.contains(entry.getKey())) {
                    observableList.add(entry.getKey());
                    shoppingListView.setItems(observableList);
                }
            }
            if (observableList.indexOf("") == 0) {
                observableList.remove(0);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Can't be reloaded");
        }
    }
}


