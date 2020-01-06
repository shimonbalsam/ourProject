package com.example.ourproject.data.model;

import android.location.Location;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

enum ParcelSize { ENVELOPE, SMALL, LARGE};
enum ParcelWeight { UPTO500GR, UPTOKG, UPTO5KG, UPTO20KG};
enum ParcelFragile { FRAGILE,NOTFRAGILE};

@Entity( tableName = "Parcels_table")
public class Parcel{
    @PrimaryKey(autoGenerate = true)
    int Id;
    @Embedded
    Memmber addressee;
    ParcelFragile parcelFragile;
    ParcelWeight parcelWeight;
    ParcelSize parcelSize;
    Location parcelAddress;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Memmber getAddressee() {
        return addressee;
    }

    public void setAddressee(Memmber addressee) {
        this.addressee = addressee;
    }

    public ParcelFragile getParcelFragile() {
        return parcelFragile;
    }

    public void setParcelFragile(ParcelFragile parcelFragile) {
        this.parcelFragile = parcelFragile;
    }

    public ParcelWeight getParcelWeight() {
        return parcelWeight;
    }

    public void setParcelWeight(ParcelWeight parcelWeight) {
        this.parcelWeight = parcelWeight;
    }

    public ParcelSize getParcelSize() {
        return parcelSize;
    }

    public void setParcelSize(ParcelSize parcelSize) {
        this.parcelSize = parcelSize;
    }

    public Location getParcelAddress() {
        return parcelAddress;
    }

    public void setParcelAddress(Location parcelAddress) {
        this.parcelAddress = parcelAddress;
    }
}
