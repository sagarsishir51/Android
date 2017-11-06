package com.example.sagar.internproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonAddData,buttonSearchData,buttonViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        buttonAddData=(Button)findViewById(R.id.buttonAddRecord);
        buttonAddData.setOnClickListener(this);
        buttonSearchData=(Button)findViewById(R.id.buttonSearchRecord);
        buttonSearchData.setOnClickListener(this);
        buttonViewData=(Button)findViewById(R.id.buttonViewRecord);
        buttonViewData.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonAddRecord:{
                Intent callGeneratingClass = new Intent(HomeActivity.this, AddingRecordActivity.class);
                startActivity(callGeneratingClass);
                break;
            }

            case R.id.buttonSearchRecord:{
                Intent callGeneratingClass = new Intent(HomeActivity.this, SearchingRecordActivity.class);
                startActivity(callGeneratingClass);
                break;
            }

            case R.id.buttonViewRecord:{
                Intent callGeneratingClass = new Intent(HomeActivity.this, ViewRecordActivity.class);
                startActivity(callGeneratingClass);
                break;
            }

        }
    }
}
