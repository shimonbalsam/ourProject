package com.example.ourproject.data.model;

import android.location.Location;

public class Memmber {
    String firstName;
    String surname;
    int phoneNumber;
    Location address;

    public Memmber(String firstName, String surname, int phoneNumber, Location address) {
        this.firstName = firstName;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
