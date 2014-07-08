package oit.edu.hw2.part2;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class StateActivity extends Activity {
	private static final String STATE_TEXT = "TEXT_STATE";
	
	private TextView txtText = null;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_state);
		
		txtText = (TextView)findViewById(R.id.txtText);
		initialize();
		
		if(savedInstanceState != null) 	{
			String text = savedInstanceState.getString(STATE_TEXT);
			txtText.setText(text);
		}
	}
	
	@Override
	protected void onSaveInstanceState (Bundle outState) {
		super.onSaveInstanceState(outState);
		
		String previousText = txtText.getText().toString();
		outState.putString(STATE_TEXT, previousText);
	}
	
	private void initialize() {
		Random rand = new Random();
		int generated = rand.nextInt(Integer.MAX_VALUE);
		txtText.setText(String.valueOf(generated));
	}
}
