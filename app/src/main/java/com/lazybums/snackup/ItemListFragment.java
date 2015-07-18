package com.lazybums.snackup;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by amsingha on 7/18/2015.
 */
public class ItemListFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        ArrayList<Item> items = new ArrayList<Item>();
        for (int i = 0; i < 10; i++) {
            String price = String.valueOf(i*10 + 1);
            String title = String.format("Item %d", i);
            Item item = new Item(price, title, "0");
            items.add(item);
        }

        setListAdapter(new ItemAdapter(getActivity(), items));

        return v;
    }
}