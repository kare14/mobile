package com.u20093296.calltest;

import com.example.calltest.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

	
	Button one,two,three,four,five,six ,seven,eight,nine,zero,call;
	EditText etex1;
	
	String string;
	String temp;
	int length;

	Activity act=this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		one = (Button) findViewById(R.id.button1);
		two = (Button) findViewById(R.id.button2);
		three = (Button) findViewById(R.id.button3);
		four = (Button) findViewById(R.id.button4);
		five = (Button) findViewById(R.id.button5);
		six = (Button) findViewById(R.id.button6);
		seven = (Button) findViewById(R.id.button7);
		eight = (Button) findViewById(R.id.button8);
		nine = (Button) findViewById(R.id.button9);
		zero = (Button) findViewById(R.id.button10);

		call = (Button) findViewById(R.id.button11);
	
		etex1 = (EditText) findViewById(R.id.editText1);

		one.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View arg0) 
			{
				setNum("1");
			}
		});
		
		two.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View arg0) 
			{
				setNum("2");
			}
		});
		
		three.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View arg0) 
			{
				setNum("3");
			}
		});
		
		four.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View arg0) 
			{
				setNum("4");
			}
		});
		
		five.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View arg0) 
			{
				setNum("5");
			}
		});
		
		six.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View arg0) 
			{
				setNum("6");
			}
		});
		
		seven.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View arg0) 
			{
				setNum("7");
			}
		});
		
		eight.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View arg0) 
			{
				setNum("8");
			}
		});
		
		nine.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View arg0) 
			{
				setNum("9");
			}
		});
		
		zero.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View arg0) 
			{
				setNum("0");
			}
		});
		
		
		call.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View arg0) 
			{
				//Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:555-1234"));
				Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+string));
				act.startActivity(intent);
			}
		});
		
		/*if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	/*public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}*/
	
	void setNum(String a){
		string=etex1.getText().toString();

		etex1.setText(string+a);

	}
}
