package com.rajat.projecta;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class BookActivity extends AppCompatActivity implements PaymentResultListener {

    ImageView Image;
    TextView Bookname;
    TextView BookRating;
    TextView BookInfo;
    EditText Duration;
    TextView textAmount;
    Button bookbtn;

    int payableamount=0;

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
        Duration = findViewById(R.id.BookDuration);
        textAmount = findViewById(R.id.BookRupees);
        Intent intent = getIntent();

        Bookname.setText(intent.getStringExtra("title"));
        BookRating.setText(intent.getStringExtra("rating"));
        BookInfo.setText(intent.getStringExtra("info"));
        Image.setImageResource(intent.getIntExtra("profile",0));

        Duration.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                int convertamount = Math.round(Integer.parseInt(String.valueOf(s))*100);
                payableamount =  convertamount*100;
                textAmount.setText(Integer.toString(convertamount));
            }
        });

        bookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checkout checkout = new Checkout();
                checkout.setKeyID("rzp_test_61LYX5ykJnED2w");
                checkout.setImage(R.drawable.razorpay);
                JSONObject object = new JSONObject();
                try{
                    object.put("abc","my payment");
                    object.put("description","Test Payment");
                    object.put("theme.color","#0093DD");
                    object.put("currency","INR");
                    object.put("amount",payableamount);
                    object.put("prefill.contact","9090909999");
                    object.put("prefill.email","crixbazz@rzp.com");
                    checkout.open(BookActivity.this,object);

                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void onPaymentSuccess(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Payment ID");

        builder.setMessage(s);

        builder.show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }
}