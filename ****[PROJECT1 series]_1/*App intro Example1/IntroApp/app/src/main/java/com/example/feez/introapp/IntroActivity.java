package com.example.feez.introapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

import android.support.v4.content.ContextCompat;

/**
 * Created by feez on 7/16/2017 AD.
 */

public class IntroActivity extends AppIntro {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(SampleSlide.newInstance(R.layout.slide_1));
        addSlide(SampleSlide.newInstance(R.layout.slide_2));
        addSlide(SampleSlide.newInstance(R.layout.slide_3));
        addSlide(AppIntroFragment.newInstance("title1", "description",R.mipmap.ic_launcher, ContextCompat.getColor(getApplicationContext(),R.color.colorAccent)));

    }//main

    @Override
    public void onSkipPressed(Fragment currentFragment){
        super.onSkipPressed(currentFragment);
        //finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment){
        super.onDonePressed(currentFragment);
        //finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment,@Nullable Fragment newFragment){
        super.onSlideChanged(oldFragment,newFragment);
        //finish();
    }


}//scope
