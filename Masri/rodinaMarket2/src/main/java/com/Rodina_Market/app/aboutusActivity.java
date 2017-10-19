package com.Rodina_Market.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class aboutusActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aboutus);
		
	}
	
	public void email(View  view){
		
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("plain/text");
		intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "info@rodinamarket.com" });
		startActivity(Intent.createChooser(intent, ""));
	}
	
	public void phone(View  view){
		   
		Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" +"+201027017660"));
		startActivity(intent);
	}

	public void web(View  view){
	
		String url = "http://www.rodinamarket.com/";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}
}
