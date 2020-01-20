package com.example.ourproject.data.model;

import android.location.Location;

import androidx.room.TypeConverter;

public class Memmber {
    String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Location getAddress() {
        return address;
    }

    public void setAddress(Location address) {
        this.address = address;
    }

    String surname;
    int phoneNumber;
    Location address;

    public Memmber(String firstName, String surname, int phoneNumber, Location address) {
        this.firstName = firstName;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    public static class AddressConverter{
        @TypeConverter
        public Location toLocation(String s) {

            String sl = s.split(" ")[0];
            String sg = s.split(" ")[1];
            Location l =  new Location("providerName");
            l.setLatitude(Double.parseDouble(sl));
            l.setLongitude(Double.parseDouble(sg));
            return l;
        }
        @TypeConverter
        public String fromLocation(Location l){
            return l.getLatitude()+" "+l.getLongitude();
        }


    }
}
