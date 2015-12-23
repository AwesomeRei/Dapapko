package com.example.andre.firebasecweather;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.andre.firebasecweather.MainActivity;
import com.example.andre.firebasecweather.R;
import com.firebase.client.Firebase;

/**
 * Created by Andre on 12/5/2015.
 */
public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT=2000;

    Firebase mRef;
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
        mRef = new Firebase("https://dpp.firebaseio.com/Recipe");
        mRef.keepSynced(true);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent i = new Intent(SplashScreen.this,MainActivity.class);

                startActivity(i);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
