package com.rajat.projecta;

import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
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

import java.util.ArrayList;


public class RecentListActivity extends AppCompatActivity {
    DatabaseReference dbr;
    private RecentAdapter adapter;
    private ArrayList<Artist> artistList =  new ArrayList<>();
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
                artistList.clear();
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        Artist artist = snapshot.getValue(Artist.class);
                        artistList.add(artist);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        artistList.add(new Artist());
        adapter = new RecentAdapter(this,images,artistList);
        recent_list.setAdapter(adapter);
    }
}
