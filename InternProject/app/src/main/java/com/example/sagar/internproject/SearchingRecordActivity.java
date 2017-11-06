package com.example.sagar.internproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class SearchingRecordActivity extends AppCompatActivity implements View.OnClickListener,KeyEvent.Callback {
    SearchView searchView;
    Button searchRecord;
    userInfoDao userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching_record);
        searchView=(SearchView)findViewById(R.id.searchView);
        searchRecord=(Button)findViewById(R.id.buttonSearching);
        searchRecord.setOnClickListener(this);
        DaoSession daoSession=((App)getApplication()).getDaoSession();
        userDao=daoSession.getUserInfoDao();
    }


    @Override
    public void onClick(View view) {
    switch(view.getId()){
        case R.id.buttonSearching:{
           String searchedWord=searchView.getQuery().toString();
            QueryBuilder queryBuilder=userDao.queryBuilder().where(userInfoDao.Properties.Name.eq(searchedWord));
            List<userInfo> searchedData=queryBuilder.list();
            if(searchedData.size()!=0){
                for(int i=0;i<searchedData.size();i++){
                    Toast.makeText(getApplicationContext(),"matched names:"+searchedData.get(i).getName(),Toast.LENGTH_LONG).show();

                }
            }
            else{
                Toast.makeText(getApplicationContext(),"match not found",Toast.LENGTH_LONG).show();
                return;
            }
            break;
        }
        }
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
