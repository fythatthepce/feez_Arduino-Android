package practise.mysql_php_json;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class member extends AppCompatActivity {

    RequestQueue requestQueue;
    String insertUrl = "http://urbanstreet101.ddns.net:8080/logout2.php";

    private ListView listView;
    //Button btnBack;

    String s;

    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        requestQueue = Volley.newRequestQueue(getApplicationContext());


        s = getIntent().getStringExtra("EXTRA_SESSION_ID2");
        TextView TextView_user1 = (TextView) findViewById(R.id.textView10);
        TextView_user1.setText(s);




        btnLogout = (Button)findViewById(R.id.button_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logout2.php
                StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        System.out.println(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        //parameters.put("Id",null);
                        parameters.put("username",s);
                        //parameters.put("password", password.getText().toString());
                        //parameters.put("email", email.getText().toString());
                        //parameters.put("loginstatus","0");
                        return parameters;
                    }
                };
                requestQueue.add(request);
                //end logout.php

                Intent i = new Intent(member.this,menu.class);
                startActivity(i);
            }
        });




        listView = (ListView)findViewById(R.id.listView);

        ArrayList<HashMap<String,String>> result = MysqlConnector.selectAllUser();

        //get username 1
        String[] resultList = new String[result.size()];

        Log.e("test", result.size()+"");

        for(int i = 0;i<result.size();i++){
            resultList[i] = result.get(i).get("username");
        }
        //end get username 1

        //Show in ListView
        ArrayAdapter<String> atdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,resultList);
        listView.setAdapter(atdapter);



    }//main
}//scope
