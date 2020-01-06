package com.example.ourproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.ourproject.data.model.Parcel;
import com.example.ourproject.data.repositories.ParcelRepository;

import java.util.List;

public class ParcelViewModel extends AndroidViewModel {

    private ParcelRepository mRepository;

    private LiveData<List<Parcel>> mAllWords;

    public ParcelViewModel(@NonNull Application application) {
        super(application);
    }

    LiveData<List<Parcel>> getAllParcels() { return mAllWords; }

    public void insert(Parcel parcel) { mRepository.insert(parcel); }
}
