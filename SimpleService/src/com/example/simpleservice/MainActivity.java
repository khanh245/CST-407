package com.example.simpleservice;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	public static final int NOTIFICATION = 1;
	public static final String COUNTER = "counter";
	
	private Button mStart = null;
	private Button mClear = null;	
	private NotificationManager mNotificationMgr = null;
	
	private BroadcastReceiver mReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			int counter = intent.getExtras().getInt(COUNTER);
			
			if (counter != 0)
				showNotification(Integer.toString(counter));
			else mNotificationMgr.cancelAll();
		}
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mStart = (Button) findViewById(R.id.btnStart);
		mClear = (Button) findViewById(R.id.btnClear);
		
		mNotificationMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);		
		
		mStart.setTag(0);
		mClear.setTag(1);
		
		IntentFilter filter = new IntentFilter("SIMPLESERVICE");
		registerReceiver(mReceiver, filter);
	}
	
	@Override
	public void onDestroy() {
		mNotificationMgr.cancel(NOTIFICATION);
		unregisterReceiver(mReceiver);
		super.onDestroy();
	}
	
	public void ButtonClicked(View view) {
		Intent mIntent = new Intent(this, SimpleIntentService.class);
		
		if(view.getTag().equals(0))
			mIntent.putExtra(SimpleIntentService.SERVICEACTION, "START");
		else if (view.getTag().equals(1))
			mIntent.putExtra(SimpleIntentService.SERVICEACTION, "CLEAR");
		
		startService(mIntent);
	}
	
	private void showNotification(String message) {
		Notification notification = new NotificationCompat.Builder(getApplicationContext())
				.setContentText(message)
				.setSmallIcon(android.R.drawable.ic_dialog_alert)
				.setContentTitle("Notification Service")
				.setContentText(message)
				.build();
		
		mNotificationMgr.notify(NOTIFICATION, notification);
	}	
}