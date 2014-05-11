package com.u20093296.BTeamMatching;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.view.Menu;
import android.widget.TabHost;

public class TabViewActivity extends TabActivity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Resources  res = this.getResources() ;
		TabHost  tabhost  = this.getTabHost() ;
		TabHost.TabSpec  spec ; 
		Intent  intent  = null ;
		
		intent  = new  Intent( ).setClass(this, Tab1Activity.class) ;
		spec=tabhost.newTabSpec("Tab1").setIndicator("°æ±â ¸ÅÄª", res.getDrawable(R.drawable.f3)).setContent(intent) ;
		tabhost.addTab(spec) ;
		
		intent  = new  Intent( ).setClass(this, Tab2Activity.class) ;
		spec=tabhost.newTabSpec("Tab2").setIndicator("ÆÀ/ÆÀ¿ø Ã£±â" , res.getDrawable(R.drawable.f4)).setContent(intent) ;
		tabhost.addTab(spec) ;
		
		intent  = new  Intent( ).setClass(this, Tab3Activity.class) ;
		spec=tabhost.newTabSpec("Tab3").setIndicator("ÆÀ" , res.getDrawable(R.drawable.f5)).setContent(intent) ;
		tabhost.addTab(spec) ;
		
		intent  = new  Intent( ).setClass(this, Tab4Activity.class) ;
		spec=tabhost.newTabSpec("Tab1").setIndicator("ÇÁ·ÎÇÊ", res.getDrawable(R.drawable.f1)).setContent(intent) ;
		tabhost.addTab(spec) ;
		
		tabhost.setCurrentTab(0) ; 
	} //end
	
} //class END


