package com.example.feez.sound1;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    //private Button openMic;
    private TextView showVoiceText;
    private ImageButton openMic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openMic = (ImageButton)findViewById(R.id.imageButton);
        showVoiceText = (TextView)findViewById(R.id.showVoice);

        openMic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                btnToOpenMic();
            }
        });
    }//main


    private void btnToOpenMic(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        //intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speak to Control ...");

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);

        } else {
            Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            case 10:{
                if (resultCode == RESULT_OK && null!= data){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //showVoiceText.setText(result.get(0));


                    //if sound = left show welcome left
                    if(result.contains("left")) {
                        showVoiceText.setText("Welcome left!!!");

                    }else if(result.contains("right")){

                        showVoiceText.setText("Welcome right!!!");

                    }else if(result.contains("up")){

                        showVoiceText.setText("Welcome up!!!");
                    }

                    else if(result.contains("down")) {
                        showVoiceText.setText("Welcome down!!!");
                    }

                    else if(result.contains("stop")) {
                        showVoiceText.setText("Welcome stop!!!");

                    }else{
                        showVoiceText.setText("No Command found !!!!");
                    }
                }
                break;
            }
        }
      }

}//scope
