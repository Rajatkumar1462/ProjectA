package com.rajat.projecta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button submitBtnFirstPage;
    private static EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
        submitBtnFirstPage = findViewById(R.id.submitBtnFirstPage);
        name = findViewById(R.id.name);


        submitBtnFirstPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FragmentActivity.class));
            }
        });

    }

    public static String getName(){
        return (name.getText().toString());
    }

}