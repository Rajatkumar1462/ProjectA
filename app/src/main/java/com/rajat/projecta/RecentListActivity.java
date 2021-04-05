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

class Post {

    public String Name;
    public Long cost;
    public Long duration;
    public Long paymentId;
    public String status;

    public Post(String Name, Long cost,Long duration,Long paymentId,String status) {
        this.Name= Name;
        this.cost = cost;
        this.duration = duration;
        this.paymentId = paymentId;
        this.status = status;
    }
    public Post(){

    }

}
public class RecentListActivity extends AppCompatActivity {
    DatabaseReference dbr;
    ArrayList<String> data = new ArrayList<>();
    int images[]={R.drawable.driver};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_list);

        final RecyclerView recent_list = findViewById(R.id.RecentOrderList);
        recent_list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        dbr = FirebaseDatabase.getInstance().getReference().child("start").child("XbS13nOFzQUifcJiOt90dXyVyNZ2").child("Orders").child("d1suresh");
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Post post = snapshot.getValue(Post.class);
                data.add(post.Name+" "+post.status+" "+post.paymentId+" "+post.duration+" "+post.cost);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        recent_list.setAdapter(new RecentAdapter(images,data));
    }
}