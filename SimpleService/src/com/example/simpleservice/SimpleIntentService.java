package com.example.simpleservice;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

public class SimpleIntentService extends IntentService {

	public static String INC_ACTION = "START";
	public static String DEC_ACTION = "CLEAR";
	private static int counter = 0;
	
	public SimpleIntentService() {
		super("SIMPLE_INTENT_SERIVCE");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		String cmd = intent.getAction();
		
		if(INC_ACTION.equalsIgnoreCase(cmd)) {
			++counter;
			Toast.makeText(this, counter, Toast.LENGTH_SHORT).show();
		}
		else if (DEC_ACTION.equalsIgnoreCase(cmd)) {
			counter = 0;
			Toast.makeText(this, counter, Toast.LENGTH_SHORT).show();
		}
	}
}
