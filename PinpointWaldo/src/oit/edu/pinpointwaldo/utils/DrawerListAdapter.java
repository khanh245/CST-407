package oit.edu.pinpointwaldo.utils;

import java.util.ArrayList;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class DrawerListAdapter extends BaseAdapter {

	private Activity mContext = null;
	private ArrayList<String> mList = null;
	
	public DrawerListAdapter(Activity context) {
		mContext = context;
	}
	
	public DrawerListAdapter(Activity context, ArrayList<String> list) {
		mContext = context;
		mList = list;
	}
	
	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		
		return rowView;
	}
}
