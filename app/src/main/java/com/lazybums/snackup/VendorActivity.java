package com.lazybums.snackup;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Abhishek on 7/18/2015.
 */
public class VendorActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_vendor);
        TextView mVendorId = (TextView) findViewById(R.id.vendorName);
        Button mCheckOutButton = (Button) findViewById(R.id.checkOutButton);
        mVendorId.setTextSize(getTextDimension());
        mCheckOutButton.setTextSize(getButtonTextDimension());
        //setContentView(R.layout.activity_list);
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
