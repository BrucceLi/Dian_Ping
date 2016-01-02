package com.dianping.activity;


import com.dianping.R;
import com.dianping.frameworkutils.SharedUtils;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

@EActivity(R.layout.welcome_page)
public class WelcomeManagerActivity extends BaseActivity {

	@Override
	protected void initVariables(){

		jumpToContent();

	}

	@UiThread(delay = 5000)
	void jumpToContent() {
		if (SharedUtils.checkIsFirstComeHere(this)) {

			//要调到哪一个界面
				WelcomeOneShotActivity_.intent(this).start();
		} else{
				HomePageActivity_.intent(this).mCityName("北京").start();
		}
		finish();
	}


}