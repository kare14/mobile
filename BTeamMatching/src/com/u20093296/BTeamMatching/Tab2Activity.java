package com.u20093296.BTeamMatching;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class Tab2Activity extends Activity {
	protected void onCreate(Bundle bun) {
		super.onCreate(bun);
		TextView  tv = new  TextView( this ) ;
		tv.setTextColor(Color.RED) ;
		tv.setTextSize(28) ;
		tv.setText("ã��") ;
		this.setContentView(tv) ;
	} //end
	
} //class END
