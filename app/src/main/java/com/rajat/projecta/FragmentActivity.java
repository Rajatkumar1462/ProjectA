package com.rajat.projecta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rajat.projecta.Fragment.AccountFragment;
import com.rajat.projecta.Fragment.FaqFragment;
import com.rajat.projecta.Fragment.HomeFragment;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        BottomNavigationView bottomnav = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();

        bottomnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment select = null;
                switch (item.getItemId()){
                    case R.id.nav_home:
                        select = new HomeFragment();
                        break;
                    case R.id.nav_faq:
                        select = new FaqFragment();
                        break;
                    case R.id.nav_account:
                        select = new AccountFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,select).commit();
                return true;
            }
        });
    }
}