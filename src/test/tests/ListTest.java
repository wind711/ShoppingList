package tests;

import model.item.Item;
import model.item.OnlineItem;
import model.list.ListManager;
import model.list.ShoppingList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ListTest {
    private ListManager testManager;
    private ShoppingList testList;
    private String userName;
    private Item testItem = new OnlineItem("test", 1f, "test.com");

    @BeforeEach
    public void runBefore() {
        userName = "test";
        testManager = new ListManager();
        testList = new ShoppingList("testList");

    }

    @Test
    public void testMakeSlist() {
        ShoppingList list = testManager.makeSList("test");
        Assertions.assertEquals(testManager.getMainList().get("test"), list);
    }

    @Test
    public void testAddOnline() {
        testManager.addOnlineItemToSList("online", 1.99f, "online.com", testList);
        Assertions.assertEquals(testList.getSlist().get(0).getItemName(), "online");
        Assertions.assertEquals(testList.getSlist().get(0).getPrice(), 1.99f);
    }

    @Test
    public void testAddInStore() {
        testManager.addInStoreItemToSList("store", 2.99f, "store", false, testList);
        Assertions.assertEquals(testList.getSlist().get(0).getItemName(), "store");
        Assertions.assertEquals(testList.getSlist().get(0).getPrice(), 2.99f);
    }

    @Test
    public void testSave() {
        testManager.makeSList("saved");
        try {
            testManager.save(userName);
        } catch (IOException e) {
        }
        testManager = new ListManager();
        try {
            testManager.load(userName);
        } catch (IOException | ClassNotFoundException e) {
        }
        Assertions.assertEquals(testManager.getMainList().get("saved").getListName(), "saved");
        testManager.makeSList("loaded");
        testManager.addOnlineItemToSList("online", 1.99f, "online.com", testManager.getMainList().get("loaded"));
        try {
            testManager.save(userName);
        } catch (IOException e) {
        }
    }

    @Test
    public void testLoad() {
        try {
            testManager.load(userName);
        } catch (IOException | ClassNotFoundException e) {
        }
        Assertions.assertTrue(testManager.getMainList().containsKey("loaded"));
        Assertions.assertEquals(testManager.getMainList().get("loaded").getSlist().get(0).getItemName(), "online");
    }

    @Test
    public void testAddItem() {
        testList.addItem(testItem);
        Assertions.assertTrue(testList.getSlist().contains(testItem));
        testList.addItem(testItem);
        Assertions.assertEquals(testList.getSlist().size(), 1);
    }

    @Test
    public void testCalcTotalPrice() {
        testList.addItem(testItem);
        testList.calcTotalPrice();
        Assertions.assertEquals(testList.getTotalPrice(), 1f);
        OnlineItem other = new OnlineItem("test2", 100f, "test2.com");
        other.setPurchased();
        testList.addItem(other);
        testList.calcTotalPrice();
        Assertions.assertEquals(testList.getTotalPrice(), 1f);
    }
}
