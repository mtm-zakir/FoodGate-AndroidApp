package com.ousl.foodgate;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DeliveryLocationActivity extends AppCompatActivity {

    TextView placeOrder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_location);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Select Delivery Location");

        //Function Place Order
        placeOrder =findViewById(R.id.placeOrderButton);
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(DeliveryLocationActivity.this, CompleteActivity.class);
                    startActivity(intent);
            }
        });

        Fragment fragment=new MapFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.mapLayout,fragment).commit();
    }

}