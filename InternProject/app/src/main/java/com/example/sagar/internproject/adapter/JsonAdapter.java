package com.example.sagar.internproject.adapter;

import android.util.JsonReader;

import com.example.sagar.internproject.model.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sagar on 12/11/2017.
 */

public class JsonAdapter {
    public JSONObject getmJsonObject() {
        return mJsonObject;
    }

    JSONObject mJsonObject;
    JsonReader mJsonReader;
    public JsonAdapter(){}
    public void makeJsonObject(){
        mJsonObject=new JSONObject();

    }

    public void put(String id, long i) throws JSONException {
    mJsonObject.put(id,i);
    }
    public void put(String idName, String value ) throws JSONException {
    mJsonObject.put(idName,value);
    }


    public void makeJsonReader(InputStreamReader responseBodyReader) {
        mJsonReader = new JsonReader(responseBodyReader);
    }

    public List<UserInfo> readJsonData() throws IOException {
        List<UserInfo> mUserInfoList=new ArrayList<UserInfo>();
        mJsonReader.beginArray();
        while (mJsonReader.hasNext()) {
            mUserInfoList.add(getUser(mJsonReader));
        }
        mJsonReader.endArray();
        return mUserInfoList;

    }

    private UserInfo getUser(JsonReader mJsonReader) throws IOException {
        mJsonReader.beginObject();
        long id = -1;
        String text = null;
        while (mJsonReader.hasNext()) {
            String readString = mJsonReader.nextName();
            if(readString.equals("id")){
                id=mJsonReader.nextLong();
            }
            else if(readString.equals("name")){
                text=mJsonReader.nextString();
            }
            else{
                mJsonReader.skipValue();
            }
        }
        mJsonReader.endObject();
        return new UserInfo(id,text);
    }
}
