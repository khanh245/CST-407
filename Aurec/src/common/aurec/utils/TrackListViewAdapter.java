package common.aurec.utils;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import common.aurec.models.TrackListItem;

public class TrackListViewAdapter extends BaseAdapter {
	
	private Context mContext = null;
	private ArrayList<TrackListItem> mList = null;
	
	public TrackListViewAdapter(Context context) {
		mContext = context;
	}
	
	public TrackListViewAdapter (Context context, ArrayList<TrackListItem> list) {
		mContext = context;
		mList = list;
	}
	
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

	// Helper Class, Viewholder
	static class Viewholder {
		TextView trackName;
		TextView trackLength;
		TextView recordedDate;
		TextView playing;
	}
}
