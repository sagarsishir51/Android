package com.example.sagar.internproject.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sagar.internproject.App;
import com.example.sagar.internproject.DaoSession;
import com.example.sagar.internproject.R;
import com.example.sagar.internproject.model.userInfo;
import com.example.sagar.internproject.userInfoDao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddingRecordActivity extends AppCompatActivity implements KeyEvent.Callback {
    userInfoDao userDao;
    @BindView(R.id.buttonSaveUserData)
    Button buttonSaveData;
    @BindView(R.id.editUserName)
    EditText username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_record);
        DaoSession daoSession=((App)getApplication()).getDaoSession();
        userDao=daoSession.getUserInfoDao();
        ButterKnife.bind(this);
    }
    @OnClick(R.id.buttonSaveUserData)
    void addUserRecord() {
        userInfo userInfo=new userInfo();
        userInfo.setName(username.getText().toString().trim());
        userDao.insert(userInfo);
        Toast.makeText(getApplicationContext(),"Added Successfully",Toast.LENGTH_LONG).show();
        finish();
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
