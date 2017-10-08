package com.example.sagar.qrcodereader;

import android.content.Intent;

import com.google.zxing.integration.android.IntentResult;

/**
 * Created by sagar on 10/7/2017.
 */

public interface scanner {
    void startScan();
    IntentResult getScanedResult(int requestCode, int resultCode, Intent data);
}
