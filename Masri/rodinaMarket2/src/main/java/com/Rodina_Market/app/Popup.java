package com.Rodina_Market.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

public class Popup extends Activity{

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
		try{
		Intent intent = new Intent(getBaseContext(), MainActivity.class);
		intent.putExtra("o", title);
		startActivity(intent);
		}catch(Exception ex){}
		return super.onTouchEvent(event);
	}
	
	String title="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popup);
		try{
			
			Intent ix=getIntent();
			String lbl=ix.getStringExtra("ad");
			title=lbl;
			TextView txt=(TextView)findViewById(R.id.textView1);
			txt.setText(lbl);
			
		}catch(Exception ex){}
	}
}
