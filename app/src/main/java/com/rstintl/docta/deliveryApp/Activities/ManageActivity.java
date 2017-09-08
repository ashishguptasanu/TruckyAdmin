package com.rstintl.docta.deliveryApp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.rstintl.docta.deliveryApp.R;

public class ManageActivity extends AppCompatActivity implements View.OnClickListener{
    CardView cardViewAddDriver, cardDriverView, cardViewAddVehicle, cardViewInventory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back_button);
        setSupportActionBar(toolbar);
        cardViewAddDriver = (CardView)findViewById(R.id.card_add_driver);
        cardViewAddDriver.setOnClickListener(this);
        cardDriverView = (CardView)findViewById(R.id.card_view_driver);
        cardDriverView.setOnClickListener(this);
        cardViewAddVehicle = (CardView)findViewById(R.id.card_add_vehicle);
        cardViewAddVehicle.setOnClickListener(this);
        cardViewInventory = (CardView)findViewById(R.id.card_view_inventory);
        cardViewInventory.setOnClickListener(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AllDriverActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.card_add_driver:
                Intent intent = new Intent(this, AddDriver.class);
                startActivity(intent);
                break;
            case R.id.card_view_driver:
                Intent intent1 = new Intent(this, ViewDrivers.class);
                startActivity(intent1);
                break;
            case R.id.card_add_vehicle:
                Intent intent2 = new Intent(this, AddVehicle.class);
                startActivity(intent2);
                break;
            case R.id.card_view_inventory:
                Intent intent3 = new Intent(this, ViewVehicle.class);
                startActivity(intent3);
                break;
        }
    }
}
