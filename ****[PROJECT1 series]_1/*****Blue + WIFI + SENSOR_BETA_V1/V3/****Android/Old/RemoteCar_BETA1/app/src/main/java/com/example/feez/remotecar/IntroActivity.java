package com.example.feez.remotecar;

/**
 * Created by feez on 7/16/2017 AD.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

import android.support.v4.content.ContextCompat;
import android.graphics.Color;

public class IntroActivity extends AppIntro{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(SampleSlide.newInstance(R.layout.slide_1));
        addSlide(SampleSlide.newInstance(R.layout.slide_2));
        addSlide(SampleSlide.newInstance(R.layout.slide_3));


        addSlide(SampleSlide.newInstance(R.layout.slide_4));
        addSlide(SampleSlide.newInstance(R.layout.slide_5));
        addSlide(SampleSlide.newInstance(R.layout.slide_6));

        addSlide(SampleSlide.newInstance(R.layout.slide_7));
        addSlide(SampleSlide.newInstance(R.layout.slide_8));
        addSlide(SampleSlide.newInstance(R.layout.slide_9));

        addSlide(SampleSlide.newInstance(R.layout.slide_10));


        addSlide(AppIntroFragment.newInstance("Done","Get Started !",R.drawable.done24, ContextCompat.getColor(getApplicationContext(),R.color.DarkSeaGreen)));

        /*
        setBarColor(Color.parseColor("#000000"));
        setSeparatorColor(Color.parseColor("#2196F3"));

        // SHOW or HIDE the statusbar
        showStatusBar(true);

        // Edit the color of the nav bar on Lollipop+ devices
        setNavBarColor(Color.parseColor("#3F51B5"));

        // Hide Skip/Done button
        showSkipButton(false);
        showDoneButton(false);

        // Turn vibration on and set intensity
        // NOTE: you will probably need to ask VIBRATE permisssion in Manifest
        setVibrate(true);
        setVibrateIntensity(30);

        // Animations -- use only one of the below. Using both could cause errors.
        setFadeAnimation(); // OR
        setZoomAnimation(); // OR
        setFlowAnimation(); // OR
        setSlideOverAnimation(); // OR
        setDepthAnimation(); // OR
        setCustomTransformer(yourCustomTransformer);

        // Permissions -- takes a permission and slide number
        askForPermissions(new String[]{Manifest.permission.CAMERA}, 3);
        */

    }//main

    @Override
    public void onSkipPressed(Fragment currentFragment){
        super.onSkipPressed(currentFragment);
        Intent intent = new Intent(this,fragmain.class);
        startActivity(intent);
    }

    @Override
    public void onDonePressed(Fragment currentFragment){
        super.onDonePressed(currentFragment);
        Intent intent = new Intent(this,fragmain.class);
        startActivity(intent);
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment,@Nullable Fragment newFragment){
        super.onSlideChanged(oldFragment,newFragment);
    }
}
