package com.dianping.adapter;

import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dianping.R;
import com.dianping.model.City;

public class CityAdapter extends BaseAdapter {

	class ViewHolder {
		TextView tv_sortKey;
		TextView tv_cityname;
	}

	LayoutInflater inflater;

	List<City> mList;

	public CityAdapter(List<City> list, LayoutInflater inflater) {
		mList = list;
		this.inflater = inflater;
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

	StringBuilder _shortKeys = new StringBuilder();
	List<String> hasKeyViewList = new ArrayList<String>();

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.layout_city_item, parent,
					false);
			viewHolder.tv_cityname = (TextView) convertView
					.findViewById(R.id.tv_cityName);
			viewHolder.tv_sortKey = (TextView) convertView
					.findViewById(R.id.tv_city_shortKey);
			convertView.setTag(viewHolder);
		} else {

			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		String key = mList.get(position).getShotKey();
		String cityName = mList.get(position).getCityName();
	
		if (_shortKeys.indexOf(key, 0) == -1) {
			_shortKeys.append(key);
			hasKeyViewList.add(cityName);
		}
		if (hasKeyViewList.contains(cityName)) {
			viewHolder.tv_sortKey.setText(key);
			viewHolder.tv_sortKey.setVisibility(View.VISIBLE);
		} else {
			viewHolder.tv_sortKey.setVisibility(View.GONE);
		}
		viewHolder.tv_cityname.setText(cityName);

		return convertView;
	}

}
