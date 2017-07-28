package practise.mysql_php_json;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class MainActivity extends AppCompatActivity {


    int status;
    ImageButton btnBack;
    EditText username, password , email;
    Button summit;


    RequestQueue requestQueue;
    String insertUrl = "http://urbanstreet101.ddns.net:8080/insertdata2.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        username = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        email = (EditText) findViewById(R.id.editText3);

        summit = (Button) findViewById(R.id.summit);

        //result = (TextView) findViewById(R.id.textView);

        btnBack = (ImageButton)findViewById(R.id.im1);

        requestQueue = Volley.newRequestQueue(getApplicationContext());


        summit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check Username
                ArrayList<HashMap<String, String>> result = MysqlConnector.selectAllUser();

                //get username
                String[] resultList = new String[result.size()];

                Log.e("test", result.size() + "");

                for (int i = 0; i < result.size(); i++) {
                    resultList[i] = result.get(i).get("username");
                }
                //end get username

                String User_Compare = username.getText().toString();
                for (int i = 0; i < result.size(); i++) {
                    if (User_Compare.startsWith(resultList[i])) {
                        status = 1;
                        break;
                    } else {
                        status = 2;
                    }
                }



                //if (status2 == 1) {
                    if (status != 1) {
                        //Check Password

                        if (password.getText().toString().length() < 21 && !isValidPassword(password.getText().toString().toLowerCase())) {
                            Toast.makeText(getApplicationContext(), "Passwords must contain : a minimum of 1 letter", Toast.LENGTH_SHORT).show();

                        } else {
                            //System.out.println("Valid");
                            if (password.getText().toString().length() < 21) {
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
                                        parameters.put("username", username.getText().toString());
                                        parameters.put("password", password.getText().toString());
                                        parameters.put("email", email.getText().toString());
                                        //parameters.put("loginstatus","0");
                                        return parameters;
                                    }
                                };
                                requestQueue.add(request);

                                Intent x = new Intent(MainActivity.this, finishpage.class);
                                startActivity(x);
                            } else {
                                Toast.makeText(getApplicationContext(), "Password must contains minimum 21 characters", Toast.LENGTH_SHORT).show();
                            }
                        }//Big else
                    } else{
                        Toast.makeText(getApplicationContext(), "This account has already been used", Toast.LENGTH_SHORT).show();
                    }
               //} else if (status2 == 2) {
                 //   Toast.makeText(getApplicationContext(), "Please complete all fill", Toast.LENGTH_SHORT).show();
                //}
            }//view
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,menu.class);
                startActivity(i);
            }
        });
    }//main



    //function regex
    public static boolean isValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;
        //final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=\\S+$).{4,}$";  //Password must contains 1 letter and no whitespace.
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();

    }

}//scope