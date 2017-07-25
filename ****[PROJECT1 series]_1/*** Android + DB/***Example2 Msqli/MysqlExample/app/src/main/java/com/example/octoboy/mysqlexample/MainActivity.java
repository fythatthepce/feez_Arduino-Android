package com.example.octoboy.mysqlexample;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import android.widget.TextView;


public class MainActivity extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        listView = (ListView)findViewById(R.id.listView);

        ArrayList<HashMap<String,String>> result = MysqlConnector.selectAllUser();

        //get username 1
        String[] resultList = new String[result.size()];

        Log.e("test", result.size()+"");

        for(int i = 0;i<result.size();i++){
            resultList[i] = result.get(i).get("username");
        }
        //end get username 1


        //get pass 1
        String[] resultList2 = new String[result.size()];

        Log.e("test", result.size()+"");

        for(int i = 0;i<result.size();i++){
            resultList2[i] = result.get(i).get("pass");
        }
        //end get pass 1




        //Show in ListView
        ArrayAdapter<String> atdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,resultList);
        listView.setAdapter(atdapter);



        //Show in TextView

        int i = 0;
        TextView TextView_user1 = (TextView) findViewById(R.id.textView);
        TextView_user1.setText(resultList[i]);

        TextView TextView_pass1 = (TextView) findViewById(R.id.textView2);
        TextView_pass1.setText(resultList2[i]);


        int j = i+1;
        TextView TextView_user2 = (TextView) findViewById(R.id.textView3);
        TextView_user2.setText(resultList[j]);

        TextView TextView_pass2 = (TextView) findViewById(R.id.textView4);
        TextView_pass2.setText(resultList2[j]);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
