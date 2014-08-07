package common.aurec.utils;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import common.aurec.R;
import common.aurec.models.TrackListItem;

public class TrackListViewAdapter extends BaseAdapter {
	
	private Activity mContext = null;
	private List<TrackListItem> mList = null;
	
	public TrackListViewAdapter(Activity context) {
		mContext = context;
	}
	
	public TrackListViewAdapter (Activity context, List<TrackListItem> list) {
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
		View rowView = convertView;
		
		if (rowView == null) {
			LayoutInflater inflater = mContext.getLayoutInflater();
			rowView = inflater.inflate(R.layout.listitem_track, parent, false);
			
			Viewholder holder = new Viewholder();
			holder.trackLength = (TextView) rowView.findViewById(R.id.length);
			holder.trackName = (TextView) rowView.findViewById(R.id.name);
			holder.recordedDate = (TextView) rowView.findViewById(R.id.date);
			//holder.playing = (TextView) rowView.findViewById(R.id.);
			
			rowView.setTag(holder);
		}
		
		Viewholder holder = (Viewholder) rowView.getTag();
		TrackListItem item = mList.get(position);
		
		holder.trackName.setText(item.getTrackName());
		holder.trackLength.setText(item.getTrackLength());
		holder.recordedDate.setText(item.getDateRecorded());
		
		return rowView;
	}

	static class Viewholder {
		TextView trackName;
		TextView trackLength;
		TextView recordedDate;
		//TextView playing;
	}
}
