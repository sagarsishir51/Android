package com.example.sagar.internproject.ui;

import android.content.Intent;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sagar.internproject.ApiURLUser;
import com.example.sagar.internproject.R;
import com.example.sagar.internproject.adapter.JsonAdapter;
import com.example.sagar.internproject.adapter.ListAdapter;
import com.example.sagar.internproject.adapter.NetworkAdapter;
import com.example.sagar.internproject.model.UserInfo;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ViewRecordActivity extends AppCompatActivity implements KeyEvent.Callback {
  //  userInfoDao userDao;
    @BindView(R.id.ListView)
    ListView listView;
    ListAdapter listAdapter;
    List<UserInfo> infoList;
    NetworkAdapter mNetworkAdapter;
    JsonAdapter mJsonAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNetworkAdapter=new NetworkAdapter();
        mJsonAdapter=new JsonAdapter();
        setContentView(R.layout.activity_view_record);
        //DaoSession daoSession=((App)getApplication()).getDaoSession();
        //userDao=daoSession.getUserInfoDao();
        listAdapter=new ListAdapter();
        ButterKnife.bind(this);
        getDataFromAPI();
        getListOfUsers();
        callBackAction();
    }

    private void getDataFromAPI() {
        try {
            mNetworkAdapter.setURL(ApiURLUser.LOCALHOST+ApiURLUser.GET_ALL_USER);
            mNetworkAdapter.GetMethodConnection();
            int responseCode=mNetworkAdapter.getResponseCode();
            Toast.makeText(getApplicationContext(),""+responseCode,Toast.LENGTH_LONG).show();
            InputStreamReader responseBodyReader1 =mNetworkAdapter.getJsonData();
            mJsonAdapter.makeJsonReader(responseBodyReader1);
            infoList=mJsonAdapter.readJsonData();
            mNetworkAdapter.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    void getListOfUsers(){
    listAdapter.loadDataToAdapter(infoList);
    List<String> names=new ArrayList<String>();
    if(listAdapter.getInfo().size()!=0){
        for(int i=0;i<listAdapter.getInfo().size();i++){
            names.add(listAdapter.getInfo().get(i).getName());
        }
    }
    else{
        Toast.makeText(getApplicationContext(),"empty data",Toast.LENGTH_LONG).show();
        return;
    }
    listAdapter.initializeAdapter(getApplicationContext(),R.layout.displaylayout,names);
    listView.setAdapter(listAdapter.getUserAdapter());
}
    void callBackAction(){
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
            Intent callGeneratingClass = new Intent(ViewRecordActivity.this, RecordEditingActivity.class);
            Long userNameId=null;
            for(int j=0;j<listAdapter.getInfo().size();j++){
                if(j==i){
                    userNameId=listAdapter.getInfo().get(j).getId();
                }
            }
            Toast.makeText(getApplicationContext(),"in "+userNameId,Toast.LENGTH_SHORT).show();

            callGeneratingClass.putExtra("id",userNameId);
            startActivity(callGeneratingClass);
        }
    });

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
    @Override
    protected  void onResume(){
        super.onResume();
        getListOfUsers();
    }
}
