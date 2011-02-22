package com.AndroidExample;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;  
import org.json.JSONException;  
import org.json.JSONObject;  

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class Listuser extends ListActivity  {
	
	public ArrayList<String> UserList = new ArrayList<String>();
	public HashMap<String, String> Object = new HashMap<String, String>();
	
	public class MyCustomAdapter extends ArrayAdapter<String> {
		public ArrayList<String> UserList = new ArrayList<String>();
		public MyCustomAdapter(Context context, int textViewResourceId,
				ArrayList<String> objects) {
			super(context, textViewResourceId, objects);
			this.UserList=objects;
			// TODO Auto-generated constructor stub
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			//return super.getView(position, convertView, parent);
			LayoutInflater inflater=getLayoutInflater();
			View row=inflater.inflate(R.layout.row, parent, false);
			TextView label=(TextView)row.findViewById(R.id.weekofday);
			TextView other=(TextView)row.findViewById(R.id.other);
			label.setText(this.UserList.get(position));
			other.setText(" - "+this.UserList.get(position));
			ImageView icon=(ImageView)row.findViewById(R.id.icon);
			icon.setImageResource(R.drawable.icon);
			
			
			return row;
		}
	}


	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		//super.onListItemClick(l, v, position, id);
		String selection = l.getItemAtPosition(position).toString();
		Toast.makeText(this, selection, Toast.LENGTH_LONG).show();
	}
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  String[] array = {"One","Two","Three","Junior","Oscar"};
		  String json = new Gson().toJson(array);
          // De-serialise
		  String[] array2 = new Gson().fromJson(json,String[].class);
		  setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array));
          getListView().setTextFilterEnabled(true);
          
		  HttpClient client = new DefaultHttpClient();
          HttpGet post = new HttpGet("http://192.168.1.6:9292/test");
          post.setHeader("Accept", "application/json");
          post.setHeader("Content-type", "application/json");
          post.addHeader(BasicScheme.authenticate(new UsernamePasswordCredentials("admin", "admin"), "UTF-8", false));
         
          
          HttpResponse reponse;
			try {
				reponse = client.execute(post);
				HttpEntity entity = reponse.getEntity();
				if (entity != null) {
	                InputStream instream = entity.getContent();
	                JSONObject jsonResponse = new JSONObject(convertStreamToString(instream)); 
	                JSONArray json_resul = jsonResponse.getJSONArray("user");  
	                instream.close();
	                
	                for(int i=0;i<json_resul.length();i++) {
	                    JSONObject jsonObj = json_resul.getJSONObject(i);
	                    String name = jsonObj.getString("name");
	                    UserList.add(name); 
	     
	                }
	                //setListAdapter(new MyCustomAdapter(this, R.layout.row, UserList));  
	               
	            }
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			 
	}
	
   
	private static String convertStreamToString(InputStream is) throws IOException{
		 
        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"ISO-8859-1"));
        StringBuilder sb = new StringBuilder();
 
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
 
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw e;
            }
        }
        return sb.toString();
    }
	  
	

}
