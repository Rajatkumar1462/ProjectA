package com.rajat.projecta.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rajat.projecta.LoginActivity;
import com.rajat.projecta.MainActivity;
import com.rajat.projecta.R;
import com.rajat.projecta.userDetail;

public class AccountFragment extends Fragment {
    @Nullable
    private Button meSignOut;
    private Button meEdit;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private TextView meName;
    private TextView meEmail;
    private TextView meAddress;
    private TextView meKyc;
    userDetail details = new userDetail();

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account,container,false);
        meSignOut = view.findViewById(R.id.me_signout);
        meEdit = view.findViewById(R.id.me_edit);
        meSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });
        meName = view.findViewById(R.id.me_name);
        meEmail = view.findViewById(R.id.me_email);
        meAddress = view.findViewById(R.id.me_address);
        meKyc = view.findViewById(R.id.me_kyc);

        //setting values in account fragment from firebase
        updateAccountSection();

        meEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("UserId", FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
                startActivity(intent);
            }
        });

        return view;
    }

    private void signOut(){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }
    public void updateAccountSection(){
        meName.setText((CharSequence) details.getName());
        meEmail.setText((CharSequence) details.getEmail());
        meAddress.setText((CharSequence) details.getAddress());
        meKyc.setText((CharSequence) details.getAadhar());
    }
}