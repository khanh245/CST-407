package com.example.simpleservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


public class AndroidService extends Service {

	private void initialize() {	
	}
	
	// Service Implementation
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
        initialize();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d("WALDO_SERVICE", "Service's started...");
		initialize();
		
		return START_NOT_STICKY;
	}
}
