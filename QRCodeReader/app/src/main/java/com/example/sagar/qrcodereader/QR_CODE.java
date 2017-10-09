package com.example.sagar.qrcodereader;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class QR_CODE extends AppCompatActivity implements View.OnClickListener,KeyEvent.Callback {
    EditText messageToEncrypt;
    ImageView QRCodeImage;
    generator _generator;
    Button generateQRCode,shareQRCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr__code);
        messageToEncrypt = (EditText) findViewById(R.id.editText);
        generateQRCode = (Button) findViewById(R.id.button3);
        generateQRCode.setOnClickListener(this);
        shareQRCode=(Button) findViewById(R.id.button4);
        shareQRCode.setOnClickListener(this);
        QRCodeImage = (ImageView) findViewById(R.id.imageView);
        _generator = new codeGenerator(getApplicationContext());
        _generator.setImageView(QRCodeImage);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button3: {
                Toast.makeText(getApplicationContext(), "here", Toast.LENGTH_LONG).show();
                _generator.setMessage(messageToEncrypt.getText().toString());
                _generator.generateCode();
                QRCodeImage.setVisibility(View.VISIBLE);
                QRCodeImage = _generator.getGeneratedCodeImage();
                break;
            }
            case R.id.button4: {
                try{

                Intent sharing=new Intent(Intent.ACTION_SEND);
                sharing.setType("image/jepg");
                sharing.putExtra(Intent.EXTRA_STREAM,_generator.getUri());
                    startActivity(Intent.createChooser(sharing,"Share Using"));
                break;
            }
            catch(Exception e){
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();

            }
            }

        }

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK: {
                finish();
                break;
            }
        }
return true;
    }
}
