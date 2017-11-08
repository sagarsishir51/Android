package com.example.sagar.internproject.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sagar.internproject.App;
import com.example.sagar.internproject.DaoSession;
import com.example.sagar.internproject.R;
import com.example.sagar.internproject.adapter.ListAdapter;
import com.example.sagar.internproject.userInfoDao;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ViewRecordActivity extends AppCompatActivity implements KeyEvent.Callback {
    userInfoDao userDao;
    @BindView(R.id.ListView)
    ListView listView;
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record);
        DaoSession daoSession=((App)getApplication()).getDaoSession();
        userDao=daoSession.getUserInfoDao();
        listAdapter=new ListAdapter();
        ButterKnife.bind(this);
        //Toast.makeText(getApplicationContext(),((App)getApplicationContext()).getDatabaseName(),Toast.LENGTH_LONG).show();
        callBackAction();
    }

void getListOfUsers(){
    listAdapter.loadDataToAdapter(userDao.loadAll());
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
