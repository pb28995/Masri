package com.Rodina_Market.app;

import com.dmbteam.catalogapp.settings.AppSettings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

public class Spalsh extends Activity{
	
	
	public static boolean isLocationEnabled(Context context) {
	    int locationMode = 0;
	    String locationProviders;
	   

	    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
	        try {
	            locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

	        } catch (SettingNotFoundException e) {
	            e.printStackTrace();
	        }

	        return locationMode != Settings.Secure.LOCATION_MODE_OFF;

	    }else{
	        locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
	        return !TextUtils.isEmpty(locationProviders);
	    }


	} 

	
	/*void locationPrompt(){
		 if(!isLocationEnabled(this)){
	            
     		 Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	         startActivity(myIntent);
		 }
	        
	}*/
	
	SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.splash);
		
		 if(AppSettings.Geo_Fencing==0){
		    	startActivity(new Intent(Spalsh.this, MainActivity.class));
				finish();
		    }
		sp=getSharedPreferences("asasasasasasas", Context.MODE_PRIVATE);
		
		Boolean force=sp.getBoolean("f", false);
		if(force){
			
			next(null);
			return;
		}
		TextView txt=(TextView)findViewById(R.id.textView1);
		txt.setText("هل تريد إستقبال عروض داخل المنطقة التي تتواجد بها؟ \nDo you want to activate GPS, to receive promotions near of you?");
		
		if(!isLocationEnabled(this))
		{
			
		}else{
			
			startActivity(new Intent(Spalsh.this, MainActivity.class));
			finish();
		}
	
	
	
	}
	
	public void next(View view){
		
		startActivity(new Intent(Spalsh.this, MainActivity.class));
		finish();
	}
	
	
	public void never(View view){
		
		
		SharedPreferences.Editor prefs = sp.edit();
    	prefs.putBoolean("f", true);
        prefs.commit();
		startActivity(new Intent(Spalsh.this, MainActivity.class));
		finish();
	}
	
	public void Onlocation(View view){
		 Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
         startActivityForResult(myIntent, 33);
		 //startActivity(myIntent);
		
	}

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		next(null);
	}
}
