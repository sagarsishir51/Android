package com.example.sagar.internproject.adapter;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sagar.internproject.R;
import com.example.sagar.internproject.model.userInfo;

import java.util.List;

/**
 * Created by sagar on 11/8/2017.
 */

public class ListAdapter {
    List<userInfo> info;

    public ArrayAdapter<String> getUserAdapter() {
        return userAdapter;
    }

    ArrayAdapter<String> userAdapter;
    public ListAdapter(){}
    public void loadDataToAdapter(List<userInfo>list){
        info=list;
    }
    public List<userInfo> getInfo() {
        return info;
    }
    public void initializeAdapter(Context c, int layoutIdentity , List<String> names){
        userAdapter=new ArrayAdapter<String>(c,layoutIdentity,names);
    }



}
