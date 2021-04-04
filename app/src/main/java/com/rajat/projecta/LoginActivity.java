package com.rajat.projecta;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rajat.projecta.Fragment.HomeFragment;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email;
    private EditText password;
    private Button loginBtn;
    private TextView registerLink;
    private TextView toploginBtn;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);
        loginBtn = findViewById(R.id.loginBtn);
        registerLink = findViewById(R.id.registerLink);
        toploginBtn = findViewById(R.id.loginTopBtn);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        if(isLoggedIn()){
            Toast.makeText(this, "Welcome "+mAuth.getCurrentUser().getDisplayName(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), FragmentActivity.class));
            finish();
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();
                email.clearFocus();
                password.clearFocus();

                final String Email = email.getText().toString().trim();
                final String Password = password.getText().toString().trim();

                if(Email.isEmpty())
                    Toast.makeText(LoginActivity.this, "Email can't be empty...", Toast.LENGTH_SHORT).show();
                else if (Password.isEmpty())
                    Toast.makeText(LoginActivity.this, "Password can't be empty...", Toast.LENGTH_SHORT).show();
                else {
                    mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                //If successful then update UI
                                FirebaseUser user = mAuth.getCurrentUser();

                                updateUI(user);
                            }else{
                                Toast.makeText(LoginActivity.this, "Authentication failed with following message : "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


        // second btn to login situated at top of page
        toploginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();
                email.clearFocus();
                password.clearFocus();

                final String Email2 = email.getText().toString().trim();
                final String Password2 = password.getText().toString().trim();

                if(Email2.isEmpty())
                    Toast.makeText(LoginActivity.this, "Email can't be empty...", Toast.LENGTH_SHORT).show();
                else if (Password2.isEmpty())
                    Toast.makeText(LoginActivity.this, "Password can't be empty...", Toast.LENGTH_SHORT).show();
                else {
                    mAuth.signInWithEmailAndPassword(Email2, Password2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                //If successful then update UI
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            }else{
                                Toast.makeText(LoginActivity.this, "Authentication failed with following message : "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });



        // goto register user page
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                finish();
            }
        });
    }

    // check if user already logged in
    public boolean isLoggedIn() {
        return mAuth.getCurrentUser() != null;
    }

    //updating if user successfully registered
    public void updateUI(FirebaseUser user) {
        if(user != null) {
            Toast.makeText(this, "Welcome "+user.getDisplayName(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), FragmentActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Username can't be null", Toast.LENGTH_SHORT).show();
        }
    }


    // closing keyboard
    final void closeKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
