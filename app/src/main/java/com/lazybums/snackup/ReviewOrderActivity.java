package com.lazybums.snackup;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.lazybums.snackup.item.Item;

import java.util.ArrayList;

/**
 * Created by Abhishek on 7/19/2015.
 */
public class ReviewOrderActivity extends Activity {

    private TextView reviewOrder;
    ArrayList<Item> itemList;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_review);

        reviewOrder = (TextView) findViewById(R.id.reviewOrder);
        reviewOrder.setText(R.string.review_order);
        reviewOrder.setTextSize(R.dimen.textsize);

        Bundle bundle = getIntent().getExtras();
        itemList = bundle.getParcelableArrayList("itemList");

        for(int i = 0; i<itemList.size();i++){

        }
    }
}
