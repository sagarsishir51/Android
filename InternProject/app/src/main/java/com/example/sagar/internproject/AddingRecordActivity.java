package com.example.sagar.internproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddingRecordActivity extends AppCompatActivity implements View.OnClickListener,KeyEvent.Callback {
    userInfoDao userDao;
    Button buttonSaveData;
    EditText username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_record);

        buttonSaveData=(Button)findViewById(R.id.buttonSaveUserData);
        buttonSaveData.setOnClickListener(this);

        username=(EditText)findViewById(R.id.editUserName);

        DaoSession daoSession=((App)getApplication()).getDaoSession();
        userDao=daoSession.getUserInfoDao();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonSaveUserData:{
                userInfo userInfo=new userInfo();
                userInfo.setName(username.getText().toString().trim());
                userDao.insert(userInfo);
                Toast.makeText(getApplicationContext(),"Added Successfully",Toast.LENGTH_LONG).show();
                finish();
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
