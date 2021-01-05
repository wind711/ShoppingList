package model.item;


public class OnlineItem extends Item {

    public String url;

    public OnlineItem(String itemName, float price, String url) {
        super(itemName, price);

        this.url = url;
        purchased = false;
    }

    public String geturl() {
        return url;
    }

    @Override
    public void setPurchased() {
        purchased = true;
    }
}


