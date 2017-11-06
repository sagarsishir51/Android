package com.example.sagar.internproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewRecordActivity extends AppCompatActivity implements KeyEvent.Callback {
    userInfoDao userDao;
    DataAdapter dataAdapter;
    RecyclerView recyclerView;
    ListView listView;
    private Query<userInfo> userInfoQuery;
    List<userInfo> info;
    ArrayAdapter<String> userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record);
        listView=(ListView)findViewById(R.id.ListView);
        DaoSession daoSession=((App)getApplication()).getDaoSession();
        userDao=daoSession.getUserInfoDao();
        Toast.makeText(getApplicationContext(),((App)getApplicationContext()).getDatabaseName(),Toast.LENGTH_LONG).show();
      //  getListOfUsers();
        callBackAction();
    }

void getListOfUsers(){
     info=userDao.loadAll();
    List<String> names=new ArrayList<String>();
    if(info.size()!=0){
        for(int i=0;i<info.size();i++){
           names.add(info.get(i).getName());
        }
    }
    else{
        Toast.makeText(getApplicationContext(),"empty data",Toast.LENGTH_LONG).show();
        return;
    }
    userAdapter=new ArrayAdapter<String>(this,R.layout.displaylayout,names);
    listView.setAdapter(userAdapter);
}
void callBackAction(){

    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
            Intent callGeneratingClass = new Intent(ViewRecordActivity.this, RecordEditingActivity.class);
            Long userNameId=null;
            for(int j=0;j<info.size();j++){
                if(j==i){
                    userNameId=info.get(j).getId();
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
