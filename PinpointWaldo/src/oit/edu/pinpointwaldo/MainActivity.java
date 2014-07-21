package oit.edu.pinpointwaldo;

import oit.edu.pinpointwaldo.services.GPSDataService;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity {
	public static final String GPS_LOCATION = "GPS_LOCATION";
	private GoogleMap mMap = null;
	private BroadcastReceiver mReceiver = null;

	private void requestGPSService() {
		IntentFilter filter = new IntentFilter("android.intent.action.MAIN");
		mReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				Location loc = null;
				loc = (Location) intent.getExtras().get(GPS_LOCATION);

				if (loc != null) {
					double lat = loc.getLatitude();
					double lon = loc.getLongitude();
					Log.d("Location: ", lat + ", " + lon);
					LatLng coord = new LatLng(lat, lon);
					
					mMap.addMarker(new MarkerOptions().position(coord).title("Waldo!"));
					mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coord, 17));
				}
			}
		};

		this.registerReceiver(mReceiver, filter);
		this.startService(new Intent(this, GPSDataService.class));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		mMap.getUiSettings().setAllGesturesEnabled(true);
		
		requestGPSService();
	}

	@Override
	protected void onResume() {
		super.onResume();

		requestGPSService();		
	}

	@Override
	protected void onPause() {
		super.onPause();
		this.unregisterReceiver(mReceiver);
	}
}
