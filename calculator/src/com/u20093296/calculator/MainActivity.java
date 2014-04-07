package com.u20093296.calculator;

import java.util.Stack;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
	Button one,two,three,four,five,six ,seven,eight,nine,zero ,plus,minus,divide,multiply,equal;
	EditText etex1,etex2;
	String string;
	String temp;
	int length;
	int result=0;
	Stack<String> s = new Stack<String>(); 

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		one = (Button) findViewById(R.id.Button00);
		two = (Button) findViewById(R.id.Button01);
		three = (Button) findViewById(R.id.Button02);
		four = (Button) findViewById(R.id.Button03);
		five = (Button) findViewById(R.id.Button04);
		six = (Button) findViewById(R.id.Button05);
		seven = (Button) findViewById(R.id.Button06);
		eight = (Button) findViewById(R.id.Button07);
		nine = (Button) findViewById(R.id.Button08);
		zero = (Button) findViewById(R.id.Button09);
		plus = (Button) findViewById(R.id.Button10);
		minus = (Button) findViewById(R.id.Button11);
		multiply = (Button) findViewById(R.id.Button12);
		divide = (Button) findViewById(R.id.Button13);
		equal = (Button) findViewById(R.id.Button14);
		etex1 = (EditText) findViewById(R.id.editText1);
		etex2= (EditText) findViewById(R.id.editText2);
		
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
		
		plus.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View arg0) 
			{
				
				string=etex1.getText().toString();

				
				s.push(string);
				s.push("+");
				if(s.size() == 2){
					calculate();
				}
				etex1.setText("");
			}
		});
		
		minus.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View arg0) 
			{

				string=etex1.getText().toString();

				
				s.push(string);
				s.push("-");
				if(s.size() == 2){
					calculate();
				}
				etex1.setText("");
			}
		});
		
		multiply.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View arg0) 
			{

				string=etex1.getText().toString();
				s.push(string);
				s.push("x");
				if(s.size() == 2){
					calculate();
				}
				etex1.setText("");
			}
		});
		
		divide.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View arg0) 
			{

				string=etex1.getText().toString();
				s.push(string);
				s.push("/");
				if(s.size() == 2){
					calculate();
				}
				etex1.setText("");
			}
		});
		
		equal.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View arg0) 
			{
				calculate();
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
	
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	} */


	void setNum(String a){
		string=etex1.getText().toString();
		length=etex1.getText().toString().length();
			
		if(string.equals("")||string.equals("0")){
			etex1.setText(""+a);
		}
		else{
			etex1.setText(string+a);
		}
	}
	void calculate(){
		String temp1,temp2,temp3;
		
		string=etex1.getText().toString();
		s.push(string);
			
		temp1 = s.pop();
		temp2 = s.pop();
		temp3 = s.pop();
		if (temp2.equals("+")){
			result = Integer.parseInt(temp3) + Integer.parseInt(temp1);
			s.push(""+result);
		}
		else if (temp2.equals("-")){
			result = Integer.parseInt(temp3) - Integer.parseInt(temp1);
			s.push(""+result);
		}
		else if (temp2.equals("x")){
			result = Integer.parseInt(temp3) * Integer.parseInt(temp1);
			s.push(""+result);
		}
		else if (temp2.equals("/")){
			
			result = Integer.parseInt(temp3) / Integer.parseInt(temp1);
			s.push(""+result);
		}

		etex2.setText(""+result);
		etex1.setText("");
	}
	void setStr(String a){
		string=etex1.getText().toString();

		etex1.setText(a);


	}
}