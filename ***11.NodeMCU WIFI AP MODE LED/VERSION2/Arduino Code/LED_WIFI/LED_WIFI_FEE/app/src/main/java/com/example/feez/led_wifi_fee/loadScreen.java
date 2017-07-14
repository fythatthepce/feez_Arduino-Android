package com.example.feez.led_wifi_fee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.os.Handler;

public class loadScreen extends AppCompatActivity {

    private Handler objHandle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_screen);

        objHandle = new Handler();
        objHandle.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent objIntent = new Intent(loadScreen.this,wificontrol.class);
                startActivity(objIntent);
                finish();
            }
        },3000); // delay 3 sec
    }
}
