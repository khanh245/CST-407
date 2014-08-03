package oit.edu.pinpointwaldo;

import oit.edu.pinpointwaldo.services.GPSDataService;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Bundle;
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
	private Marker mMarker = null;
	private View root;

	public WaldoMapFragment() { }
	
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

				mMarker = mMap.addMarker(new MarkerOptions().position(coord)
						.title("Waldo!"));
				mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coord, 17));
			}
		}
	};

	private void requestGPSService() {
		IntentFilter filter = new IntentFilter("android.intent.action.WALDO");
		getActivity().registerReceiver(mReceiver, filter);
		getActivity().startService(new Intent(getActivity(), GPSDataService.class));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		root = inflater.inflate(R.layout.fragment_map, container, false);
		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		if (mMap != null)
			mMap.getUiSettings().setAllGesturesEnabled(true);

		requestGPSService();
		
		return root;
	}

	@Override
	public void onPause() {
		super.onPause();
		this.getActivity()
				.getFragmentManager()
				.beginTransaction()
				.remove(this.getActivity().getFragmentManager()
						.findFragmentById(R.id.map)).commit();
	}

	@Override
	public void onDestroyView() {
		getActivity().unregisterReceiver(mReceiver);
		super.onDestroyView();
	}
}
