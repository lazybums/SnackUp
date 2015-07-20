package com.lazybums.snackup;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Geocoder;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lazybums.snackup.constants.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static java.lang.Thread.sleep;

/**
 * Activity to select city and Mall.
 */
public class LandingActivity  extends Activity {
    Spinner mCitySpinner;
    Spinner mMallSpinner;
    TextView mScreenTextView;
    TextView mSeatTextView;
    EditText mScreenNum;
    EditText mSeatNum;
    Button mProceedButton;
    String mScreen;
    String mSeat;
    String mCity = "";
    String mMall = "";
    String mVendor;
    List<String> malls = new ArrayList<>();
    Double latitude = null, longitude = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_landing);

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
        if(null != this.getIntent().getExtras()) {
            Bundle extras = getIntent().getExtras();
            latitude = extras.getDouble(Constants.latitude);
            longitude = extras.getDouble(Constants.longitude);
            mCity = geocoder.getFromLocation(latitude, longitude, 1).get(0).getLocality();
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "Your City is - " + mCity, Toast.LENGTH_LONG).show();

        mCitySpinner = (Spinner) findViewById(R.id.citySpinner);
        mMallSpinner = (Spinner) findViewById(R.id.mallSpinner);
        mScreenTextView = (TextView) findViewById(R.id.screenTextView);
        mSeatTextView = (TextView) findViewById(R.id.seatTextView);
        mScreenNum = (EditText) findViewById(R.id.screenNum);
        mSeatNum = (EditText) findViewById(R.id.seatNum);
        mProceedButton = (Button) findViewById(R.id.proceedButton);

        //TODO: See what the problem is here
        /*mScreenTextView.setText("Screen");
        mScreenTextView.setTextSize(R.dimen.textsize);

        mSeatTextView.setText("Seat");
        mSeatTextView.setTextSize(R.dimen.textsize);*/

        ArrayAdapter<String> citySpinnerAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, Constants.cities);
        citySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCitySpinner.setAdapter(citySpinnerAdapter);
        mCitySpinner.setSelection(citySpinnerAdapter.getPosition(mCity));
        malls.clear();
        malls.addAll(getMallsFromCity(mCity));

        ArrayAdapter<String> mallsSpinnerAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, malls);
        mallsSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mMallSpinner.setAdapter(mallsSpinnerAdapter);

        mScreenNum.setEnabled(false);
        mSeatNum.setEnabled(false);

        mCitySpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        int position = mCitySpinner.getSelectedItemPosition();
                        mCity = String.valueOf(mCitySpinner.getSelectedItem());
                        malls.clear();
                        malls.addAll(getMallsFromCity(mCity));
                        ((BaseAdapter) mMallSpinner.getAdapter()).notifyDataSetChanged();
                        mMallSpinner.setSelection(0);
                        mScreenNum.setEnabled(false);
                        mSeatNum.setEnabled(false);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                    }
                }
        );

        mMallSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        int position = mMallSpinner.getSelectedItemPosition();
                        mMall = String.valueOf(mMallSpinner.getSelectedItem());
                        if(!"".equalsIgnoreCase(mMall)) {
                            mScreenNum.setEnabled(true);
                            mSeatNum.setEnabled(true);
                        } else {
                            mScreenNum.setEnabled(false);
                            mSeatNum.setEnabled(false);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                    }
                }
        );

        mProceedButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                    //TODO:Do we need a sanity check here?
                    if(mScreenNum.getText().toString().matches("") || mSeatNum.getText().toString().matches("")){
                        Toast.makeText(getApplicationContext(),"Fill in the necessary details",Toast.LENGTH_LONG);
                    }
                    else {
                        Intent intent = new Intent(LandingActivity.this,
                                VendorActivity.class);
                        intent.putExtra("Mall", mMall);
                        startActivity(intent);
                        finish();
                    }
            }

        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(LandingActivity.this);

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alertDialog.setNegativeButton("No", null);
        alertDialog.setMessage("Do you want to exit?");
        alertDialog.setTitle("SnackUp");
        alertDialog.show();
    }

    private List<String> getMallsFromCity(String city) {
        String[] hyderabadMalls = {"", "Pvr Banjara Hills", "Prasads Imax Screen", "Inorbit - Cinemax"};
        String[] bangaloreMalls = {"", "Mantri Square", "The Forum", "The ForumValue Mall"};
        String[] result = {""};
        Map<String, String[]> cityMalls = new HashMap<>();
        cityMalls.put("Hyderabad", hyderabadMalls);
        cityMalls.put("Bangalore", bangaloreMalls);
        if(cityMalls.containsKey(city)) {
            result = cityMalls.get(city);
        }
        return Arrays.asList(result);
    }

}