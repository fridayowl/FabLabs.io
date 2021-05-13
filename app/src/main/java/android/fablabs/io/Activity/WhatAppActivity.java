package android.fablabs.io.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ObbInfo;
import android.fablabs.io.BuildConfig;
import android.fablabs.io.R;
import android.fablabs.io.databinding.ActivityWhatAppBinding;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.zxing.WriterException;

import java.io.File;
import java.io.FileOutputStream;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class WhatAppActivity extends AppCompatActivity {
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    ActivityWhatAppBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityWhatAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.date.setText("DATE: "+ Variables.dateselected);

      //  binding.numberofitems.setText(String.valueOf(Variables.selectedList1.size() + Variables.selectedList2.size() + Variables.selectedList3.size() + Variables.selectedList4.size()));
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);



        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);

        int width = point.x;
        int height = point.y;


        int dimen = width < height ? width : height;
        dimen = dimen * 3 / 4;


        qrgEncoder = new QRGEncoder("https://fablabs-7546c-default-rtdb.firebaseio.com/ORDERDB/"+Variables.DATABASEID+"/"+Variables.SELECTEDDATE, null, QRGContents.Type.TEXT, dimen);
        try {
            // getting our qrcode in the form of bitmap.
            bitmap = qrgEncoder.encodeAsBitmap();
            // the bitmap is set inside our image
            // view using .setimagebitmap method.
         binding.imageview.setImageBitmap(bitmap);
    } catch (WriterException e) {
            // this method is called for
            // exception handling.
            Log.e("Tag", e.toString());
        }
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharecarddata();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent in = new Intent(WhatAppActivity.this,Homepage.class);
        startActivity(in);
        super.onBackPressed();
    }


    public void sharecarddata() {

        try {

            binding.button.setVisibility(View.INVISIBLE);

            getBitmapFromView(binding.layout);


        } catch (Exception e) {

           Log.d("WHATS",e.toString());
        }
    }

    private void getBitmapFromView(View view) {

        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        } else {
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);

        shareImage(returnedBitmap);
    }

    private void shareImage(Bitmap bitmap) {

        File dirSaveFile = getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imagePath = new File(dirSaveFile, "external_files");
        imagePath.mkdir();
        File imageFile = new File(imagePath.getPath(), "fabimage.jpg");

        try {
            String smsNumber = "919846560463";
            FileOutputStream fos = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            Uri uri = FileProvider.getUriForFile(WhatAppActivity.this, BuildConfig.APPLICATION_ID, imageFile);
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("image/jpeg");
            share.putExtra(Intent.EXTRA_STREAM, uri);
            share.putExtra("jid", "919036001411" + "@s.whatsapp.net");
            share.setPackage("com.whatsapp");//package name of the app
            startActivity(Intent.createChooser(share, "BOOKED"));
            binding.button.setVisibility(View.VISIBLE);

        } catch (Exception e) {
            Log.d("TAG",e.toString());


        }

    }
}