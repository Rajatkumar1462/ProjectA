package com.rajat.projecta;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rajat.projecta.Adapter.RecentAdapter;
import com.rajat.projecta.Helper.ServiceProviderBookHelper;

import java.util.ArrayList;


public class RecentListActivity extends AppCompatActivity {
    DatabaseReference dbr;
    private RecentAdapter adapter;
    private ArrayList<ServiceProviderBookHelper> serviceProviderBookHelperList =  new ArrayList<>();
    int images[]={R.drawable.driver,R.drawable.baby_sitter,R.drawable.baby_sitter};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_list);

        final RecyclerView recent_list = findViewById(R.id.RecentOrderList);
        recent_list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        dbr = FirebaseDatabase.getInstance().getReference().child("start").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("All Orders");
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                serviceProviderBookHelperList.clear();
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        ServiceProviderBookHelper serviceProviderBookHelper = snapshot.getValue(ServiceProviderBookHelper.class);
                        serviceProviderBookHelperList.add(serviceProviderBookHelper);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        serviceProviderBookHelperList.add(new ServiceProviderBookHelper());
        adapter = new RecentAdapter(this,images, serviceProviderBookHelperList);
        recent_list.setAdapter(adapter);
    }
}
