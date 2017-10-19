package com.Rodina_Market.app;

import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;


/**
 * The Class ParseReceiver.
 */
public class ParseReceiver extends BroadcastReceiver {
	
	/** The tag. */
	private final String TAG = "Parse Notification";
	
	/** The msg. */
	private String msg = "";

	
	@Override
	public void onReceive(Context ctx, Intent intent) {

		try{
		if(intent!=null){
			
			try {
				JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));
				String productID=json.get("product_name").toString();
				shownotification(ctx, productID+"");
				
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		}catch(Exception ex){}
		//Toast.makeText(ctx, "asas", 1).show();
	}
	
		@SuppressLint("NewApi")
		public static void  shownotification(Context context,String productname){
		
		Intent intent = new Intent(context, MainActivity.class);
		intent.putExtra("o", productname);
		PendingIntent pIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent, 0);
		
		Notification n  = new Notification.Builder(context)
        .setContentTitle(Global.appname)
        .setContentText(productname)
        .setSmallIcon(R.drawable.icon)
        .setContentIntent(pIntent)
        .setAutoCancel(true)
        .build();
    
  
			NotificationManager notificationManager = 
				(NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

			notificationManager.notify(Global.NotificationCode, n); 
			try{
				
				 MediaPlayer mPlayer = MediaPlayer.create(context, R.raw.tone);
			        mPlayer.start();
			}catch(Exception ex){}
			
			
			Intent dialogIntent = new Intent(context, Popup.class);
			dialogIntent.putExtra("ad", productname);
			dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(dialogIntent);

	}

}
