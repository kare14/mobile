package com.example.bteammatching;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;

public class ProfileActivity extends Activity implements OnClickListener {
	DBManagerHandler handler;
	String phone,name,pass, area, height, weight;
	int pos;
	EditText etxt2, etxt3, etxt4,etxt5;
	TextView tv1;
	AutoCompleteTextView autoCompView;
	Spinner obj;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.tab4);
		handler = new DBManagerHandler(getApplicationContext());
		SharedPreferences pref = getSharedPreferences("profile", MODE_PRIVATE);
		
		tv1 = (TextView) findViewById(R.id.Phone);
		etxt2 = (EditText) findViewById(R.id.editText2);
		etxt3 = (EditText) findViewById(R.id.editText3);
		etxt4 = (EditText) findViewById(R.id.editText4);
		etxt5 = (EditText) findViewById(R.id.EditText01);
		autoCompView = (AutoCompleteTextView) findViewById(R.id.AutoCompleteTextView1);
		
		tv1.setText(pref.getString("Phone", ""));
		etxt5.setText(pref.getString("Name", ""));
		etxt2.setText(pref.getString("PW", ""));
		etxt3.setText(pref.getString("Height", "" ));
		etxt4.setText(pref.getString("Weight", "" ));

		
	    //pass=pref.getString("PW", "");
		pos=Integer.parseInt(pref.getString("Position", ""));
		
		String[] autoDataList = getResources().getStringArray(
				R.array.autoDataArray1);
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, autoDataList);
		
		autoCompView = (AutoCompleteTextView) findViewById(R.id.AutoCompleteTextView1);
		
		autoCompView.setAdapter(adapter1);

		autoCompView.setText(pref.getString("Area", ""));
		
		String[] optionLevel = getResources().getStringArray(R.array.spinnerArray1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, optionLevel);
		obj = (Spinner) findViewById(R.id.spinner1);

		obj.setAdapter(adapter);

		obj.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parenView,
					View selectedView, int position, long id) {
				obj.setSelection(pos);
				pos =obj.getSelectedItemPosition();

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
		phone = tv1.getText().toString();
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
				        val.put("Name", name);
				        val.put("PW", pass);
				        val.put("Area", area);
				        val.put("Height", height);
				        val.put("Weight", weight);
				        val.put("Position", ""+pos);
						handler.update("player",val,"Phone", phone);
				        handler.close();
						
						//스마트폰에 data 저장
						SharedPreferences pref = getSharedPreferences("profile", MODE_PRIVATE);
				        SharedPreferences.Editor editor = pref.edit();
				        editor.putString("Phone", phone);
				        editor.putString("Name", name);
				        editor.putString("PW", pass);
				        editor.putString("Area", area);
				        editor.putString("Height", height);
				        editor.putString("Weight", weight);
				        editor.putString("Position", ""+pos);
				        editor.commit();
						
				        Toast.makeText(ProfileActivity.this, "성공적으로 수정하였습니다.",
								Toast.LENGTH_LONG).show();
					}
				});
		builder.show();
		
	}

} // class END
