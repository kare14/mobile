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
			// 버튼 클릭 후 처리될 작업이 여기에서 실행됩니다.
			loginProcess(); // 로그인 버튼이 클릭되면 로그인 처리를 시작한다.
			Intent intent = new Intent(LoginActivity.this, TabViewActivity.class);
			startActivity(intent);
			finish();
		} else if (v.getId() == R.id.regButton) {
			Intent intent = new Intent(LoginActivity.this, RegActivity.class);
			startActivity(intent);
		}
	}

	// 네트웍 처리결과를 화면에 반영하기 위한 안드로이드 핸들러

	// responseHandler에 의해 처리된 결과가 success인 경우 바탕화면을 초록색으로 바꾸고

	// 로그인이 성공했다는 메시지를 토스트로 출력

	// 로그인이 실패한 경우 바탕화면을 빨강색으로 바꾸고 로그인실패 메시지를 토스트로 출력
	private final Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			pDialog.dismiss();
			String result = msg.getData().getString("RESULT");
			if (result.equals("success")) {
				layout01.setBackgroundColor(Color.GREEN);
				Toast.makeText(LoginActivity.this, "성공적으로 로그인하였습니다.",
						Toast.LENGTH_LONG).show();
			} else {
				layout01.setBackgroundColor(Color.RED);
				Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_LONG)
						.show();
			}
		}
	};

	// 서버에서 전송된 XML 데이터를 파싱하기 위한 메서드

	// 이 예제에서는 서버에서 로그인이 성공하는 경우(id=kim&passwd=111)하는 경우
	// <result>success</result>

	// 실패하는 경우 <result>failed</result>를 반환하도록 설정해 두었다.
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

	// 로그인 버튼이 클릭되면 수행되는 메서드

	// responseHandler는 Http요청에 대한 HttpResponse가 반환되면 결과를 처리하기 위한

	// 콜백메서드를 정의하고 있는 객체이다.

	// Response를 받게 되면 parsingData()메서드를 호출하여 서버로 부터 받은 XML 파일을 처리하여

	// 그결과를 result 문자열로 반환받는다.

	// 이렇게 반환받은 result문자열을 화면에 반영하기위해 안드로이드UI핸들러인 handler를 통해 값을 전달한다.
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

		// 로그인이 처리되고 있다는 다이얼로그를 화면에 표시한다.
		pDialog = ProgressDialog.show(this, "", "로그인 처리중....");

		// 서버에 HTTP 처리 요청은 새로운 스레드를 생성하여 비동기식으로 처리하는것이 효율적이다.
		new Thread() {

			@Override
			public void run() {
				String url = "http://192.168.10.2:8080/login.jsp";
				HttpClient http = new DefaultHttpClient();
				try {
					// 서버에 전달할 파라메터 세팅
					ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
					nameValuePairs.add(new BasicNameValuePair("id", id
							.getText().toString()));
					nameValuePairs.add(new BasicNameValuePair("passwd", passwd
							.getText().toString()));

					// 응답시간이 5초가 넘으면 timeout 처리하려면 아래 코드의 커맨트를 풀고 실행한다.
					// HttpParams params = http.getParams();
					// HttpConnectionParams.setConnectionTimeout(params, 5000);
					// HttpConnectionParams.setSoTimeout(params, 5000);

					// HTTP를 통해 서버에 요청을 전달한다.

					// 요청에 대한결과는 responseHandler의 handleResponse()메서드가 호출되어
					// 처리한다.

					// 서버에 전달되는 파라메터값을 인코딩하기위해 UrlEncodedFormEntity() 메서드를 사용한다.

					HttpPost httpPost = new HttpPost(url);
					UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(
							nameValuePairs, "UTF-8");
					httpPost.setEntity(entityRequest);
					http.execute(httpPost, responseHandler);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start(); // 스레드를 실행시킨다.
	}

}
