package tests;

import model.item.InStoreItem;
import model.item.OnlineItem;
import model.list.ShoppingList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ItemTest {

    private InStoreItem testInStore;
    private OnlineItem testOnline;
    private ShoppingList testSlist;



    @BeforeEach
    public void runBefore() {
        testInStore = new InStoreItem("Eggs", 1.99f, "SaveOnFoods", false);
        testOnline = new OnlineItem("Phone", 199f, "Amazon.com");
        testSlist = new ShoppingList("Test");
    }

    @Test
    public void testGetStoreName() {
        Assertions.assertEquals(testInStore.getStoreName(), "SaveOnFoods");
    }

    @Test
    public void testGetUrl() {
        Assertions.assertEquals(testOnline.geturl(), "Amazon.com");

    }

    @Test
    public void testSetReoccurring() {
        Assertions.assertFalse(testInStore.isReoccurring());
        testInStore.setReoccurring(true);
        Assertions.assertTrue(testInStore.isReoccurring());

    }

    @Test
    public void testSetPurchased() {
        Assertions.assertFalse(testInStore.isPurchased());
        testInStore.setPurchased();
        Assertions.assertTrue(testInStore.isPurchased());
        Assertions.assertFalse(testOnline.isPurchased());
        testOnline.setPurchased();
        Assertions.assertTrue(testOnline.isPurchased());
    }

    @Test
    public void testSetShoppingList() {
        testInStore.setShoppingList(testSlist);
        Assertions.assertEquals(testInStore.getShoppingList(), testSlist);
        Assertions.assertEquals(testSlist.getSlist().size() ,1);
        testInStore.setShoppingList(testSlist);
        Assertions.assertEquals(testInStore.getShoppingList(), testSlist);
        Assertions.assertEquals(testSlist.getSlist().size() ,1);
    }
    @Test
    public void testGetName() {
        Assertions.assertEquals(testInStore.getItemName(), "Eggs");

    }

    @Test
    public void testGetPrice() {
        Assertions.assertEquals(testInStore.getPrice(), 1.99f);
    }
}
