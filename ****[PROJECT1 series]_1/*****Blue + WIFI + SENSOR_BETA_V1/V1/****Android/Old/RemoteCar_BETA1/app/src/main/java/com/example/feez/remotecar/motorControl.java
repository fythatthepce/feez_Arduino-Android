package com.example.feez.remotecar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageButton;
import android.app.ProgressDialog;


import java.io.IOException;
import java.util.UUID;


import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.ProgressBar;


import android.os.Handler;
import java.io.InputStream;
import java.io.OutputStream;
import android.widget.CheckBox;





public class motorControl extends AppCompatActivity {

    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;

    TextView sensorView0;
    Handler bluetoothIn;

    ImageView sensorViewimg0;

    final int handlerState = 0;
    private StringBuilder recDataString = new StringBuilder();
    private ConnectedThread mConnectedThread;

    ImageButton btnForward;
    ImageButton btnLeft;
    ImageButton btnRight;
    ImageButton btnBack;
    Button btnDis;

    //Button btnLaser;
    //Button btnBreak;
    String address = null;
    private ProgressDialog progress;
    private boolean isBtConnected = false;


    //SPP UUID. Look for it
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    //Progress bar
    private ProgressBar pg1;
    private CheckBox ck1;
    private CheckBox ck2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor_control);

        Intent newint = getIntent();
        address = newint.getStringExtra(DeviceList.EXTRA_ADDRESS); //receive the address of the bluetooth device

        //call the widgets
        btnForward = (ImageButton) findViewById(R.id.up);
        btnLeft = (ImageButton) findViewById(R.id.left);
        btnBack = (ImageButton) findViewById(R.id.down);
        btnRight = (ImageButton) findViewById(R.id.right);
        btnDis = (Button) findViewById(R.id.button_disconnect);

        //btnLaser = (Button)findViewById(R.id.button_laser);
        //btnBreak = (Button)findViewById(R.id.break_motor);
        pg1 = (ProgressBar) findViewById(R.id.progressBar);

        sensorView0 = (TextView) findViewById(R.id.sensorView0);
        sensorViewimg0 = (ImageView) findViewById(R.id.imageView);

        ck1 = (CheckBox) findViewById(R.id.checkBox);
        ck2 = (CheckBox) findViewById(R.id.checkBox2);


        bluetoothIn = new Handler() {
            public void handleMessage(android.os.Message msg) {
                if (msg.what == handlerState) {                                     //if message is what we want
                    String readMessage = (String) msg.obj;                                                                // msg.arg1 = bytes from connect thread
                    recDataString.append(readMessage);                                      //keep appending to string until ~
                    int endOfLineIndex = recDataString.indexOf("~");                    // determine the end-of-line
                    if (endOfLineIndex > 0) {                                           // make sure there data before ~
                        String dataInPrint = recDataString.substring(0, endOfLineIndex);    // extract string
                        //txtString.setText("Data Received = " + dataInPrint);
                        int dataLength = dataInPrint.length();                          //get length of data received
                        //txtStringLength.setText("String Length = " + String.valueOf(dataLength));

                        if (recDataString.charAt(0) == '#')                             //if it starts with # we know it is what we are looking for
                        {
                            //String sensor0 = recDataString.substring(1, 5);             //get sensor value from string between indices 1-5
                            //String sensor1 = recDataString.substring(6, 10);            //same again...
                            //String sensor2 = recDataString.substring(11, 15);
                            //String sensor3 = recDataString.substring(16, 20);

                            String sensor0 = recDataString.substring(1, 2);  // get 1 char
                            String check0 = "0";
                            //String check1 = "1";

                            //show str
                            //sensorView0.setText(" Value : " + sensor0);    //update the textviews with sensor values

                            if (sensor0.equals(check0)) {
                                //sensorViewimg0.setImageResource(R.drawable.tank2);
                                Toast.makeText(getApplicationContext(), "Detect Object in 3 cm : Auto Backward  !!!!", Toast.LENGTH_SHORT).show();
                            }/*else if(sensor0.equals(check1)) {
                                sensorViewimg0.setImageResource(R.drawable.down);

                            }*/
                            //sensorView1.setText(" Sensor 1 Voltage = " + sensor1 + "V");
                            //sensorView2.setText(" Sensor 2 Voltage = " + sensor2 + "V");
                            //sensorView3.setText(" Sensor 3 Voltage = " + sensor3 + "V");
                        }
                        recDataString.delete(0, recDataString.length());                    //clear all string data
                        // strIncom =" ";
                        dataInPrint = " ";
                    }
                }
            }
        };


        btAdapter = BluetoothAdapter.getDefaultAdapter();       // get Bluetooth adapter
        checkBTState();


        btnForward.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //forward();
                        //sensorView0.setText(" Value : 20");
                        //On_LED();
                        mConnectedThread.write("1");
                        pg1.setProgress(100);
                        break;
                    case MotionEvent.ACTION_UP:
                        //break_motor();
                        //Off_LED();
                        mConnectedThread.write("0");
                        pg1.setProgress(0);
                        break;
                }
                return false;
            }
        });


        btnBack.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mConnectedThread.write("3");
                        pg1.setProgress(100);
                        break;
                    case MotionEvent.ACTION_UP:
                        mConnectedThread.write("2");
                        //sensorViewimg0.setImageResource(R.drawable.tank);
                        //break_motor();
                        pg1.setProgress(0);
                        break;
                }
                return false;
            }
        });

        btnLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //left();
                        mConnectedThread.write("5");
                        pg1.setProgress(100);
                        break;
                    case MotionEvent.ACTION_UP:
                        mConnectedThread.write("4");
                        pg1.setProgress(0);
                        //break_motor();
                        break;
                }
                return false;
            }
        });

        btnRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mConnectedThread.write("7");
                        pg1.setProgress(100);
                        //right();
                        break;
                    case MotionEvent.ACTION_UP:
                        mConnectedThread.write("6");
                        pg1.setProgress(0);
                        //break_motor();
                        break;
                }
                return false;
            }
        });

        setInitCheck();
        ck1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ck1.isChecked()) {
                    mConnectedThread.write("9");
                    Toast.makeText(getApplicationContext(), "Enable Collision prevention", Toast.LENGTH_SHORT).show();
                } else {
                    mConnectedThread.write("8");
                    Toast.makeText(getApplicationContext(), "Disable Collision prevention", Toast.LENGTH_SHORT).show();
                }
            }
        });//end checkbox1


        ck2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ck2.isChecked()) {
                    mConnectedThread.write("a");
                    Toast.makeText(getApplicationContext(), "Enable Laser Control Mode", Toast.LENGTH_SHORT).show();

                    btnForward.setEnabled(false);
                    btnBack.setEnabled(false);
                    btnLeft.setEnabled(false);
                    btnRight.setEnabled(false);

                    btnForward.setVisibility(View.INVISIBLE);
                    btnBack.setVisibility(View.INVISIBLE);
                    btnLeft.setVisibility(View.INVISIBLE);
                    btnRight.setVisibility(View.INVISIBLE);

                    sensorViewimg0.setImageResource(R.drawable.laser2);

                } else {
                    mConnectedThread.write("b");
                    Toast.makeText(getApplicationContext(), "Disable Laser Control Mode", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(motorControl.this, fragmain.class);
                    startActivity(i);
                }
            }
        });//end checkbox2

        /*
        btnLaser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mConnectedThread.write("a");
                //Toast.makeText(getApplicationContext(), "Enable Laser Control Mode", Toast.LENGTH_SHORT).show();
                //Disconnect();

                Intent j = new Intent(motorControl.this,DeviceList.class);
                startActivity(j);
            }
        });*/


        btnDis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Disconnect(); //close connection
                Toast.makeText(getApplicationContext(), "Disconnect ...", Toast.LENGTH_SHORT).show();
                mConnectedThread.write("r");
                Intent i = new Intent(motorControl.this, fragmain.class);
                startActivity(i);
            }
        });

    }//main

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {

        return device.createRfcommSocketToServiceRecord(BTMODULEUUID);
        //creates secure outgoing connecetion with BT device using UUID
    }

    @Override
    public void onResume() {
        super.onResume();

        //Get MAC address from DeviceListActivity via intent
        Intent intent = getIntent();

        //Get the MAC address from the DeviceListActivty via EXTRA
        address = intent.getStringExtra(DeviceList.EXTRA_ADDRESS);

        //create device and set the MAC address
        BluetoothDevice device = btAdapter.getRemoteDevice(address);

        try {
            btSocket = createBluetoothSocket(device);
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), "Socket creation failed", Toast.LENGTH_LONG).show();
        }
        // Establish the Bluetooth socket connection.
        try {
            btSocket.connect();
        } catch (IOException e) {
            try {
                btSocket.close();
            } catch (IOException e2) {
                //insert code to deal with this
            }
        }
        mConnectedThread = new ConnectedThread(btSocket);
        mConnectedThread.start();

        //I send a character when resuming.beginning transmission to check device is connected
        //If it is not an exception will be thrown in the write method and finish() will be called
        mConnectedThread.write("x");
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            //Don't leave Bluetooth sockets open when leaving activity
            btSocket.close();
        } catch (IOException e2) {
            //insert code to deal with this
        }
    }

    //Checks that the Android device Bluetooth is available and prompts to be turned on if off
    private void checkBTState() {

        if (btAdapter == null) {
            Toast.makeText(getBaseContext(), "Device does not support bluetooth", Toast.LENGTH_LONG).show();
        } else {
            if (btAdapter.isEnabled()) {
            } else {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
    }

    private void Disconnect() {
        if (btSocket != null) //If the btSocket is busy
        {
            try {
                btSocket.close(); //close connection
            } catch (IOException e) {
                //msg("Error");
            }
        }
        finish(); //return to the first layout

    }

    //create new class for connect thread
    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        //creation of the connect thread
        public ConnectedThread(BluetoothSocket socket) {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {
                //Create I/O streams for connection
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }


        public void run() {
            byte[] buffer = new byte[256];
            int bytes;

            // Keep looping to listen for received messages
            while (true) {
                try {
                    bytes = mmInStream.read(buffer);            //read bytes from input buffer
                    String readMessage = new String(buffer, 0, bytes);
                    // Send the obtained bytes to the UI Activity via handler
                    bluetoothIn.obtainMessage(handlerState, bytes, -1, readMessage).sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }
        }

        //write method
        public void write(String input) {
            byte[] msgBuffer = input.getBytes();           //converts entered String into bytes
            try {
                mmOutStream.write(msgBuffer);                //write bytes over BT connection via outstream
            } catch (IOException e) {
                //if you cannot write, close the application
                Toast.makeText(getBaseContext(), "Connection Failure", Toast.LENGTH_LONG).show();
                finish();

            }
        }

    }

    private void setInitCheck() {
        ck1.setChecked(false);
    }
}



