package com.example.feez.remotecar;


import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.List;
import java.util.ArrayList;
import android.util.Log;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link wifiFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class wifiFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public wifiFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment wifiFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static wifiFrag newInstance(String param1, String param2) {
        wifiFrag fragment = new wifiFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    //WIFI
    //SCOPE

    /*BUG
    WifiManager mWifiMan;
    Button btnPaired;
    ListView devicelist;
    WifiConfiguration conf;

    public static String networkSSID="LED_via_NodeMCU_by_FeezCEkmitl";
    public static String networkPass="123fee456";

    Context context;


    int size = 0;
    List<ScanResult> results;

    ArrayList<String> arraylist = new ArrayList<>();
    ArrayAdapter adapter2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {//main


        View v = inflater.inflate(R.layout.fragment_wifi, container, false);
        //CODE HERE

        //context=getActivity();

        //init variable widget
        btnPaired = (Button)v.findViewById(R.id.button_scan);
        devicelist = (ListView)v.findViewById(R.id.listview);

        mWifiMan = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (mWifiMan.isWifiEnabled() == false)
        {
            //Toast.makeText(getApplicationContext(), "wifi is disabled..making it enabled", Toast.LENGTH_LONG).show();
            mWifiMan.setWifiEnabled(true);
        }
        this.adapter2 =  new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,arraylist);
        devicelist.setAdapter(this.adapter2);

        //scanWifi();

        btnPaired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                scanWifi();
            }
        });

        return v;
    }//main

    //function
    private void scanWifi(){
        arraylist.clear();
        getActivity().registerReceiver(mReceiver , new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

        mWifiMan.startScan();

        Log.d("WifScanner", "scanWifiNetworks");

        Toast.makeText(getActivity(), "Scanning....", Toast.LENGTH_SHORT).show();
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            results = mWifiMan.getScanResults();
            size = results.size();
            getActivity().unregisterReceiver(this);

            try
            {
                while (size >= 0)
                {
                    size--;
                    arraylist.add(results.get(size).SSID+ "\n" + "Signal : " + results.get(size).level+" dBm");
                    adapter2.notifyDataSetChanged();
                }
            }
            catch (Exception e)
            {
                Log.w("WifScanner", "Exception: "+e);

            }

            devicelist.setAdapter(adapter2);
            devicelist.setOnItemClickListener(myListClickListener2); //Method called when the device from the list is clicked

        }
    };//end function



    private AdapterView.OnItemClickListener myListClickListener2 = new AdapterView.OnItemClickListener()
    {
        public void onItemClick (AdapterView<?> av, View v, int arg2, long arg3)
        {
            Intent a = new Intent(getActivity(),wificontrol.class);
            startActivity(a);
            connect();
        }
    };

    private void connect() {
        mWifiMan = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);

        Toast.makeText(getActivity(), "Connecting ...", Toast.LENGTH_SHORT).show();
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
        int netid = mWifiMan.addNetwork(conf);
        mWifiMan.disconnect();
        mWifiMan.enableNetwork(netid, true);
        mWifiMan.reconnect();
    }*/

    Button btnEnter;

    static WifiManager wifiManager;
    Context context;
    WifiConfiguration conf;
    public static String networkSSID="LED_via_NodeMCU_by_FeezCEkmitl";
    public static String networkPass="123fee456";
    byte[] buf = new byte[1024];//used to sending information to esp is a form of byte

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_wifi, container, false);

        btnEnter = (Button)v.findViewById(R.id.button_scan);

        // this is for thread policy the AOS doesn't allow to transfer data using wifi module so we take the permission
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        btnEnter.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), wificontrol.class);
                startActivity(intent);
                //connect();

            }

        });

        return v;

    }//main

    /*
    //function
    private void connect(){
        wifiManager = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);

        turnOnOffWifi(context, true); //on
        Toast.makeText(getActivity(), "turning on...", Toast.LENGTH_SHORT).show();
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

    public static void turnOnOffWifi(Context context, boolean isTurnToOn) {
        wifiManager = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(isTurnToOn);
    }*/


}//SCOPE
