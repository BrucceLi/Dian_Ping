package com.dianping.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dianping.R;
import com.dianping.events.CitySelectedEvent;
import com.dianping.events.LetterCheckedEvent;

import com.dianping.fragment.*;
import com.dianping.view.SiderMenu;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import de.greenrobot.event.EventBus;

@EActivity(R.layout.layout_all_cities)
public class AllCityActivity extends BaseActivity implements SiderMenu.OnLetterCheckedListener{
	
	@ViewById
	Button btn_backpress;
	@ViewById
	SiderMenu sm_selector;
	@ViewById
	TextView tv_show_city_key;

	@Override
	protected void initVariables() {

		getSupportFragmentManager().beginTransaction()
				.add(R.id.fragment_container, new CityFragment_()).commit();

		sm_selector.setOnLetterCheckedListener(this);
	}
	@Click
	void btn_backpressClicked() {

		finish();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}


	@Override
	public void onLetterChecked(String letter) {
		if(letter.equals(""))
		{
			tv_show_city_key.setVisibility(View.GONE);
		}else
		{
			tv_show_city_key.setVisibility(View.VISIBLE);
			tv_show_city_key.bringToFront();
			tv_show_city_key.setText(letter);
		}
		EventBus.getDefault().post(new LetterCheckedEvent(letter));

	}

	//得到城市选定的事件
	public void onEvent(CitySelectedEvent event)
	{
		HomePageActivity_.intent(this).mCityName(event.cityName).start();
	}
}
