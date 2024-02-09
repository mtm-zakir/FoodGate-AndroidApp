package com.ousl.foodgate;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ousl.foodgate.model.UserModel;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    String userData;
    Button logoutButton;
    TextView userName,userEmail,userPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("User Profile");

        //Receive Pushed Database Data
        userData=getIntent().getStringExtra("user_data");

        //Parse Database Data
        userName = findViewById(R.id.userNameText);
        userEmail = findViewById(R.id.userEmailText);
        userPhone = findViewById(R.id.userPhoneText);

        logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogout();
            }
        });

        getUserDetails();
    }
    //Function Start Login Activity
    public void openLogout() {
        Toast.makeText(UserActivity.this, "Logout Successful", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    //Function Get Current User Details
    public void getUserDetails(){
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        ArrayList<UserModel> aList = databaseHelper.getUserDetails(userData);
        UserModel userModel = aList.get(0);
        userName.setText(userModel.getUsed_name());
        userEmail.setText(userModel.getUsed_email());
        userPhone.setText("0"+userModel.getUsed_phone());
    }

    //Function Back Press
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, DashboardActivity.class);
        intent.putExtra("user_data",userData);  //User Data Push
        startActivity(intent);
    }
}