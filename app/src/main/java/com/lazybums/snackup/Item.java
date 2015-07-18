package com.lazybums.snackup;

/**
 * Created by amsingha on 7/18/2015.
 */
public class Item {
    private String mPrice;
    private String mTitle;
    private String mQuantity;

    public Item(String price, String title, String quantity) {
        super();
        mPrice = price;
        mTitle = title;
        mQuantity = quantity;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getQuantity() {
        return mQuantity;
    }

    public void setQuantity(String quantity) {
        mQuantity = quantity;
    }
}