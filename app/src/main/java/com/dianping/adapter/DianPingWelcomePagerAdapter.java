package com.dianping.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.dianping.R;
import com.dianping.activity.*;

public class DianPingWelcomePagerAdapter extends PagerAdapter {

	List<View> mPagerViews;
	public final static int[] mResIds = {
			R.layout.layout_welcome_to_dead_first,
			R.layout.layout_welcome_to_dead_second,
			R.layout.layout_welcome_to_dead_third,
			};
	Activity mContext;
	public DianPingWelcomePagerAdapter(Activity context) {
		mContext=context;
		mPagerViews = new ArrayList<>();
		mPagerViews.add(LayoutInflater.from(context).inflate(mResIds[0], null));
		mPagerViews.add(LayoutInflater.from(context).inflate(mResIds[1], null));
		mPagerViews.add(LayoutInflater.from(context).inflate(mResIds[2], null));
	}

	@Override
	public int getCount() {
		return mResIds.length;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(mPagerViews.get(position));
		Button btn=(Button) mPagerViews.get(position).findViewById(R.id.btn_jump);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AllCityActivity_.intent(mContext).start();
				mContext.finish();
				
			}
		});
		return mPagerViews.get(position);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(mPagerViews.get(position));
	}

}
