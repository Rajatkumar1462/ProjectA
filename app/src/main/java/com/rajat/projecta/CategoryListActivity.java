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
import com.rajat.projecta.Helper.ServiceProviderHelper;

import java.util.ArrayList;

public class CategoryListActivity extends AppCompatActivity implements ServiceProviderAdapter2.ServiceproviderClickInterface {

    //Declaration
    private TextView AtTopName;
    ArrayList<ServiceProviderHelper> sp_list = new ArrayList<>();
    FirebaseUser user;

    private String Type;

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

        sp_list.add(new ServiceProviderHelper(R.drawable.cook,"Suresh","5 star","3 year Experience"));
        sp_list.add(new ServiceProviderHelper(R.drawable.cook,"Mamta Devi","4.7 star","1 year Experience"));
        sp_list.add(new ServiceProviderHelper(R.drawable.cook,"Gopal","3.7 star","6 Month Experience"));

        sp_list.add(new ServiceProviderHelper(R.drawable.driver,"Basant","4.8 star","2.5 year Experience"));
        sp_list.add(new ServiceProviderHelper(R.drawable.driver,"Tahir","4.5 star","2 year Experience"));
        sp_list.add(new ServiceProviderHelper(R.drawable.driver,"Rajiv Anna","4.4 star","1 year Experience"));

        sp_list.add(new ServiceProviderHelper(R.drawable.maid,"Mala","4.9 star","5 year Experience"));
        sp_list.add(new ServiceProviderHelper(R.drawable.maid,"Shakuntala","4.8 star","3.5 year Experience"));
        sp_list.add(new ServiceProviderHelper(R.drawable.maid,"Ramu","4.5 star","2 year Experience"));

        sp_list.add(new ServiceProviderHelper(R.drawable.gardner,"Prakash","4.5 star","2 year Experience"));
        sp_list.add(new ServiceProviderHelper(R.drawable.gardner,"Raman","4.4 star","1 year Experience"));
        sp_list.add(new ServiceProviderHelper(R.drawable.gardner,"Shadique","4.3 star","New"));

        sp_list.add(new ServiceProviderHelper(R.drawable.baby_sitter,"Pragati","4.5 star","3 year Experience"));
        sp_list.add(new ServiceProviderHelper(R.drawable.baby_sitter,"Mohini","4 star","2 year Experience"));
        sp_list.add(new ServiceProviderHelper(R.drawable.baby_sitter,"Rohini","3.8 star","1 year Experience"));

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
        intent.putExtra("Type",Type);

        startActivity(intent);
    }
}
