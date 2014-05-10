package com.u20093296.BTeamMatching;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class Tab3Activity extends Activity {
	protected void onCreate(Bundle bun) {
		super.onCreate(bun);
		TextView  tv = new  TextView( this ) ;
		tv.setTextColor(Color.GREEN) ;
		tv.setTextSize(28) ;
		tv.setText("ÆÀ") ;
		this.setContentView(tv) ;
	} //end
	
} //class END
