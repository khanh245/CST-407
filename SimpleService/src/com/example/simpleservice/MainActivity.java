package com.example.simpleservice;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button mStart = null;
	private Button mClear = null;
	
	private BroadcastReceiver mLocalRcvr = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			
		}
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mStart = (Button) findViewById(R.id.btnStart);
		mClear = (Button) findViewById(R.id.btnClear);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
	public void ButtonClicked(View view) {
		
	}
}