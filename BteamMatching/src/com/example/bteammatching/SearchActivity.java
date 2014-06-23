package com.example.bteammatching;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class SearchActivity extends Activity implements OnClickListener {

	ListView list;
	String sql;
	Cursor cursor;
	DBManagerHandler handler;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		list = (ListView) findViewById(R.id.listView1);
		handler = new DBManagerHandler(getApplicationContext());
		
		cursor = handler.selectAll("player");
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
			DBAdapter dbAdapter = new DBAdapter(this, cursor);
			list.setAdapter(dbAdapter);
		}

		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				cursor.moveToPosition(position);
				String str = cursor.getString(cursor.getColumnIndex("name"));
				Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT)
						.show();
			}
		});

	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

} // class END

