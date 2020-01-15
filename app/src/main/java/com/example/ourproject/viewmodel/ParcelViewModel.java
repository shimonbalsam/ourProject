package com.example.ourproject.viewmodel;

import android.app.Application;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.ourproject.data.model.Parcel;
import com.example.ourproject.data.repositories.ParcelRepository;

import java.util.List;

public class ParcelViewModel extends AndroidViewModel {

     ParcelRepository mRepository;
    private static ParcelViewModel parcelViewModel;

    private LiveData<List<Parcel>> mAllParcel;

    public ParcelViewModel(@NonNull Application application) {
        super(application);
        mRepository= ParcelRepository.getInstance(application);
        //mRepository.del();

    }
    /*public static ParcelViewModel getInstance(Application application){
        if (parcelViewModel==null)
            parcelViewModel = new ParcelViewModel(application);
        return parcelViewModel;
    }*/

    LiveData<List<Parcel>> getAllParcels() { return mAllParcel; }

    public void insert(Parcel parcel) { mRepository.insert(parcel); }

    public void addParcel(Parcel parcel) {
        mRepository.insert(parcel);
    }

    public LiveData<List<Parcel>> get() {
        return mRepository.getAllParcels();
    }
}
