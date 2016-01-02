package com.dianping.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioButton;

import com.dianping.R;
import com.dianping.adapter.DianPingWelcomePagerAdapter;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;



@EActivity(R.layout.layout_welcome_first)
public class WelcomeOneShotActivity extends BaseActivity implements ViewPager.OnPageChangeListener{


	@ViewById
	ViewPager vp_welcome_first_to_dead;
	@ViewById
	RadioGroup rgp_page_select;
	@ViewById
	Button btn_get_into;

	@Override
	public void initVariables(){
		vp_welcome_first_to_dead.setAdapter(new DianPingWelcomePagerAdapter(this));
		vp_welcome_first_to_dead.addOnPageChangeListener(this);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {}
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {}
	@Override
	public void onPageSelected(int arg0) {
		switch (arg0) {
		case 0:
			((RadioButton)rgp_page_select.getChildAt(0)).setChecked(true);
			btn_get_into.setVisibility(View.GONE);
			break;
		case 1:
			((RadioButton)rgp_page_select.getChildAt(1)).setChecked(true);
			btn_get_into.setVisibility(View.GONE);
			break;
		case 2:
			((RadioButton)rgp_page_select.getChildAt(2)).setChecked(true);
			btn_get_into.setVisibility(View.VISIBLE);
			
			break;

		}		
	}
	@Click
	void btn_get_intoClicked(View v) {
		AllCityActivity_.intent(this).start();
		this.finish();
	}

}