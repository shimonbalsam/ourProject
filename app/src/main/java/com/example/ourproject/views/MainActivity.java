package com.example.ourproject.views;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Application;
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
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ourproject.R;
import com.example.ourproject.data.model.Memmber;
import com.example.ourproject.data.model.Parcel;
import com.example.ourproject.viewmodel.ParcelViewModel;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Location location;
    private ParcelViewModel parcelViewModel;
    private Spinner size_tv;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parcelViewModel = ViewModelProviders.of(this).get(ParcelViewModel.class);


            requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION},1);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                location = getLastBestLocation();
            }

        //size_tv= (Spinner) findViewById(R.id.size);



        /*String query = "select sqlite_version() AS sqlite_version";
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(":memory:", null);
        Cursor cursor = db.rawQuery(query, null);
        String sqliteVersion = "";
        if (cursor.moveToNext()) {
            sqliteVersion = cursor.getString(0);
        }*/

        final Button buttonAdd = (Button)findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addParcel();

            }
        });
        final Button button2 = (Button)findViewById(R.id.button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiveData<List<Parcel>> l= parcelViewModel.get();

                ((TextView)findViewById(R.id.result)).setText(parcelViewModel.get().getValue().get(0).getId());
            }
        });

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
                    button.setText(/*" "+location.getLongitude()+" , "+location.getLatitude()+" "+*/location.toString());

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



    private void addParcel() {
        String firstName= ((TextView) findViewById(R.id.editFirstName)).getText().toString();
        String lastName =((TextView) findViewById(R.id.editLastName)).getText().toString();
        int phoneNumber;
        try {
            phoneNumber = Integer.parseInt(((TextView) findViewById(R.id.editPhone)).getText().toString());
        }
        catch (NumberFormatException e)
        {
            phoneNumber = 0;
        }
        Parcel.ParcelFragile parcelFragile = (Parcel.ParcelFragile) ((Spinner) findViewById(R.id.frigile)).getSelectedItem();
        Parcel.ParcelWeight parcelWeight = (Parcel.ParcelWeight) ((Spinner) findViewById(R.id.Weight)).getSelectedItem();
        Parcel.ParcelSize parcelSize = (Parcel.ParcelSize) ((Spinner) findViewById(R.id.size)).getSelectedItem();
        Memmber memmber = new Memmber(firstName,lastName,phoneNumber,location);
        Parcel parcel = new Parcel(memmber,parcelFragile,parcelWeight,parcelSize,location);
    parcelViewModel.addParcel(parcel);
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