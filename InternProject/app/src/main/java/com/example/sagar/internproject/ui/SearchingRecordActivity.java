package com.example.sagar.internproject.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.SearchView;
import com.example.sagar.internproject.App;
import com.example.sagar.internproject.DaoSession;
import com.example.sagar.internproject.R;
import com.example.sagar.internproject.userInfoDao;
import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchingRecordActivity extends AppCompatActivity implements KeyEvent.Callback,SearchView.OnQueryTextListener {
    @BindView(R.id.searchView)
    SearchView searchView;
    userInfoDao userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching_record);
        ButterKnife.bind(this);
        DaoSession daoSession=((App)getApplication()).getDaoSession();
        searchView.setOnQueryTextListener(this);
        userDao=daoSession.getUserInfoDao();

    }
    @Override
    public boolean onQueryTextSubmit(String s) {
        String searchedWord=searchView.getQuery().toString();
        Intent callGeneratingClass = new Intent(SearchingRecordActivity.this, DisplaySearchedRecordActivity.class);
        callGeneratingClass.putExtra("searchedWord",searchedWord);
        startActivity(callGeneratingClass);
        return true;
    }
    @Override
    public  boolean onQueryTextChange(String newText) {
        return  true;
    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK: {
                finish();
                break;
            }
        }
        return true;
    }
}
