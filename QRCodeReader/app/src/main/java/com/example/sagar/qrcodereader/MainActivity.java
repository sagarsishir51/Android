package com.example.sagar.qrcodereader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentResult;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button startScan, generateCode,decrypt,fireBaseLogin;
    TextView scanedContentE,scanedContentD;
    EditText keyToEncrypt;
    scanner _Scanner;
    Decrypt ECDecryption;
    KeyManager keyManager;
    String code="EncryptedQR_CODE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            startScan = (Button) findViewById(R.id.button);
            generateCode = (Button) findViewById(R.id.button2);
            decrypt=(Button)findViewById(R.id.buttonDecrypt);
            fireBaseLogin=(Button)findViewById(R.id.buttonFireBase);

            scanedContentE = (TextView) findViewById(R.id.textViewEncrypted);
            scanedContentD = (TextView) findViewById(R.id.textViewDecrypted);

            keyToEncrypt=(EditText)findViewById(R.id.editTextKey);

           keyManager=new KeyManager();
            if(keyManager.checkFirstAttempt(getApplicationContext())){
                Toast.makeText(getApplicationContext(), "private key time"+keyManager.getPrivateKey().toString(), Toast.LENGTH_LONG).show();
                keyManager.writeKeyToPreferences(getApplicationContext());
                Toast.makeText(getApplicationContext(), "public key time"+keyManager.getPublicKey().toString(), Toast.LENGTH_LONG).show();
            }


            startScan.setOnClickListener(this);
            generateCode.setOnClickListener(this);
            decrypt.setOnClickListener(this);
            fireBaseLogin.setOnClickListener(this);
            _Scanner = new codeScanner(this, getApplicationContext());


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button: {
                Toast.makeText(getApplicationContext(), "here", Toast.LENGTH_LONG).show();
                ECDecryption=new EllipticCurve();
                _Scanner.startScan();
                break;
            }
            case R.id.button2: {
                Toast.makeText(getApplicationContext(), "generating", Toast.LENGTH_LONG).show();
                Intent callGeneratingClass = new Intent(MainActivity.this, QR_CODE.class);
                startActivity(callGeneratingClass);
                break;

            }
            case R.id.buttonDecrypt:{
                try {
                    keyManager.readFromPreferences(getApplicationContext());
                    String totalMessage = scanedContentE.getText().toString();
                    String messageToDecrypt = totalMessage.substring(code.length());
                    ECDecryption.startDecryption(keyManager.getPrivateKey(), messageToDecrypt);
                    scanedContentD.setText(ECDecryption.getDecryptedData());
                    Toast.makeText(getApplicationContext(), ECDecryption.getDecryptedData(), Toast.LENGTH_LONG).show();
                    break;
                }
                catch(Exception e){
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();

                }

                break;
            }
            case R.id.buttonFireBase:{
                Toast.makeText(getApplicationContext(), "firebase", Toast.LENGTH_LONG).show();
                Intent callGeneratingClass = new Intent(MainActivity.this, FireBaseActivity.class);
                startActivity(callGeneratingClass);
            }
            break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        IntentResult result= _Scanner.getScanedResult(requestCode,resultCode,data);
        if(result!=null) {
            String scannedData=result.getContents();
            if(scannedData.startsWith(code)){
                //should be decrypted
                decrypt.setVisibility(View.VISIBLE);
                scanedContentD.setVisibility(View.VISIBLE);
            }
            scanedContentE.setVisibility(View.VISIBLE);
            scanedContentE.setText(scannedData);
            Toast.makeText(this, "FORMAT: " + result.getFormatName() + " \nCONTENT: " + result.getContents(), Toast.LENGTH_LONG).show();
        }
    }
}
