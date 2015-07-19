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
import android.widget.Spinner;
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
    Spinner citySpinner;
    Spinner mallSpinner;
    String city = "";
    String mall = "";
    String vendor;
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
            city = geocoder.getFromLocation(latitude, longitude, 1).get(0).getLocality();
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "Your City is - " + city, Toast.LENGTH_LONG).show();

        citySpinner = (Spinner) findViewById(R.id.citySpinner);
        mallSpinner = (Spinner) findViewById(R.id.mallSpinner);

        ArrayAdapter<String> citySpinnerAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, Constants.cities);
        citySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(citySpinnerAdapter);
        citySpinner.setSelection(citySpinnerAdapter.getPosition(city));
        malls.clear();
        malls.addAll(getMallsFromCity(city));

        ArrayAdapter<String> mallsSpinnerAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, malls);
        mallsSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mallSpinner.setAdapter(mallsSpinnerAdapter);

        citySpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        int position = citySpinner.getSelectedItemPosition();
                        city = String.valueOf(citySpinner.getSelectedItem());
                        malls.clear();malls.addAll(getMallsFromCity(city));
                        ((BaseAdapter) mallSpinner.getAdapter()).notifyDataSetChanged();
                        mallSpinner.setSelection(0);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                    }
                }
        );

        mallSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        int position = mallSpinner.getSelectedItemPosition();
                        mall = String.valueOf(mallSpinner.getSelectedItem());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                    }
                }
        );
        Button btnSubmit = (Button) findViewById(R.id.proceedButton);
        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(LandingActivity.this,
                            VendorActivity.class);
                    startActivity(intent);
                    finish();
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