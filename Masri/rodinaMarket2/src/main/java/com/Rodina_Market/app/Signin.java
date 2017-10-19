package com.Rodina_Market.app;

import java.io.ByteArrayOutputStream;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signin extends Activity {

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
	 private boolean validPhone(String phone) {
	        Pattern pattern = Patterns.PHONE;
	        return pattern.matcher(phone).matches();
	    }
	 
	 private boolean validEmail(String phone) {
	        Pattern pattern = Patterns.EMAIL_ADDRESS;
	        return pattern.matcher(phone).matches();
	    }
	 
	String GetPhoneNumber(){
		
		String phone="";
		String email="";
		AccountManager am = AccountManager.get(this);
		   Account[] accounts = am.getAccounts();
		      for (Account ac : accounts) 
		       {
		         if(validPhone(ac.name)){
		        	 if(phone.length()==0){
		        	 phone=ac.name.trim();}else{
		        		 phone=phone+" , "+ac.name.trim();
		        	 }
		         }
		    	  if(validEmail(ac.name)){
		    		 if(email.length()==0){
		    		  email=ac.name.trim();}else{
		    		  	  email=email+" ,"+ac.name.trim();
		    		  }
		    	  }
		       }
		    
		    Toast.makeText(getApplicationContext(), "Phone : "+phone+"\nEmail : "+email, 1).show();
		
		// displaySmsLog();
		 return "";
		//TelephonyManager tMgr = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
	//	String mPhoneNumber = tMgr.getSimSerialNumber();
	//	return mPhoneNumber;
	}
	
	private void  displaySmsLog() {
			
		Uri allMessages = Uri.parse("content://sms/inbox");
	     Cursor cursor = this.getContentResolver().query(allMessages, null,
	            null, null, null);

	    while (cursor.moveToNext()) {
	        for (int i = 0; i < cursor.getColumnCount(); i++) {
	            Log.d(cursor.getColumnName(i) + "", cursor.getString(i) + "");
	        }
	        Log.d("One row finished",
	                "**************************************************");
	    }

	}
	String phoneStr;
	class signupTask extends AsyncTask{

		String response="";
		ProgressDialog pd;
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd=new ProgressDialog(Signin.this);
			pd.setTitle("wait");
			pd.setMessage("singup ...");
			pd.show();
		}
		@Override
		protected Object doInBackground(Object... params) {
			
			//response=HTPP(Global.server+"/clientmobileinfo.php?v=sing&u="+phoneStr);
			
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
				
				startActivity(new Intent(Signin.this, MainActivity.class));
			}else if(ixx==-1){
				Toast.makeText(getApplicationContext(), "not active account ", 0).show();
				
			}else{
				Toast.makeText(getApplicationContext(), "oppps some error please try latter ", 0).show();
				
			}
		}
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signin);
		
		
		final EditText edt=(EditText)findViewById(R.id.editText1);
		Button btn=(Button)findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				phoneStr=edt.getText().toString();
				Toast.makeText(Signin.this,  GetPhoneNumber(), 0).show();
				//new signupTask().execute();
			}
		});
		
		
		
		TextView signup=(TextView)findViewById(R.id.textView1);
		signup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Signin.this,SignUp.class));
			}
		});
	}
}
