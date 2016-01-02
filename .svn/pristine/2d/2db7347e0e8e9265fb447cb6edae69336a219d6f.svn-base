package com.dianping.adapter;

import java.util.List;

import com.dianping.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SubCategoryGridViewAdapter extends BaseAdapter {

	List<String> mList;
	TextView   tview;
	public SubCategoryGridViewAdapter(List<String> list) {
		mList=list;
	}
	
	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		tview=null;
		if(convertView==null)
		{
			tview = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_category_text_view, parent,false);
			
			
			
			convertView=tview;
			tview=(TextView) convertView;
			convertView.setTag(tview);
		}else
		{
			tview=(TextView) convertView.getTag();
		}
		tview.setText(mList.get(position));
		return convertView;
	}

}
