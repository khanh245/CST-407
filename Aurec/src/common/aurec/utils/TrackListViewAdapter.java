package common.aurec.utils;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class TrackListViewAdapter extends BaseAdapter {

	private List<TrackListItem> mList = null;
	
	@Override
	public int getCount() {
		return (!mList.isEmpty() ? mList.size() : -1);
	}

	@Override
	public TrackListItem getItem(int arg0) {
		return (!mList.isEmpty() ? mList.get(arg0) : null);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		return null;
	}

}
