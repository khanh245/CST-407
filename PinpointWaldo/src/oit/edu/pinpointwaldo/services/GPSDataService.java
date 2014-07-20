package oit.edu.pinpointwaldo.services;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

public class GPSDataService extends Service implements LocationListener {

	private LocationManager mManager = null;
	
	private void initialize() {
		mManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
	}
	
	/// Service Implementation
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		initialize();
		
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if(mManager != null)
			mManager.removeUpdates(this);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		return START_NOT_STICKY;
	}
	
	/// Location Listener Implementation
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) { }
	
	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText(getBaseContext(), provider + " enabled.", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onProviderDisabled(String provider) {
		Toast.makeText(getBaseContext(), provider + " disabled.", Toast.LENGTH_SHORT).show();
	}
}
