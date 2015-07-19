package com.lazybums.snackup.item;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by amsingha on 7/18/2015.
 */
public class Item implements Parcelable {
    private String mPrice;
    private String mTitle;
    private String mQuantity;
    private String mVendor;

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

    public String getmVendor(){return mVendor; }

    public void setmVendor(String vendor){this.mVendor = vendor; }

    public static final Parcelable.Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {

            Item item = new Item();
            item.mVendor = source.readString();
            item.mPrice = source.readString();
            item.mQuantity = source.readString();
            item.mTitle = source.readString();

            return item;
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mVendor);
        dest.writeString(mQuantity);
        dest.writeString(mPrice);
        dest.writeString(mTitle);
    }
}