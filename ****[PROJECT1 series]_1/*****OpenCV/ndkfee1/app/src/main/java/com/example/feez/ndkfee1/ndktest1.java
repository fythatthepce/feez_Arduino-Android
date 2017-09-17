package com.example.feez.ndkfee1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ndktest1 extends AppCompatActivity {

    static {
        System.loadLibrary("MyLibs");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndktest1);

        ((TextView)findViewById(R.id.textView)).setText(NativeClass.getMessageFromJNI());
    }
}
