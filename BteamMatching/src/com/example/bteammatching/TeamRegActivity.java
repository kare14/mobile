package com.example.bteammatching;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.view.View.OnClickListener;

public class TeamRegActivity extends Activity implements OnClickListener {
	DBManagerHandler handler;
	String name, area;
	EditText etxt1;
	AutoCompleteTextView autoCompView;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.regteam);
		handler = new DBManagerHandler(getApplicationContext());
		etxt1 = (EditText) findViewById(R.id.EditText01);

		String[] autoDataList = getResources().getStringArray(
				R.array.autoDataArray1);
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, autoDataList);

		autoCompView = (AutoCompleteTextView) findViewById(R.id.AutoCompleteTextView1);

		autoCompView.setAdapter(adapter1);

		Button resultBnt = (Button) findViewById(R.id.button1);

		resultBnt.setOnClickListener(this);

	} // end

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		name = etxt1.getText().toString();
		area = autoCompView.getText().toString();

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Dialog");
		builder.setMessage("이 름: " + name + "\n활동지역: " + area + "\n\n맞습니까?");
		builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		builder.setPositiveButton("등록", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				SharedPreferences pref1 = getSharedPreferences("profile",
						MODE_PRIVATE);
				SharedPreferences.Editor editor1 = pref1.edit();

				ContentValues val = new ContentValues();
				val.put("Name", name);
				val.put("Area", area);
				val.put("player1", pref1.getString("ID", ""));
				Long id = handler.insert("team", val);
				val.clear();
				
				val.put("Team", "" + id);
				handler.update("player", val, "_id",pref1.getString("ID", ""));
				handler.close();

				editor1.putString("Team", "" + id);
				editor1.commit();

				// 스마트폰에 data 저장
				SharedPreferences pref2 = getSharedPreferences("profileT",
						MODE_PRIVATE);
				SharedPreferences.Editor editor2 = pref2.edit();
				editor2.putString("Name", name);
				editor2.putString("Area", area);
				editor2.putString("player1", pref1.getString("ID", ""));
				editor2.commit();

				Intent intent = new Intent(TeamRegActivity.this,
						TabViewActivity.class);
				startActivity(intent);
				finish();
			}
		});
		builder.show();
	}

} // class END
