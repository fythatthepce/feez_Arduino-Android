package practise.mysql_php_json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;

public class sp_screen extends AppCompatActivity {

    private Handler objHandle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_screen);

        objHandle = new Handler();
        objHandle.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent objIntent = new Intent(sp_screen.this,menu.class);
                //Intent objIntent = new Intent(sp_screen.this,MainActivity.class);
                startActivity(objIntent);
                finish();
            }
        },2000); // delay / sec

    }
}
