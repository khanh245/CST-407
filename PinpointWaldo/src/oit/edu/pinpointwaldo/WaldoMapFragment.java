package oit.edu.pinpointwaldo;

import oit.edu.pinpointwaldo.services.GPSDataService;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class WaldoMapFragment extends Fragment {

	public static final String GPS_LOCATION = "GPS_LOCATION";
	private GoogleMap mMap = null;
	private Location mLocation = null;
	private Marker mMarker = null;
	private View root = null;
	private Intent mIntent = null;

	public WaldoMapFragment() { }
	
	private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			mLocation = (Location) intent.getExtras().get(GPS_LOCATION);
			setLocation(mLocation);
		}
	};

	private void setLocation(Location loc) {
		if (loc != null) {
			double lat = loc.getLatitude();
			double lon = loc.getLongitude();
			Log.d("LOCATION", lat + ", " + lon);
			LatLng coord = new LatLng(lat, lon);

			if (mMarker != null)
				mMarker.remove();

			mMarker = mMap.addMarker(new MarkerOptions().position(coord)
					.title("Waldo!"));
			mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coord, 17));
		}		
	}
	
	private void requestGPSService() {
		IntentFilter filter = new IntentFilter("android.intent.action.WALDO");
		LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mReceiver, filter);
		if (mIntent == null)
			mIntent = new Intent(getActivity(), GPSDataService.class);
		
		getActivity().startService(mIntent);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRetainInstance(true);
		
		/*
		if(savedInstanceState != null) {
			mLocation = (Location) savedInstanceState.get(GPS_LOCATION);
			setLocation(mLocation);
		}*/
	}
	
	/*
	@Override
	public void onSaveInstanceState(Bundle outState) {
		if (mLocation != null)
			outState.putParcelable(GPS_LOCATION, mLocation);
		super.onSaveInstanceState(outState);
	}*/

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		root = inflater.inflate(R.layout.fragment_map, container, false);
		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		if (mMap != null)
			mMap.getUiSettings().setAllGesturesEnabled(true);

		requestGPSService();
		
		return root;
	}
	
	@Override
	public void onResume() {
		if (mMap == null) {
			mMap = ((MapFragment)getActivity().getFragmentManager().findFragmentById(R.id.frame_container)).getMap();
		}		
		super.onResume();
	}

	@Override
	public void onDestroyView() {
		LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mReceiver);
		if (mIntent != null)
			getActivity().stopService(mIntent);
		
		super.onDestroyView();
	}
	
	/*
	@Override
	public void onDestroy() {
		if (getFragmentManager().findFragmentById(R.id.map) != null)
			this.getActivity()
			.getFragmentManager()
			.beginTransaction()
			.remove(this.getActivity().getFragmentManager()
					.findFragmentById(R.id.map)).commit();
		super.onDestroy();
	}*/
}
