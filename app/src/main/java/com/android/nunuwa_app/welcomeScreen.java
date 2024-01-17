package com.android.nunuwa_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.nunuwa_app.StrtingScreen.firstScreen;

public class welcomeScreen extends AppCompatActivity {

    public static final int SPLASH_TIME_OUT = 1900;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TextView nunuw_text_id =  findViewById(R.id.nunuw_text_id);
//        tV =(TextView) findViewById(R.id.version);
//        txtM =(TextView) findViewById(R.id.txtmotor);
        Animation anZoom = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);
        nunuw_text_id.startAnimation(anZoom);

//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                Intent ihomeIntent = new Intent(welcomeScreen.this, welcomeScreen.class);
//                startActivity(ihomeIntent);
//                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//                finish();
//            }
//        }, SPLASH_TIME_OUT);


//        txt4 = (TextView) findViewById(R.id.txt4Slide);
        Animation moveToLeft = AnimationUtils.loadAnimation(welcomeScreen.this, R.anim.move_left);
        Animation moveToRight = AnimationUtils.loadAnimation(welcomeScreen.this, R.anim.move_right);


//        txt1.startAnimation(moveToRight);
//        txt2.startAnimation(moveToLeft);
//        txt3.startAnimation(moveToRight);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent ihomeIntent = new Intent(welcomeScreen.this, firstScreen.class);
                startActivity(ihomeIntent);
                //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }

    @Override
    protected void onStart() {
        super.onStart();
//        Animation animimg = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.moveup);
//        Animation anim2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.first_screen);
//
//        //image1.startAnimation(animimg);
////        txtM.startAni mation(animimg);
//        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bouncetext);
////        tV.startAnimation(animation);

    }

}
