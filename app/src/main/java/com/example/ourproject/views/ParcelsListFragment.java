package com.example.ourproject.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ourproject.R;
import com.example.ourproject.data.model.Parcel;
import com.example.ourproject.data.repositories.ParcelDao;
import com.example.ourproject.viewmodel.ParcelListViewModel;
import com.example.ourproject.viewmodel.ParcelViewModel;

import java.util.ArrayList;
import java.util.List;


public class ParcelsListFragment extends Fragment {
    ParcelListViewModel parcelListViewModel;
    List<Parcel> parcelsView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.parcels_list, container, false);


        parcelsView = new ArrayList<>();
        RecyclerView recyclerView = root.findViewById(R.id.recycleViewParcels);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        RecyclerViewAdater recyclerViewAdater = new RecyclerViewAdater(parcelsView);
        recyclerView.setAdapter(recyclerViewAdater);

        parcelListViewModel = ViewModelProviders.of(this).get(ParcelListViewModel.class);
        parcelListViewModel.get().observe(this, new Observer<List<Parcel>>() {
            @Override
            public void onChanged(List<Parcel> parcels) {
                parcelsView = parcels;
                recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
                RecyclerViewAdater recyclerViewAdater = new RecyclerViewAdater(parcelsView);
                recyclerView.setAdapter(recyclerViewAdater);
                // ((TextView) findViewById(R.id.result)).setText(parcels.isEmpty()?"null":parcels.get(parcels.size()-1).getAddressee().getFirstName());
            }
        });


        final Button delete = (Button) root.findViewById(R.id.deleteList);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                parcelListViewModel.deleteAll();

            }

        });
        return root;
    }




    }

    /*@Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTitle("List of this phone");
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
                recyclerView.setLayoutManager(new LinearLayoutManager(ParcelsListFragment.this));
                RecyclerViewAdater recyclerViewAdater = new RecyclerViewAdater(parcelsView);
                recyclerView.setAdapter(recyclerViewAdater);
                // ((TextView) findViewById(R.id.result)).setText(parcels.isEmpty()?"null":parcels.get(parcels.size()-1).getAddressee().getFirstName());
            }
        });


        final Button delete = (Button) findViewById(R.id.deleteList);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                parcelListViewModel.deleteAll();

            }

        });

    }*/

