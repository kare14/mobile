package com.example.bteammatching;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {
	private EditText id;
	private EditText passwd;
	private ProgressDialog pDialog;
	private Button loginbnt, regbnt;
	DBManagerHandler dbhandler;
	String result = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		dbhandler = new DBManagerHandler(getApplicationContext());
		dbhandler.createTest();

		id = (EditText) findViewById(R.id.id);
		passwd = (EditText) findViewById(R.id.passwd);
		loginbnt = (Button) findViewById(R.id.loginButton);
		regbnt = (Button) findViewById(R.id.regButton);

		SharedPreferences pref = getSharedPreferences("profile", MODE_PRIVATE);
		String telPhoneNo;
		TelephonyManager telephony = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

		if (pref.getString("Phone", "") != "")
			telPhoneNo = pref.getString("Phone", "");
		else {

			telPhoneNo = telephony.getLine1Number();
		}

		id.setText(telPhoneNo);
		if (pref.getString("PW", "") != "")
			passwd.setText(pref.getString("PW", ""));

		loginbnt.setOnClickListener(this);
		regbnt.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.loginButton) {
			// 버튼 클릭 후 처리될 작업이 여기에서 실행됩니다.
			loginProcess(); // 로그인 버튼이 클릭되면 로그인 처리를 시작한다.
			if (result.equals("success")) {

				Toast.makeText(LoginActivity.this, "성공적으로 로그인하였습니다.",
						Toast.LENGTH_LONG).show();

				Intent intent = new Intent(LoginActivity.this,
						TabViewActivity.class);
				startActivity(intent);
				finish();
			} else {

				Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_LONG)
						.show();
				pDialog.cancel();
			}
		} else if (v.getId() == R.id.regButton) {
			Intent intent = new Intent(LoginActivity.this, RegActivity.class);
			startActivity(intent);
			finish();
		}
	}

	public void loginProcess() {
		Cursor cursor = dbhandler.select("player", " Phone= ?",
				new String[] { id.getText().toString() });

		while (cursor.moveToNext()) {
			String phone = cursor.getString(cursor.getColumnIndex("Phone"));
			String pw = cursor.getString(cursor.getColumnIndex("PW"));
			String pos = cursor.getString(cursor.getColumnIndex("Position"));
			String _id = cursor.getString(cursor.getColumnIndex("_id"));
			String name = cursor.getString(cursor.getColumnIndex("Name"));
			String area = cursor.getString(cursor.getColumnIndex("Area"));
			String h = cursor.getString(cursor.getColumnIndex("Height"));
			String w = cursor.getString(cursor.getColumnIndex("Weight"));
			


			if (phone.equals(id.getText().toString())
					&& pw.equals(passwd.getText().toString())) {
				
				SharedPreferences pref = getSharedPreferences("profile",
						MODE_PRIVATE);
				SharedPreferences.Editor editor = pref.edit();
				editor.putString("ID", "" + _id);
				editor.putString("Phone", phone);
				editor.putString("Name", name);
				editor.putString("PW", pw);
				editor.putString("Area", area);
				editor.putString("Height", h);
				editor.putString("Weight", w);
				editor.putString("Position", "" + pos);
				editor.commit();
				result = "success";
				break;
			}
		}
		dbhandler.close();

		// 로그인이 처리되고 있다는 다이얼로그를 화면에 표시한다.
		pDialog = ProgressDialog.show(this, "", "로그인 처리중....");
	}

}
