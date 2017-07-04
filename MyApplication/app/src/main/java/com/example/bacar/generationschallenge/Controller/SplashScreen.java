package com.example.bacar.generationschallenge.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.bacar.generationschallenge.R;
import com.example.bacar.generationschallenge.Test.NavMainActivity;
import com.romainpiel.shimmer.Shimmer;

/**
 * Created by Bacar on 16/06/2017.
 */

public class SplashScreen extends Activity {

    private static int SPLASH_TIME_OUT = 4500;
    private Shimmer shimmer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

        setContentView(R.layout.splash_screen);

       /* shimmer = new Shimmer();
        shimmer.start(R.id.titleSplash); */

        new Handler().postDelayed(new Runnable () {

            @Override
            public void run() {
                Intent act = new Intent(SplashScreen.this, NavMainActivity.class);
                startActivity(act);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}
