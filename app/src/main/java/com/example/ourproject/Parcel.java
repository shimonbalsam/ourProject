package com.example.ourproject;

import android.location.Location;

enum ParcelSize { ENVELOPE, SMALL, LARGE};
enum ParcelWeight { UPTO500GR, UPTOKG, UPTO5KG, UPTO20KG};
enum ParcelFragile { FRAGILE,NOTFRAGILE};

public class Parcel{
    Memmber addressee;
    ParcelFragile parcelFragile;
    ParcelWeight parcelWeight;
    ParcelSize parcelSize;
    Location parcelAddress;
}
