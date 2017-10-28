package com.example.sagar.qrcodereader;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import	android.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Created by sagar on 10/24/2017.
 */

public class EllipticCurve implements Encrypt,Decrypt {
    String algo="ECIES";
    String provider="SC";
    String curveUsed="secp256r1";
    String decryptMessage;
    String encryptedMessage;
    public EllipticCurve() {
        Security.insertProviderAt(new org.spongycastle.jce.provider.BouncyCastleProvider(), 1);

    }
    @Override
    public void startDecryption(String key, String chiperText) {
        // TODO Auto-generated method stub

    }



    @Override
    public String getDecryptedData() {
        // TODO Auto-generated method stub
        return decryptMessage;
    }

    @Override
    public void startEncryption(String key, String messageToEncrypt) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getEncryptedData() {
        // TODO Auto-generated method stub
        return encryptedMessage;
    }
    @Override
    public void startDecryption(PrivateKey key, String chiperText) {
        // TODO Auto-generated method stub
            try {
                Cipher cipher = Cipher.getInstance(algo,provider);
                cipher.init(Cipher.DECRYPT_MODE, key);
                byte[] data=cipher.doFinal(Base64.decode(chiperText,Base64.DEFAULT));
                decryptMessage=new String(data);
            } catch (NoSuchAlgorithmException |InvalidKeyException e) {
                e.printStackTrace();
            } catch (NoSuchProviderException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            }


    }
    @Override
    public void startEncryption( PublicKey key, String messageToEncrypt) {
        // TODO Auto-generated method stub
        // TODO Auto-generated method stub
        try {
            Cipher cipher=Cipher.getInstance(algo,provider);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] data=cipher.doFinal(messageToEncrypt.getBytes());
            encryptedMessage=Base64.encodeToString(data,Base64.DEFAULT);
        } catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}

