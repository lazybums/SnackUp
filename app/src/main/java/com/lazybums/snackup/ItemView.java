package com.lazybums.snackup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by amsingha on 7/18/2015.
 */
public class ItemView extends RelativeLayout {
    private TextView mTitleTextView;
    private TextView mQuantityTextView;
    private TextView mPriceTextView;
    private Button mAddToCartButton;

    public static ItemView inflate(ViewGroup parent) {
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
        setupChildren();
    }

    private void setupChildren() {
        mTitleTextView = (TextView) findViewById(R.id.item_titleTextView);
        mQuantityTextView = (TextView) findViewById(R.id.item_quantityTextView);
        mPriceTextView = (TextView) findViewById(R.id.item_priceView);
        mAddToCartButton = (Button) findViewById(R.id.addToCart);
        mTitleTextView.setTextSize(getResources().getDimension(R.dimen.textsize));
        mPriceTextView.setTextSize(getResources().getDimension(R.dimen.textsize));
        mQuantityTextView.setTextSize(getResources().getDimension(R.dimen.textsize));
        mAddToCartButton.setTextSize(getResources().getDimension(R.dimen.buttontextsize));
    }

    public void setItem(Item item) {
        mTitleTextView.setText(item.getTitle());
        mQuantityTextView.setText(item.getQuantity());
        mPriceTextView.setText(item.getPrice());
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