package com.example.ourproject.data.repositories;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.ourproject.data.model.Parcel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FireBase {
//    public void insertFireBase(){
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("aba");
//        myRef.child("jjjj").setValue("sjhjk").addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                int a;
//                a=5;
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                int b;
//                b=5;
//            }
//        });
//    }


    public void insertFireBase(Parcel parcel)

    {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("parcel");
       String id= myRef.push().getKey();
        myRef.child(id).setValue(parcel);
    }
}
