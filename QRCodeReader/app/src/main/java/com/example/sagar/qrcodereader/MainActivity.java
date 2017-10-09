package com.example.sagar.qrcodereader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button startScan, generateCode;
    TextView scanedType, scanedContent;

    scanner _Scanner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            startScan = (Button) findViewById(R.id.button);
            generateCode = (Button) findViewById(R.id.button2);
            scanedContent = (TextView) findViewById(R.id.scanContent);
            scanedType = (TextView) findViewById(R.id.scanFormat);

            startScan.setOnClickListener(this);
            generateCode.setOnClickListener(this);
            _Scanner = new codeScanner(this, getApplicationContext());

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button: {
                Toast.makeText(getApplicationContext(), "here", Toast.LENGTH_LONG);
                _Scanner.startScan();
                break;
            }
            case R.id.button2: {
                try {
                   Intent callGeneratingClass=new Intent(MainActivity.this,QR_CODE.class);
                    startActivity(callGeneratingClass);


                } catch (Exception c) {
                    Toast.makeText(getApplicationContext(), c.toString(), Toast.LENGTH_LONG).show();
                }
            }

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        IntentResult result= _Scanner.getScanedResult(requestCode,resultCode,data);
        if(result!=null) {
            scanedContent.setText(result.getContents());
            scanedType.setText(result.getFormatName());
            Toast.makeText(this, "FORMAT: " + result.getFormatName() + " \nCONTENT: " + result.getContents(), Toast.LENGTH_LONG).show();
        }
    }
}
