package com.Rodina_Market.app;

import java.io.ByteArrayOutputStream;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends Activity {

	String phoneStr="";
	 EditText code;
	 Button conti2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		//http://mobile.togetherpro.com/clientmobileinfo.php?v=active&u=ali
		
		final EditText phone=(EditText)findViewById(R.id.editText1);
		 code=(EditText)findViewById(R.id.editText2);
		Button conti1=(Button)findViewById(R.id.button1);
	    conti2=(Button)findViewById(R.id.button2);
		
	    code.setVisibility(View.GONE);
	    conti2.setVisibility(View.GONE);
	    
	    conti2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				startActivity(new Intent(SignUp.this,Signin.class));
				Toast.makeText(getApplicationContext(), "your verification is completed!", 0).show();
			}
		});
		conti1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				phoneStr=phone.getText().toString();
				if(phone.getText().toString().length()==0){
					phone.setError("field required");
					return;
				}
				new	signupTask().execute();
			}
		});
		
	}
	
	String HTPP(String URL){

		try{
		HttpClient httpclient = new DefaultHttpClient();

		HttpResponse response = httpclient.execute(new HttpGet(URL));
		StatusLine statusLine = response.getStatusLine();
		if(statusLine.getStatusCode() == HttpStatus.SC_OK){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		response.getEntity().writeTo(out);
		String responseString = out.toString();
		out.close();
		return responseString;
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
  return "";

}
	
	class signupTask extends AsyncTask{

		String response="";
		ProgressDialog pd;
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd=new ProgressDialog(SignUp.this);
			pd.setTitle("wait");
			pd.setMessage("singup ...");
			pd.show();
		}
		@Override
		protected Object doInBackground(Object... params) {
			
		//	response=HTPP(Global.server+"/clientmobileinfo.php?v=signup&u="+phoneStr);
			
			return null;
		}
		
		int convertint(String str){
			
			try{
				return Integer.parseInt(str);
			}catch(Exception ex){return 0;}
		}
		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);	
			pd.dismiss();
			int ixx=convertint(response);
			
			if(ixx==1){
				 code.setVisibility(View.VISIBLE);
				    conti2.setVisibility(View.VISIBLE);
				Toast.makeText(getApplicationContext(), "You need to enter verification code now ", 0).show();
			}else if(ixx==-1){
				Toast.makeText(getApplicationContext(), "Already exist account ", 0).show();
				
			}else{
				Toast.makeText(getApplicationContext(), "oppps some error please try latter ", 0).show();
				
			}
		}
		
	}
}
