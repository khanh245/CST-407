package oit.edu.pinpointwaldo.services;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

public class GPSDataService extends Service implements LocationListener {

	private LocationManager mManager = null;
	
	/// Service Implementation
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
	/// Location Listener Implementation
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) { }
	@Override
	public void onProviderEnabled(String provider) { }
	@Override
	public void onProviderDisabled(String provider) { }
}
