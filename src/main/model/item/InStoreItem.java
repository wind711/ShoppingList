package model.item;

public class InStoreItem extends Item {
    private String storeName;
    private boolean reoccurring;


    public InStoreItem(String itemName, float price, String storeName, boolean reoccurring) {
        super(itemName, price);

        this.storeName = storeName;
        purchased = false;
        this.reoccurring = reoccurring;
    }

    public String getStoreName() {
        return storeName;
    }

    @Override
    public void setPurchased() {
        purchased = true;
    }

    public boolean isReoccurring() {
        return reoccurring;
    }

    public void setReoccurring(boolean reoccurring) {
        this.reoccurring = reoccurring;
    }
}
