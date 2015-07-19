package com.lazybums.snackup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lazybums.snackup.constants.Constants;
import com.lazybums.snackup.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Created by Abhishek on 7/18/2015.
 */
public class VendorActivity extends Activity{

    private String mMallName;
    private HashMap<String, Item> cartItemsMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.cartItemsMap = new HashMap<String, Item>();
        setContentView(R.layout.layout_vendor);

        Bundle extras = getIntent().getExtras();
        mMallName = extras.getString("Mall");

        TextView mMallId = (TextView) findViewById(R.id.mallName);
        mMallId.setText(mMallName);

        Button mCheckOutButton = (Button) findViewById(R.id.checkOutButton);
        mMallId.setTextSize(getTextDimension());
        mCheckOutButton.setTextSize(getButtonTextDimension());
        mCheckOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartItemsMap.size() == 0) {
                    Toast.makeText(getApplicationContext(), "Your cart is empty", Toast.LENGTH_LONG).show();
                    return;
                }
                ArrayList<Item> itemsList = convertFromMapToList(cartItemsMap);
                Intent intent = new Intent(VendorActivity.this,
                        ReviewOrderActivity.class);
                intent.putExtra(Constants.itemList, itemsList);
                startActivity(intent);
                finish();
            }
        });
    }

    private ArrayList<Item> convertFromMapToList(HashMap<String, Item> cartItemsMap) {
        ArrayList<Item> items = new ArrayList<Item>();
        for(Entry<String, Item> entry : cartItemsMap.entrySet()) {
            items.add(entry.getValue());
        }
        return items;
    }

    public void setCartItemsMap(HashMap<String, Item> cartItemsMap) {
        this.cartItemsMap = cartItemsMap;
    }

    public HashMap<String, Item> getCartItemsMap() {
        return cartItemsMap;
    }

    private float getTextDimension() {
        return getResources().getDimension(R.dimen.textsize);
    }

    private float getButtonTextDimension() {
        return getResources().getDimension(R.dimen.buttontextsize);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
