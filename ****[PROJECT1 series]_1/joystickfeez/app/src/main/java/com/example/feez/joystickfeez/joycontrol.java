package com.example.feez.joystickfeez;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class joycontrol extends Activity  {

    RelativeLayout layout_joystick;
    ImageView image_joystick, image_border;
    //TextView textView1, textView2, textView3, textView4, textView5;
    TextView textView5;

    JoyStickClass js;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joystick);

        //textView1 = (TextView)findViewById(R.id.textView1);
        //textView2 = (TextView)findViewById(R.id.textView2);
        //textView3 = (TextView)findViewById(R.id.textView3);
        //textView4 = (TextView)findViewById(R.id.textView4);
        textView5 = (TextView)findViewById(R.id.textView5);

        layout_joystick = (RelativeLayout)findViewById(R.id.layout_joystick);

        js = new JoyStickClass(getApplicationContext()
                , layout_joystick, R.drawable.image_button);
        js.setStickSize(100, 100);
        //js.setLayoutSize(500, 500);
        js.setLayoutSize(350, 350);
        js.setLayoutAlpha(150);
        js.setStickAlpha(100);
        js.setOffset(90);
        js.setMinimumDistance(50);

        layout_joystick.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                js.drawStick(arg1);
                if(arg1.getAction() == MotionEvent.ACTION_DOWN
                        || arg1.getAction() == MotionEvent.ACTION_MOVE) {
                    //textView1.setText("X : " + String.valueOf(js.getX()));
                    //textView2.setText("Y : " + String.valueOf(js.getY()));
                    //textView3.setText("Angle : " + String.valueOf(js.getAngle()));
                    //textView4.setText("Distance : " + String.valueOf(js.getDistance()));

                    int direction = js.get8Direction();
                    if(direction == JoyStickClass.STICK_UP) {
                        textView5.setText("Up");


                    } else if(direction == JoyStickClass.STICK_UPRIGHT) {
                        //textView5.setText("Direction : Up Right");
                        textView5.setText("Right");

                    } else if(direction == JoyStickClass.STICK_RIGHT) {
                        textView5.setText("Right");

                    } else if(direction == JoyStickClass.STICK_DOWNRIGHT) {
                        //textView5.setText("Direction : Down Right");
                        textView5.setText("Right");

                    } else if(direction == JoyStickClass.STICK_DOWN) {
                        textView5.setText("Down");

                    } else if(direction == JoyStickClass.STICK_DOWNLEFT) {
                        //textView5.setText("Direction : Down Left");
                        textView5.setText("Left");

                    } else if(direction == JoyStickClass.STICK_LEFT) {
                        textView5.setText("Left");

                    } else if(direction == JoyStickClass.STICK_UPLEFT) {
                        textView5.setText("Left");

                    } else if(direction == JoyStickClass.STICK_NONE) {
                        textView5.setText("Center");
                    }


                } else if(arg1.getAction() == MotionEvent.ACTION_UP) {
                    //textView1.setText("X :");
                    //textView2.setText("Y :");
                    //textView3.setText("Angle :");
                    //textView4.setText("Distance :");
                    textView5.setText("");
                }
                return true;
            }
        });

    }//main


}//scope
