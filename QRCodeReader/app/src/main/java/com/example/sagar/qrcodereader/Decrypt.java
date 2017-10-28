package com.example.sagar.qrcodereader;

import java.security.PrivateKey;

/**
 * Created by sagar on 10/17/2017.
 */

public interface Decrypt {
    void startDecryption(String key,String chiperText);
    void startDecryption(PrivateKey key, String chiperText);
    String getDecryptedData();
}
