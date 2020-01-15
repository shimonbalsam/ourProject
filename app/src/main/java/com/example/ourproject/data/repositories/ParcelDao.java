package com.example.ourproject.data.repositories;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ourproject.data.model.Parcel;

import java.util.List;

@Dao
public interface ParcelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Parcel parcel);

    @Update
    void update(Parcel parcel);

    @Delete
    void delete(Parcel parcel);

    @Query("DELETE FROM Parcels_table")
    void deleteAll();

    @Query("SELECT * from Parcels_table")
    LiveData<List<Parcel>> getAllParcel();

}
