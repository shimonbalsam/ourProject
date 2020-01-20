package com.example.ourproject.views;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ourproject.R;
import com.example.ourproject.data.model.Parcel;
import com.example.ourproject.viewmodel.ParcelListViewModel;
import com.example.ourproject.viewmodel.ParcelViewModel;

import java.util.ArrayList;
import java.util.List;

public class ParcelsList extends AppCompatActivity {
    ParcelListViewModel parcelListViewModel;
    List<Parcel> parcelsView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parcels_list);
        parcelsView = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.recycleViewParcels);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdater recyclerViewAdater = new RecyclerViewAdater(parcelsView);
        recyclerView.setAdapter(recyclerViewAdater);

        parcelListViewModel = ViewModelProviders.of(this).get(ParcelListViewModel.class);
        parcelListViewModel.get().observe(this, new Observer<List<Parcel>>() {
            @Override
            public void onChanged(List<Parcel> parcels) {
                parcelsView = parcels;
                recyclerView.setLayoutManager(new LinearLayoutManager(ParcelsList.this));
                RecyclerViewAdater recyclerViewAdater = new RecyclerViewAdater(parcelsView);
                recyclerView.setAdapter(recyclerViewAdater);
                // ((TextView) findViewById(R.id.result)).setText(parcels.isEmpty()?"null":parcels.get(parcels.size()-1).getAddressee().getFirstName());
            }
        });

    }
}
