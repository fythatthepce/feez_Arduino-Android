package practise.mysql_php_json;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class menu extends AppCompatActivity {

    Button btnClick;
    Button btnLogin;
    EditText username, password;
    int status = 0;

    //Button btnPass;

    RequestQueue requestQueue;
    String insertUrl = "http://urbanstreet101.ddns.net:8080/updatedata2.php";

    //String value1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        requestQueue = Volley.newRequestQueue(getApplicationContext());


        username = (EditText) findViewById(R.id.editText_user);
        password = (EditText) findViewById(R.id.editText_pass);

        btnLogin = (Button)findViewById(R.id.button_login);

        //value1 = username.getText().toString();
        //value1 = "Hello world";





        //----------------------------------------------------------------------------------------

        btnClick = (Button)findViewById(R.id.button_click);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(menu.this,MainActivity.class);
                startActivity(i);
            }
        });



        /*
        btnPass = (Button)findViewById(R.id.button_pass);
        btnPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(menu.this,Passvalue.class);
                i.putExtra("EXTRA_SESSION_ID2",value1);
                startActivity(i);
            }
        });*/



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<HashMap<String,String>> result = MysqlConnector.selectAllUser();

                //get username
                String[] resultList = new String[result.size()];

                Log.e("test", result.size()+"");

                for(int i = 0;i<result.size();i++){
                    resultList[i] = result.get(i).get("username");
                }
                //end get username


                //get password
                String[] resultList2 = new String[result.size()];

                Log.e("test", result.size()+"");

                for(int i = 0;i<result.size();i++){
                    resultList2[i] = result.get(i).get("password");
                }
                //end get password

                //EditText to string for compare
                String User_Compare = username.getText().toString();
                String Pass_Compare  = password.getText().toString();



                for(int i = 0;i<result.size();i++){
                    if (User_Compare.startsWith(resultList[i]) && Pass_Compare.startsWith(resultList2[i]) ){
                        status = 1;
                        //test state

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

                            //update login status

                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> parameters = new HashMap<String, String>();
                                //parameters.put("Id",null);
                                parameters.put("username", username.getText().toString());
                                //parameters.put("password", password.getText().toString());
                                //parameters.put("email", email.getText().toString());
                                //parameters.put("loginstatus","0");
                                return parameters;
                            }
                        };
                        requestQueue.add(request);


                        Intent j = new Intent(menu.this,member.class);
                        j.putExtra("EXTRA_SESSION_ID2",username.getText().toString());
                        startActivity(j);

                        break;   //out loop
                    }else{
                        status = 2;
                    }
                }

                if(status == 2){
                    Toast.makeText(getApplicationContext(),"Username or Password incorrect",Toast.LENGTH_SHORT).show();
                }else if(status == 1){
                    Toast.makeText(getApplicationContext(),"Welcome to feez DB",Toast.LENGTH_SHORT).show();

                }

            }
        });

    }//main
}//scope
