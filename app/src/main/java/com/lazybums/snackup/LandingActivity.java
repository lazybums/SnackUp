package com.lazybums.snackup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Activity to select city and Mall.
 */
public class LandingActivity  extends Activity {
    private static final String[] cities = {"Hyderabad", "Bangalore"};
    Spinner citySpinner;
    Spinner mallSpinner;
    String city;
    String mall;
    String vendor;
    String[] malls = {""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_landing);
        citySpinner = (Spinner) findViewById(R.id.citySpinner);
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, cities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        citySpinner.setAdapter(adapter);
        citySpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        int position = citySpinner.getSelectedItemPosition();
                        city = String.valueOf(citySpinner.getSelectedItem());

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                    }
                }
        );
        malls = (String[]) getMallsFromCity(city);
        mallSpinner = (Spinner) findViewById(R.id.mallSpinner);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, malls);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mallSpinner.setAdapter(adapter1);
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
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(LandingActivity.this,
                            VendorActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

        });
    }

    private String[] getMallsFromCity(String city) {
        String[] malls = {"Pvr Banjara Hills", "Prasads Imax Screen", "Cinemax Inorbit"};
        return malls;
    }

}