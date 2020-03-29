package com.example.corona;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EasySplashScreen config = new EasySplashScreen(Splash.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(4000)
                .withBackgroundResource(android.R.color.holo_blue_dark)
                .withHeaderText("")
                .withFooterText("Developed by Shrinidhi Hegde")
                .withBeforeLogoText("covED-19")
                .withLogo(R.mipmap.ic_launcher_round)
                .withAfterLogoText("");

        Typeface footerFont = Typeface.createFromAsset(getAssets(), "Oswald-Bold.ttf");
        Typeface beforeFone = Typeface.createFromAsset(getAssets(),"coco.ttf");
        config.getBeforeLogoTextView().setTypeface(beforeFone);
        config.getFooterTextView().setTypeface(footerFont);
        config.getFooterTextView().setTextSize(16);
        config.getBeforeLogoTextView().setTextSize(60);
        config.getFooterTextView().setTextColor(Color.WHITE);
        config.getBeforeLogoTextView().setTextColor(Color.WHITE);
        View easySplashScreenView = config.create();
        setContentView(easySplashScreenView);
    }
}
