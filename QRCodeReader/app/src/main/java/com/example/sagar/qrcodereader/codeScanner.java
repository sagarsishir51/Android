package com.example.sagar.qrcodereader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * Created by sagar on 10/7/2017.
 */

public class codeScanner implements scanner {
    IntentIntegrator _intentIntegrator;
    IntentResult _result;
    Context _context;
    public codeScanner(Activity activity,Context c){
        _context=c;
        _intentIntegrator=new IntentIntegrator(activity);
    }

    @Override
    public void startScan() {
        _intentIntegrator.initiateScan();
        _intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        _intentIntegrator.setPrompt("Scan");
        _intentIntegrator.setCameraId(0);
        _intentIntegrator.setBeepEnabled(true);
    }

    @Override
    public IntentResult getScanedResult(int requestCode, int resultCode, Intent data) {
        _result=_intentIntegrator.parseActivityResult( requestCode,  resultCode,  data);
        if(_result!=null){
            if(_result.getContents()==null){
                Toast.makeText(_context,"cancelled",Toast.LENGTH_LONG);
                return null;
            }
            else{
                return _result;
            }
        }
        Toast.makeText(_context,"null",Toast.LENGTH_LONG);
        return null;
    }
}
