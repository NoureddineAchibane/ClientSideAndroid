package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         ListView lv = findViewById(R.id.listV);
        ArrayList<String> items = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.31.17:45455/api/Etudiants";
        TextView textView = findViewById(R.id.idd);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(MainActivity.this,response.toString(),Toast.LENGTH_LONG);
                Log.d("hey",response.toString());
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject ob = response.getJSONObject(i);
                        String s = "Name : ";
                        s+= ob.getString("name");
                        s+= " Prenom : ";
                        s+= ob.getString("prenom");
                        Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG);
                        items.add(s);

                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }
                ArrayAdapter<String> itemsAdapter =
                        new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, items);
                lv.setAdapter(itemsAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        queue.add(jsonArrayRequest);
        Log.d("hey","HEYYYY items"+ items.toString());


// Request a string response from the provided URL.

    }
    }
