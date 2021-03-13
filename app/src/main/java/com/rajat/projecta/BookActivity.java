package com.rajat.projecta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BookActivity extends AppCompatActivity {

    ImageView Image;
    TextView Bookname;
    TextView BookRating;
    TextView BookInfo;
    Button bookbtn;

    String title;
    String rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        Image = findViewById(R.id.BookProfile);
        Bookname = findViewById(R.id.Bookname);
        BookRating = findViewById(R.id.BookRating);
        BookInfo = findViewById(R.id.BookInfo);
        bookbtn = findViewById(R.id.bookbtn);

        Intent intent = getIntent();

        Bookname.setText(intent.getStringExtra("title"));
        BookRating.setText(intent.getStringExtra("rating"));
        BookInfo.setText(intent.getStringExtra("info"));
        Image.setImageResource(intent.getIntExtra("profile",0));
        bookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(com.rajat.projecta.BookActivity.this,"Booked...",Toast.LENGTH_SHORT).show();
            }
        });
    }
}