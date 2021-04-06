package com.rajat.projecta;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class userDetail {

    private static String name = "";
    private static String email = "";
    private static String address = "";
    private static String aadhar = "";
    private static DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private static FirebaseAuth mAuth;

    public userDetail() {
        mAuth = FirebaseAuth.getInstance();
        name = "Name : " + mAuth.getCurrentUser().getDisplayName();
        email = "Email : " + mAuth.getCurrentUser().getEmail();
        Task<DataSnapshot> snapshot = ref.child("start").child(mAuth.getCurrentUser().getUid()).child("Address").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    address = "Address : " + String.valueOf(task.getResult().getValue());
                }
            }
        });
        snapshot = ref.child("start").child(mAuth.getCurrentUser().getUid()).child("Aadhar Number").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    aadhar = "Aadhar : " + String.valueOf(task.getResult().getValue());
                }
            }
        });
    }

    public static String getName() {
        return name;
    }

    public static String getEmail() {
        return email;
    }

    public static String getAddress() {
        return address;
    }

    public static String getAadhar() {
        return aadhar;
    }
}