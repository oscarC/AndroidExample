package com.AndroidExample;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;


public class Dashboard extends TabActivity {
	private TabHost mTabHost; 
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        mTabHost = getTabHost();
        mTabHost.addTab(mTabHost.newTabSpec("RFSpot").setIndicator("Me").setContent(new Intent(this, User.class)));
        mTabHost.addTab(mTabHost.newTabSpec("RFSpot").setIndicator("Spot Deal").setContent(new Intent(this, Listuser.class)));
        mTabHost.addTab(mTabHost.newTabSpec("RFSpot").setIndicator("More").setContent(new Intent(this, Listuser.class)));
        
        mTabHost.setCurrentTab(0);
      

    }
}
