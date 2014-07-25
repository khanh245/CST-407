package oit.edu.pinpointwaldo;

import oit.edu.pinpointwaldo.services.GPSDataService;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WaldoMapFragment extends Fragment {

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
		getActivity().registerReceiver(mReceiver, filter);
		getActivity().startService(new Intent(getActivity(), GPSDataService.class));
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		SupportMapFragment smf = (SupportMapFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.map);
		if (smf != null) {
			mMap = smf.getMap();
		}
		mMap.getUiSettings().setAllGesturesEnabled(true);
		requestGPSService();
		
		return inflater.inflate(R.layout.fragment_map, container, false);
	}	
	
	@Override
	public void onDestroyView() {
		getActivity().unregisterReceiver(mReceiver);
		super.onDestroyView();
	}
}
