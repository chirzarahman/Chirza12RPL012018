package com.example.chiza12rpl012018;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import static android.os.Build.ID;

public class SplashscreenActivity extends AppCompatActivity {

    private static int SplashScreen = 1150;

    private Animation topAnim, bottomAnim;
    private ImageView imgLogo;
    private TextView txtLogo;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.left_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        imgLogo = findViewById(R.id.imageLogo);
        txtLogo = findViewById(R.id.textLogo);

        imgLogo.setAnimation(topAnim);
        txtLogo.setAnimation(topAnim);

        SharedPreferences sharedPreferences = getSharedPreferences("data users", MODE_PRIVATE);
        id = sharedPreferences.getString("id", "");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (id.isEmpty()) {
                    Intent intent = new Intent(SplashscreenActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashscreenActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },SplashScreen);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView(); //set status background black
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); //set status text  light
            getWindow().setStatusBarColor(ContextCompat.getColor(SplashscreenActivity.this, R.color.blue));
        }
    }
}