package com.example.ourproject.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.ourproject.data.model.Parcel;

import java.util.List;

public class ParcelRepository {

    private ParcelDao mParcelDao;
    private LiveData<List<Parcel>> mAllParcels;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public ParcelRepository(Application application) {
        ParcelRoomDatabase db = ParcelRoomDatabase.getDatabase(application);
        mParcelDao = db.parcelDao();
        mAllParcels = mParcelDao.getAllParcel();
    }
    public void del(){
        mParcelDao.deleteAll();
    }


    private static ParcelRepository instance;
    public static ParcelRepository getInstance(Application application) {
        if (instance == null)
            instance = new ParcelRepository(application);
        return instance;
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Parcel>> getAllParcels() {

        mAllParcels = mParcelDao.getAllParcel();
        return mAllParcels;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    /*public void insert(Parcel parcel) {
        ParcelRoomDatabase.databaseWriteExecutor.execute(() -> {
            mParcelDao.insert(parcel);
        });
    }*/
    public void insert (Parcel parcel)
    {
        mParcelDao.insert(parcel);
    }


}