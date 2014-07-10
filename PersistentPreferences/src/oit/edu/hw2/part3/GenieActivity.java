package oit.edu.hw2.part3;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

public class GenieActivity extends Activity {

	private ToggleButton btn1 = null;
	private ToggleButton btn2 = null;
	private ToggleButton btn3 = null;

	private void saveState(View view) {
		SharedPreferences prefs = getSharedPreferences(
				GenieActivity.class.getName(), MODE_PRIVATE);
		Editor editor = prefs.edit();
		editor.putBoolean(((ToggleButton) view).getText().toString(),
				((ToggleButton) view).isEnabled());
		editor.commit();
	}

	public void ButtonOnClick(View view) {
		((ToggleButton)view).setEnabled(false);		
		saveState(view);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_genie);

		btn1 = (ToggleButton) findViewById(R.id.btnWish1);
		btn2 = (ToggleButton) findViewById(R.id.btnWish2);
		btn3 = (ToggleButton) findViewById(R.id.btnWish3);

		SharedPreferences prefs = getSharedPreferences(GenieActivity.class.getName(), MODE_PRIVATE);
		btn1.setEnabled(prefs.getBoolean(btn1.getText().toString(), true));
		btn2.setEnabled(prefs.getBoolean(btn2.getText().toString(), true));
		btn3.setEnabled(prefs.getBoolean(btn3.getText().toString(), true));
	}
	
	@Override
	protected void onDestroy() {
		saveState(btn1);
		saveState(btn2);
		saveState(btn3);
		
		super.onDestroy();
	}
}
