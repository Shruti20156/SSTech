package com.shruti.school;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.lottie.LottieAnimationView;

public class aftersplashActivity extends AppCompatActivity {
    LottieAnimationView laview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aftersplash);

        // Initialize the LottieAnimationView
        laview = findViewById(R.id.lot);

        laview.setAnimation(R.raw.lod1); // Ensure lottie1.json is placed in res/raw folder
        laview.playAnimation();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(aftersplashActivity.this, LoginActivity.class);
                startActivity(i);
                finish(); // Close this activity
            }
        }, 2000);
    }
}
