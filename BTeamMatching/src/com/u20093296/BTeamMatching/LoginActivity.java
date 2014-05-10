package com.u20093296.BTeamMatching;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity{
	EditText e1;
    EditText e2;
    TextView s1;
    Button b1;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        
      e1=(EditText)findViewById(R.id.id);
      e2=(EditText)findViewById(R.id.pass);
      s1=(TextView)findViewById(R.id.show1);
      b1=(Button)findViewById(R.id.login);
     
        b1.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Message q=new Message();
                q.what=0;
                q.obj=e1.getText().toString()+"/"+e2.getText().toString();
    
            
            Check th=new Check();
                th.setDaemon(true);
                th.start();
                
            
                th.ht.sendMessage(q);
            }
            
        });
        
        
        
        
    }//메인끝
    
   Handler ha=new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
            case 0:e1.setVisibility(8);
            e2.setVisibility(8);
            b1.setVisibility(8);
            s1.setVisibility(0);
            s1.setText(msg.obj.toString()+"님이 로그인에 성공하였습니다");break;
            
            case 1:    
            s1.setVisibility(0);
            s1.setText("로그인에 실패하였습니다");break;
            }
        
        }
    };
    
    //--------------------------

    class Check extends Thread{
        String idc;
        String passc;
        public Check(){
            
            
            
        }
        public void run(){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            
        }
            if(idc.toString().matches("busan")&&passc.toString().matches("1234")){
        
                Message u=new Message();
                u.what=0;
                u.obj=idc;
              ha.sendMessage(u);
              
            }
            else{
                Message y=new Message();
                y.what=1;
              ha.sendMessage(y);
            
            
        }
        }
        
       Handler ht=new Handler(){
            public void handleMessage(Message m){
            if(m.what==0){
                
                String[] arrSp = m.obj.toString().split("/",2);
                
            idc=    arrSp[0];
            passc = arrSp[1];      
            // Toast.makeText(Main.this, "idc", Toast.LENGTH_SHORT).show();
            }
                
            }
            
        };// 핸들러끝
       
        
        
    }//클래스끝

}