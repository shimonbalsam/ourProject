package com.example.ourproject;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Location location;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String query = "select sqlite_version() AS sqlite_version";
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(":memory:", null);
        Cursor cursor = db.rawQuery(query, null);
        String sqliteVersion = "";
        if (cursor.moveToNext()) {
            sqliteVersion = cursor.getString(0);
        }

        final Button button= (Button)findViewById(R.id.Gps_bt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION},1);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    /*Thread thread = new Thread(){
                        public void run(){
                            location = getLastBestLocation();                        }
                    };
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    thread.start();*/

                    location = getLastBestLocation();
                    button.setText(" "+location.getLongitude()+" , "+location.getLatitude());

                }
            }
        });


       /* final Button press = findViewById(R.id.pressButton);
        final TextView resultsTextView = findViewById(R.id.showInput);
        final EditText inputText = findViewById(R.id.editText);

        press.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                resultsTextView.setText("hello "+ inputText.getText());
            }
        });*/
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==1){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                location = getLastBestLocation();

            }
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private Location getLastBestLocation() {
        LocationManager mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        @SuppressLint("MissingPermission") Location locationGPS = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            ;
            return null;
        }

            Location locationNet = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            long GPSLocationTime = 0;
            if (null != locationGPS) {
                GPSLocationTime = locationGPS.getTime();
            }

            long NetLocationTime = 0;

            if (null != locationNet) {
                NetLocationTime = locationNet.getTime();
            }

            if (0 < GPSLocationTime - NetLocationTime) {
                return locationGPS;
            } else {
                return locationNet;
            }

    }
}