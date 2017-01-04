package com.test.demo.myapp.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.test.demo.myapp.R;

/**
 * Created by marsyv on 18.08.2016.
 */
public class ZxingGenerateBarcodeActvity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zxing_generate_barcode_activity);

        imageView = (ImageView)findViewById(R.id.barcode_image_view);

        com.google.zxing. MultiFormatWriter writer =new MultiFormatWriter();


        String finaldata = Uri.encode("7040280013102", "utf-8");

        BitMatrix bm = null;
        try {
            bm = writer.encode(finaldata, BarcodeFormat.EAN_13,150, 150);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        Bitmap ImageBitmap = Bitmap.createBitmap(180, 40, Bitmap.Config.ARGB_8888);

        for (int i = 0; i < 180; i++) {//width
            for (int j = 0; j < 40; j++) {//height
                ImageBitmap.setPixel(i, j, bm.get(i, j) ? Color.BLACK: Color.WHITE);
            }
        }

        if (ImageBitmap != null) {
            imageView.setImageBitmap(ImageBitmap);
        } else {
            Toast.makeText(getApplicationContext(), "Error",
                    Toast.LENGTH_SHORT).show();
        }

    }
}
