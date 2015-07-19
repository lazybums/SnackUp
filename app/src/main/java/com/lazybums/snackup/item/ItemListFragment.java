package com.lazybums.snackup.item;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lazybums.snackup.VendorActivity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by amsingha on 7/18/2015.
 */
public class ItemListFragment extends ListFragment {
    ArrayList<Item> items;
    String ARG_PAGE = "test";
    HashMap<String, Item> cartItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        VendorActivity vendorActivity = (VendorActivity) getActivity();
        cartItems = vendorActivity.getCartItemsMap();
        if(savedInstanceState == null) {
            items = new ArrayList<Item>();
            for (int i = 0; i < 10; i++) {
                String price = String.valueOf(i * 10 + 1);
                String title = String.format("Item %d", i);
                String vendor = "xyz";
                Item item = new Item(price, title, "0", vendor);
                items.add(item);
            }
        }
        else {
            items = savedInstanceState.getParcelableArrayList(ARG_PAGE);
        }
        setListAdapter(new ItemAdapter(getActivity(), items, cartItems));
        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(ARG_PAGE, items);
        super.onSaveInstanceState(outState);
    }
}