package com.example.sagar.qrcodereader;

import android.util.Base64;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by sagar on 10/17/2017.
 */

public class AES_Algo implements Encrypt,Decrypt {
    Cipher AESchiper;
    String decryptMessage;
    String encryptedMessage;
    SecretKeySpec secretKeySpec;
    public AES_Algo() {
        try {
            AESchiper=Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (NoSuchAlgorithmException |NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    byte[] generateRandomIv() {
        int ivSize = 16;
        byte[] iv = new byte[ivSize];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        return iv;
    }
    SecretKey generateKey() {
        KeyGenerator _generator=null;
        try {
            _generator=KeyGenerator.getInstance("AES");

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return _generator.generateKey();
    }
    SecretKeySpec generateKey(String keySpace) {
        secretKeySpec= new SecretKeySpec(keySpace.getBytes(),"AES");
        return secretKeySpec;
    }
    @Override
    public String getEncryptedData() {
        return encryptedMessage;
        // TODO Auto-generated method stub
        //return null;
    }
    @Override
    public String getDecryptedData() {
        return decryptMessage;
        // TODO Auto-generated method stub
        //return null;
    }
    @Override
    public void startDecryption(String key,String chiperText) {
        try {

            //System.out.println(ivP);
            byte []temp = Base64.decode(chiperText,Base64.DEFAULT);
            byte[] ivpattern= Arrays.copyOfRange(temp, 0, 16);
            byte[] message=Arrays.copyOfRange(temp, 16, temp.length);

            AESchiper.init(Cipher.DECRYPT_MODE,generateKey(key),new IvParameterSpec(ivpattern));
            //System.out.println(new String(Base64.encode(iv)));
            decryptMessage=new String(AESchiper.doFinal(message));
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Override
    public void startEncryption(String key, String messageToEncrypt) {
        // TODO Auto-generated method stub
        try {
            byte[] randomIV=generateRandomIv();
            AESchiper.init(Cipher.ENCRYPT_MODE,generateKey(key),new IvParameterSpec(randomIV));
            byte[] cipherText=AESchiper.doFinal(messageToEncrypt.getBytes());
            byte[] cipherMessageToStore=new byte[randomIV.length+cipherText.length];
            System.arraycopy(randomIV, 0, cipherMessageToStore, 0, randomIV.length);
            System.arraycopy(cipherText, 0, cipherMessageToStore, randomIV.length, cipherText.length);
            encryptedMessage=new String(Base64.encode(cipherMessageToStore,Base64.DEFAULT));
        } catch (InvalidAlgorithmParameterException|InvalidKeyException|BadPaddingException | IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}


