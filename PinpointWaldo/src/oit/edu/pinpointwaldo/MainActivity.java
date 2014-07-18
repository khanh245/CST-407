package oit.edu.pinpointwaldo;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

public class MainActivity extends Activity {
	private GoogleMap mMap = null;
	
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
		mMap.setMyLocationEnabled(true);
	}
}
