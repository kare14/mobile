package com.u20093296.BTeamMatching;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {
	private EditText id;
	private EditText passwd;
	private ProgressDialog pDialog;
	private LinearLayout layout01;
	private Button loginbnt, regbnt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		id = (EditText) findViewById(R.id.id);
		passwd = (EditText) findViewById(R.id.passwd);
		loginbnt = (Button) findViewById(R.id.loginButton);
		regbnt = (Button) findViewById(R.id.regButton);
		layout01 = (LinearLayout) findViewById(R.id.layout01);
		loginbnt.setOnClickListener(this);
		regbnt.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.loginButton) {
			// ��ư Ŭ�� �� ó���� �۾��� ���⿡�� ����˴ϴ�.
			loginProcess(); // �α��� ��ư�� Ŭ���Ǹ� �α��� ó���� �����Ѵ�.
			Intent intent = new Intent(LoginActivity.this, TabViewActivity.class);
			startActivity(intent);
			finish();
		} else if (v.getId() == R.id.regButton) {
			Intent intent = new Intent(LoginActivity.this, RegActivity.class);
			startActivity(intent);
		}
	}

	// ��Ʈ�� ó������� ȭ�鿡 �ݿ��ϱ� ���� �ȵ���̵� �ڵ鷯

	// responseHandler�� ���� ó���� ����� success�� ��� ����ȭ���� �ʷϻ����� �ٲٰ�

	// �α����� �����ߴٴ� �޽����� �佺Ʈ�� ���

	// �α����� ������ ��� ����ȭ���� ���������� �ٲٰ� �α��ν��� �޽����� �佺Ʈ�� ���
	private final Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			pDialog.dismiss();
			String result = msg.getData().getString("RESULT");
			if (result.equals("success")) {
				layout01.setBackgroundColor(Color.GREEN);
				Toast.makeText(LoginActivity.this, "���������� �α����Ͽ����ϴ�.",
						Toast.LENGTH_LONG).show();
			} else {
				layout01.setBackgroundColor(Color.RED);
				Toast.makeText(LoginActivity.this, "�α��� ����", Toast.LENGTH_LONG)
						.show();
			}
		}
	};

	// �������� ���۵� XML �����͸� �Ľ��ϱ� ���� �޼���

	// �� ���������� �������� �α����� �����ϴ� ���(id=kim&passwd=111)�ϴ� ���
	// <result>success</result>

	// �����ϴ� ��� <result>failed</result>�� ��ȯ�ϵ��� ������ �ξ���.
	public String parsingData(InputStream input) {
		String result = null;
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(new InputStreamReader(input));
			while (parser.next() != XmlPullParser.END_DOCUMENT) {
				String name = parser.getName();
				if (name != null && name.equals("result"))
					result = parser.nextText();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// �α��� ��ư�� Ŭ���Ǹ� ����Ǵ� �޼���

	// responseHandler�� Http��û�� ���� HttpResponse�� ��ȯ�Ǹ� ����� ó���ϱ� ����

	// �ݹ�޼��带 �����ϰ� �ִ� ��ü�̴�.

	// Response�� �ް� �Ǹ� parsingData()�޼��带 ȣ���Ͽ� ������ ���� ���� XML ������ ó���Ͽ�

	// �װ���� result ���ڿ��� ��ȯ�޴´�.

	// �̷��� ��ȯ���� result���ڿ��� ȭ�鿡 �ݿ��ϱ����� �ȵ���̵�UI�ڵ鷯�� handler�� ���� ���� �����Ѵ�.
	public void loginProcess() {
		final ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

			@Override
			public String handleResponse(HttpResponse response)
					throws ClientProtocolException, IOException {
				String result = null;
				HttpEntity entity = response.getEntity();
				result = parsingData(entity.getContent());
				Message message = handler.obtainMessage();
				Bundle bundle = new Bundle();
				if (result.equals("success"))
					bundle.putString("RESULT", "success");
				else
					bundle.putString("RESULT", "failed");
				message.setData(bundle);
				handler.sendMessage(message);
				return result;
			}
		};

		// �α����� ó���ǰ� �ִٴ� ���̾�α׸� ȭ�鿡 ǥ���Ѵ�.
		pDialog = ProgressDialog.show(this, "", "�α��� ó����....");

		// ������ HTTP ó�� ��û�� ���ο� �����带 �����Ͽ� �񵿱������ ó���ϴ°��� ȿ�����̴�.
		new Thread() {

			@Override
			public void run() {
				String url = "http://192.168.10.2:8080/login.jsp";
				HttpClient http = new DefaultHttpClient();
				try {
					// ������ ������ �Ķ���� ����
					ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
					nameValuePairs.add(new BasicNameValuePair("id", id
							.getText().toString()));
					nameValuePairs.add(new BasicNameValuePair("passwd", passwd
							.getText().toString()));

					// ����ð��� 5�ʰ� ������ timeout ó���Ϸ��� �Ʒ� �ڵ��� Ŀ��Ʈ�� Ǯ�� �����Ѵ�.
					// HttpParams params = http.getParams();
					// HttpConnectionParams.setConnectionTimeout(params, 5000);
					// HttpConnectionParams.setSoTimeout(params, 5000);

					// HTTP�� ���� ������ ��û�� �����Ѵ�.

					// ��û�� ���Ѱ���� responseHandler�� handleResponse()�޼��尡 ȣ��Ǿ�
					// ó���Ѵ�.

					// ������ ���޵Ǵ� �Ķ���Ͱ��� ���ڵ��ϱ����� UrlEncodedFormEntity() �޼��带 ����Ѵ�.

					HttpPost httpPost = new HttpPost(url);
					UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(
							nameValuePairs, "UTF-8");
					httpPost.setEntity(entityRequest);
					http.execute(httpPost, responseHandler);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start(); // �����带 �����Ų��.
	}

}
