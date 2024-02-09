package com.ousl.foodgate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ousl.foodgate.model.RestaurantModel;

public class CompleteActivity extends AppCompatActivity {

    TextView buttonDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);
        getSupportActionBar().hide();

        RestaurantModel restaurantModel = getIntent().getParcelableExtra("RestaurantModel");

        //Function Done Button
        buttonDone = findViewById(R.id.buttonDone);
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain();
            }
        });
    }

    //Function Start Main Activity
    public void openMain() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }
}