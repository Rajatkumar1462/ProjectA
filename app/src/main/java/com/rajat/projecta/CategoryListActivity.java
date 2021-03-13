package com.rajat.projecta;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rajat.projecta.Adapter.ServiceProviderAdapter;
import com.rajat.projecta.Adapter.ServiceProviderAdapter2;
import com.rajat.projecta.Helper.ServiceProviderHelper;

import java.util.ArrayList;

public class CategoryListActivity extends AppCompatActivity implements ServiceProviderAdapter2.ServiceproviderClickInterface {

    //Declaration
    ArrayList<ServiceProviderHelper> sp_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_categorylist);

        RecyclerView serviceprovider_full_list = findViewById(R.id.sp_full_list);
        serviceprovider_full_list.setLayoutManager(new GridLayoutManager(this,2));

        sp_list.add(new ServiceProviderHelper(R.drawable.driver,"Driver1","5 star","he is a 5star driver"));
        sp_list.add(new ServiceProviderHelper(R.drawable.driver,"Driver2","5 star","he is a 5star driver"));
        sp_list.add(new ServiceProviderHelper(R.drawable.driver,"Driver3","4.5 star","he is a 4.5star driver"));

        sp_list.add(new ServiceProviderHelper(R.drawable.maid,"maid1","5 star","he is a 5 star maid"));
        sp_list.add(new ServiceProviderHelper(R.drawable.maid,"maid2","5 star","he is a 5 star maid"));
        sp_list.add(new ServiceProviderHelper(R.drawable.maid,"maid3","4.5 star","he is a 4.5 star maid"));

        sp_list.add(new ServiceProviderHelper(R.drawable.gardner,"gard1","5 star","he is a 5star gardner"));
        sp_list.add(new ServiceProviderHelper(R.drawable.gardner,"gard2","5 star","he is a 5star gardner"));
        sp_list.add(new ServiceProviderHelper(R.drawable.gardner,"gard3","4.5 star","he is a 4.5star gardner"));

        serviceprovider_full_list.setAdapter(new ServiceProviderAdapter2(sp_list, (ServiceProviderAdapter2.ServiceproviderClickInterface) this));
    }

    @Override
    public void onSpItemClick(int position) {
        String title = sp_list.get(position).getTitle();
        String rating = sp_list.get(position).getRating();
        String info = sp_list.get(position).getInfo();
        int image = sp_list.get(position).getImage();

        Intent intent = new Intent(CategoryListActivity.this,BookActivity.class);

        intent.putExtra("title",title);
        intent.putExtra("rating",rating);
        intent.putExtra("info",info);
        intent.putExtra("profile",image);

        startActivity(intent);
    }
}
