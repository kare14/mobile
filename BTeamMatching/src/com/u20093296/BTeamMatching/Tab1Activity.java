package com.u20093296.BTeamMatching;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class Tab1Activity extends Activity {
	protected void onCreate(Bundle bun) {
		super.onCreate(bun);
		TextView  tv = new  TextView( this ) ;
		tv.setTextColor(Color.BLUE) ;
		tv.setTextSize(28) ;
		tv.setText("°æ±â ¸ÅÄª") ;
		this.setContentView(tv) ;
	} //end
	
} //class END
