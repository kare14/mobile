package com.example.bteammatching;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DBAdapter extends CursorAdapter {


	public DBAdapter(Context context, Cursor c) {
		super(context, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {

		final ImageView image = (ImageView) view.findViewById(R.id.image);
		final TextView name = (TextView) view.findViewById(R.id.name);
		final TextView age = (TextView) view.findViewById(R.id.area);

		image.setImageResource(R.drawable.ic_launcher);
		name.setText("이름 : " + cursor.getString(cursor.getColumnIndex("Name")));
		age.setText("지역 : " + cursor.getString(cursor.getColumnIndex("Area")));

	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.listlayout2, parent, false);
		return v;
	}

}
