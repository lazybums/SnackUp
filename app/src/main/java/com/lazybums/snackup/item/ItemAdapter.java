package com.lazybums.snackup.item;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.HashMap;
import java.util.List;

/**
 * Created by amsingha on 7/18/2015.
 */
public class ItemAdapter extends ArrayAdapter<Item> {
    HashMap<String, Item> cartItemsMap;

    public ItemAdapter(Context c, List<Item> items, HashMap<String, Item> cartItems) {
        super(c, 0, items);
        this.cartItemsMap = cartItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemView itemView = (ItemView)convertView;
        if (null == itemView)
            itemView = ItemView.inflate(parent);
        itemView.setItem(getItem(position), cartItemsMap);
        return itemView;
    }

}
