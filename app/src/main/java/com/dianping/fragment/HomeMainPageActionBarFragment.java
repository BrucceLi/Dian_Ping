package com.dianping.fragment;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.dianping.R;
import com.dianping.activity.*;
import com.dianping.events.CitySelectedEvent;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.layout_home_main_page_action_bar)
public class HomeMainPageActionBarFragment extends BaseFragment{
	@SystemService
	LayoutInflater inflater;


	@ViewById
	LinearLayout ll_title;
	@ViewById
	Button btn_main_menu_settings;
	@ViewById
	Button btn_preview ;

	@NonNull
	OnSettingClickListener l;

	@Override
	public void onAttach(Context context) {

		if(context instanceof OnSettingClickListener)
		{
			l = (OnSettingClickListener)context;
		}else
		{
			try {

				throw new Exception(context.getClass().getName()+"没有实现 OnSettingClickListener接口");
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		super.onAttach(context);

	}
	@Override
	protected void initVariables() {

		btn_preview.setText(HomePageActivity_.cityName);
	}

	@Click({R.id.btn_preview,R.id.btn_main_menu_settings})
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_preview:
			AllCityActivity_.intent(getActivity()).start();
			break;
		case R.id.btn_main_menu_settings:
			//调用宿主上下文的函数
			l.onSettingClicked();
		}
	}


	public interface OnSettingClickListener{
		void onSettingClicked();
	}


	public void onEvent(CitySelectedEvent event) {
		btn_preview.setText(event.cityName);
	}
}
