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

import java.util.ArrayList;
import java.util.HashMap;

public class menu extends AppCompatActivity {

    Button btnClick;
    Button btnLogin;
    EditText username, password;
    int status = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        /*
        //listView = (ListView)findViewById(R.id.listView);

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
            resultList2[i] = result.get(i).get("pass");
        }
        //end get password
        */

        username = (EditText) findViewById(R.id.editText_user);
        password = (EditText) findViewById(R.id.editText_pass);

        btnLogin = (Button)findViewById(R.id.button_login);

        btnClick = (Button)findViewById(R.id.button_click);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(menu.this,MainActivity.class);
                startActivity(i);
            }
        });


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
                    resultList2[i] = result.get(i).get("pass");
                }
                //end get password

                String User_Compare = username.getText().toString();
                String Pass_Compare  = password.getText().toString();

                for(int i = 0;i<result.size();i++){
                    if (User_Compare.startsWith(resultList[i]) && Pass_Compare.startsWith(resultList2[i]) ){
                        status = 1;
                        Intent j = new Intent(menu.this,member.class);
                        startActivity(j);
                        break;   //out loop
                    }else{
                        status = 2;
                    }

                    /* BUG
                    else{
                        //Toast.makeText(getApplicationContext(),"Username or Password incorrect",Toast.LENGTH_SHORT).show();
                        //break;
                    }*/
                }

                if(status == 2){
                    Toast.makeText(getApplicationContext(),"Username or Password incorrect",Toast.LENGTH_SHORT).show();
                }else if(status == 1){
                    Toast.makeText(getApplicationContext(),"Welcome to feez DB",Toast.LENGTH_SHORT).show();
                }

            }
        });


        /*
        String User_Compare = username.getText().toString();
        String Pass_Compare  = password.getText().toString();

        for(int i = 0;i<result.size();i++){
            if (User_Compare.startsWith(resultList[i]) && Pass_Compare.startsWith(resultList2[i]) ){
                Intent j = new Intent(menu.this,member.class);
                startActivity(j);
            }else{
                Toast.makeText(getApplicationContext(),"Username or Password incorrect",Toast.LENGTH_SHORT).show();
            }
        }*/

        /*
        if (toCompare.startsWith("a")) {

        }*/



    }//main
}//scope
