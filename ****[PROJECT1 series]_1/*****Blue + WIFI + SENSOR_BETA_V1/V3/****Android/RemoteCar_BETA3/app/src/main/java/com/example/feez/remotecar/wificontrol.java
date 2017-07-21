package com.example.feez.remotecar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.StrictMode;

import android.view.MotionEvent;
import android.view.View;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class wificontrol extends AppCompatActivity {//scope

    Button btnCon;
    Button btnDis;

    ImageButton btnForward;
    ImageButton  btnBack ;
    ImageButton  btnLeft;
    ImageButton  btnRight;

    static WifiManager wifiManager;
    Context context;
    WifiConfiguration conf;
    public static String networkSSID="LED_via_NodeMCU_by_FeezCEkmitl";
    public static String networkPass="123fee456";
    byte[] buf = new byte[1024];//used to sending information to esp is a form of byte


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wificontrol);

        context=this;

        //call the widgets
        btnForward = (ImageButton)findViewById(R.id.imageButton_up);
        btnBack = (ImageButton)findViewById(R.id.imageButton_down);
        btnLeft = (ImageButton)findViewById(R.id.imageButton_left);
        btnRight = (ImageButton)findViewById(R.id.imageButton_right);

        btnDis = (Button)findViewById(R.id.button_disconnect);
        btnCon = (Button)findViewById(R.id.button_connect);

        // this is for thread policy the AOS doesn't allow to transfer data using wifi module so we take the permission
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        btnCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset_connection(); //clear old connection
                connect(); //open connection
            }
        });

        btnDis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Disconnect(); //close connection
            }
        });

        btnForward.setOnTouchListener (new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //pg1.setProgress(100);
                        //led1_on();
                        forward_on();
                        break;
                    case MotionEvent.ACTION_UP:
                        //pg1.setProgress(0);
                        //break_motor();
                        //led1_off();
                        forward_off();
                        break;
                }
                return false;
            }
        });

        btnBack.setOnTouchListener (new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //pg1.setProgress(100);
                        //led2_on();
                        backward_on();
                        break;
                    case MotionEvent.ACTION_UP:
                        //pg1.setProgress(0);
                        //break_motor();
                        //led2_off();
                        backward_off();
                        break;
                }
                return false;
            }
        });

        btnLeft.setOnTouchListener (new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //pg1.setProgress(100);
                        //led3_on();
                        left_on();
                        break;
                    case MotionEvent.ACTION_UP:
                        //pg1.setProgress(0);
                        //break_motor();
                        //led3_off();
                        left_off();
                        break;
                }
                return false;
            }
        });

        btnRight.setOnTouchListener (new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //pg1.setProgress(100);
                        //led4_on();
                        right_on();
                        break;
                    case MotionEvent.ACTION_UP:
                        //pg1.setProgress(0);
                        //break_motor();
                        //led4_off();
                        right_off();
                        break;
                }
                return false;
            }
        });

    }//main

    //function
    private void connect(){
        wifiManager = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);

        turnOnOffWifi(context, true); //on
        Toast.makeText(getApplicationContext(), "turning on...", Toast.LENGTH_SHORT).show();
        conf = new WifiConfiguration();
        conf.SSID = "\"" + networkSSID + "\"";
        conf.preSharedKey = "\"" + networkPass + "\"";
        conf.status = WifiConfiguration.Status.ENABLED;
        conf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
        conf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
        conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
        conf.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
        conf.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
        conf.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
        int netid= wifiManager.addNetwork(conf);
        wifiManager.disconnect();
        wifiManager.enableNetwork(netid, true);
        wifiManager.reconnect();
    }

    private void Disconnect(){
        turnOnOffWifi(context, false);
        Toast.makeText(getApplicationContext(), "turning off...", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(wificontrol.this, fragmain.class);
        startActivity(i);
    }

    //forward
    private void forward_on(){
        Client a=new Client(); //object of class client
        buf=null;
        buf=("101").getBytes(); // value to be send to esp
        a.run(); //use run() in class client to send data
    }

    private void forward_off(){
        Client a=new Client();//object of class client
        buf=null;
        buf=("100").getBytes();// value to be send to esp
        a.run(); //use run() in class client to send data
    }

    //backward
    private void backward_on(){
        Client a=new Client(); //object of class client
        buf=null;
        buf=("201").getBytes(); // value to be send to esp
        a.run(); //use run() in class client to send data
    }

    private void backward_off(){
        Client a=new Client();//object of class client
        buf=null;
        buf=("200").getBytes();// value to be send to esp
        a.run(); //use run() in class client to send data
    }

    //left
    private void left_on(){
        Client a=new Client(); //object of class client
        buf=null;
        buf=("301").getBytes(); // value to be send to esp
        a.run(); //use run() in class client to send data
    }

    private void left_off(){
        Client a=new Client();//object of class client
        buf=null;
        buf=("300").getBytes();// value to be send to esp
        a.run(); //use run() in class client to send data
    }

    //right
    private void right_on(){
        Client a=new Client(); //object of class client
        buf=null;
        buf=("401").getBytes(); // value to be send to esp
        a.run(); //use run() in class client to send data
    }

    private void right_off(){
        Client a=new Client();//object of class client
        buf=null;
        buf=("400").getBytes();// value to be send to esp
        a.run(); //use run() in class client to send data
    }

    //reset NodeMCU : clear connection
    private void reset_connection(){
        Client a=new Client();//object of class client
        buf=null;
        buf=("999").getBytes();// value to be send to esp
        a.run(); //use run() in class client to send data
    }


    public static void turnOnOffWifi(Context context, boolean isTurnToOn) {
        wifiManager = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(isTurnToOn);
    }

    //used to send data to esp module
    public class Client implements Runnable{
        private final static String SERVER_ADDRESS = "192.168.4.1";//public ip of my server
        private final static int SERVER_PORT = 8888;


        public void run(){

            InetAddress serverAddr;
            DatagramPacket packet;
            DatagramSocket socket;


            try {
                serverAddr = InetAddress.getByName(SERVER_ADDRESS);
                socket = new DatagramSocket(); //DataGram socket is created
                packet = new DatagramPacket(buf, buf.length, serverAddr, SERVER_PORT);//Data is loaded with information where to send on address and port number
                socket.send(packet);//Data is send in the form of packets
                socket.close();//Needs to close the socket before other operation... its a good programming
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}//scope
