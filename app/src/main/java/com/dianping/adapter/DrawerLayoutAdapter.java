package com.dianping.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dianping.R;
import com.dianping.model.Item;

import java.util.List;

public class DrawerLayoutAdapter extends BaseAdapter {

	List<Item> mList;
	LayoutInflater inflater;
	Context mContext;
	class ViewHolder{
	public ImageView mImageView;
	public 	TextView mTextView;
	}
	public DrawerLayoutAdapter(List<Item> items,LayoutInflater inflater,Context context) {
		mList=items;
		this.inflater=inflater;
		mContext=context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder=null;
		if(convertView==null)
		{
			viewHolder=new ViewHolder();
			convertView =  inflater.inflate(R.layout.spinner_drop_down_item, parent, false);
			viewHolder.mTextView=(TextView) convertView.findViewById(R.id.tv_spinner_text);
			viewHolder.mImageView=(ImageView) convertView.findViewById(R.id.iv_setting_icon);
			convertView.setTag(viewHolder);
		}else
		{
			viewHolder = (ViewHolder) convertView.getTag();
		}
	
		viewHolder.mImageView.setBackgroundResource(mList.get(position).getResId());
		
		viewHolder.mTextView.setText(mList.get(position).getText());
		return convertView;
	}

}
