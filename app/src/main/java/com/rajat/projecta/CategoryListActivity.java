package com.rajat.projecta;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rajat.projecta.Adapter.ServiceProviderAdapter;
import com.rajat.projecta.Adapter.ServiceProviderAdapter2;
import com.rajat.projecta.Fragment.HomeFragment;
import com.rajat.projecta.Helper.ServiceProviderHelper;

import java.util.ArrayList;

public class CategoryListActivity extends AppCompatActivity implements ServiceProviderAdapter2.ServiceproviderClickInterface {

    //Declaration
    private TextView AtTopName;
    ArrayList<ServiceProviderHelper> sp_list2 = new ArrayList<>();
    FirebaseUser user;

    private String Type;
    HomeFragment hf = new HomeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_categorylist);
        user = FirebaseAuth.getInstance().getCurrentUser();
        //settingDisplayName
        AtTopName = findViewById(R.id.atTopName_all);
        AtTopName.setText(user.getDisplayName());

        RecyclerView serviceprovider_full_list = findViewById(R.id.sp_full_list);
        serviceprovider_full_list.setLayoutManager(new GridLayoutManager(this,1));

        hf.loadValues("Cook", sp_list2,R.drawable.cook);
        hf.loadValues("Driver", sp_list2,R.drawable.driver);
        hf.loadValues("Maid", sp_list2,R.drawable.maid);
        hf.loadValues("Gardener", sp_list2,R.drawable.gardner);
        hf.loadValues("BabySitter", sp_list2,R.drawable.baby_sitter);

        serviceprovider_full_list.setAdapter(new ServiceProviderAdapter2(sp_list2, (ServiceProviderAdapter2.ServiceproviderClickInterface) this));
    }

    @Override
    public void onSpItemClick(int position) {
        String title = sp_list2.get(position).getTitle();
        String rating = sp_list2.get(position).getRating();
        String info = sp_list2.get(position).getInfo();
        int image = sp_list2.get(position).getImage();

        Intent intent = new Intent(CategoryListActivity.this,BookActivity.class);

        intent.putExtra("title",title);
        intent.putExtra("rating",rating);
        intent.putExtra("info",info);
        intent.putExtra("profile",image);
        intent.putExtra("Type",Type);

        startActivity(intent);
    }
}
