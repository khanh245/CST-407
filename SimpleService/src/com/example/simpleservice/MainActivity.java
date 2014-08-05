package com.example.simpleservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button mStart = null;
	private Button mClear = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mStart = (Button) findViewById(R.id.btnStart);
		mClear = (Button) findViewById(R.id.btnClear);
		
		mStart.setTag(0);
		mClear.setTag(1);
	}
	
	public void ButtonClicked(View view) {
		Intent sendInt = new Intent(this, SimpleIntentService.class);
		
		if(view.getTag().equals(0))
			sendInt.putExtra(SimpleIntentService.INC_ACTION, "START");
		else if (view.getTag().equals(1))
			sendInt.putExtra(SimpleIntentService.DEC_ACTION, "CLEAR");
		
		this.startService(sendInt);
	}
}