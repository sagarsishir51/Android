package com.example.sagar.internproject.adapter;

import android.os.StrictMode;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Sagar on 12/11/2017.
 */

public class NetworkAdapter {
    StrictMode.ThreadPolicy policy;
    HttpURLConnection request;
    public URL getmUrl() {
        return mUrl;
    }

    URL mUrl;
    public NetworkAdapter() {
         policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    public void setURL(String s) throws MalformedURLException {
        mUrl=new URL(s);
    }

    public void PostMethodConnect() throws IOException {
        if(request==null){
        request = (HttpURLConnection) mUrl.openConnection();
        request.setRequestMethod("POST");
        request.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        request.setDoOutput(true);
        request.connect();
        }
        else {return;}
    }

    public void sendJsonObject(JSONObject jsonObject) throws IOException {
        DataOutputStream wr = new DataOutputStream(request.getOutputStream());
        wr.writeBytes(jsonObject.toString());
        wr.flush();
        wr.close();
    }

    public int getResponseCode() throws IOException {
        if(request!=null){
    return request.getResponseCode();}
    return -1;
    }

    public void close() {
        request.disconnect();
    }

    public void GetMethodConnection() throws IOException {
        if(request==null){
        request = (HttpURLConnection) mUrl.openConnection();
        request.setRequestMethod("GET");
        request.connect();}
        else{
            return;
        }
    }

    public InputStreamReader getJsonData() throws IOException {
        if (request != null) {
            InputStream responseBody = request.getInputStream();
            InputStreamReader responseBodyReader =
                    new InputStreamReader(responseBody, "UTF-8");
            return responseBodyReader;
        }
        return null;
    }
}
