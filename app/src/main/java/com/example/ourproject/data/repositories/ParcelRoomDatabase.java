package com.example.ourproject.data.repositories;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.ourproject.data.model.Parcel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities= {Parcel.class}, version =  1 ,exportSchema = false)
public abstract class ParcelRoomDatabase extends RoomDatabase {

    public  abstract  ParcelDao parcelDao();

    private static volatile ParcelRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ParcelRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ParcelRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ParcelRoomDatabase.class, "word_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
