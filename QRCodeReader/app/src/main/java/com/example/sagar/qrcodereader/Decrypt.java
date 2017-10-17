package com.example.sagar.qrcodereader;

/**
 * Created by sagar on 10/17/2017.
 */

public interface Decrypt {
    void startDecryption(String key,String chiperText);
    String getDecryptedData();
}
