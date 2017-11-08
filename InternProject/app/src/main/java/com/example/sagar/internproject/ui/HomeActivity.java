package com.example.sagar.internproject.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sagar.internproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.buttonAddRecord)
    Button buttonAddData;
    @BindView(R.id.buttonSearchRecord)
    Button buttonSearchData;
    @BindView(R.id.buttonViewRecord)
    Button buttonViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonAddRecord)
    void openAddingActivity() {
        Intent callGeneratingClass = new Intent(HomeActivity.this, AddingRecordActivity.class);
        startActivity(callGeneratingClass);
    }

    @OnClick(R.id.buttonSearchRecord)
    void openSearchingActivity() {
        Intent callGeneratingClass = new Intent(HomeActivity.this, SearchingRecordActivity.class);
        startActivity(callGeneratingClass);
    }

    @OnClick(R.id.buttonViewRecord)
    void openViewingActivity() {
        Intent callGeneratingClass = new Intent(HomeActivity.this, ViewRecordActivity.class);
        startActivity(callGeneratingClass);
    }

}
