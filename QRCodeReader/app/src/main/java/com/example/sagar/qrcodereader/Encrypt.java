package com.example.sagar.qrcodereader;

/**
 * Created by sagar on 10/17/2017.
 */

public interface Encrypt {
    String getEncryptedData();
    void startEncryption(String key, String messageToEncrypt);
}
