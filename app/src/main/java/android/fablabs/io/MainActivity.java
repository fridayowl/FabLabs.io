package android.fablabs.io;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.fablabs.io.Activity.EditProfileActivity;
import android.fablabs.io.Activity.Homepage;
import android.fablabs.io.Activity.OtpActivity;
import android.fablabs.io.Activity.PhoneAuth;
import android.fablabs.io.Activity.PhoneAuthKt;
import android.fablabs.io.Activity.Variables;
import android.fablabs.io.Activity.WhatAppActivity;
import android.fablabs.io.LoginActivity.GoogleSignin;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedpreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        final Handler handler = new Handler();
//        handler.postDelayed(() -> {
//
//
//            try {
//                sharedpreference = getSharedPreferences(Variables.DEFAUlTDATA, Context.MODE_PRIVATE);
//
//                if (sharedpreference.contains(Variables.FLOW)) {
//                    String key = sharedpreference.getString(Variables.FLOW, "null");
//                    if(key.equals("HASHISTORY"))
//                    {
//                        Intent in = new Intent(MainActivity.this, Homepage.class);
//                        startActivity(in);
//                    }else
//                    {
//                        Intent in = new Intent(MainActivity.this, GoogleSignin.class);
//                        startActivity(in);
//                    }
//
//                }
//                else {
//                    Intent in = new Intent(MainActivity.this, GoogleSignin.class);
//                    startActivity(in);
//                }
//            }catch ( Exception e)
//            {
//                Intent in = new Intent(MainActivity.this, GoogleSignin.class);
//                startActivity(in);
//            }
//
//
//
//
//        }, 1000);

        Intent in = new Intent(MainActivity.this, PhoneAuth.class );
        startActivity(in );
    }





}