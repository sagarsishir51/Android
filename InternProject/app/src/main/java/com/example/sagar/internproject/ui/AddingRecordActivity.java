package com.example.sagar.internproject.ui;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sagar.internproject.ApiURLUser;
import com.example.sagar.internproject.R;
import com.example.sagar.internproject.adapter.JsonAdapter;
import com.example.sagar.internproject.adapter.NetworkAdapter;
import com.example.sagar.internproject.model.UserInfo;
import com.example.sagar.internproject.model.userInfoDao;


import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddingRecordActivity extends AppCompatActivity implements KeyEvent.Callback {
    userInfoDao userDao;
    @BindView(R.id.buttonSaveUserData)
    Button buttonSaveData;
    @BindView(R.id.editUserName)
    EditText username;
    StrictMode.ThreadPolicy policy;
    NetworkAdapter mNetworkAdapter;
    JsonAdapter mJsonAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_record);
        mNetworkAdapter=new NetworkAdapter();
        mJsonAdapter=new JsonAdapter();
      //  DaoSession daoSession=((App)getApplication()).getDaoSession();
       // userDao=daoSession.getUserInfoDao();
        ButterKnife.bind(this);
    }
    @OnClick(R.id.buttonSaveUserData)
    void addUserRecord()  {
        UserInfo userInfo=new UserInfo();
        userInfo.setName(username.getText().toString().trim());
        mJsonAdapter.makeJsonObject();
        try {

            mJsonAdapter.put("id",-1);
            mJsonAdapter.put("name",userInfo.getName());
            mNetworkAdapter.setURL(ApiURLUser.LOCALHOST+ApiURLUser.CREATE_USER);
            mNetworkAdapter.PostMethodConnect();
            mNetworkAdapter.sendJsonObject(mJsonAdapter.getmJsonObject());
            int responseCode=mNetworkAdapter.getResponseCode();
            mNetworkAdapter.close();
            Toast.makeText(getApplicationContext(),""+responseCode,Toast.LENGTH_LONG).show();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        //  userDao.insert(UserInfo);
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
