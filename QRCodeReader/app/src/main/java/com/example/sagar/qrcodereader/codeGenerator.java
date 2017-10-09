package com.example.sagar.qrcodereader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.FileOutputStream;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.Exchanger;

/**
 * Created by sagar on 10/8/2017.
 */

public class codeGenerator implements generator {
    String messageToEncrypt,filePath;
    String key;
    Bitmap _bitmap;
    ImageView outputImage;
    Context context;
    File file;
    public codeGenerator(Context c){
        _bitmap=null;
        context=c;

    }
    @Override
    public void  setMessage(String message){
        messageToEncrypt=message;

    }


    @Override
    public void generateCode() {
        if(outputImage!=null) {
            encodeAsBitmap(messageToEncrypt, BarcodeFormat.QR_CODE, 256, 256);
             outputImage.setImageBitmap(_bitmap);
            saveBitmap();
        }
    }
    void saveBitmap(){

        try{
             file = new File(context.getCacheDir(), messageToEncrypt + ".png");
            FileOutputStream fOut = new FileOutputStream(file);
            _bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, false);
        }
        catch(Exception e){
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();

        }
    }
    @Override
    public void setImageView(ImageView view){
        outputImage= view;
    }

    @Override
    public Uri getUri() {
        return Uri.fromFile(file);
    }

    @Override
    public ImageView getGeneratedCodeImage() {
        return outputImage;
    }

    void encodeAsBitmap(String message, BarcodeFormat format,int IMGwidth,int IMGheight) {
        try {
            if (message == null) {
                return;
            }
            Map<EncodeHintType, Object> hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            MultiFormatWriter writer = new MultiFormatWriter();
            BitMatrix result = null;
            try {
                result = writer.encode(message, format, IMGwidth, IMGheight, hints);
            } catch (IllegalArgumentException iae) {
                // Unsupported format
                return;
            } catch (WriterException e) {
                e.printStackTrace();
            }
            int width = result.getWidth();
            int height = result.getHeight();
            int[] pixels = new int[width * height];
            for (int y = 0; y < height; y++) {
                int offset = y * width;
                for (int x = 0; x < width; x++) {
                    pixels[offset + x] = result.get(x, y) ? Color.BLACK : Color.WHITE;
                }
            }

            _bitmap = Bitmap.createBitmap(width, height,
                    Bitmap.Config.ARGB_8888);
            _bitmap.setPixels(pixels, 0, width, 0, 0, width, height);

        } catch (Exception e) {
            Toast.makeText(context, "inside"+e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
