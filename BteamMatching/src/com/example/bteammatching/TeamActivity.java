package com.example.bteammatching;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TeamActivity extends Activity implements OnClickListener {
	DBManagerHandler handler;
	TextView name, area, gamenum, win, lose;
	Button regbnt;

	TextView[] player = new TextView[5];
	String[] p = new String[5];
	int i;
	String n, a;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.tab3);
		handler = new DBManagerHandler(getApplicationContext());
		SharedPreferences pref1 = getSharedPreferences("profileT", MODE_PRIVATE);
		SharedPreferences pref2 = getSharedPreferences("profile", MODE_PRIVATE);
		
		name = (TextView) findViewById(R.id.name);
		area = (TextView) findViewById(R.id.area);
		gamenum = (TextView) findViewById(R.id.gn);
		win = (TextView) findViewById(R.id.w);
		lose = (TextView) findViewById(R.id.l);
		regbnt = (Button) findViewById(R.id.button1);
		player[0] = (TextView) findViewById(R.id.player1);
		player[1] = (TextView) findViewById(R.id.player2);
		player[2] = (TextView) findViewById(R.id.player3);
		player[3] = (TextView) findViewById(R.id.player4);
		player[4] = (TextView) findViewById(R.id.player5);

		// regbnt = (Button) findViewById(R.id.button2);
		n=pref1.getString("Name", "");
		a=pref1.getString("Area", "");
		name.setText(name.getText().toString() + n);
		area.setText(area.getText().toString() + a);

		if (pref2.getString("Team", "") != "") {
			Cursor cursor = handler.select("team", " _id= ?",	new String[] { pref2.getString("Team", "") });

			while (cursor.moveToNext()) {
				String name = cursor.getString(cursor.getColumnIndex("Name"));
				String area = cursor.getString(cursor.getColumnIndex("Area"));

				if (name.equals(n)) {
					String gn = cursor.getString(cursor
							.getColumnIndex("GameNumber"));
					String w = cursor.getString(cursor.getColumnIndex("Win"));
					String l = cursor.getString(cursor.getColumnIndex("Lose"));
					p[0] = cursor.getString(cursor.getColumnIndex("player1"));
					p[1] = cursor.getString(cursor.getColumnIndex("player2"));
					p[2] = cursor.getString(cursor.getColumnIndex("player3"));
					p[3] = cursor.getString(cursor.getColumnIndex("player4"));
					p[4] = cursor.getString(cursor.getColumnIndex("player5"));
					gamenum.setText(gn);
					win.setText(w);
					lose.setText(l);
					break;
				}
			}
			String[] optionLevel = getResources().getStringArray(R.array.spinnerArray1);
			for (i = 0; i < 5; i++) {
				if (p[i] != null)
				{
						Cursor cursor1 = handler.select("player",
								"_id= ?", new String[] { p[i] });

						while (cursor1.moveToNext()) {
							String id = cursor1.getString(cursor1
									.getColumnIndex("_id"));

							if (id.equals(p[i])) {
								player[i]
										.setText(""
												+ cursor1.getString(cursor1
														.getColumnIndex("Name"))
												+ "   "
												+ optionLevel[Integer.parseInt(cursor1.getString(cursor1.getColumnIndex("Position")))]
												+ "   "
												+ cursor1.getString(cursor1
														.getColumnIndex("Height"))
												+ "cm/"
												+ cursor1.getString(cursor1
														.getColumnIndex("Weight"))
												+ "kg "	);
										// +cursor.getString(cursor.getColumnIndex("WP"))+" "+
										// +cursor.getString(cursor.getColumnIndex("SP"))+" "
									
								break;
							}
		
						}
				}
			}
			handler.close();
		}
		regbnt.setOnClickListener((OnClickListener) this);
		// bnt.setOnClickListener(this);

	} // end

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.button1) {
			// 버튼 클릭 후 처리될 작업이 여기에서 실행됩니다.
			Intent intent = new Intent(TeamActivity.this, TeamRegActivity.class);
			startActivity(intent);
		}
		/*
		 * else if (v.getId() == R.id.button2) { Intent intent = new
		 * Intent(TeamActivity.this, RegActivity.class); startActivity(intent);
		 * }
		 */
	}
} // class END
