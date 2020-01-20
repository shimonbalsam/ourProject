package com.example.ourproject.data.model;

import android.location.Address;
import android.location.Location;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.google.firebase.database.Exclude;

import java.util.List;

/*enum ParcelSize { ENVELOPE, SMALL, LARGE};
enum ParcelWeight { UPTO500GR, UPTOKG, UPTO5KG, UPTO20KG};
enum ParcelFragile { FRAGILE,NOTFRAGILE};*/

@Entity(tableName = "Parcels_table")
public class Parcel {
    @Embedded
    private Memmber addressee;
    @TypeConverters(Parcel.ParcelFragile.class)
    private ParcelFragile parcelFragile;
    @TypeConverters(Parcel.ParcelWeight.class)

    private ParcelWeight parcelWeight;
    @TypeConverters(Parcel.ParcelSize.class)
    private ParcelSize parcelSize;
    private Location parcelAddress;


    public Parcel(Memmber addressee, ParcelFragile parcelFragile, ParcelWeight parcelWeight, ParcelSize parcelSize, Location parcelAddress) {
        this.addressee = addressee;
        this.parcelFragile = parcelFragile;
        this.parcelWeight = parcelWeight;
        this.parcelSize = parcelSize;
        this.parcelAddress = parcelAddress;
    }

    public enum ParcelSize {
        ENVELOPE(0), SMALL(1), LARGE(2);
        private final Integer code;

        ParcelSize(Integer value) {
            this.code = value;
        }

        public Integer getCode() {
            return code;
        }

        @TypeConverter
        public static ParcelSize getType(Integer numeral) {
            for (ParcelSize ds : values()) {
                if (ds.code == numeral) {
                    return ds;
                }
            }
            return null;
        }
        @TypeConverter
        public static Integer GetSize (ParcelSize parcelSize)
        {
            return parcelSize.code;

        }
    }

    public enum ParcelWeight {
        UPTO500GR(0), UPTOKG(1), UPTO5KG(2), UPTO20KG(3);
        private final Integer code;

        ParcelWeight(Integer value) {
            this.code = value;
        }

        public Integer getCode() {
            return code;
        }

        @TypeConverter
        public static ParcelWeight getType(Integer numeral) {
            for (Parcel.ParcelWeight ds : values()) {
                if (ds.code == numeral) {
                    return ds;
                }
            }
            return null;
        }
        @TypeConverter
        public static Integer GetWeight (ParcelWeight parcelWeight)
        {
            return parcelWeight.code;

        }
    }

    public enum ParcelFragile {
        FRAGILE(0), NOTFRAGILE(1);
        private final Integer code;

        ParcelFragile(Integer value) {
            this.code = value;
        }

        public Integer getCode() {
            return code;
        }

        @TypeConverter
        public static ParcelFragile getType(Integer numeral) {
            for (Parcel.ParcelFragile ds : values()) {
                if (ds.code == numeral) {
                    return ds;
                }
            }
            return null;
        }
        @TypeConverter
        public static Integer GetFrigile (ParcelFragile parcelFragile)
        {
            return parcelFragile.code;

        }
    }

    @PrimaryKey(autoGenerate = true)
    int Id;

    public static class AddressConverter {
        @TypeConverter
        public Location toLocation(String s) {

            String sl = s.split(" ")[0];
            String sg = s.split(" ")[1];
            Location l = new Location("providerName");
            l.setLatitude(Double.parseDouble(sl));
            l.setLongitude(Double.parseDouble(sg));
            return l;
        }

        @TypeConverter
        public String fromLocation(Location l) {
            return l.getLatitude() + " " + l.getLongitude();
        }


    }


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
