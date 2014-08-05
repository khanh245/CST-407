package com.example.simpleservice;

import android.app.IntentService;
import android.content.Intent;

public class SimpleIntentService extends IntentService {

	public static String SERVICEACTION = "action";
	private static int counter = 0;

	public SimpleIntentService() {
		super("SimpleIntentService");
	}
	
	@Override
	protected void onHandleIntent(Intent intent) {
		String cmd = intent.getStringExtra(SERVICEACTION);
		
		if(cmd.equalsIgnoreCase("start"))
			++counter;
		else if (cmd.equalsIgnoreCase("clear"))
			counter = 0;
		
		Intent i = new Intent("SIMPLESERVICE").putExtra(MainActivity.COUNTER, counter);
		this.sendBroadcast(i);
		stopSelf();
	}
}
