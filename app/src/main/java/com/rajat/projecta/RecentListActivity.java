package com.rajat.projecta;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rajat.projecta.Adapter.RecentAdapter;

public class RecentListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_list);

        RecyclerView recent_list = findViewById(R.id.RecentOrderList);
        recent_list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        int images[]={R.drawable.driver,R.drawable.maid,R.drawable.gardner};
        String data[] = {"recent order 1","recent order 2","recent order 3"};
        recent_list.setAdapter(new RecentAdapter(images,data));
    }
}
