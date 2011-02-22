package com.AndroidExample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AndroidExample extends Activity {
    /** Called when the activity is first created. */
    private Button loginButton;
    private EditText username;
    private EditText password;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        loginButton = (Button) findViewById(R.id.login);
        // Register handler for UI elements
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	username = (EditText) findViewById(R.id.username);
            	password = (EditText) findViewById(R.id.password);
            	ViewUser(username.getText().toString());
        
           }
        });
        
       
        
    }
    
    
    void ViewUser(String name){
    	Bundle bundle = new Bundle();

    	//Add the parameters to bundle as 
    	bundle.putString("NAME",name);

    	Intent i = new Intent(this, Listuser.class);
    	i.putExtras(bundle);
    	startActivity(i);
    }    
}