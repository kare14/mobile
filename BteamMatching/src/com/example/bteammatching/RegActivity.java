package com.example.bteammatching;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;


public class RegActivity extends Activity implements OnClickListener {
	DBManagerHandler handler;
	String phone, name, pass, area, height, weight;
	int pos;
	EditText etxt1,etxt2, etxt3, etxt4,etxt5;
	AutoCompleteTextView autoCompView;
	Spinner obj;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.reg);

		handler = new DBManagerHandler(getApplicationContext());
		
		etxt1 = (EditText) findViewById(R.id.Phone);
		etxt2 = (EditText) findViewById(R.id.editText2);
		etxt3 = (EditText) findViewById(R.id.editText3);
		etxt4 = (EditText) findViewById(R.id.editText4);
		etxt5 = (EditText) findViewById(R.id.EditText01);

		TelephonyManager telephony = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

		String telPhoneNo = telephony.getLine1Number();
		etxt1.setText(telPhoneNo);

		String[] autoDataList = getResources().getStringArray(
				R.array.autoDataArray1);
		
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, autoDataList);
		
		autoCompView = (AutoCompleteTextView) findViewById(R.id.AutoCompleteTextView1);
		
		autoCompView.setAdapter(adapter1);

		String[] optionLevel = getResources().getStringArray(
				R.array.spinnerArray1);
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, optionLevel);
		obj = (Spinner) findViewById(R.id.spinner1);

		obj.setAdapter(adapter2);

		obj.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parenView,
					View selectedView, int position, long id) {
				pos = obj.getSelectedItemPosition();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parenView) {

			}

		});

		Button resultBnt = (Button) findViewById(R.id.button1);

		resultBnt.setOnClickListener(this);

	} // end

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		phone = etxt1.getText().toString();
		name = etxt5.getText().toString();
		pass = etxt2.getText().toString();
		area = autoCompView.getText().toString();
		height = etxt3.getText().toString();
		weight = etxt4.getText().toString();

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Dialog");
		builder.setMessage("전화번호: " + phone + "\n이 름: " + name +"\n패스워드: " + pass + "\n활동지역: " + area + "\n키: "
				+ height + "\n몸무게: " + weight + "\n포지션: " + (String) obj.getAdapter().getItem(
						pos) + "\n\n맞습니까?");
		builder.setNegativeButton("취소",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				});
		builder.setPositiveButton("등록",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						
					    ContentValues val = new ContentValues();
				        val.put("Phone", phone);
				        val.put("Name", name);
				        val.put("PW", pass);
				        val.put("Area", area);
				        val.put("Height", height);
				        val.put("Weight", weight);
				        val.put("Position", ""+pos);
						Long id=handler.insert("player",val);
				        handler.close();
				        
				        Toast.makeText(RegActivity.this, ""+id,
								Toast.LENGTH_LONG).show();
						//스마트폰에 data 저장
						SharedPreferences pref = getSharedPreferences("profile", MODE_PRIVATE);
				        SharedPreferences.Editor editor = pref.edit();
				        editor.putString("ID", ""+id);
				        editor.putString("Phone", phone);
				        editor.putString("Name", name);
				        editor.putString("PW", pass);
				        editor.putString("Area", area);
				        editor.putString("Height", height);
				        editor.putString("Weight", weight);
				        editor.putString("Position", ""+pos);
				        editor.commit();
						
				        
				        Intent intent = new Intent(RegActivity.this, LoginActivity.class);
						startActivity(intent);
						finish();
						
					}
				});
		builder.show();
	}

} // class END
