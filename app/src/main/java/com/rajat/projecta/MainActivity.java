package com.rajat.projecta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private Button submitBtnFirstPage;
    private static EditText name;
    private DatabaseReference mDatabase;
    private EditText dob;
    private EditText address;
    private EditText aadharNumber;
    private EditText mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = FirebaseDatabase.getInstance().getReference();
//        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
        submitBtnFirstPage = findViewById(R.id.submitBtnFirstPage);
        name = findViewById(R.id.name);
        dob = findViewById(R.id.dob);
        address = findViewById(R.id.address);
        aadharNumber = findViewById(R.id.aadharNumber);
        mobile = findViewById(R.id.mobile);

        name.setText((CharSequence) getIntent().getStringExtra("UserId"));

        submitBtnFirstPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //making entries to firebase
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                mDatabase.child("start").child(user.getUid()).child("Address").setValue(address.getText().toString());
                mDatabase.child("start").child(user.getUid()).child("DOB").setValue(dob.getText().toString());
                mDatabase.child("start").child(user.getUid()).child("Aadhar Number").setValue(aadharNumber.getText().toString());
                mDatabase.child("start").child(user.getUid()).child("mobile").setValue(mobile.getText().toString());


                startActivity(new Intent(getApplicationContext(), FragmentActivity.class));
            }
        });
    }

    public static String getName(){
        return (name.getText().toString());
    }

}