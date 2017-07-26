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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class member extends AppCompatActivity {

    private ListView listView;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        btnBack = (Button)findViewById(R.id.button_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(member.this,menu.class);
                startActivity(i);
            }
        });

        //Toast.makeText(getApplicationContext(),"Welcome to feez DB",Toast.LENGTH_SHORT).show();

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

        //Show in ListView
        ArrayAdapter<String> atdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,resultList);
        listView.setAdapter(atdapter);



    }//main
}//scope
