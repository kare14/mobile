package com.example.bteammatching;

import android.os.Bundle;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class TabViewActivity extends TabActivity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Resources  res = this.getResources() ;
		TabHost  tabhost  = this.getTabHost() ;
		TabHost.TabSpec  spec ; 
		Intent  intent  = null ;
		
		intent  = new  Intent( ).setClass(this, MatchActivity.class) ;
		spec=tabhost.newTabSpec("Tab1").setIndicator("°æ±â ¸ÅÄª", res.getDrawable(R.drawable.f3)).setContent(intent) ;
		tabhost.addTab(spec) ;
		
		intent  = new  Intent( ).setClass(this, SearchActivity.class) ;
		spec=tabhost.newTabSpec("Tab2").setIndicator("ÆÀ/ÆÀ¿ø Ã£±â" , res.getDrawable(R.drawable.f4)).setContent(intent) ;
		tabhost.addTab(spec) ;
		
		intent  = new  Intent( ).setClass(this, TeamActivity.class) ;
		spec=tabhost.newTabSpec("Tab3").setIndicator("ÆÀ" , res.getDrawable(R.drawable.f5)).setContent(intent) ;
		tabhost.addTab(spec) ;
		
		intent  = new  Intent( ).setClass(this, ProfileActivity.class) ;
		spec=tabhost.newTabSpec("Tab1").setIndicator("ÇÁ·ÎÇÊ", res.getDrawable(R.drawable.f1)).setContent(intent) ;
		tabhost.addTab(spec) ;
		
		tabhost.setCurrentTab(0) ; 
	} //end
	
} //class END


