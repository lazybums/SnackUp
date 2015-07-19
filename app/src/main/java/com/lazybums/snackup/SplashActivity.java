package com.lazybums.snackup;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;


public class SplashActivity extends Activity {
    AlertDialog dialog;
    AlertDialog.Builder build;
    GPSTracker gps;
    Double latitude = null;
    Double longitude = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        build = new AlertDialog.Builder(this);
        if (!NetworkManager.isConnected(this)) {

            build.setMessage("This application requires Internet connection.");
            build.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    finish();
                    startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));

                }
            });
            build.setNegativeButton("No", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    build.setMessage("Are sure you want to exit?");
                    build.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            finish();
                        }
                    });
                    build.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            finish();
                            Intent intent = new Intent(SplashActivity.this,
                                    SplashActivity.class);
                            startActivity(intent);

                            dialog.dismiss();

                        }
                    });
                    //dialog = build.create();
                   // dialog.
                }
            });
            dialog = build.create();
            dialog.show();

        }

        gps = new GPSTracker(this);

        if(gps.canGetLocation()){

            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            String locality = null;
            /*try {
                locality = gps.getLocality();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude+"\nLocality: " + locality , Toast.LENGTH_LONG).show();
        }

        Thread mythread = new Thread() {
            public void run() {
                try {

                    sleep(5000);

                } catch (Exception e) {
                } finally {
                    Intent intent = new Intent(SplashActivity.this,
                            LandingActivity.class);
                    if(null != latitude && null != longitude) {
                        intent.putExtra("latitude",latitude);
                        intent.putExtra("longitude",longitude);
                    }
                    startActivity(intent);
                    finish();
                }
            }
        };
        mythread.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
