package com.rajat.projecta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email;
    private EditText password;
    private Button registerBtn;
    private EditText userName;
    private TextView loginLink;
    private TextView topregisterBtn;
    private DatabaseReference mDatabase;

    final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = findViewById(R.id.registerEmail);
        password = findViewById(R.id.registerPassword);
        registerBtn = findViewById(R.id.registerBtn);
        userName = findViewById(R.id.registerUsername);
        loginLink = findViewById(R.id.LoginBtnOnRegister);
        topregisterBtn = findViewById(R.id.registerBtn2);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();


        // checking if user already logged in or not
        if(isLoggedIn()){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        // clicking activity on Create Account Button
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();
                email.clearFocus();
                password.clearFocus();

                //sign in method
                final String Email = email.getText().toString().trim();
                final String Password = password.getText().toString().trim();
                final String UserId = userName.getText().toString().trim();

                if(Email.isEmpty())
                    Toast.makeText(RegisterActivity.this, "Email Field can't be empty...", Toast.LENGTH_SHORT).show();
                else if(Password.isEmpty())
                    Toast.makeText(RegisterActivity.this, "Password can't be empty...", Toast.LENGTH_SHORT).show();
                else if(UserId.isEmpty())
                    Toast.makeText(RegisterActivity.this, "UserName can't be empty...", Toast.LENGTH_SHORT).show();
                else{
                    mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                //If sign in successful then update UI
                                FirebaseUser user = mAuth.getCurrentUser();
                                writeNewUser(UserId, Email, Password);
                                updateUI(user);
                            } else{
                                Toast.makeText(RegisterActivity.this, "Authentication failed with following message /n"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


        //clicking activity on top situated register account button
        topregisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();
                email.clearFocus();
                password.clearFocus();

                //sign in method
                final String Email = email.getText().toString().trim();
                final String Password = password.getText().toString().trim();
                final String UserId = userName.getText().toString().trim();

                if(Email.isEmpty())
                    Toast.makeText(RegisterActivity.this, "Email Field can't be empty...", Toast.LENGTH_SHORT).show();
                else if(Password.isEmpty())
                    Toast.makeText(RegisterActivity.this, "Password can't be empty...", Toast.LENGTH_SHORT).show();
                else if(UserId.isEmpty())
                    Toast.makeText(RegisterActivity.this, "UserName can't be empty...", Toast.LENGTH_SHORT).show();
                else{
                    mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                //If sign in successful then update UI
                                FirebaseUser user = mAuth.getCurrentUser();
                                writeNewUser(UserId, Email, Password);
                                updateUI(user);
                            } else{
                                Toast.makeText(RegisterActivity.this, "Authentication failed with following message /n"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


        //clicking activity to launch login page
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
            Toast.makeText(this, "Welcome "+user.getEmail(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
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

    void writeNewUser(String UserId, String Email, String Password){
        User user = new User(Email, Password);
        mDatabase.child("users").child(UserId).setValue(user);
    }

}

// user defination for realtime database
class User {
    public String Password;
    public String Email;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    // parameterized constructor
    public User(String Email, String Password) {
        this.Password = Password;
        this.Email = Email;
    }
}
