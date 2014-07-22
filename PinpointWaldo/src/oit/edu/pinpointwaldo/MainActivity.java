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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity {
	public static final String GPS_LOCATION = "GPS_LOCATION";
	private GoogleMap mMap = null;
	private Marker mMarker = null;

	private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			Location loc = null;
			loc = (Location) intent.getExtras().get(GPS_LOCATION);

			if (loc != null) {
				double lat = loc.getLatitude();
				double lon = loc.getLongitude();
				Log.d("LOCATION", lat + ", " + lon);
				LatLng coord = new LatLng(lat, lon);

				if (mMarker != null)
					mMarker.remove();
				
				mMarker = mMap.addMarker(new MarkerOptions().position(coord).title("Waldo!"));
				mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coord, 17));
			}
		}
	};

	private void requestGPSService() {
		IntentFilter filter = new IntentFilter("android.intent.action.MAIN");
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
	protected void onDestroy() {
		super.onDestroy();
		this.unregisterReceiver(mReceiver);
	}
}
