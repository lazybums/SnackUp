package com.lazybums.snackup.item;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lazybums.snackup.R;
import com.lazybums.snackup.VendorActivity;

import java.util.HashMap;

/**
 * Created by amsingha on 7/18/2015.
 */
public class ItemView extends RelativeLayout{
    private TextView mTitleTextView;
    private TextView mQuantityTextView;
    private TextView mPriceTextView;
    private Button mAddToCartButton;
    private Button mMinusButton;
    private Button mPlusButton;
    private Item item;
    private Context context;
    private HashMap<String, Item> cartItems;

    public static ItemView inflate(ViewGroup parent){
        ItemView itemView = (ItemView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);
        return itemView;
    }

    public ItemView(Context c) {
        this(c, null);
    }

    public ItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.item_view_children, this, true);
        this.context = context;
        setupChildren();
    }

    private void setupChildren() {
        mTitleTextView = (TextView) findViewById(R.id.item_titleTextView);
        mQuantityTextView = (TextView) findViewById(R.id.item_quantityTextView);
        mPriceTextView = (TextView) findViewById(R.id.item_priceView);
        mAddToCartButton = (Button) findViewById(R.id.addToCart);
        mMinusButton = (Button) findViewById(R.id.minusButton);
        mPlusButton = (Button) findViewById(R.id.plusButton);
        mTitleTextView.setTextSize(getTextDimension());
        mPriceTextView.setTextSize(getTextDimension());
        mQuantityTextView.setTextSize(getTextDimension());
        mAddToCartButton.setTextSize(getButtonTextDimension());
        mMinusButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer quantity = Integer.parseInt(mQuantityTextView.getText().toString());
                if (quantity <= 0) {
                    //do nothing;
                } else {
                    quantity--;
                    setQuantityOnTextView(quantity, mQuantityTextView, mAddToCartButton);
                }
            }
        });
        mPlusButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer quantity = Integer.parseInt(mQuantityTextView.getText().toString());
                if (quantity < 0) {
                    //do nothing;
                    //should never reach here
                } else {
                    quantity++;
                    setQuantityOnTextView(quantity, mQuantityTextView, mAddToCartButton);
                }
            }
        });
        mAddToCartButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer quantity = Integer.parseInt(mQuantityTextView.getText().toString());
                if(quantity <= 0) {
                    Toast.makeText(context, "0 items selected for " + mTitleTextView.getText(), Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    cartItems.put(item.getTitle(), item);
                }

            }
        });
    }

    private void setQuantityOnTextView(int quantity, TextView quantityTextView, Button addToCartButton) {
        quantityTextView.setText(String.valueOf(quantity));
        quantityTextView.setTextSize(getTextDimension());
        this.item.setQuantity(String.valueOf(quantity));
    }

    private float getTextDimension() {
        return getResources().getDimension(R.dimen.textsize);
    }

    private float getButtonTextDimension() {
        return getResources().getDimension(R.dimen.buttontextsize);
    }

    public void setItem(Item item, HashMap<String, Item> cartItemsMap) {
        mTitleTextView.setText(item.getTitle());
        mQuantityTextView.setText(item.getQuantity());
        mPriceTextView.setText(item.getPrice());
        this.item = item;
        this.cartItems = cartItemsMap;
    }

    public TextView getPriceTextView () {
        return mPriceTextView;
    }

    public TextView getTitleTextView() {
        return mTitleTextView;
    }

    public TextView getQuantityTextView() {
        return mQuantityTextView;
    }

}