package com.kilimanjaroz.tips.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.kilimanjaroz.tips.R;


public class SplashActivity extends AppCompatActivity {

    AppCompatImageView logo;
    Animation top, bottom,slideup,leftto_right,bounce;
    SharedPreferences onBoardingScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        initView();
        loadContent();
    }

    private void initView() {

        logo=findViewById(R.id.img_splash);
        top = AnimationUtils.loadAnimation(this, R.anim.bottom);
        bottom = AnimationUtils.loadAnimation(this, R.anim.top);
        slideup = AnimationUtils.loadAnimation(this, R.anim.slide_in);
        leftto_right = AnimationUtils.loadAnimation(this, R.anim.lefttoright);
        bounce = AnimationUtils.loadAnimation(this, R.anim.bounce);
        logo.setAnimation(bounce);

    }

    private void loadContent() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent1);
                finish();
            }
        },3000);
    }
}

