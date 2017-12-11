package com.example.sagar.internproject;

import android.app.Application;

import com.example.sagar.internproject.model.DaoMaster;
import com.example.sagar.internproject.model.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by sagar on 11/5/2017.
 */

public class App extends Application {
    private DaoSession daoSession;
    private String databaseName;
    @Override
    public void onCreate(){
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "UserInfo-db");
        Database db = helper.getWritableDb();
        databaseName=helper.getDatabaseName();
        daoSession = new DaoMaster(db).newSession();
    }
    public DaoSession getDaoSession() {
        return daoSession;
    }
    public String getDatabaseName(){
        return databaseName;
    }
}
