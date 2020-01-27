package com.example.ourproject.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ourproject.R;
import com.example.ourproject.data.model.Memmber;
import com.example.ourproject.data.model.Parcel;
import com.example.ourproject.data.repositories.FireBase;
import com.example.ourproject.viewmodel.ParcelViewModel;

import java.io.IOException;
import java.util.List;


public class MainActivity extends Fragment {
    private List<Parcel> updatParcel;
    private Location location;
    private ParcelViewModel parcelViewModel;
    private Spinner size_tv;
    Geocoder geocoder;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.activity_main, container, false);
        geocoder = new Geocoder(root.getContext());



        parcelViewModel = ViewModelProviders.of(this).get(ParcelViewModel.class);
        parcelViewModel.get().observe(this, new Observer<List<Parcel>>() {
            @Override
            public void onChanged(List<Parcel> parcels) {
                updatParcel = parcels;
                // ((TextView) findViewById(R.id.result)).setText(parcels.isEmpty()?"null":parcels.get(parcels.size()-1).getAddressee().getFirstName());
            }
        });


        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

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

        final Button buttonAdd = (Button) root.findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addParcel(root);

            }
        });

        final Button button2 = (Button) root.findViewById(R.id.button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //LiveData<List<Parcel>> l = parcelViewModel.get();

                //      ((TextView) findViewById(R.id.result)).setText(parcelViewModel.get().getValue().get(0).getId());
                ((TextView) root.findViewById(R.id.result)).setText(updatParcel.get(updatParcel.size() - 1).getId() + "");


            }
        });

        final Button button = (Button) root.findViewById(R.id.Gps_bt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

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


                    //    location = getLastBestLocation();
                    if (location == null)
                        button.setText("no gps");
                    else{
                        try {
                            List<Address> l = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            if (!l.isEmpty())
                                button.setText(l.get(0).getAddressLine(0) + "");

                        } catch (IOException e) {

                        }
                    }

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
        return root;
    }




    /*protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        geocoder = new Geocoder(this);



        parcelViewModel = ViewModelProviders.of(this).get(ParcelViewModel.class);
        parcelViewModel.get().observe(this, new Observer<List<Parcel>>() {
            @Override
            public void onChanged(List<Parcel> parcels) {
                updatParcel = parcels;
                // ((TextView) findViewById(R.id.result)).setText(parcels.isEmpty()?"null":parcels.get(parcels.size()-1).getAddressee().getFirstName());
            }
        });


        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            location = getLastBestLocation();
        }

        //size_tv= (Spinner) findViewById(R.id.size);



        *//*String query = "select sqlite_version() AS sqlite_version";
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(":memory:", null);
        Cursor cursor = db.rawQuery(query, null);
        String sqliteVersion = "";
        if (cursor.moveToNext()) {
            sqliteVersion = cursor.getString(0);
        }*//*

        final Button buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addParcel();

            }
        });
        final Button buttonList = (Button) findViewById(R.id.buttenlist);
        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ParcelsList.class);
                startActivity(intent);
            }
        });
        final Button button2 = (Button) findViewById(R.id.button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //LiveData<List<Parcel>> l = parcelViewModel.get();

                //      ((TextView) findViewById(R.id.result)).setText(parcelViewModel.get().getValue().get(0).getId());
                ((TextView) findViewById(R.id.result)).setText(updatParcel.get(updatParcel.size() - 1).getId() + "");


            }
        });

        final Button button = (Button) findViewById(R.id.Gps_bt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    *//*Thread thread = new Thread(){
                        public void run(){
                            location = getLastBestLocation();                        }
                    };
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    thread.start();*//*


                //    location = getLastBestLocation();
                    if (location == null)
                        button.setText("no gps");
                    else{
                        try {
                            List<Address> l = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            if (!l.isEmpty())
                                button.setText(l.get(0).getAddressLine(0) + "");

                        } catch (IOException e) {

                        }
                    }

                }
            }
        });


       *//* final Button press = findViewById(R.id.pressButton);
        final TextView resultsTextView = findViewById(R.id.showInput);
        final EditText inputText = findViewById(R.id.editText);

        press.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                resultsTextView.setText("hello "+ inputText.getText());
            }
        });*//*
    }
*/

    private void addParcel(View root) {

        String firstName = ((TextView) root.findViewById(R.id.editFirstName)).getText().toString();
        String lastName = ((TextView) root.findViewById(R.id.editLastName)).getText().toString();
        int phoneNumber;
        try {
            phoneNumber = Integer.parseInt(((TextView) root.findViewById(R.id.editPhone)).getText().toString());
        } catch (NumberFormatException e) {
            phoneNumber = 0;
        }
        Parcel.ParcelFragile parcelFragile;
        switch (((Spinner) root.findViewById(R.id.frigile)).getSelectedItemPosition()) {
            case 0:
                parcelFragile = Parcel.ParcelFragile.FRAGILE;
                break;
            case 1:
                parcelFragile = Parcel.ParcelFragile.NOTFRAGILE;
            default:
                parcelFragile = null;
        }
        Parcel.ParcelWeight parcelWeight;
        switch (((Spinner) root.findViewById(R.id.Weight)).getSelectedItemPosition()) {
            case 0:
                parcelWeight = Parcel.ParcelWeight.UPTO500GR;
                break;
            case 1:
                parcelWeight = Parcel.ParcelWeight.UPTOKG;
                break;
            case 2:
                parcelWeight = Parcel.ParcelWeight.UPTO5KG;
                break;
            case 3:
                parcelWeight = Parcel.ParcelWeight.UPTO20KG;
                break;
            default:
                parcelWeight = null;
        }
        Parcel.ParcelSize parcelSize;
        switch (((Spinner) root.findViewById(R.id.size)).getSelectedItemPosition()) {
            case 0:
                parcelSize = Parcel.ParcelSize.ENVELOPE;
                break;
            case 1:
                parcelSize = Parcel.ParcelSize.SMALL;
                break;
            case 2:
                parcelSize = Parcel.ParcelSize.LARGE;
            default:
                parcelSize = null;

        }
        Memmber memmber = new Memmber(firstName, lastName, phoneNumber, location);
        Parcel parcel = new Parcel(memmber, parcelFragile, parcelWeight, parcelSize, location);
        if (location != null) {
            try {
                List<Address> l = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                if (!l.isEmpty())
                    parcel.setParcelAddressAuto(l.get(0).getAddressLine(0) + "");

            } catch (IOException e) {

            }
        }
        parcelViewModel.addParcel(parcel);

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                location = getLastBestLocation();

            }
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private Location getLastBestLocation() {
        LocationManager mLocationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        @SuppressLint("MissingPermission") Location locationGPS = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (getActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && getActivity().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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