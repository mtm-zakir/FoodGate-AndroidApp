package com.ousl.foodgate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    Button createButton;
    EditText username, email , mobileNumber, password;
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();

        username = (EditText) findViewById(R.id.usernameText);
        email = (EditText) findViewById(R.id.emailText);
        mobileNumber = (EditText) findViewById(R.id.mobileText);
        password = (EditText) findViewById(R.id.passwordText);
        createButton = findViewById(R.id.createButton);
        DB = new DatabaseHelper(this);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernames = username.getText().toString();
                String emails = email.getText().toString();
                String mobileNumbers = mobileNumber.getText().toString();
                String passwords = password.getText().toString();

                if (usernames.equals("") || emails.equals("") || mobileNumbers.equals("") || passwords.equals(""))
                    Toast.makeText(SignUpActivity.this, "Fields Cannot Be Empty", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuser = DB.checkuser(emails);
                    if (checkuser==false) {
                        Boolean insert = DB.insertData(usernames,emails,mobileNumbers,passwords);
                        if (insert==true) {
                            Toast.makeText(SignUpActivity.this, "Account Create Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignUpActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else Toast.makeText(SignUpActivity.this, "User Already Exist", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}