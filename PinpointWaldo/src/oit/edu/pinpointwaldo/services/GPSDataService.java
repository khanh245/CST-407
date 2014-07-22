package oit.edu.pinpointwaldo.services;

import oit.edu.pinpointwaldo.MainActivity;
import android.app.Service;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class GPSDataService extends Service implements LocationListener {

	private LocationManager mManager = null;
	private String provider = null;

	private void initialize() {
		mManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
		Location loc = mManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		broadcast(loc);
		provider = mManager.getBestProvider(new Criteria(), false);
		mManager.requestLocationUpdates(provider, 1000, 1, this);		
	}
	
	private void broadcast(Location loc) {
		Intent i = new Intent("android.intent.action.MAIN").putExtra(MainActivity.GPS_LOCATION, loc);
		this.sendBroadcast(i);
		this.stopSelf();
		Log.d("WALDO_SERVICE", "Service's stopped...");
	}
	
	// Service Implementation
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		
		int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        if( status != ConnectionResult.SUCCESS) {
            Log.e("LOCATION_SERVICE", "API NOT AVAILABLE");
            stopSelf();
        }
        initialize();
	}

	@Override
	public void onDestroy() {
		if (mManager != null)
			mManager.removeUpdates(this);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d("WALDO_SERVICE", "Service's started...");
		initialize();
		
		return START_NOT_STICKY;
	}

	// Location Listener Implementation
	@Override
	public void onLocationChanged(Location location) {
		Log.d("WALDO_SERVICE", "Location changed");
		broadcast(location);
	}

	@Override
	public void onStatusChanged(final String provider, final int status, final Bundle extras) { }

	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText(getBaseContext(), provider + " enabled.",
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onProviderDisabled(String provider) {
		Toast.makeText(getBaseContext(), provider + " disabled.",
				Toast.LENGTH_SHORT).show();
	}
}
