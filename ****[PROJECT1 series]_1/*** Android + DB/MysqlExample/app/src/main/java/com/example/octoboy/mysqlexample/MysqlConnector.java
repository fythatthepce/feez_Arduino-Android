package com.example.octoboy.mysqlexample;

/**
 * Created by octoboy on 11/12/2557.
 */
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class MysqlConnector {

    public static ArrayList<HashMap<String, String>> selectAllUser() { //------------ Method to select data ---------------//

        InputStream is = null;
        String js_result = "";

        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();


        try {

            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

			/* Set to Http post*/

			/* End set Value*/

            HttpClient httpclient = new DefaultHttpClient();

			/* Set URL*/
            HttpPost httppost = new HttpPost("http://urbanstreet101.ddns.net:8080/phpget.php"); // https://10.0.2.2/
			/* End Set URL*/

            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {
            Log.d("log_err", "Error in http connection " + e.toString());
            return list;
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            is.close();
            js_result = sb.toString();
        } catch (Exception e) {
            Log.e("log_tag", "Error converting result " + e.toString());
            return list;
        }

        try {

            final JSONArray jArray = new JSONArray(js_result);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jo = jArray.getJSONObject(i);
                HashMap<String, String> user = new HashMap<String,String>();
                user.put("Id", jo.get("Id").toString());
                user.put("username", jo.get("username").toString());
                user.put("pass", jo.get("pass").toString());
                Log.e("test",jo.get("username").toString());
                list.add(user);
            }
        } catch (JSONException e) {
            Log.e("log_tag", "Error parsing data " + e.toString());
            return list;
        }

        return list;
    }

}
