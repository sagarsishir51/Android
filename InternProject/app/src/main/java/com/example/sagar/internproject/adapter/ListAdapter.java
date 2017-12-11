package com.example.sagar.internproject.adapter;
import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.sagar.internproject.model.UserInfo;

import java.util.List;

/**
 * Created by sagar on 11/8/2017.
 */

public class ListAdapter {
    List<UserInfo> info;

    public ArrayAdapter<String> getUserAdapter() {
        return userAdapter;
    }

    ArrayAdapter<String> userAdapter;
    public ListAdapter(){}
    public void loadDataToAdapter(List<UserInfo>list){
        info=list;
    }
    public List<UserInfo> getInfo() {
        return info;
    }
    public void initializeAdapter(Context c, int layoutIdentity , List<String> names){
        userAdapter=new ArrayAdapter<String>(c,layoutIdentity,names);
    }



}
