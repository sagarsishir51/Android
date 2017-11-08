package com.example.sagar.internproject.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sagar.internproject.App;
import com.example.sagar.internproject.DaoSession;
import com.example.sagar.internproject.R;
import com.example.sagar.internproject.adapter.ListAdapter;
import com.example.sagar.internproject.model.userInfo;
import com.example.sagar.internproject.userInfoDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DisplaySearchedRecordActivity extends AppCompatActivity {
    @BindView(R.id.ListViewDisplaySearchedRecord)
    ListView listView;
    userInfoDao userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_searched_record);
        DaoSession daoSession=((App)getApplication()).getDaoSession();
        userDao=daoSession.getUserInfoDao();
        ButterKnife.bind(this);
        try {
           String searchedWord  = getIntent().getStringExtra("searchedWord");
            QueryBuilder queryBuilder=userDao.queryBuilder().where(userInfoDao.Properties.Name.eq(searchedWord));
            List<userInfo> searchedData=queryBuilder.list();
            if(searchedData.size()==0){
                queryBuilder=userDao.queryBuilder().where(userInfoDao.Properties.Name.like("%"+searchedWord+"%"));
                searchedData=queryBuilder.list();
            }
            ListAdapter listAdapter=new ListAdapter();
            listAdapter.loadDataToAdapter(searchedData);
            List<String> names=new ArrayList<String>();
            if(listAdapter.getInfo().size()!=0){
                for(int i=0;i<listAdapter.getInfo().size();i++){
                    names.add(listAdapter.getInfo().get(i).getName());
                }
                Collections.sort(names, String.CASE_INSENSITIVE_ORDER);
                listAdapter.initializeAdapter(getApplicationContext(),R.layout.displaylayout,names);
                listView.setAdapter(listAdapter.getUserAdapter());
            }
            else{

                Toast.makeText(getApplicationContext(),"empty data",Toast.LENGTH_LONG).show();
                return;
            }
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(),"in intend"+e.toString(),Toast.LENGTH_SHORT).show();
        }
    }
}
