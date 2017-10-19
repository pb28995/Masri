package com.Rodina_Market.app;

import com.dmbteam.catalogapp.settings.AppSettings;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.PushService;

import android.app.Application;
   

public class CatalogAppContext extends Application {
	
	
	@Override
	public void onCreate() {
		super.onCreate();  
		
			Parse.initialize(this, AppSettings.applicationId, AppSettings.clientKey);
			PushService.setDefaultPushCallback(this, MainActivity.class);
			PushService.subscribe(this, "user_"+AppSettings.cid, MainActivity.class);
		 	ParseInstallation.getCurrentInstallation().saveEventually();
		
	}
}
  