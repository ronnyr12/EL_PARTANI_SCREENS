package com.example.el_partani_screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash_screen extends AppCompatActivity {
    Animation topAnimation, bottomAnimation;
    ImageView imageAnim;
    TextView appName;
    private static int SPLASH_SCREEN=2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //animations
        topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        //Hooks
        imageAnim=findViewById(R.id.imageView);
        appName=findViewById(R.id.appName);


        imageAnim.setAnimation(topAnimation);
        appName.setAnimation(bottomAnimation);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(Splash_screen.this,login_screen.class );
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);

    }
}