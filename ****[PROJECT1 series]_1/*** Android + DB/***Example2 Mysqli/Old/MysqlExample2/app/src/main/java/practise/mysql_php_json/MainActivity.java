package practise.mysql_php_json;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

import java.util.HashMap;
import java.util.Map;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class MainActivity extends AppCompatActivity {

    ImageButton btnBack;

    EditText username, password;
    Button summit;
    RequestQueue requestQueue;
    String insertUrl = "http://urbanstreet101.ddns.net:8080/insertdata.php";

    //String showUrl = "http://192.168.1.65/tutorial/showStudents.php";
    //TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        summit = (Button) findViewById(R.id.summit);

        //result = (TextView) findViewById(R.id.textView);

        btnBack = (ImageButton)findViewById(R.id.im1);


        requestQueue = Volley.newRequestQueue(getApplicationContext());

        /*
        show.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                System.out.println("ww");
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                        showUrl, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());
                        try {
                            JSONArray students = response.getJSONArray("students");
                            for (int i = 0; i < students.length(); i++) {
                                JSONObject student = students.getJSONObject(i);

                                String firstname = student.getString("firstname");
                                String lastname = student.getString("lastname");
                                String age = student.getString("age");

                                result.append(firstname + " " + lastname + " " + age + " \n");
                            }
                            result.append("===\n");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.append(error.getMessage());

                    }
                });
                requestQueue.add(jsonObjectRequest);
            }
        });*/

        summit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Check Password
                if(password.getText().toString().length()< 8 &&!isValidPassword(password.getText().toString())){
                    //System.out.println("Not Valid : Password must contains minimum 8 characters at least 1 Alphabet, 1 Number and 1 Special Character");
                    Toast.makeText(getApplicationContext(),"Password must contains minimum 8 characters at least 1 Capital letters, 1 Number and 1 Special Character",Toast.LENGTH_SHORT).show();

                }else {
                    //System.out.println("Valid");
                    //if(password.getText().toString().length()< 8)
                    if(password.getText().toString().length()< 8)
                    {
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
                                parameters.put("pass", password.getText().toString());
                                return parameters;
                            }
                        };
                        requestQueue.add(request);

                        Intent i = new Intent(MainActivity.this, finishpage.class);
                        startActivity(i);
                   }else{
                        Toast.makeText(getApplicationContext(),"Password must contains minimum 8 characters",Toast.LENGTH_SHORT).show();
                    }
                }//Big else


                /*
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
                        Map<String,String> parameters  = new HashMap<String, String>();
                        //parameters.put("Id",null);
                        parameters.put("username",username.getText().toString());
                        parameters.put("pass",password.getText().toString());
                        return parameters;
                    }
                };
                requestQueue.add(request);

                Intent i = new Intent(MainActivity.this,finishpage.class);
                startActivity(i);
                */


            }

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
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";  //Password must contains Big 1 Alphabet, 1 Number and 1 Special Character and no whitespace.
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();

    }

}//scope