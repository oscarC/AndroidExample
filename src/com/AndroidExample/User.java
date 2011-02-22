package com.AndroidExample;

//JSON
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import android.util.Log;

///


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;

public class User extends Activity {
	
	  private Button BackButton;
	  private String name;
	  private EditText username;
	public void onCreate(Bundle savedInstanceState)
    {
       
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("NAME");
        username = (EditText) findViewById(R.id.username);
        BackButton = (Button) findViewById(R.id.back);
        username.setText(name);
        
        String jString = "{\"username\": \"oscar\", \"message\": \"Hi, I am Primal\"}  ";


        GsonBuilder gsonb = new GsonBuilder();
        Gson gson = gsonb.create();
        Post pst = null;

        try {
            JSONObject j =new JSONObject(jString);
            pst=gson.fromJson(j.toString(),  Post.class);
         } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        Log.d("TAggeD", pst.username + pst.message);
        Log.d("TAGGED", gson.toJson(pst));
      
      
        
        
        //setListAdapter(new ArrayAdapter<String>(this,android.R.layout.user_list,valArray));
        
         // Register handler for UI elements
        BackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Back();
           }
        });
    }
	
	
	void Back(){
    	Intent i = new Intent(this, AndroidExample.class);
        startActivity(i);
    }   

}
