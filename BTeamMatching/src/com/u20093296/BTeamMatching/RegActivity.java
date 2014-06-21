package com.u20093296.bteammatching;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class RegActivity extends Activity implements OnClickListener {

	String phone, pass, area, height, weight;
	int pos;
	EditText etxt1,etxt2, etxt3, etxt4;
	AutoCompleteTextView autoCompView;
	Spinner obj;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.reg);

		etxt1 = (EditText) findViewById(R.id.Phone);
		etxt2 = (EditText) findViewById(R.id.editText2);
		etxt3 = (EditText) findViewById(R.id.editText3);
		etxt4 = (EditText) findViewById(R.id.editText4);

		TelephonyManager telephony = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

		String telPhoneNo = telephony.getLine1Number();
		etxt1.setText(telPhoneNo);

		autoCompView = (AutoCompleteTextView) findViewById(R.id.AutoCompleteTextView1);
		autoCompView.setAdapter(new PlacesAutoCompleteAdapter(this,R.layout.list_item));

		String[] optionLevel = getResources().getStringArray(
				R.array.spinnerArray1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, optionLevel);
		obj = (Spinner) findViewById(R.id.spinner1);

		obj.setAdapter(adapter);

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
		pass = etxt2.getText().toString();
		area = autoCompView.getText().toString();
		height = etxt3.getText().toString();
		weight = etxt4.getText().toString();

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Dialog");
		builder.setMessage("전화번호: " + phone + "\n패스워드: " + pass + "\n활동지역: " + area + "\n키: "
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
						
						SharedPreferences pref = getSharedPreferences("profile", MODE_PRIVATE);
				        SharedPreferences.Editor editor = pref.edit();
				        editor.putString("Phone", phone);
				        editor.putString("PW", pass);
				        editor.putString("Area", area);
				        editor.putString("height", height);
				        editor.putString("weight", weight);
				        editor.putString("position", ""+pos);
				        editor.commit();
						
						Intent intent = new Intent(RegActivity.this, TabViewActivity.class);
						startActivity(intent);
						finish();
					}
				});
		builder.show();
	}

	private static final String LOG_TAG = "ExampleApp";

	private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
	private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
	private static final String OUT_JSON = "/json";

	private static final String API_KEY = "AIzaSyB8ZCPZOzhgHnzTe_4TBxY0WIB51wKj_To";

	private ArrayList<String> autocomplete(String input) {
		ArrayList<String> resultList = null;

		HttpURLConnection conn = null;
		StringBuilder jsonResults = new StringBuilder();
		try {
			StringBuilder sb = new StringBuilder(PLACES_API_BASE
					+ TYPE_AUTOCOMPLETE + OUT_JSON);
			sb.append("?sensor=false&key=" + API_KEY);
			sb.append("&components=country:uk");
			sb.append("&input=" + URLEncoder.encode(input, "utf8"));

			URL url = new URL(sb.toString());
			conn = (HttpURLConnection) url.openConnection();
			InputStreamReader in = new InputStreamReader(conn.getInputStream());

			// Load the results into a StringBuilder
			int read;
			char[] buff = new char[1024];
			while ((read = in.read(buff)) != -1) {
				jsonResults.append(buff, 0, read);
			}
		} catch (MalformedURLException e) {
			Log.e(LOG_TAG, "Error processing Places API URL", e);
			return resultList;
		} catch (IOException e) {
			Log.e(LOG_TAG, "Error connecting to Places API", e);
			return resultList;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		try {
			// Create a JSON object hierarchy from the results
			JSONObject jsonObj = new JSONObject(jsonResults.toString());
			JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");

			// Extract the Place descriptions from the results
			resultList = new ArrayList<String>(predsJsonArray.length());
			for (int i = 0; i < predsJsonArray.length(); i++) {
				resultList.add(predsJsonArray.getJSONObject(i).getString(
						"description"));
			}
		} catch (JSONException e) {
			Log.e(LOG_TAG, "Cannot process JSON results", e);
		}

		return resultList;
	}

	private class PlacesAutoCompleteAdapter extends ArrayAdapter<String>
			implements Filterable {
		private ArrayList<String> resultList;

		public PlacesAutoCompleteAdapter(Context context, int textViewResourceId) {
			super(context, textViewResourceId);
		}

		@Override
		public int getCount() {
			return resultList.size();
		}

		@Override
		public String getItem(int index) {
			return resultList.get(index);
		}

		@Override
		public Filter getFilter() {
			Filter filter = new Filter() {
				@Override
				protected FilterResults performFiltering(CharSequence constraint) {
					FilterResults filterResults = new FilterResults();
					if (constraint != null) {
						// Retrieve the autocomplete results.
						resultList = autocomplete(constraint.toString());

						// Assign the data to the FilterResults
						filterResults.values = resultList;
						filterResults.count = resultList.size();
					}
					return filterResults;
				}

				@Override
				protected void publishResults(CharSequence constraint,
						FilterResults results) {
					if (results != null && results.count > 0) {
						notifyDataSetChanged();
					} else {
						notifyDataSetInvalidated();
					}
				}
			};
			return filter;
		}
	}
	//지역 자동완성 
	public class PlacesAutocompleteActivity extends Activity implements
			OnItemClickListener {
		@Override
		public void onCreate(Bundle savedInstanceState) {
			AutoCompleteTextView autoCompView = (AutoCompleteTextView) findViewById(R.id.AutoCompleteTextView1);
			autoCompView.setAdapter(new PlacesAutoCompleteAdapter(this,R.layout.list_item));
			autoCompView.setOnItemClickListener(this);
		}

		public void onItemClick(AdapterView<?> adapterView, View view,
				int position, long id) {
			String str = (String) adapterView.getItemAtPosition(position);
			Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
		}
	}
} // class END
