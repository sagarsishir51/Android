package com.example.sagar.qrcodereader;

import java.security.PublicKey;

/**
 * Created by sagar on 10/17/2017.
 */

public interface Encrypt {
    String getEncryptedData();
    void startEncryption(String key, String messageToEncrypt);
    void startEncryption(PublicKey key, String messageToEncrypt);
}
